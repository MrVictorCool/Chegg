package egg;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Team;
import main.tools.Vector2i;

public class ChickenEgg extends Egg{

    public ChickenEgg(int xCoordinate, int yCoordinate, int worldX, int worldY, Team team) {
        super(xCoordinate, yCoordinate, worldX, worldY, team);
        name = "Chicken egg";
        movementVectors = new Vector2i[8];
        movementVectors[0] = new Vector2i(-1, -1);
        movementVectors[1] = new Vector2i(0, -1);
        movementVectors[2] = new Vector2i(1, -1);
        movementVectors[3] = new Vector2i(-1, 0);
        movementVectors[4] = new Vector2i(1, 0);
        movementVectors[5] = new Vector2i(-1, 1);
        movementVectors[6] = new Vector2i(0, 1);
        movementVectors[7] = new Vector2i(1, 1);
        try {setImage(ImageIO.read(getClass().getResourceAsStream("/eggs/chicken_spawn_egg.png")));}
        catch (IOException e) {e.printStackTrace();}
    }
    
}
