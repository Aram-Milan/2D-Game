package game.levels;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import game.Game;
import game.GameLevel;
import game.assets.JumpPad;
import game.assets.PortalSpawner;
import game.enemy.Bastion;
import game.enemy.Bombers;
import game.enemy.Boss;
import game.items.Ammo;
import game.items.GenjiStar;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class Level4 extends GameLevel {

    Boss boss;
    public Level4(Game g) {
        super(g);
        getPlayer().setPosition(new Vec2(-45,10));
        // Create and add long platforms to the game world

        getGround().setFillColor(Color.black);

        city.cs.engine.Shape platformShapeLong = new BoxShape(30f, 1.5f);
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

        boss = new Boss(this,getPlayer());
        boss.setPosition(new Vec2(5,-37));

        JumpPad jp = new JumpPad(this);
        jp.setPosition(new Vec2(-37,-38));

        Ammo ammo1 = new Ammo(this);
        ammo1.setPosition(new Vec2(-45,10));

        // spawn a portal
        PortalSpawner ps =new PortalSpawner(this);
        this.addStepListener(ps);
    }

    @Override
    public boolean isCompleted() {
        return boss.getHealth()<=0;
    }
}
