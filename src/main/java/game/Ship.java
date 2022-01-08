package game;

import engine.ExecutionContext;
import engine.GameObject;
import engine.Script;

import java.awt.geom.Point2D;

/**
 * Represents a script
 */
public class Ship extends Script {
    private static final long NANO_TO_SECOND = 1000000000;
    private Point2D.Double velocity = new Point2D.Double(1, -1);

    public Ship() {
        super();
    }

    public Ship createCopy() {
        return new Ship();
    }

    public void update(GameObject object, ExecutionContext context) {
        var transform = object.getTransform();
        var delta = new Point2D.Double(
                velocity.getX() * context.getDeltaTime() / NANO_TO_SECOND,
                velocity.getY() * context.getDeltaTime() / NANO_TO_SECOND);
        transform.addPosition(delta);
    }
}

