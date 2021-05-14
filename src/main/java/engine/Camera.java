package engine;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Camera {
    private Point2D.Double position = new Point2D.Double(0, 0);
    private double ortographicSize = 5;
    private double aspectRatio = 1;
    private Rectangle2D.Double viewport = new Rectangle2D.Double(0, 0, 1, 1);

    public Camera(Point2D.Double position, double ortographicSize, double aspectRatio, Rectangle2D.Double viewport) {
        this.position = position;
        this.viewport = viewport;
        this.ortographicSize = ortographicSize;
        this.aspectRatio = aspectRatio;
    }
    public Point2D.Double getPosition() {
        return position;
    }

    public void setPosition(Point2D.Double position) {
        this.position = position;
    }

    public double getOrtographicSize() {
        return ortographicSize;
    }

    public void setOrtographicSize(double ortographicSize) {
        this.ortographicSize = ortographicSize;
    }

    public double getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public Rectangle2D.Double getViewport() {
        return viewport;
    }

    public void setViewport(Rectangle2D.Double viewport) {
        this.viewport = viewport;
    }

    public AffineTransform getWorldToScreenTransform(Dimension screenSize) {
        var worldToScreen = new AffineTransform();
        worldToScreen.scale(screenSize.height / (2 * ortographicSize), screenSize.width / (2 * ortographicSize * aspectRatio));
        return worldToScreen;
    }
}
