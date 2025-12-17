package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import tile.Board;

public class GamePanel extends JPanel implements Runnable {

    public final int VIRTUAL_SCREEN_WIDTH = 256; 
    public final int VIRTUAL_SCREEN_HEIGHT = 144;
    public final int tileSize = 16;
    public final int screenTileWidth = VIRTUAL_SCREEN_WIDTH / tileSize;
    public final int screenTileHeight = VIRTUAL_SCREEN_HEIGHT / tileSize;
    public int screenWidth = 1024;
    public int screenHeight = 576;

    int FPS = 60;

    Thread gameThread;
    
    Board board = new Board(this);
    BufferedImage backBuffer = new BufferedImage(VIRTUAL_SCREEN_WIDTH, VIRTUAL_SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);

    GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.decode("#1c1827"));
        this.setDoubleBuffered(true); //For some reason boosts performance
        // TODO: this.addKeyListener(keyH);
        this.setFocusable(true); //To be focused and receive input
    }

    public void setup() {
        board.initializeBoard();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            update();
            repaint();
        }
    }

    public void update() {
        //TODO: implement update
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        Graphics2D bg = backBuffer.createGraphics();

        bg.setColor(Color.BLACK);
        bg.fillRect(0, 0, VIRTUAL_SCREEN_WIDTH, VIRTUAL_SCREEN_HEIGHT);
        // BufferedImage testImage = null;
        // try {
        //     testImage = ImageIO.read(getClass().getResourceAsStream("/eggs/chicken_spawn_egg.png"));
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        // bg.drawImage(testImage, 16, 16, 16, 16, null);

        board.draw(bg);
        
        
        bg.dispose();

        drawScaled(g2);
    }

    private void drawScaled(Graphics2D g) {
    g.setRenderingHint(
        RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
    );

    int scale = getScale();
    int xOffset = (getWidth()  - VIRTUAL_SCREEN_WIDTH  * scale) / 2;
    int yOffset = (getHeight() - VIRTUAL_SCREEN_HEIGHT * scale) / 2;

    g.drawImage(
        backBuffer,
        xOffset,
        yOffset,
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

}
