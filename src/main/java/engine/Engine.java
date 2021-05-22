package engine;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import viewer.*;

public class Engine {
    private Viewer viewer;
    private Camera camera;
    private List<GameObject> gameObjects = new ArrayList<>();
    private BufferedImage rendererd;
    private Config config;

    public Engine(Viewer viewer, Camera camera, Config config) {
        this.viewer = viewer;
        this.camera = camera;
        this.config = config;
    }

    public void load(Scene scene) {
        gameObjects = scene.copyGameObjects();
    }

    public void render() {
        var screenbSize = viewer.getDimention();
        var renderedNext = new BufferedImage(screenbSize.width, screenbSize.height, BufferedImage.TYPE_INT_RGB);
        var graphicContext = renderedNext.createGraphics();

        var worldToScreen = camera.getWorldToScreenTransform(screenbSize);
        gameObjects.stream()
            .sorted(Comparator.comparingDouble(go -> - go.getTransform().getZ())) // camera in on negative z, higher z values are further away from the camera
            .forEach(gameObject -> gameObject.render(graphicContext, worldToScreen));

        graphicContext.dispose();
        rendererd = renderedNext;
        viewer.setImage(renderedNext);
    }

    public void updateScripts(long dt){
        gameObjects.stream()
            .forEach(gameObject -> gameObject.getComponentsOfType(Script.class)
                .forEach(Script -> Script.update(gameObject, dt)));
    }

    public void updateRigidBody(long dt){
        gameObjects.stream()
            .forEach(gameObject -> gameObject.getComponentsOfType(RigidBody.class)
                .forEach(RigidBody -> RigidBody.update(gameObject, dt)));
    }

    public String toString() {
        return gameObjects.toString();
    }

    public void gameLoop() {
        long prev_time = System.currentTimeMillis();
        long dt = 0;
        long delayTime_ms = Math.round(1000/this.config.getFrameRate());

        while(true)
        {
            try
            {
                Thread.sleep(delayTime_ms);
            }
            catch(InterruptedException e)
            {
                System.out.println(e);
            }

            dt = System.currentTimeMillis() - prev_time;
            prev_time = System.currentTimeMillis();

            updateRigidBody(dt);
            updateScripts(dt);
            render();
        }

    }
}