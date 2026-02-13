package egg;


import java.util.ArrayList;
import java.util.List;

import main.tools.Vector2i;
import tile.Board;

public class SkeletonHorseEgg extends Egg {

    public SkeletonHorseEgg() {
        name = "Skeleton horse egg";
        imagePath = "/eggs/skeleton_horse_spawn_egg.png";
    }

    @Override
    public Vector2i[] getMoves(Board board) {
        List<Vector2i> moves = new ArrayList<>();
        for (Vector2i vector2i : calculateMovesInVector(new Vector2i(-1, -2), board)) {
            moves.add(vector2i);
        }

        for (Vector2i vector2i : calculateMovesInVector(new Vector2i(1, -2), board)) {
            moves.add(vector2i);
        }

        for (Vector2i vector2i : calculateMovesInVector(new Vector2i(2, 1), board)) {
            moves.add(vector2i);
        }

        for (Vector2i vector2i : calculateMovesInVector(new Vector2i(2, -1), board)) {
            moves.add(vector2i);
        }

        for (Vector2i vector2i : calculateMovesInVector(new Vector2i(-2, 1), board)) {
            moves.add(vector2i);
        }

        for (Vector2i vector2i : calculateMovesInVector(new Vector2i(-2, -1), board)) {
            moves.add(vector2i);
        }

        for (Vector2i vector2i : calculateMovesInVector(new Vector2i(1, 2), board)) {
            moves.add(vector2i);
        }

        for (Vector2i vector2i : calculateMovesInVector(new Vector2i(-1, 2), board)) {
            moves.add(vector2i);
        }

        return moves.toArray(new Vector2i[0]);
    }
}
