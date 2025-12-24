package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import main.tools.Vector2i;
import tile.Board;

public class GamePanel extends JPanel implements Runnable { 

    public final int VIRTUAL_SCREEN_WIDTH = 256; 
    public final int VIRTUAL_SCREEN_HEIGHT = 144;
    public final int tileSize = 16;
    public final int screenTileWidth = VIRTUAL_SCREEN_WIDTH / tileSize;
    public final int screenTileHeight = VIRTUAL_SCREEN_HEIGHT / tileSize;
    public int screenWidth = 1024;
    public int screenHeight = 576;

    private int xOffset;
    public int getxOffset() {
        return xOffset = (getWidth()  - VIRTUAL_SCREEN_WIDTH  * scale) / 2;
    }

    private int yOffset;
    public int getyOffset() {
        return yOffset = (getHeight() - VIRTUAL_SCREEN_HEIGHT * scale) / 2;
    }

    private int scale;

    int FPS = 60;

    Thread gameThread;
    
    Board board = new Board(this);
    Cursor cursor = new Cursor(this, board);
    BufferedImage backBuffer = new BufferedImage(VIRTUAL_SCREEN_WIDTH, VIRTUAL_SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
    public GameManager gameManager = new GameManager(board);
    KeyHandler keyHandler = new KeyHandler(board, gameManager);
    MouseHandler mouseHandler = new MouseHandler(cursor, this, board, gameManager);

    GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.decode("#1c1827"));
        this.setDoubleBuffered(true); //For some reason boosts performance
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        this.setFocusable(true); //To be focused and receive input
    }

    public void setup() {
        board.initializeBoard();
        cursor.initializeCursor();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long frameStart;
        long frameRemain;
        final long frameDuration = 1_000_000_000 / FPS;
        double delta = 1.0 / FPS;

        while (gameThread != null) {
            frameStart = System.nanoTime();
            update(delta);
            repaint();
            frameRemain = frameStart + frameDuration - System.nanoTime();
            if (frameRemain > 0) {
                long frameRemainMillis = frameRemain / 1_000_000;
                // long frameRemainNanos = frameRemain - frameRemainMillis * 1_000_000;
                try {
                    Thread.sleep(frameRemainMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            delta = (System.nanoTime() - frameStart) / 1_000_000_000.0; //, (int) frameRemainNanos
        }
    }

    public void update(double delta) {
        //TODO: implement update
        mouseHandler.scale = getScale();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        Graphics2D bg = backBuffer.createGraphics();

        bg.setColor(Color.BLACK);
        bg.fillRect(0, 0, VIRTUAL_SCREEN_WIDTH, VIRTUAL_SCREEN_HEIGHT);

        board.draw(bg);
        cursor.draw(bg, this);
        
        
        bg.dispose();

        drawScaled(g2);
    }

    private void drawScaled(Graphics2D g) {
    g.setRenderingHint(
        RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
    );

    scale = getScale();
    
    g.drawImage(
        backBuffer,
        getxOffset(),
        getyOffset(),
        VIRTUAL_SCREEN_WIDTH * scale,
        VIRTUAL_SCREEN_HEIGHT * scale,
        null
    );
    }

    private int getScale() {
        int scaleX = getWidth()  / VIRTUAL_SCREEN_WIDTH;
        int scaleY = getHeight() / VIRTUAL_SCREEN_HEIGHT;
        return Math.max(1, Math.min(scaleX, scaleY));
    }

    public Vector2i screenToWorld(int x, int y) {
        x = (x - xOffset) / scale;
        y = (y - yOffset) / scale;

        return new Vector2i(x, y);
    }

    public Vector2i screenToWorld(Vector2i v2) {
        return screenToWorld(v2.x, v2.y);
    }
}
