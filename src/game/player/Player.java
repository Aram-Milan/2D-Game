package game.player;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

// Player(class) extends Walker to move the character
public class Player extends Walker{
    private static PolygonShape playerShape = new PolygonShape(-2.12f,1.96f, 2.07f,1.96f, 2.08f,-1.96f, -2.15f,-1.94f);
    // Define the image of the player facing right
    private static BodyImage playerImageRight = new BodyImage("data/mccreeFlip.gif",4f);
    // Define the image of the player facing left
    private static  BodyImage playerImageLeft = new BodyImage("data/mccree.gif",4f);
    private static final float WALKING_SPEED = 20;
    private static final float RUNNING_SPEED = 40;
    private int ammo,health;
    // Define a boolean to determine if the player is facing right
    public boolean facingRight;
    // Define the speed of the player's bullet(projectile)
    float bulletSpeed=40;
    public boolean hack = false;
    public boolean heroChangedToGenji =false;

    public int checkPointCount = 0;
    public static boolean genjiAttacking = false;
    public static boolean genjiStar=false;

    private static SoundClip pistolSound;
    private static SoundClip swordSound;
    private static SoundClip damageTakenSound;
    static {
        try {
            pistolSound = new SoundClip("data/pistol.wav");
            swordSound = new SoundClip("data/sword.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    // to have access to players ammo counts,health we have setters and getters for it
    public Player (World world, int health){
        super(world,playerShape);
        facingRight=true;
        addImage(playerImageRight);
        this.health=health;
        ammo=0;
        PlayerCollision pc = new PlayerCollision(this);
        this.addCollisionListener(pc);
    }
    public void setgenjiAttacking(boolean genjiAttacking) {
        Player.genjiAttacking = genjiAttacking;
    }
    public void setAmmo(int a){
        this.ammo = a;
    }
    public int getAmmo(){return ammo;}
    public int getHealth() {
        return health;
    }
    public void setHealth(int healths) {
        this.health = healths;
    }
    // Method for the player to walk left
    public void walkLeft(){
        removeAllImages();
        // Add the image of the player facing left
        addImage(playerImageLeft);
        startWalking(-WALKING_SPEED);
        facingRight=false;
    }
    // Method for the player to walk right
    public void walkRight(){
        removeAllImages();
        // Add the image of the player facing right
        addImage(playerImageRight);
        startWalking(WALKING_SPEED);
        facingRight=true;
    }
    public void run(){
        //Running
        if(facingRight)
            startWalking(RUNNING_SPEED);
        else startWalking(-RUNNING_SPEED);
    }
    // Method for the player to shoot a projectile
    public void shoot(){
            DynamicBody bullet = new DynamicBody(this.getWorld(), new PolygonShape(-1.08f, 0.39f, 0.88f, 0.33f, 1.05f, 0.06f, 0.88f, -0.22f, -1.08f, -0.32f));

            // Create a new PlayerProjectileImpact instance to handle collisions with the bullet.
            PlayerProjectileImpact impact = new PlayerProjectileImpact();

            // Add the collision listener to the bullet.
            bullet.addCollisionListener(impact);

            // Set the gravity scale to 0 to make the bullet unaffected by gravity.
            bullet.setGravityScale(0);

            // If the player is facing right and has ammo left.
            if (facingRight && ammo > 0) {
                bullet.addImage(new BodyImage("data/bullet.png", 2));

                pistolSound.play();
                // Set the position of the bullet to the right of the player's current position.
                bullet.setPosition(new Vec2(this.getPosition().x + 4, this.getPosition().y));

                // Set the velocity of the bullet to the right.
                bullet.setLinearVelocity(new Vec2(bulletSpeed, 0));
            }

            // If the player is facing left and has ammo left.
            else if (!facingRight && ammo > 0) {
                bullet.addImage(new BodyImage("data/bulletFlip.png", 2));

                pistolSound.play();
                // Set the position of the bullet to the left of the player's current position.
                bullet.setPosition(new Vec2(this.getPosition().x - 4, this.getPosition().y));

                bullet.setLinearVelocity(new Vec2(-bulletSpeed, 0));
            }

            setAmmo(getAmmo() - 1);
            if (getAmmo() < 0) {
                setAmmo(0);
            }
    }

    public void setPlayerImageRight(BodyImage playerImageRight) {
        Player.playerImageRight = playerImageRight;
    }

    public void setPlayerImageLeft(BodyImage playerImageLeft) {
        Player.playerImageLeft = playerImageLeft;
    }

    public BodyImage getPlayerImageRight() {
        return playerImageRight;
    }

    public BodyImage getPlayerImageLeft() {
        return playerImageLeft;
    }

    public int getCheckPointCount() {
        return checkPointCount;
    }

    public void setCheckPointCount(int checkPointCount) {
        this.checkPointCount = checkPointCount;
    }

    public static void setPlayerShape(PolygonShape playerShape) {
        Player.playerShape = playerShape;
    }

}
