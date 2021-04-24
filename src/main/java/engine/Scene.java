package engine;

import java.util.*;
import java.util.stream.Collectors;

public class Scene {
    private List<GameObject> gameObjects = new ArrayList<>();

    public void add(GameObject go) {
        gameObjects.add(go);
    }

    public List<GameObject> copyGameObjects() {
        return gameObjects.stream()
                .map(go -> go.clone())
                .collect(Collectors.toList());
    }
}
