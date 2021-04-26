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

    public Engine(Viewer viewer, Camera camera) {
        this.viewer = viewer;
        this.camera = camera;
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

    public String toString() {
        return gameObjects.toString();
    }

    public void gameLoop() { }
}