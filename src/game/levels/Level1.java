package game.levels;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.*;
import game.assets.PortalSpawner;
import game.assets.Vehicle;
import game.enemy.Bastion;
import game.items.Ammo;
import game.items.GenjiStar;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class Level1 extends GameLevel {
    public static StaticBody wall;
    public Level1(Game game) {
        super(game);
        getPlayer().setPosition(new Vec2(-50,-37));;
        getGround().setFillColor(Color.LIGHT_GRAY);

        Shape box = new BoxShape(60f,3f);
        StaticBody wall2 = new StaticBody(this,box);
        wall2.setFillColor(Color.LIGHT_GRAY);
        wall2.rotateDegrees(90);
        wall2.setPosition(new Vec2(-51f,20f));

        // Create and add long platforms to the game world
        Shape platformShapeLong = new BoxShape(30f, 1.5f);
        StaticBody p2 = new StaticBody(this,platformShapeLong);
        p2.setPosition(new Vec2(106f,-52.65f));
        p2.rotate(-35);
        p2.setFillColor(Color.LIGHT_GRAY);

        StaticBody p3 = new StaticBody(this,platformShapeLong);
        p3.setPosition(new Vec2(162.6f,-65.5f));
        p3.setFillColor(Color.LIGHT_GRAY);
        // Create and add ammo to the game world
        Ammo ammo1 = new Ammo(this);
        ammo1.setPosition(new Vec2(-40f,-38f));

        GenjiStar gstar = new GenjiStar(this);
        gstar.setPosition(new Vec2(-30,-37));

        Shape wallShape = new BoxShape(60f,3f);
        wall = new StaticBody(this,wallShape);
        wall.setFillColor(Color.MAGENTA);
        wall.rotateDegrees(90);
        wall.setPosition(new Vec2(0f,20f));

        Vehicle explosionTruck = new Vehicle(this);
        explosionTruck.setPosition(new Vec2(-15,-38));


        Bastion bastion = new Bastion(this);
        bastion.removeAllImages();
        bastion.addImage(new BodyImage("data/bastionTurretMode.png",6));
        bastion.setPosition(new Vec2(150f,-58f));
        bastion.stopWalking();
        bastion.setGravityScale(200);

        Bastion bastion2 = new Bastion(this);
        bastion2.setPosition(new Vec2(20,-38));
        bastion2.stopWalking();
        bastion2.setGravityScale(200);

        // spawn a portal
        PortalSpawner ps =new PortalSpawner(this);
        this.addStepListener(ps);

        StaticBody wall3 = new StaticBody(this,box);
        wall3.setFillColor(Color.LIGHT_GRAY);
        wall3.rotateDegrees(90);
        wall3.setPosition(new Vec2(189f,-7f));
    }

    @Override
    public boolean isCompleted() {
        hint=true;
        return getPlayer().checkPointCount == 1;
    }
}
