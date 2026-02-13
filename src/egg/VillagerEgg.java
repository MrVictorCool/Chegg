package egg;

import main.tools.Vector2i;

public class VillagerEgg extends Egg{

    public VillagerEgg() {
        name = "Villager egg";
        imagePath = "/eggs/villager_spawn_egg.png";
        movementVectors = new Vector2i[8];
        movementVectors[0] = new Vector2i(-1, -1);
        movementVectors[1] = new Vector2i(0, -1);
        movementVectors[2] = new Vector2i(1, -1);
        movementVectors[3] = new Vector2i(-1, 0);
        movementVectors[4] = new Vector2i(1, 0);
        movementVectors[5] = new Vector2i(-1, 1);
        movementVectors[6] = new Vector2i(0, 1);
        movementVectors[7] = new Vector2i(1, 1);
    }

    //TODO: Add castling
    //TODO: Add check, checkmate and limit moves based on it
    //TODO: Make it so a player can win
}