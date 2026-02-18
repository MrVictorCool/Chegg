package egg;

import java.util.ArrayList;
import java.util.List;

import main.tools.Vector2i;
import tile.Board;

public class SheepEgg extends Egg {

    public SheepEgg() {
        name = "Sheep egg";
        imagePath = "/eggs/sheep_spawn_egg.png";
    }

    @Override
    public Vector2i[] getMoves(Board board, Boolean checkForSameTeam) {
        List<Vector2i> moves = new ArrayList<>();
        
        for (Vector2i vector2i : calculateMovesInVector(new Vector2i(1, 1), board, checkForSameTeam)) {
            moves.add(vector2i);
        }

        for (Vector2i vector2i : calculateMovesInVector(new Vector2i(1, -1), board, checkForSameTeam)) {
            moves.add(vector2i);
        }

        for (Vector2i vector2i : calculateMovesInVector(new Vector2i(-1, 1), board, checkForSameTeam)) {
            moves.add(vector2i);
        }

        for (Vector2i vector2i : calculateMovesInVector(new Vector2i(-1, -1), board, checkForSameTeam)) {
            moves.add(vector2i);
        }

        return moves.toArray(new Vector2i[0]);
    }

    @Override
    public Vector2i[] getMoves(Board board) {
        return getMoves(board, true);
    }
}
