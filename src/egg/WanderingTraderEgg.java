package egg;

import java.util.ArrayList;
import java.util.List;

import main.tools.Vector2i;
import tile.Board;

public class WanderingTraderEgg extends Egg {

    public WanderingTraderEgg() {
        name = "Sheep egg";
        imagePath = "/eggs/wandering_trader_spawn_egg.png";
    }

    @Override
    public Vector2i[] getMoves(Board board) {
        List<Vector2i> moves = new ArrayList<>();
        
        for (Vector2i vector2i : calculateMovesInVector(new Vector2i(1, 1), board)) {
            moves.add(vector2i);
        }

        for (Vector2i vector2i : calculateMovesInVector(new Vector2i(1, -1), board)) {
            moves.add(vector2i);
        }

        for (Vector2i vector2i : calculateMovesInVector(new Vector2i(-1, 1), board)) {
            moves.add(vector2i);
        }

        for (Vector2i vector2i : calculateMovesInVector(new Vector2i(-1, -1), board)) {
            moves.add(vector2i);
        }

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
