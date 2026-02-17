package egg;

import java.util.ArrayList;
import java.util.List;

import main.tools.Vector2i;
import tile.Board;

public class PigEgg extends Egg{

    public boolean hasMoved = false;

    public PigEgg() {
        name = "Pig egg";
        imagePath = "/eggs/pig_spawn_egg.png";
    }

    @Override
    public Vector2i[] getMoves(Board board) {
        List<Vector2i> moves = new ArrayList<>();
        
        for (Vector2i vector2i : calculateMovesInVector(Vector2i.DOWN, board)) {
            moves.add(vector2i);
        }

        for (Vector2i vector2i : calculateMovesInVector(Vector2i.UP, board)) {
            moves.add(vector2i);
        }

        for (Vector2i vector2i : calculateMovesInVector(Vector2i.LEFT, board)) {
            moves.add(vector2i);
        }

        for (Vector2i vector2i : calculateMovesInVector(Vector2i.RIGHT, board)) {
            moves.add(vector2i);
        }

        return moves.toArray(new Vector2i[0]);
    }
}
