package game.player;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.characters.Genji;
import game.characters.Sombra;
import game.items.Ammo;
import game.items.HealthPack;
import game.levels.Level2;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// PlayerController class implements KeyListener interface to handle keyboard events
public class PlayerController implements KeyListener, MouseListener {
    private Player player;
    private boolean sombraHacking;
    // added sound
    private static SoundClip swordSound;
    static {
        try {
            swordSound = new SoundClip("data/sword.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    // Schedule task to destroy reporting body after 2 seconds
    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    public PlayerController(Player p) {
        player = p;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_A){
            // to change the player face in left direction we need to remove the image and add the left-faced image
            player.walkLeft();

        } else if (code == KeyEvent.VK_D){
            // to change the player face in right direction we need to remove the image and add the right-faced image
            player.walkRight();

        }else if (code == KeyEvent.VK_W){
            player.jump(33);
        } else if (code == KeyEvent.VK_R) { // roll
            if(player.facingRight && player.heroChangedToGenji) {
                player.removeAllImages();
                player.addImage(Genji.genjiRollRightImage);
                player.setPosition(new Vec2(player.getPosition().x+3,player.getPosition().y));

                // after 0.8 secs goes back to prev image
                executor.schedule(() -> player.removeAllImages(), 800, TimeUnit.MILLISECONDS);
                executor.schedule(() -> player.addImage(Genji.genjiImageRight), 800, TimeUnit.MILLISECONDS);
            }
            else if(!player.facingRight && player.heroChangedToGenji){
                player.removeAllImages();
                player.addImage(Genji.genjiRollLeftImage);
                player.setPosition(new Vec2(player.getPosition().x-4,player.getPosition().y));

                // after 0.8 secs goes back to prev image
                executor.schedule(() -> player.removeAllImages(), 800, TimeUnit.MILLISECONDS);
                executor.schedule(() -> player.addImage(Genji.genjiImageLeft), 800, TimeUnit.MILLISECONDS);
            }
        }
        else if(code == KeyEvent.VK_SPACE){
            shooting();
        }
        else if(code == KeyEvent.VK_SHIFT) {
            player.run();
        }
        else if(code==KeyEvent.VK_E){
            if(player.getPosition().x>36 && player.getPosition().x<44 && !Level2.shorterPlatformsState){

                Level2.shorterPlatformsState=true;

                Level2.p7.setFillColor(Color.green);

                // Create three new StaticBody platforms using the platformShapeShorter BoxShape
                Shape platformShapeShorter = new BoxShape(4f, 0.8f);
                StaticBody p4 = new StaticBody(Level2.p7.getWorld(), platformShapeShorter);
                p4.setPosition(new Vec2(-45f, -10f));
                p4.setFillColor(new Color(196,113,56));

                StaticBody p5 = new StaticBody(Level2.p7.getWorld(), platformShapeShorter);
                p5.setPosition(new Vec2(-37f, -1f));
                p5.setFillColor(new Color(196,113,56));

                StaticBody p6 = new StaticBody(Level2.p7.getWorld(), platformShapeShorter);
                p6.setPosition(new Vec2(-29f, 7f));
                p6.setFillColor(new Color(196,113,56));

                Ammo ammo5 = new Ammo(Level2.p7.getWorld());
                ammo5.setPosition(new Vec2(-43.5f,-7f));

                HealthPack healthPack3 = new HealthPack(Level2.p7.getWorld());
                healthPack3.setPosition(new Vec2(-29.5f,8f));
            }
        }

        if(code==KeyEvent.VK_3) {
            player.setPlayerImageRight(new BodyImage("data/mccreeFlip.gif",4f));
            player.setPlayerImageLeft(new BodyImage("data/mccree.gif",4f));
            player.heroChangedToGenji =false;
            sombraHacking=false;
            player.setgenjiAttacking(false);
            if (player.facingRight) {
                player.removeAllImages();
                player.addImage(player.getPlayerImageRight());
            }
            else {
                player.removeAllImages();
                player.addImage(player.getPlayerImageLeft());
            }
        }

        if(code==KeyEvent.VK_2) {
            player.setPlayerImageRight(Sombra.sombraImageRight);
            player.setPlayerImageLeft(Sombra.sombraImageLeft);
            player.heroChangedToGenji =false;
            sombraHacking=true;
            player.setgenjiAttacking(false);
            if (player.facingRight) {
                player.removeAllImages();
                player.addImage(player.getPlayerImageRight());
            }
            else {
                player.removeAllImages();
                player.addImage(player.getPlayerImageLeft());
            }
        }
        if(code==KeyEvent.VK_1 && Player.genjiStar) {
            player.setPlayerImageRight(Genji.genjiImageRight);
            player.setPlayerImageLeft(Genji.genjiImageLeft);
            player.heroChangedToGenji =true;
            sombraHacking=false;
            if (player.facingRight) {
                player.removeAllImages();
                player.addImage(player.getPlayerImageRight());
            }
            else {
                player.removeAllImages();
                player.addImage(player.getPlayerImageLeft());
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            // to stop sliding I used startWalking(0) instead of stopWalking because after keyReleased player slides then stops
            //player.stopWalking();
            player.startWalking(0);
        } else if (code == KeyEvent.VK_D) {
            //player.stopWalking();
            player.startWalking(0);
        }else if (code == KeyEvent.VK_R) {
            //player.stopWalking();
            if(player.heroChangedToGenji) {
                player.startWalking(0);
            }
        }
        if(code==KeyEvent.VK_H&&sombraHacking){
            player.hack=true;
        }
    }
    public void updatePlayer(Player newPlayer){
        player=newPlayer;
    }
    public void shooting(){
        if(player.heroChangedToGenji) {
            player.setgenjiAttacking(true);
            if (player.facingRight) {
                // player.attacked=true;
                player.removeAllImages();
                player.addImage(Genji.genjiAttackingRightImage);
                swordSound.play();
                // after 1 secs goes back to prev image
                executor.schedule(() -> player.removeAllImages(), 1, TimeUnit.SECONDS);
                executor.schedule(() -> player.addImage(Genji.genjiImageRight), 1, TimeUnit.SECONDS);
            } else {
                // player.attacked=true;
                player.removeAllImages();
                player.addImage(Genji.genjiAttackingLeftImage);
                swordSound.play();
                // after 1 secs goes back to prev image
                executor.schedule(() -> player.removeAllImages(), 1, TimeUnit.SECONDS);
                executor.schedule(() -> player.addImage(Genji.genjiImageLeft), 1, TimeUnit.SECONDS);
            }
        }
        else {
            player.setgenjiAttacking(false);
            player.shoot();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        shooting();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
