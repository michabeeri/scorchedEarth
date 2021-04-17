package engine;

import java.util.*;

public class Engine {

    private List<GameObject> gameObjects = new ArrayList<>();

    public void load(Scene scene) {
        gameObjects = scene.getGameObjects();
        render();
    }

    public void render() {
        gameObjects.stream().forEach(go -> go.render());
    }
    public void gameLoop() {

    }
}
