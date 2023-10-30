package game.enemy;

import city.cs.engine.*;
import game.GameLevel;
import game.player.Player;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Bombers extends Walker implements CollisionListener,StepListener{
    private static final PolygonShape bomberShape = new PolygonShape(-0.32f,1.94f, 1.3f,1.96f, 1.34f,-1.48f, -1.48f,-1.54f, -1.53f,0.83f);
    private static final BodyImage bomberRightimage = new BodyImage("data/bomberRight.gif", 5);
    private static final BodyImage bomberLeftimage = new BodyImage("data/bomberLeft.gif", 5);
    private static final BodyImage explosionImage = new BodyImage("data/explosion.gif",9);
    private final int speed = 7;
    private final int range = 3;
    private float left, right;
    boolean facingRight;
    private Player player;
    private GameLevel level;
    // added sound
    private static SoundClip bombSound;
    static {
        try {
            bombSound = new SoundClip("data/bomb.mp3");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    public Bombers(GameLevel level,Player p) {
        super(level,bomberShape); // Call the constructor for the superclass (Walker) and pass in the world and polygon shape
        player=p;
        addImage(bomberRightimage);
        this.level=level;
        level.addStepListener(this); // Add the StepListener to the world
        this.addCollisionListener(this);
    }
    @Override
    public void destroy()
    {
        bombSound.play();
        super.destroy();
    }
    @Override
    public void setPosition(Vec2 position) { // Override the setPosition method from the superclass
        super.setPosition(position);
        left = position.x - range; // Set the left range of movement
        right = position.x + range;  // Set the right range of movement
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if (getPosition().x > right) { // If the Enemy has moved too far right
            startWalking(-speed);
            removeAllImages();
            addImage(bomberLeftimage); // Add the BodyImage for the Enemy facing right
            facingRight = false;
        }
        if (getPosition().x < left) { // If the Enemy has moved too far left
            startWalking(speed);
            removeAllImages();
            addImage(bomberRightimage); // Add the BodyImage for the Enemy facing left
            facingRight = true;
        }
        if(this.getPosition().x-player.getPosition().x<0) {
            startWalking(speed);
            removeAllImages();
            addImage(bomberRightimage); // Add the BodyImage for the Enemy facing right
            facingRight = false;
        }
        if(this.getPosition().x-player.getPosition().x>0) {
            startWalking(-speed);
            removeAllImages();
            addImage(bomberLeftimage); // Add the BodyImage for the Enemy facing right
            facingRight = true;
        }
    }
    @Override
    public void postStep(StepEvent stepEvent) {
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player){
            e.getOtherBody().removeAllImages();
            e.getOtherBody().addImage(explosionImage);
            e.getReportingBody().destroy();

            // Schedule task to destroy reporting body after 2 seconds
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.schedule(() -> e.getOtherBody().destroy(), 1, TimeUnit.SECONDS);
            level.playerDead=true;
        }
    }
}
