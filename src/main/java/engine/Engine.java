package engine;

import java.util.*;

public class Engine {

    private List<GameObject> gameObjects = new ArrayList<>();

    public void load(Scene scene) {
        gameObjects = scene.getGameObjects();
    }

    public void render() {
        gameObjects.stream().forEach(go -> go.render());
    }

    public String toString() {
        return gameObjects.toString();
    }

    public void gameLoop() {

    }
}
