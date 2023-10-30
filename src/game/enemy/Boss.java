package game.enemy;

import city.cs.engine.*;
import game.player.Player;
import org.jbox2d.common.Vec2;

public class Boss extends Walker implements StepListener {
    private static final PolygonShape bossShape = new PolygonShape(-1.67f,3.17f, 1.45f,3.15f, 4.5f,-0.39f, 2.75f,-3.44f, -1.75f,-3.44f, -3.78f,-0.37f);
    private static final BodyImage bossImageRight = new BodyImage("data/bossRight.gif", 10);
    private static final BodyImage bossImageLeft = new BodyImage("data/bossLeft.gif", 10);
    private int health;
    private final int speed = 4;
    boolean facingRight;
    float projectileSpeed = 30;
    private int counter=0;

    private Player player;

    public Boss(World world, Player p) {
        super(world,bossShape); // Call the constructor for the superclass (Walker) and pass in the world and polygon shape
        addImage(bossImageLeft);
        world.addStepListener(this); // Add the StepListener to the world
        startWalking(speed);
        player=p;
        health=50;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void preStep(StepEvent stepEvent) {
    }

    @Override
    public void postStep(StepEvent stepEvent) {
        counter++;
        if(counter%60==0) // If the counter is divisible by 60 (1 second has passed)
            enemyShoot();

        if((this.getPosition().x - player.getPosition().x) < 0) {
            startWalking(speed);
            removeAllImages();
            addImage(bossImageRight); // Add the BodyImage for the Enemy facing right
            facingRight = true;
        }
        if((this.getPosition().x - player.getPosition().x) > 0) {
            startWalking(-speed);
            removeAllImages();
            addImage(bossImageLeft); // Add the BodyImage for the Enemy facing right
            facingRight = false;
        }
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
            projectile.addImage(new BodyImage("data/greenCircleBullet.gif",2f));
            projectile.setPosition(new Vec2(this.getPosition().x + 5, this.getPosition().y));
            projectile.setLinearVelocity(new Vec2(projectileSpeed, 0));
        }
        else{
            projectile.addImage(new BodyImage("data/greenCircleBullet.gif",2f));
            projectile.setPosition(new Vec2(this.getPosition().x - 5, this.getPosition().y));
            projectile.setLinearVelocity(new Vec2(-projectileSpeed, 0));
        }
    }
}
