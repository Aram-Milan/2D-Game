package game.levels;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.*;
import game.assets.JumpPad;
import game.assets.PortalSpawner;
import game.enemy.Bastion;
import game.enemy.Bombers;
import game.items.Ammo;
import game.items.GenjiStar;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class Level3 extends GameLevel {
    public Level3(Game g) {
        super(g);
        getPlayer().setPosition(new Vec2(-45,10));
        getGround().setFillColor(Color.black);
        // Create and add long platforms to the game world
        Shape platformShapeLong = new BoxShape(30f, 1.5f);
        StaticBody p2 = new StaticBody(this,platformShapeLong);
        p2.setPosition(new Vec2(-52f,-10f));
        p2.rotateDegrees(90);
        p2.setFillColor(Color.black);

        StaticBody p3 = new StaticBody(this,platformShapeLong);
        p3.setPosition(new Vec2(-21f,18.5f));
        p3.setFillColor(Color.BLACK);

        StaticBody p4 = new StaticBody(this,platformShapeLong);
        p4.setPosition(new Vec2(9f,-10f));
        p4.rotateDegrees(90);
        p4.setFillColor(Color.BLACK);

        Shape platformShapeSmall = new BoxShape(5f, 1f);
        StaticBody p5 = new StaticBody(this,platformShapeSmall);
        p5.setPosition(new Vec2(-45.3f,9f));
        p5.setFillColor(Color.LIGHT_GRAY);

        StaticBody p7 = new StaticBody(this,platformShapeSmall);
        p7.setPosition(new Vec2(-45.3f,-15f));
        p7.setFillColor(Color.LIGHT_GRAY);

        StaticBody p8 = new StaticBody(this,platformShapeSmall);
        p8.setPosition(new Vec2(2.3f,-15f));
        p8.setFillColor(Color.LIGHT_GRAY);

        Shape platformShapeMedium = new BoxShape(9f, 1.5f);

        StaticBody p9 = new StaticBody(this,platformShapeMedium);
        p9.setPosition(new Vec2(-24f,-7f));
        p9.setFillColor(Color.LIGHT_GRAY);

        StaticBody p6 = new StaticBody(this,platformShapeMedium);
        p6.setPosition(new Vec2(1f,3f));
        p6.setFillColor(Color.LIGHT_GRAY);

        JumpPad jp = new JumpPad(this);
        jp.setPosition(new Vec2(-37,-38));

        Bastion bastion1 = new Bastion(this);
        bastion1.setPosition(new Vec2(-20f,-3));

        Bastion bastion2 = new Bastion(this);
        bastion2.setPosition(new Vec2(2.3f,-13));
        bastion2.stopWalking();
        bastion2.setGravityScale(150);

        GenjiStar gstar = new GenjiStar(this);
        gstar.setPosition(new Vec2(-23f,-3));

        Bombers bomber1 = new Bombers(this,getPlayer());
        bomber1.setPosition(new Vec2(-5,-38));
        Bombers bomber2 = new Bombers(this,getPlayer());
        bomber2.setPosition(new Vec2(5,-38));

        Ammo ammo1 = new Ammo(this);
        ammo1.setPosition(new Vec2(-45,10));

        // spawn portal
        PortalSpawner ps =new PortalSpawner(this);
        this.addStepListener(ps);

    }

    @Override
    public boolean isCompleted() {
        return getPlayer().checkPointCount == 3;
    }
}
