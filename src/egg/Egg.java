package egg;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.Team;
import main.tools.OutlineCreator;

public abstract class Egg {

    public String name;
    public BufferedImage image;
    protected BufferedImage outline;
    public int xCoordinate, yCoordinate, worldX, worldY;
    public Team team;

    public Egg(int xCoordinate, int yCoordinate, int worldX, int worldY, Team team) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.worldX = worldX;
        this.worldY = worldY;
        this.team = team;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        outline = OutlineCreator.createOutline(image, team.color);
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        // g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
        g2.drawImage(outline, worldX - 2, worldY - 2, gp.tileSize + 4, gp.tileSize + 4, null);
    }
}
