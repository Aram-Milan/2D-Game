package game.assets;

import city.cs.engine.*;
import game.enemy.Bastion;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Vehicle extends DynamicBody implements CollisionListener {
    private static final PolygonShape truckShape = new PolygonShape(-2.67f,2.44f, 0.56f,2.39f, 3.82f,-0.2f, 3.86f,-2.82f, -3.89f,-2.71f, -3.87f,0.22f);
    private static BodyImage truckImage = new BodyImage("data/truck.gif",6);
    private static final BodyImage explosionImage = new BodyImage("data/explosion.gif",6);
    // added sound
    private static SoundClip explosionSound;
    static {
        try {
            explosionSound = new SoundClip("data/explosion.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public Vehicle(World w) {
        super(w,truckShape);
        addImage(truckImage);
        this.addCollisionListener(this);
    }

    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody() instanceof Bastion){
            explosionSound.play();
            Bastion.dead++;
            e.getReportingBody().removeAllImages();
            e.getReportingBody().addImage(explosionImage);
            e.getOtherBody().destroy();

            // Schedule task to destroy reporting body after 2 seconds
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.schedule(() -> e.getReportingBody().destroy(), 1, TimeUnit.SECONDS);
        }
    }
}
