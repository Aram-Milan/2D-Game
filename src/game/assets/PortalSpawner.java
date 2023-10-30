package game.assets;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import game.GameLevel;
import game.enemy.Bastion;
import game.levels.Level1;
import game.levels.Level2;
import game.levels.Level3;
import org.jbox2d.common.Vec2;

public class PortalSpawner implements StepListener {
    private GameLevel level;
    public PortalSpawner(GameLevel l) {
        this.level=l;
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if(Bastion.dead==2 && level instanceof Level1){
            Portal cp1 =new Portal(level,level,level.game);
            cp1.setPosition(new Vec2(180f,-61f));

        }if(Bastion.dead==4 && level instanceof Level2){
            Portal cp1 =new Portal(level,level,level.game);
            cp1.setPosition(new Vec2(10,16f));
        }
        if(Bastion.dead==4 && level instanceof Level3){
            Portal cp1 =new Portal(level,level,level.game);
            cp1.setPosition(new Vec2(3f, 11f));
        }
    }
    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
