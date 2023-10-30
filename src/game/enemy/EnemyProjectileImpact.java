package game.enemy;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import game.player.Player;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class EnemyProjectileImpact implements CollisionListener {
    //This line defines a new class called "EnemyProjectileImpact" that implements the "CollisionListener" interface.
    // This means that it will be able to listen for collision events and respond accordingly.

    // added sound
    private static SoundClip damageTakenSound;
    static {
        try {
            damageTakenSound = new SoundClip("data/damageTaken.mp3");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    @Override
    public void collide(CollisionEvent collisionEvent) {
        // Check if the other body is a player and decrease its health
        if(collisionEvent.getOtherBody() instanceof Player p){
            damageTakenSound.play();
            p.setHealth(p.getHealth()-10);
        }
        // Destroy the projectile
        collisionEvent.getReportingBody().destroy();
    }
}
