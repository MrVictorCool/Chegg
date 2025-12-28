package egg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import main.Team;
import main.tools.Vector2i;
import tile.Board;

public class PigEgg extends Egg{

    public PigEgg(int xCoordinate, int yCoordinate, int worldX, int worldY, Team team) {
        super(xCoordinate, yCoordinate, worldX, worldY, team);
        name = "Pig egg";
        try {setImage(ImageIO.read(getClass().getResourceAsStream("/eggs/pig_spawn_egg.png")));}
        catch (IOException e) {e.printStackTrace();}
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
