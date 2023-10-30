package game;

import city.cs.engine.*;
import game.levels.*;
import game.player.Player;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public abstract class GameLevel extends World {
    private Player player;
    public boolean playerDead=false;
    public StaticBody ground;
    public Game game;
    private SoundClip gameMusic;

    public static boolean hint;
    public GameLevel(Game g){
        this.game=g;
        Shape groundShape = new BoxShape(80f, 1.5f);
        ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0f, -40f));
        player = new Player(this, 60);
        setGravity(50);
    }
    public void playMusic(){
        if(this instanceof Menu){
            try {
                gameMusic = new SoundClip("data/lobby.wav");   // Open an audio input stream
                gameMusic.loop();                              // Set it to continuous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                //code in here will deal with any errors
                //that might occur while loading/playing sound
                System.out.println(e);
            }
        }if(this instanceof Level1){
            try {
                gameMusic = new SoundClip("data/level1.wav");   // Open an audio input stream
                gameMusic.loop();                              // Set it to continuous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                //code in here will deal with any errors
                //that might occur while loading/playing sound
                System.out.println(e);
            }
        }if(this instanceof Level2){
            try {
                gameMusic = new SoundClip("data/wildWest.wav");   // Open an audio input stream
                gameMusic.loop();                              // Set it to continuous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                //code in here will deal with any errors
                //that might occur while loading/playing sound
                System.out.println(e);
            }
        }if(this instanceof Level3){
            try {
                gameMusic = new SoundClip("data/level3.wav");   // Open an audio input stream
                gameMusic.loop();                              // Set it to continuous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                //code in here will deal with any errors
                //that might occur while loading/playing sound
                System.out.println(e);
            }
        }if(this instanceof Level4){
            try {
                gameMusic = new SoundClip("data/risk.wav");   // Open an audio input stream
                gameMusic.loop();                              // Set it to continuous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                //code in here will deal with any errors
                //that might occur while loading/playing sound
                System.out.println(e);
            }
        }
    }
    public void stopMusic(){
        this.gameMusic.stop();
    }
    public Player getPlayer() {return player;}
    public StaticBody getGround() {return ground;}
    public SoundClip getGameMusic(){return gameMusic;}
    public abstract boolean isCompleted();
}
