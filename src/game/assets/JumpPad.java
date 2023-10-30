package game.assets;

import city.cs.engine.*;
import game.player.Player;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class JumpPad extends StaticBody implements CollisionListener {
    private static final PolygonShape jumpPadShape = new PolygonShape(-2.45f,1.54f, 2.45f,1.57f, 3.15f,-1.07f, -3.26f,-1.06f);
    private final BodyImage image = new BodyImage("data/jumpPad.png",8);
    // added sound
    private static SoundClip jumpPadSound;
    static {
        try {
            jumpPadSound = new SoundClip("data/jump.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public JumpPad(World w) {
        super(w);
        addImage(image);
        SolidFixture sf = new SolidFixture(this,jumpPadShape);
        //Declares a SolidFixture object called "sf" that is a fixture of the jump pad body and has the jumpPadShape as its shape.
        this.addCollisionListener(this); //Adds the collision listener to the jump pad body.
    }
    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody() instanceof Player p){ // Checks if the other body in the collision is an instance of the Player class
            // and assigns it to a local variable "p" of type Player.
            p.jump(50);
            jumpPadSound.play();
        }
    }
}
