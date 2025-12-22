package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import egg.ChickenEgg;
import main.GamePanel;

public class Board {

    GamePanel gp;
    Tile[][] board = new Tile[8][8];
    public int xOffset;
    public int yOffset;

    public Board(GamePanel gp) {
        this.gp = gp;
        xOffset = gp.VIRTUAL_SCREEN_WIDTH / 2 - board[0].length * gp.tileSize / 2;
        yOffset = gp.VIRTUAL_SCREEN_HEIGHT / 2 - board.length * gp.tileSize / 2;
    }

    public void initializeBoard() {
        BufferedImage itemFrame = null;
        try {
            itemFrame = ImageIO.read(getClass().getResourceAsStream("/misc/item_frame.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Opsie");
        }

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = new Tile();

                board[row][column].image = itemFrame;
                board[row][column].xCoordinate = column;
                board[row][column].yCoordinate = row;
                board[row][column].worldX = column * gp.tileSize + xOffset;
                board[row][column].worldY = row * gp.tileSize + yOffset;

                if ((column + row) % 2 == 0 ) {board[row][column].egg = new ChickenEgg(
                    column,
                    row,
                    board[row][column].worldX,
                    board[row][column].worldY
                );}
            }
        }
    }

    public void draw(Graphics2D g2) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column].draw(g2, gp);
                if (board[row][column].egg != null) {
                    board[row][column].egg.draw(g2, gp);
                }
            }
        }
    }
}
