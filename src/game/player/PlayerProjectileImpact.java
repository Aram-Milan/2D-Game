package game.player;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import game.enemy.Bombers;
import game.enemy.Bastion;
import game.enemy.Boss;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

// PlayerProjectileImpact class implements CollisionListener to listen to the collision event
public class PlayerProjectileImpact implements CollisionListener {
    // added sound
    private static SoundClip damageHitSound;
    static {
        try {
            damageHitSound = new SoundClip("data/damageHit.mp3");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    @Override
    public void collide(CollisionEvent collisionEvent) {
        // Check if the other body is an Enemy and destroy it if it is
        if (collisionEvent.getOtherBody() instanceof Bastion) {
            damageHitSound.play();
            Bastion.dead++;
            collisionEvent.getOtherBody().destroy();
        }
        if (collisionEvent.getOtherBody() instanceof Bombers) {
            damageHitSound.play();
            Bastion.dead++;
            collisionEvent.getOtherBody().destroy();
        }
        if(collisionEvent.getOtherBody() instanceof Boss b){
            damageHitSound.play();
            b.setHealth(b.getHealth()-10);
            if(b.getHealth()<=0){
                b.destroy();
            }
        }
        // Destroy the projectile
        collisionEvent.getReportingBody().destroy();
    }
}
