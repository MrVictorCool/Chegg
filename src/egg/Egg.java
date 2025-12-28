package egg;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import main.GamePanel;
import main.Team;
import main.tools.OutlineCreator;
import main.tools.Vector2i;
import tile.Board;

public abstract class Egg {

    public String name;
    public BufferedImage image;
    protected BufferedImage outline;
    public Vector2i[] movementVectors;
    public int xCoordinate, yCoordinate, worldX, worldY;
    public Team team;

    public Egg(int xCoordinate, int yCoordinate, int worldX, int worldY, Team team) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.worldX = worldX;
        this.worldY = worldY;
        this.team = team;
    }

    /**
     * Method for getting an egg's possible moves, it should check for other eggs to collide into unless you explicitly want it the other way around when overriding
     * @param board The {@code Board} to which it's movements are limited to
     * @return
     * A list of posible moves in board coordinates in form of a {@code Vector2i[]}
     */
    public Vector2i[] getMoves(Board board) {
        List<Vector2i> moves = new ArrayList<>();
        for (Vector2i vector2i : movementVectors) {
            Vector2i target = getCoordinates().add(vector2i);
            Boolean sameTeam;
            if (!target.isOnBound(board)) {continue;}
            if (board.getEggAt(target) == null) {
                sameTeam = false;
            } else {
                sameTeam = board.getEggAt(target).isSameTeamAs(this);
            }
            if (!sameTeam) {
                moves.add(target);
            }
        }
        return moves.toArray(new Vector2i[0]);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        outline = OutlineCreator.createOutline(image, team.color);
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        // g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
        g2.drawImage(outline, worldX - 2, worldY - 2, gp.tileSize + 4, gp.tileSize + 4, null);
    }

    public Vector2i getCoordinates() {
        return new Vector2i(xCoordinate, yCoordinate);
    }

    public boolean isSameTeamAs(Egg otherEgg) {
        if (otherEgg == null) {return false;}
        if (team.equals(otherEgg.team)) {return true;}
        return false;
    }

    public static boolean isSameTeamAs(Egg startingEgg, Egg otherEgg) {
        return startingEgg.isSameTeamAs(otherEgg);
    }

    /**
     * <p> Finds as many moves as possible while repeating a step {@code Vector2i} and checking in each if it collided. It <b> does not </b> account for in-between tiles.
     * </p>
     * <ul> <p> <b> The expected behaviour is as follows: </b> </p> </ul>
     * 
     * <ul>
     *   <li>If it finds an empty tile it adds it and continues</li>
     *   <li>If it finds an egg from the same team it stops adding tiles</li>
     *   <li>If it finds an egg from another team it adds it (to capture it) and it stops futur tiles from being added</li>
     * </ul>
     *
     * @param step {@code Vector2i} that indicates the each step as a relative vector from the previous position
     * @param board The board from which the limits of size are taken (assumed to be a rectangle)
     * @return An array containing possible moves
     */
    protected Vector2i[] calculateMovesInVector(Vector2i step, Board board) {
        List<Vector2i> moves = new ArrayList<>();
        Vector2i pointer = getCoordinates();
        Vector2i target = pointer.add(step);

        while (target.isOnBound(board)) {
            if (board.getEggAt(target) != null) {
                if (!board.getEggAt(target).isSameTeamAs(this)) {
                    moves.add(target);
                    break;
                }
                break;
            }
            moves.add(target);
            pointer = Vector2i.ZERO.add(target);
            target = pointer.add(step);
        }

        return moves.toArray(new Vector2i[0]);
    }
}
