package egg;

import main.tools.Vector2i;

public class HorseEgg extends Egg {

    public HorseEgg() {
        name = "Horse egg";
        imagePath = "/eggs/horse_spawn_egg.png";
        movementVectors = new Vector2i[8];
        movementVectors[0] = new Vector2i(-1, -2);
        movementVectors[1] = new Vector2i(1, -2);
        movementVectors[2] = new Vector2i(2, 1);
        movementVectors[3] = new Vector2i(2, -1);
        movementVectors[4] = new Vector2i(-2, 1);
        movementVectors[5] = new Vector2i(-2, -1);
        movementVectors[6] = new Vector2i(1, 2);
        movementVectors[7] = new Vector2i(-1, 2);
    }
}
