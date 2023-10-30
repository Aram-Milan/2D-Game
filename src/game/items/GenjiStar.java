package game.items;

import city.cs.engine.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class GenjiStar extends DynamicBody {

    private static final PolygonShape genjiStarShape = new PolygonShape(0.12f,1.28f, 1.42f,-0.02f, 0.91f,-1.31f, -0.74f,-1.31f, -1.24f,-0.01f);
    private static final BodyImage image = new BodyImage("data/genjiStar.gif",3);
    // added sound
    private static SoundClip pickUpSound;
    static {
        try {
            pickUpSound = new SoundClip("data/pickUp.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    @Override
    public void destroy()
    {
        pickUpSound.play();
        super.destroy();
    }

    public GenjiStar(World w) {
        super(w,genjiStarShape);
        addImage(image);
    }
}
