package engine;

import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

import viewer.*;

public class Engine {
    private Viewer viewer;
    private Camera camera;
    private List<GameObject> gameObjects = new ArrayList<>();
    private BufferedImage rendererd;
    private long elapsedTime;
    private long lastRenderTime;

    public Engine(Viewer viewer, Camera camera) {
        this.viewer = viewer;
        this.camera = camera;
    }

    public void load(Scene scene) {
        this.gameObjects = scene.copyGameObjects();
        this.elapsedTime = System.nanoTime();
        this.lastRenderTime = this.elapsedTime;
        while(true) {
            this.render();
        }
    }

    public void render() {
        var currentTime = System.nanoTime();
        var deltaTime = currentTime - this.lastRenderTime;
        this.lastRenderTime = currentTime;
        var context = new ExecutionContext(deltaTime);
        var screenbSize = viewer.getDimention();
        var renderedNext = new BufferedImage(screenbSize.width, screenbSize.height, BufferedImage.TYPE_INT_RGB);
        var graphicContext = renderedNext.createGraphics();

        gameObjects.stream()
            .forEach(gameObject -> gameObject.getComponentsOfType(Script.class)
                .forEach(script -> script.update(gameObject, context)));

        var worldToScreen = camera.getWorldToScreenTransform(screenbSize);
        gameObjects.stream()
            .sorted(Comparator.comparingDouble(go -> - go.getTransform().getZ())) // camera in on negative z, higher z values are further away from the camera
            .forEach(gameObject -> gameObject.render(graphicContext, worldToScreen));

        graphicContext.dispose();
        rendererd = renderedNext;
        viewer.setImage(renderedNext);
    }

    public String toString() {
        return gameObjects.toString();
    }

    public void gameLoop() { }
}