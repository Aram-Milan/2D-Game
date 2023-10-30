package game.characters;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.World;
import game.player.Player;

public class Sombra extends Player {
    private static PolygonShape sombraShape = new PolygonShape(0.13f,1.96f, 1.02f,1.95f, 2.09f,-0.1f, 1.91f,-1.96f, -1.04f,-1.9f, -2.11f,0.77f);
    // Define the image of the player facing right
    public static BodyImage sombraImageRight = new BodyImage("data/sombraRight.gif",4f);
    // Define the image of the player facing left
    public static BodyImage sombraImageLeft = new BodyImage("data/sombraLeft.gif",4f);

    public Sombra(World w) {
        super(w, 50);
        setPlayerShape(sombraShape);
        addImage(sombraImageLeft);
    }
}
