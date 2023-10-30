package game.levels;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import game.*;
import game.assets.JumpPad;
import game.assets.Lift;
import game.assets.PortalSpawner;
import game.enemy.Bastion;
import game.items.Ammo;
import game.items.GenjiStar;
import game.items.HealthPack;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class Level2 extends GameLevel {
    public static StaticBody p7;
    public static boolean shorterPlatformsState = false;

    public Level2(Game game) {
        super(game); // call super class constructor

        getPlayer().setPosition(new Vec2(-2f, -38f));
        getGround().setFillColor(Color.LIGHT_GRAY);
        // Create and add lifts to the game world
        Lift lift = new Lift(this);
        lift.setPosition(new Vec2(-15, -9));
        lift.setFillColor(new Color(196,113,56));
        Lift lift2 = new Lift(this);
        lift2.setPosition(new Vec2(9, -2));
        lift2.setFillColor(new Color(196,113,56));

        // Create and add a shorter platform to the game world
        Shape platformShapeShorter = new BoxShape(3f, 0.8f);
        p7 = new StaticBody(this, platformShapeShorter);
        p7.setPosition(new Vec2(40f, -38f));
        p7.setFillColor(Color.RED);

        Shape box = new BoxShape(60f,3f);
        StaticBody wall2 = new StaticBody(this,box);
        wall2.setFillColor(Color.LIGHT_GRAY);
        wall2.rotateDegrees(90);
        wall2.setPosition(new Vec2(-80f,18.5f));

        StaticBody wall3 = new StaticBody(this,box);
        wall3.setFillColor(Color.LIGHT_GRAY);
        wall3.rotateDegrees(90);
        wall3.setPosition(new Vec2(50f,19f));

        GenjiStar gstar = new GenjiStar(this);
        gstar.setPosition(new Vec2(-30,-38));

        // Create and add long platforms to the game world
        Shape platformShapeLong = new BoxShape(15f, 0.8f);
        StaticBody p2 = new StaticBody(this, platformShapeLong);
        p2.setPosition(new Vec2(-2, 12));
        p2.setFillColor(new Color(196,113,56));
        StaticBody p3 = new StaticBody(this, platformShapeLong);
        p3.setPosition(new Vec2(-65, -20));
        p3.setFillColor(new Color(196,113,56));

        // Create and add enemies to the game world
        Bastion bastion2 = new Bastion(this);
        bastion2.setPosition(new Vec2(-65f, -17f));

        Bastion bastion3 = new Bastion(this);
        bastion3.setPosition(new Vec2(-15f, -7f));
        bastion3.stopWalking();

        Bastion bastion4 = new Bastion(this);
        bastion4.setPosition(new Vec2(9f, -1f));
        bastion4.stopWalking();

        Bastion bastion5 = new Bastion(this);
        bastion5.setPosition(new Vec2(-7f, 13f));
        bastion5.removeAllImages();
        bastion5.addImage(new BodyImage("data/bastionFlip.gif", 4));


        // Create and add ammo to the game world
        Ammo ammo1 = new Ammo(this);
        ammo1.setPosition(new Vec2(20f, -38f));
        Ammo ammo2 = new Ammo(this);
        ammo2.setPosition(new Vec2(-75f, -18f));
        Ammo ammo3 = new Ammo(this);
        ammo3.setPosition(new Vec2(-5f, 13f));

        // Create and add health packs to the game world
        HealthPack healthP1 = new HealthPack(this);
        healthP1.setPosition(new Vec2(40, -37));
        HealthPack healthP2 = new HealthPack(this);
        healthP2.setPosition(new Vec2(8, +13));

        // spawn a portal
        PortalSpawner ps =new PortalSpawner(this);
        this.addStepListener(ps);

        // Create and add jump pads to the game world
        JumpPad jumpPad1 = new JumpPad(this);
        jumpPad1.setPosition(new Vec2(-43f, -38f));
        //the game has gravity
        setGravity(50);
    }

    @Override
    public boolean isCompleted() {
        hint=false;
        return getPlayer().checkPointCount == 2;
    }
}
