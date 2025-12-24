package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import egg.Egg;
import main.GamePanel;

public class Tile {

    public Egg egg;
    public BufferedImage image;
    public int xCoordinate, yCoordinate, worldX, worldY;

    public void draw(Graphics2D g2, GamePanel gp) {
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }

    public void update() {
        if (egg != null) {
            egg.worldX = worldX;
            egg.worldY = worldY;
            egg.xCoordinate = xCoordinate;
            egg.yCoordinate = yCoordinate;
        }
    }
}
