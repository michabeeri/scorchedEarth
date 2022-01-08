package engine;

import java.awt.geom.Point2D;

public class Transform implements GameComponent<Transform>{
    private Point2D.Double position;
    private Point2D.Double scale;
    private double rotation;
    private double z;

    public Transform() {
        position = new Point2D.Double(0, 0);
        scale = new Point2D.Double(1, 1);
        rotation = 0;
        z = 0;
    }

    public Transform(Point2D.Double position, Point2D.Double scale, double rotation, double z) {
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
        this.z = z;
    }

    public Transform createCopy() {
        return new Transform(
            new Point2D.Double(position.x, position.y),
            new Point2D.Double(scale.x, scale.y),
            rotation, z);
    }

    public Point2D.Double getPosition() {
        return position;
    }

    public void setPosition(Point2D.Double position) {
        this.position = position;
    }

    public void addPosition(Point2D.Double deltaPosition) {
        this.position = new Point2D.Double(this.position.getX() + deltaPosition.getX(), this.position.getY() + deltaPosition.getY());
    }

    public Point2D.Double getScale() {
        return scale;
    }

    public void setScale(Point2D.Double scale) {
        this.scale = scale;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
