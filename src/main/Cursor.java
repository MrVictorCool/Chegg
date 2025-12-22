package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import tile.Board;

public class Cursor {

    public BufferedImage[] sprites = new BufferedImage[4];
    public int xCoordinate, yCoordinate, worldX, worldY;
    GamePanel gp;
    Board board;

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
        worldX = xCoordinate * gp.tileSize + board.xOffset;
        worldY = yCoordinate * gp.tileSize + board.yOffset;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        g2.drawImage(sprites[0], worldX, worldY, gp.tileSize, gp.tileSize, null);
    }

    public Cursor(GamePanel gp, Board board) {

        this.gp = gp;
        this.board = board;

        try {
            sprites[0] = ImageIO.read(getClass().getResourceAsStream("/misc/cursor.png"));
            sprites[1] = ImageIO.read(getClass().getResourceAsStream("/misc/cursor.png"));
            sprites[2] = ImageIO.read(getClass().getResourceAsStream("/misc/cursor.png"));
            sprites[3] = ImageIO.read(getClass().getResourceAsStream("/misc/cursor.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initializeCursor() {
        xCoordinate = 0;
        yCoordinate = 0;
        worldX = board.xOffset;
        worldY = board.yOffset;

    }
}
