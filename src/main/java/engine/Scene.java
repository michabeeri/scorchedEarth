package engine;

import java.util.*;
import java.util.stream.Collectors;

public class Scene {
    private List<GameObject> gameObjects = new ArrayList<>();

    public void add(GameObject go) {
        gameObjects.add(new GameObject(go));
    }

    public List<GameObject> getGameObjects() {
        return gameObjects.stream()
                .map(go -> new GameObject(go))
                .collect(Collectors.toList());
    }
}
