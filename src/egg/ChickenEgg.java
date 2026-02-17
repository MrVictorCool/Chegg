package egg;

import java.util.ArrayList;
import java.util.List;

import main.Team;
import main.tools.Vector2i;
import tile.Board;

public class ChickenEgg extends Egg{

    /**
     * Represents to where this piece is able to move, only intended for UP and DOWN.
     * Also derived from this is this piece's diagonal attacks and En Passant
     */
    public Vector2i direction;

    public ChickenEgg() {
        name = "Chicken egg";
        imagePath = "/eggs/chicken_spawn_egg.png";
    }

    @Override
    public void initializeEgg(int xCoordinate, int yCoordinate, int worldX, int worldY, Team team) {
        super.initializeEgg(xCoordinate, yCoordinate, worldX, worldY, team);
        if (yCoordinate < 4) {
            direction = Vector2i.DOWN;
        } else {
            direction = Vector2i.UP;
        }
    }

    //TODO: Consider easier or cleaner implementation for pawn movement
    @Override
    public Vector2i[] getMoves(Board board) {
        List<Vector2i> moves = new ArrayList<>();
        Vector2i target;

        if (!getCoordinates().add(direction).isOnBound(board)) {return new Vector2i[0];}

        if (board.getEggAt(getCoordinates().add(direction)) == null) {
            moves.add(getCoordinates().add(direction));
            if (yCoordinate == 6 && direction.equals(Vector2i.UP) || yCoordinate == 1 && direction.equals(Vector2i.DOWN)) {
                moves.add(getCoordinates().add(direction.add(direction)));
            }
        }

        target = getCoordinates().add(direction).add(Vector2i.LEFT);

        if (target.isOnBound(board)) {
            if (board.getEggAt(target) != null && !board.getEggAt(target).isSameTeamAs(this) || target.equals(board.enPassantTile)) {
                moves.add(target);
            }
        }

        target = getCoordinates().add(direction).add(Vector2i.RIGHT);

        if (target.isOnBound(board)) {
            if (board.getEggAt(target) != null && !board.getEggAt(target).isSameTeamAs(this) || target.equals(board.enPassantTile)) {
                moves.add(target);
            }
        }

        return moves.toArray(new Vector2i[0]);
    }
    
}
