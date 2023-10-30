package game.player;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import game.enemy.Bastion;
import game.enemy.Boss;
import game.items.GenjiStar;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class PlayerCollision implements CollisionListener {
    Player player;
    public PlayerCollision(Player player) {
        this.player=player;
    }
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

        if(collisionEvent.getOtherBody() instanceof Bastion && player.heroChangedToGenji){
            if(Player.genjiAttacking) {
                collisionEvent.getOtherBody().destroy();
                Bastion.dead++;
            }
        }
        if(collisionEvent.getOtherBody() instanceof GenjiStar){
            Player.genjiStar=true;
            collisionEvent.getOtherBody().destroy();
        }
        if(collisionEvent.getOtherBody() instanceof Boss){
           Player player = (Player)collisionEvent.getReportingBody();
           damageTakenSound.play();
           player.setHealth(player.getHealth()-20);
        }
    }
}
