package game.items;

import city.cs.engine.*;
import game.player.Player;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class HealthPack extends DynamicBody implements CollisionListener {
   private static final PolygonShape healthPackShape = new PolygonShape(-1.08f,1.36f, 1.1f,1.33f, 1.05f,-1.36f, -1.06f,-1.34f);
   private static final BodyImage image = new BodyImage("data/healthpack.png",3f);
   private static final int health=50;
   // added sound
    private static SoundClip healthSound;
    static {
        try {
            healthSound = new SoundClip("data/health.mp3");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    @Override
    public void destroy()
    {
        healthSound.play();
        super.destroy();
    }

    public HealthPack(World w) {
        // Calls the constructor of the DynamicBody class with the World object and the healthPackShape as parameters.
        super(w,healthPackShape);
        addImage(image);
        this.addCollisionListener(this); //Adds the collision listener to the health pack body.
    }
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player){
            Player p = (Player)e.getOtherBody(); // Casts the other body to a Player object.
            p.setHealth(p.getHealth()+health);
            e.getReportingBody().destroy();
        }
    }
}
