package game.assets;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

//The class Lift is defined, which extends the StaticBody class
// and implements the StepListener interface to listen to each step event.
public class Lift extends StaticBody implements StepListener {
    private static final Shape liftShape = new BoxShape(5f, 0.8f);
    private float delta;
    public Lift(World w) {
        super(w, liftShape);
        delta=0.08f; // We use delta for the speed of lifts
        w.addStepListener(this); //The StepListener is added to the World.
    }
    @Override
    public void preStep(StepEvent stepEvent) {
        if (getPosition().y < -10){
            delta*=-1;
        }
        if (getPosition().y > -1){
            delta*=-1;
        }
        //The Lift is moved by calling the setPosition() method and adding the delta value to the y position.
        this.setPosition(new Vec2(this.getPosition().x, this.getPosition().y+delta));
    }
    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
