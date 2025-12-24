package egg;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Team;

public class ChickenEgg extends Egg{

    public ChickenEgg(int xCoordinate, int yCoordinate, int worldX, int worldY, Team team) {
        super(xCoordinate, yCoordinate, worldX, worldY, team);
        name = "Chicken egg";
        try {setImage(ImageIO.read(getClass().getResourceAsStream("/eggs/chicken_spawn_egg.png")));}
        catch (IOException e) {e.printStackTrace();}
    }
    
}
