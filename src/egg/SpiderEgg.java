package egg;

import main.tools.Vector2i;

public class SpiderEgg extends Egg {

    // public SpiderEgg(int xCoordinate, int yCoordinate, int worldX, int worldY, Team team) {
    //     super(xCoordinate, yCoordinate, worldX, worldY, team);
    //     name = "Spider egg";
    //     movementVectors = new Vector2i[8];
    //     movementVectors[0] = new Vector2i(-2, -2);
    //     movementVectors[1] = new Vector2i(0, -2);
    //     movementVectors[2] = new Vector2i(2, -2);
    //     movementVectors[3] = new Vector2i(-2, 0);
    //     movementVectors[4] = new Vector2i(2, 0);
    //     movementVectors[5] = new Vector2i(-2, 2);
    //     movementVectors[6] = new Vector2i(0, 2);
    //     movementVectors[7] = new Vector2i(2, 2);
    //     try {setImage(ImageIO.read(getClass().getResourceAsStream("/eggs/spider_spawn_egg.png")));}
    //     catch (IOException e) {e.printStackTrace();}
    // }

    public SpiderEgg() {
         name = "Spider egg";
         imagePath = "/eggs/spider_spawn_egg.png";
        movementVectors = new Vector2i[8];
        movementVectors[0] = new Vector2i(-2, -2);
        movementVectors[1] = new Vector2i(0, -2);
        movementVectors[2] = new Vector2i(2, -2);
        movementVectors[3] = new Vector2i(-2, 0);
        movementVectors[4] = new Vector2i(2, 0);
        movementVectors[5] = new Vector2i(-2, 2);
        movementVectors[6] = new Vector2i(0, 2);
        movementVectors[7] = new Vector2i(2, 2);
    }
}
