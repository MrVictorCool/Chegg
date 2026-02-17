package egg;

import java.util.ArrayList;
import java.util.List;

import main.tools.Vector2i;
import tile.Board;

public class VillagerEgg extends Egg{

    public boolean hasMoved = false;

    public VillagerEgg() {
        name = "Villager egg";
        imagePath = "/eggs/villager_spawn_egg.png";
    }

    @Override
    public Vector2i[] getMoves(Board board) {
        List<Vector2i> moves = new ArrayList<>();

        for (int i = -1; i <= 1 ; i++) {
            Boolean sameTeam;
            for (int j = -1; j <= 1; j++) {
                if (j == i && i == 0) {continue;}
                Vector2i v2i = getCoordinates().add(i, j);
                if (!v2i.isOnBound(board)) {continue;}
                if (board.getEggAt(v2i) == null) {
                    sameTeam = false;
                } else {
                    sameTeam = board.getEggAt(v2i).isSameTeamAs(this);
                }
                if (!sameTeam) {
                    moves.add(v2i);
                }
            }
        }

        if (hasMoved) {return moves.toArray(new Vector2i[0]);}

        Vector2i targetCoordinates = scanForEggInVector(Vector2i.RIGHT, board);
        Egg target = board.getEggAt(targetCoordinates);
        PigEgg pigTarget;
        if (target != null) {
            if (target instanceof PigEgg) {
                pigTarget = (PigEgg) target;
                if (!pigTarget.hasMoved && pigTarget.isSameTeamAs(this)) {
                    moves.add(getCoordinates().add(2, 0));
                }
            }
        }

        targetCoordinates = scanForEggInVector(Vector2i.LEFT, board);
        target = board.getEggAt(targetCoordinates);
        if (target != null) {
            if (target instanceof PigEgg) {
                pigTarget = (PigEgg) target;
                if (!pigTarget.hasMoved && pigTarget.isSameTeamAs(this)) {
                    moves.add(getCoordinates().add(-2, 0));
                }
            }
        }

        return moves.toArray(new Vector2i[0]);
    }

    //TODO: Add check, checkmate and limit moves based on it
    //TODO: Make it so a player can win
}