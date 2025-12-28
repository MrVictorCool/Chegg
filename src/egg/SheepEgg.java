package egg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import main.Team;
import main.tools.Vector2i;
import tile.Board;

public class SheepEgg extends Egg {

    public SheepEgg(int xCoordinate, int yCoordinate, int worldX, int worldY, Team team) {
        super(xCoordinate, yCoordinate, worldX, worldY, team);
        name = "Sheep egg";
        try {setImage(ImageIO.read(getClass().getResourceAsStream("/eggs/sheep_spawn_egg.png")));}
        catch (IOException e) {e.printStackTrace();}
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

        return moves.toArray(new Vector2i[0]);
    }
}
