package egg;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ChickenEgg extends Egg{

    public ChickenEgg(int xCoordinate, int yCoordinate, int worldX, int worldY) {
        super(xCoordinate, yCoordinate, worldX, worldY);
        try {image = ImageIO.read(getClass().getResourceAsStream("/eggs/chicken_spawn_egg.png"));}
        catch (IOException e) {e.printStackTrace();}
    }
    
}
