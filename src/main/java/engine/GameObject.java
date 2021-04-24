package engine;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class GameObject {
    private List<GameComponent> components = new ArrayList<>();

    public GameObject clone() {
        var clonedGameComponent = new GameObject();
        clonedGameComponent.components = components.stream()
                .map(gc -> gc.clone())
                .collect(Collectors.toList());

        return clonedGameComponent;
    }

    public void addComponent(GameComponent component) {
        components.add(component);
    }

    public <T> Stream<T> getComponentsOfType(Class<T> compClass) {
        return (Stream<T>) components.stream().filter(gc -> compClass.isInstance(gc));
    }
}
