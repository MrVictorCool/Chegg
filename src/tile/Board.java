package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import egg.*;
import main.GameManager;
import main.GamePanel;
import main.Team;
import main.tools.Vector2i;

public class Board {

    GamePanel gp; 
    public Tile[][] board = new Tile[8][8];
    public int xOffset;
    public int yOffset;
    public GameManager gameManager;
    public Vector2i enPassantTile = new Vector2i(-1, -1);
    public Vector2i enPassantEggCoordinate = new Vector2i(-1, -1);
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
        }

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[column][row] = new Tile();

                board[column][row].image = itemFrame;
                board[column][row].xCoordinate = column;
                board[column][row].yCoordinate = row;
                board[column][row].worldX = column * gp.tileSize + xOffset;
                board[column][row].worldY = row * gp.tileSize + yOffset;

                if (row == 1 || row == 6) {
                    createEggAt(EggType.CHICKEN, row == 1 ? 1 : 0, column, row);
                }
            }
        }

        createEggAt(EggType.VILLAGER, 0, 4, 7);
        gameManager.makeKing(getEggAt(4, 7), GameManager.teamList[0]);
        createEggAt(EggType.VILLAGER, 1, 4, 0);
        gameManager.makeKing(getEggAt(4, 0), GameManager.teamList[1]);
        // createEggAt(EggType.CHICKEN, 0, 4, 6);
        // createEggAt(EggType.CHICKEN, 1, 4, 1);
        createEggAt(EggType.WANDERING_TRADER, 0, 3, 7);
        createEggAt(EggType.WANDERING_TRADER, 1, 3, 0);
        createEggAt(EggType.HORSE, 0, 1, 7);
        createEggAt(EggType.HORSE, 0, 6, 7);
        createEggAt(EggType.HORSE, 1, 1, 0);
        createEggAt(EggType.HORSE, 1, 6, 0);
        createEggAt(EggType.SHEEP, 0, 2, 7);
        createEggAt(EggType.SHEEP, 0, 5, 7);
        createEggAt(EggType.SHEEP, 1, 2, 0);
        createEggAt(EggType.SHEEP, 1, 5, 0);
        createEggAt(EggType.PIG, 0, 0, 7);
        createEggAt(EggType.PIG, 0, 7, 7);
        createEggAt(EggType.PIG, 1, 0, 0);
        createEggAt(EggType.PIG, 1, 7, 0);

        gameManager.recalculateAttackedTiles();
    }

    public void draw(Graphics2D g2) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column].draw(g2, gp);
                if (getEggAt(row, column) != null) {
                    getEggAt(row, column).draw(g2, gp);
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

    public void setEggAt(Egg egg, int x, int y) {
        board[x][y].egg = egg;
        board[x][y].update();
    }

    public void setEggAt(Egg egg, Vector2i v2) {
        setEggAt(egg, v2.x, v2.y);
    }

    public void createEggAt(EggType eggType, Team team, int x, int y) {
        switch (eggType) {
            case CHICKEN:
                board[x][y].egg = new ChickenEgg();
                break;
            case HORSE:
                board[x][y].egg = new HorseEgg();
                break;
            case PIG:
                board[x][y].egg = new PigEgg();
                break;
            case SHEEP:
                board[x][y].egg = new SheepEgg();
                break;
            case SKELETON_HORSE:
                board[x][y].egg = new SkeletonHorseEgg();
                break;
            case SPIDER:
                board[x][y].egg = new SpiderEgg();
                break;
            case VILLAGER:
                board[x][y].egg = new VillagerEgg();
                break;
            case WANDERING_TRADER:
                board[x][y].egg = new WanderingTraderEgg();
                break;
            default:
                System.err.println(eggType + " not handled by createEggAt");
                System.exit(1);
        }

        board[x][y].egg.initializeEgg(
            x, y,
            board[x][y].worldX,
            board[x][y].worldY,
            team
        );
    }

    public void createEggAt(EggType eggType, int team, int x, int y) {
        createEggAt(eggType, GameManager.teamList[team], x, y);
    }

    public void createEggAt(EggType eggType, int team, Vector2i v2) {
        createEggAt(eggType, GameManager.teamList[team], v2.x, v2.y);
    }

    public void createEggAt(EggType eggType, Team team, Vector2i v2) {
        createEggAt(eggType, team, v2.x, v2.y);
    }

    public void setTileAttackedBool(int x, int y, int teamInt, boolean attacked) {
        board[x][y].beingAttackedByTeam[teamInt] = attacked;
    }

    public void setTileAttackedBool(Vector2i v2, int teamInt, boolean attacked) {
        setTileAttackedBool(v2.x, v2.y, teamInt, attacked);
    }

    public boolean getTileAttackedBool(int x, int y, int teamInt) {
        return board[x][y].beingAttackedByTeam[teamInt];
    }

    public boolean getTileAttackedBool(Vector2i v2, int teamInt) {
        return getTileAttackedBool(v2.x, v2.y, teamInt);
    }
}
