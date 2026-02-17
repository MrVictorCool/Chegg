package ui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.tools.Vector2i;
import tile.Board;

public class UI {
    
    Board board;
    public static boolean promotionVisible = false;
    Vector2i worldCordinates;
    BufferedImage horseImage;
    BufferedImage sheepImage;
    BufferedImage pigImage;
    BufferedImage wanderingTraderImage;

    public UI(Board board) {
        this.board = board;
        worldCordinates = this.board.coordinateToWorld(9, 0);
        try {
            horseImage = ImageIO.read(getClass().getResourceAsStream("/eggs/horse_spawn_egg.png"));
            sheepImage = ImageIO.read(getClass().getResourceAsStream("/eggs/sheep_spawn_egg.png"));
            pigImage = ImageIO.read(getClass().getResourceAsStream("/eggs/pig_spawn_egg.png"));
            wanderingTraderImage = ImageIO.read(getClass().getResourceAsStream("/eggs/wandering_trader_spawn_egg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        if (!promotionVisible) {return;}
        g2.drawImage(horseImage, worldCordinates.x, worldCordinates.y, 16, 16, null);
        g2.drawImage(sheepImage, worldCordinates.x + 16, worldCordinates.y, 16, 16, null);
        g2.drawImage(pigImage, worldCordinates.x, worldCordinates.y + 16, 16, 16, null);
        g2.drawImage(wanderingTraderImage, worldCordinates.x + 16, worldCordinates.y + 16, 16, 16, null);
    }
}
