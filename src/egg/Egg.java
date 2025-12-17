package egg;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public abstract class Egg {

    String name;
    public BufferedImage image;
    public int xCoordinate, yCoordinate, worldX, worldY;

    public Egg(int xCoordinate, int yCoordinate, int worldX, int worldY) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.worldX = worldX;
        this.worldY = worldY;
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }
}
