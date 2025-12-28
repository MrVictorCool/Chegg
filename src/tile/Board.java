package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import egg.ChickenEgg;
import egg.Egg;
import egg.PigEgg;
import egg.SheepEgg;
import main.GameManager;
import main.GamePanel;
import main.tools.Vector2i;

public class Board {

    GamePanel gp;
    public Tile[][] board = new Tile[8][8];
    public int xOffset;
    public int yOffset;
    public GameManager gameManager;
    BufferedImage itemFrame = null;
    BufferedImage glowItemFrame = null;

    public Board(GamePanel gp) {
        this.gp = gp;
        xOffset = gp.VIRTUAL_SCREEN_WIDTH / 2 - board[0].length * gp.tileSize / 2;
        yOffset = gp.VIRTUAL_SCREEN_HEIGHT / 2 - board.length * gp.tileSize / 2;
    }

    public void initializeBoard() {
        gameManager = gp.gameManager;
        try {
            itemFrame = ImageIO.read(getClass().getResourceAsStream("/misc/item_frame.png"));
            glowItemFrame = ImageIO.read(getClass().getResourceAsStream("/misc/glow_item_frame.png"));
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

                if ((column + row) % 2 == 0) {
                    if (row > 5) {
                        board[column][row].egg = new ChickenEgg(
                            column,
                            row,
                            board[column][row].worldX,
                            board[column][row].worldY,
                            gameManager.teamList[0]
                        );
                    } else if (row < 2) {
                        board[column][row].egg = new ChickenEgg(
                            column,
                            row,
                            board[column][row].worldX,
                            board[column][row].worldY,
                            gameManager.teamList[1]
                        );
                    }
                }
            }
        }

        board[3][3].egg = new SheepEgg(
            3, 3,
            board[3][3].worldX,
            board[3][3].worldY,
            gameManager.teamList[1]);
        board[4][4].egg = new PigEgg(
            4, 4,
            board[4][4].worldX,
            board[4][4].worldY,
            gameManager.teamList[0]);
        
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

    public void highlight(Vector2i[] tiles) {
        clearHighlights();
        for (Vector2i tilev2 : tiles) {
            board[tilev2.x][tilev2.y].image = glowItemFrame;
        }
    }

    public void clearHighlights() {
        for (Tile[] tiles : board) {
            for (Tile tile : tiles) {
                tile.image = itemFrame;
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

    public Egg getEggAt(int x, int y) {
        return board[x][y].egg;
    }

    public Egg getEggAt(Vector2i v2) {
        return getEggAt(v2.x, v2.y);
    }
}
