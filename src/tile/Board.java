package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import egg.ChickenEgg;
import main.GameManager;
import main.GamePanel;
import main.tools.Vector2i;

public class Board {

    GamePanel gp;
    public Tile[][] board = new Tile[8][8];
    public int xOffset;
    public int yOffset;
    public GameManager gameManager;

    public Board(GamePanel gp) {
        this.gp = gp;
        xOffset = gp.VIRTUAL_SCREEN_WIDTH / 2 - board[0].length * gp.tileSize / 2;
        yOffset = gp.VIRTUAL_SCREEN_HEIGHT / 2 - board.length * gp.tileSize / 2;
    }

    public void initializeBoard() {
        gameManager = gp.gameManager;
        BufferedImage itemFrame = null;
        try {
            itemFrame = ImageIO.read(getClass().getResourceAsStream("/misc/item_frame.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Opsie");
        }

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[column][row] = new Tile();

                board[column][row].image = itemFrame;
                board[column][row].xCoordinate = column;
                board[column][row].yCoordinate = row;
                board[column][row].worldX = column * gp.tileSize + xOffset;
                board[column][row].worldY = row * gp.tileSize + yOffset;

                if ((column + row) % 2 == 0 ) {

                    board[column][row].egg = new ChickenEgg(
                        column,
                        row,
                        board[column][row].worldX,
                        board[column][row].worldY,
                        gameManager.teams[column % 2]
                    );
                }
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

    public Vector2i worldToCoordinate(int x, int y) {
        x = (int) Math.floor((double) (x - xOffset) / gp.tileSize);
        y = (int) Math.floor((double) (y - yOffset) / gp.tileSize);
        return new Vector2i(x, y);
    }

    public Vector2i worldToCoordinate(Vector2i v2) {
        return worldToCoordinate(v2.x, v2.y);
    }
    
    public Vector2i coordinateToWorld(int x, int y) {
        x = x * gp.tileSize + xOffset;
        y = y * gp.tileSize + yOffset;
        return new Vector2i(x, y);
    }

    public Vector2i coordinateToWorld(Vector2i v2) {
        return coordinateToWorld(v2.x, v2.y);
    }
}
