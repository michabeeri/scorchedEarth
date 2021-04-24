package engine;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

import viewer.*;

public class Engine {
    private Viewer viewer;
    private List<GameObject> gameObjects = new ArrayList<>();
    private BufferedImage rendererd;

    public Engine(Viewer viewer) {
        this.viewer = viewer;
    }

    public void load(Scene scene) {
        gameObjects = scene.copyGameObjects();
    }

    public void render() {
        var dimension = viewer.getDimention();
        var renderedNext = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_RGB);
        var graphicContext = renderedNext.createGraphics();

        var userSpace = new Rectangle2D.Double(0, 0, dimension.width, dimension.height);
        gameObjects.stream()
//            .sorted((go1, go2) -> Double.compare(go1.z, go2.z))
            .flatMap(gameObject -> gameObject.getComponentsOfType(SpriteRenderer.class))
            .forEach(spriteRenderer -> spriteRenderer.render(graphicContext, userSpace));

        graphicContext.dispose();
        rendererd = renderedNext;
        viewer.setImage(renderedNext);
    }

    public String toString() {
        return gameObjects.toString();
    }

    public void gameLoop() { }
}