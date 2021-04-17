package engine;

import java.util.*;
import java.util.stream.Collectors;

public class GameObject {
    private List<GameComponent> components = new ArrayList<>();

    public GameObject() {

    }

    public GameObject(GameObject other) {
        components = other.components.stream()
                .map(gc -> new GameComponent(gc))
                .collect(Collectors.toList());
    }

    public void render() {
        System.out.println("GameObject: " + this);
    }
}
