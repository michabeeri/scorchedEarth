package game;

import engine.GameObject;
import engine.Script;

import java.awt.geom.Point2D;

public class Spaceship extends Script {
    public void update(GameObject gameObject, long dt) {
        double speed = 0.001;
        Point2D.Double position = gameObject.getTransform().getPosition();
//        position.x = position.x + dt*speed;

    }
}
