package game.enemy;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Bastion extends Walker implements StepListener {
    private static final PolygonShape enemyShape = new PolygonShape(-2.27f,2.86f, 2.26f,2.84f, 2.28f,-2.95f, -2.26f,-2.95f);
    private static final BodyImage image = new BodyImage("data/bastion.gif", 6);
    public static int dead=0;
    private final int speed = 4;
    private final int range = 3;
    private float left, right;
    boolean facingRight;
    float projectileSpeed = 30;
    private int counter=0;
    public Bastion(World world) {
        super(world,enemyShape); // Call the constructor for the superclass (Walker) and pass in the world and polygon shape
        addImage(image);
        world.addStepListener(this); // Add the StepListener to the world
        startWalking(speed);
    }

    @Override
    public void setPosition(Vec2 position) { // Override the setPosition method from the superclass
        super.setPosition(position);
        left = position.x - range; // Set the left range of movement
        right = position.x + range;  // Set the right range of movement
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        counter++;
        if(counter%50==0) // If the counter is divisible by 60 (1 second has passed)
            enemyShoot();

        if (getPosition().x > right) { // If the Enemy has moved too far right
            startWalking(-speed);
            removeAllImages();
            addImage(new BodyImage("data/bastion.gif", 6)); // Add the BodyImage for the Enemy facing right
            facingRight = false;

        }
        if (getPosition().x < left) { // If the Enemy has moved too far left
            startWalking(speed);
            removeAllImages();
            addImage(new BodyImage("data/bastionFlip.gif", 6)); // Add the BodyImage for the Enemy facing left
            facingRight = true;
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }

    public void enemyShoot() {
        DynamicBody projectile = new DynamicBody(this.getWorld(), new PolygonShape(-0.683f,0.328f, 0.188f,0.323f, 0.188f,-0.016f, -0.693f,-0.01f));

        //This creates an instance of the class EnemyProjectileImpact which implements CollisionListener.
        EnemyProjectileImpact impact = new EnemyProjectileImpact();

        // The projectile is then set to listen for collisions with this impact object.
        projectile.addCollisionListener(impact);

        projectile.setGravityScale(0.01f);

        //This sets the position and velocity of the projectile based on the orientation of the enemy.
        if (facingRight) {
            projectile.addImage(new BodyImage("data/enemybulletFlip.png",2f));
            projectile.setPosition(new Vec2(this.getPosition().x + 3, this.getPosition().y));
            projectile.setLinearVelocity(new Vec2(projectileSpeed, 0));
        }
        else{
            projectile.addImage(new BodyImage("data/enemybullet.png",2f));
            projectile.setPosition(new Vec2(this.getPosition().x - 3, this.getPosition().y));
            projectile.setLinearVelocity(new Vec2(-projectileSpeed, 0));
        }
    }
}
