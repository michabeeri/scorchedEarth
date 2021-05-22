package engine;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Config {
    private double gravity;
    private double frameRate;

    public Config() {
        this.gravity = 0;
        this.frameRate = 60;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(double frameRate) {
        this.frameRate = frameRate;
    }
}
