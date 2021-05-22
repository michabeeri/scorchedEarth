package engine;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Implements physical attributes
 */
public class RigidBody implements GameComponent<RigidBody> {
    private double mass;
    private Point2D.Double velocity;
    private Point2D.Double acceleration;
    private boolean isEffectedByGravity;

    public RigidBody(double mass, boolean isEffectedByGravity) {
        super();
        this.mass = mass;
        this.velocity = new Point2D.Double(0, 0);
        this.acceleration = new Point2D.Double(0, 0);
        this.isEffectedByGravity = isEffectedByGravity;
    }

    public RigidBody(double mass, Point2D.Double velocity, Point2D.Double acceleration, boolean isEffectedByGravity) {
        super();
        this.mass = mass;
        this.velocity = velocity;
        this.acceleration =acceleration;
        this.isEffectedByGravity = isEffectedByGravity;
    }

    public RigidBody createCopy() {
        return new RigidBody(
            this.mass,
            this.velocity,
            this.acceleration,
            this.isEffectedByGravity
        );
    }

    public void updateVelocity(long dt){
        this.velocity.x = this.velocity.x + dt * this.acceleration.x;
        this.velocity.y = this.velocity.y + dt * this.acceleration.y;
    }

    public void updateLocation(GameObject gameObject, long dt){
        Point2D.Double position = gameObject.getTransform().getPosition();
        position.x = position.x + dt * this.velocity.x + Math.pow(dt, 2) * this.acceleration.x/2;
        position.y = position.y + dt * this.velocity.y + Math.pow(dt, 2) * this.acceleration.y/2;
    }

    public void update(GameObject gameObject, long dt)
    {
        updateLocation(gameObject, dt);
        updateVelocity(dt);
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public Point2D.Double getVelocity() {
        return velocity;
    }

    public void setVelocity(Point2D.Double velocity) {
        this.velocity = velocity;
    }

    public boolean getIsEffectedByGravity() {
        return isEffectedByGravity;
    }

    public void setIsEffectedByGravity(boolean isEffectedByGravity) { this.isEffectedByGravity = isEffectedByGravity; }

    public Point2D.Double getAcceleration() { return acceleration; }

    public void setAcceleration(Point2D.Double acceleration) {
        this.acceleration = acceleration;
    }
}