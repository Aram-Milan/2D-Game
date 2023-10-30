package game.characters;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.World;
import game.player.Player;

public class Genji extends Player {
    private static PolygonShape genjiShape = new PolygonShape(-1.3f,1.97f, 1.22f,1.95f, 1.2f,-1.9f, -1.3f,-1.9f);
    // Define the image of the player facing right
    public static BodyImage genjiImageRight = new BodyImage("data/genjiStopRight.png",4f);
    // Define the image of the player facing left
    public static BodyImage genjiImageLeft = new BodyImage("data/genjiStopLeft.png",4f);
    public static BodyImage genjiAttackingRightImage = new BodyImage("data/genjiAttackingRight.gif",5f);
    public static BodyImage genjiAttackingLeftImage = new BodyImage("data/genjiAttackingLeft.gif",5f);
    public static BodyImage genjiRollRightImage = new BodyImage("data/genjiRollRight.gif",5f);
    public static BodyImage genjiRollLeftImage = new BodyImage("data/genjiRollLeft.gif",5f);

    public Genji(World world, int health) {
        super(world, health);
        setPlayerShape(genjiShape);
        addImage(genjiImageRight);
    }
    @Override
    public void shoot() {
        if(super.facingRight){
            removeAllImages();
            addImage(genjiAttackingRightImage);
        }
        else{
            removeAllImages();
            addImage(genjiAttackingLeftImage);
        }
    }
}
