package game.items;

import city.cs.engine.*;
import game.player.Player;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

//The code declares that the "Ammo" class extends the "DynamicBody" class,
// which means that it inherits all the properties and methods of "DynamicBody".

//The code also declares that the "Ammo" class implements the "CollisionListener" interface.
// This means that instances of "Ammo" can listen for collisions with other objects.
public class Ammo extends DynamicBody implements CollisionListener {
    private static final PolygonShape ammoShape = new PolygonShape(-1.38f,0.62f, 1.38f,0.61f, 1.36f,-0.64f, -1.38f,-0.64f);
    private static final BodyImage image = new BodyImage("data/ammo.gif",3);
    // added sound
    private static SoundClip ammoSound;
    static {
        try {
            ammoSound = new SoundClip("data/ammo.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    @Override
    public void destroy()
    {
        ammoSound.play();
        super.destroy();
    }

    public Ammo(World w) {
        //Inside the constructor, the code calls the "super" constructor to create a new instance of "DynamicBody".
        // It passes in the "World" object and the "AmmoShape" object, which sets the shape of the ammo object.
        super(w,ammoShape);
        addImage(image);
        //The code calls the "addCollisionListener" method to register the instance as a collision listener.
        // This means that the instance will listen for collision events with other objects.
        this.addCollisionListener(this);
    }

    // override the collide method for ammo collision with player
    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody() instanceof Player){
            Player p = (Player)e.getOtherBody();
            p.setAmmo(p.getAmmo()+3);
            e.getReportingBody().destroy();
        }
    }

}
