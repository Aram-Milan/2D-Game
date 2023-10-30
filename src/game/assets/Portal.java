package game.assets;

import city.cs.engine.*;
import game.Game;
import game.GameLevel;
import game.player.Player;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Portal extends StaticBody implements CollisionListener {
    private static final PolygonShape checkpointShape = new PolygonShape(-3.11f,4.17f, 1.34f,4.2f, 1.37f,-3.5f, -3.0f,-3.64f );
    private static final BodyImage image = new BodyImage("data/portal.gif",14);
    private static int checkPointCounter=0;
    public GameLevel level;
    public Game game;
    // added sound
    private static SoundClip portalSound;
    static {
        try {
            portalSound = new SoundClip("data/portal.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public Portal(World w, GameLevel level, Game g) {
        //Inside the constructor, the code calls the "super" constructor to create a new instance of "StaticBody".
        // It passes in the "World" object and the "checkpointShape" object, which sets the shape of the checkpoint object.
        super(w,checkpointShape);
        addImage(image);
        //The code calls the "addCollisionListener" method to register the instance as a collision listener.
        // This means that the instance will listen for collision events with other objects.
        this.addCollisionListener(this);
        this.level=level;
        this.game=g;
    }
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player player){
            checkPointCounter++;
            player.checkPointCount++;
            if(level.isCompleted()){
                portalSound.play();
                game.getView().levelChanged=true;
                game.goToNextLevel();
            }
        }
    }

}
