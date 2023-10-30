package game.player;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import game.GameLevel;
import game.GameView;
import game.levels.Level1;
import game.levels.Level2;
import game.levels.Level3;
import game.player.Player;
import org.jbox2d.common.Vec2;

// Class to track the player's position and update the view accordingly
public class PlayerTracker implements StepListener {
    private final GameView view;
    private final Player player;
    private GameLevel level;

    // Constructor to set the GameView and Player objects
    public PlayerTracker(GameView v,Player p,GameLevel l){
        this.player=p;
        this.view=v;
        level=l;
    }
    @Override
    public void preStep(StepEvent stepEvent) {
    }
    @Override
    public void postStep(StepEvent stepEvent) {
        // Called after each step of the simulation is performed
        if(level instanceof Level1) {
            view.setCentre(new Vec2(view.getX(),-20));
            if (player.getPosition().x > 55) {
                view.setCentre(new Vec2(120, -50));
            }
            if (player.getPosition().x > 160) {
                view.setCentre(new Vec2(200, -50));
            }
        }
        else if(level instanceof Level2){
            // Sets the centre of the GameView to the player's position
            view.setCentre(player.getPosition());
        }
        else if(level instanceof Level3){
            // Sets the centre of the GameView to the player's position
            view.setCentre(new Vec2(-26,-11));
        }
    }
}
