package engine;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.util.List;
//import java.util.Map.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class GameObject {
    private List<GameComponent> components;
//    private ComponentsMap components;

    public GameObject() {
//        this.components = new ComponentsMap();
        this.components = new ArrayList<>();
        this.components.add(new Transform());
    }

    public GameObject(List<GameComponent> components) {
        this.components = components;
    }

    public GameObject createCopy() {
        return new GameObject(components.stream()
                .map(gc -> gc.createCopy())
                .collect(Collectors.toList()));
//        return new GameObject(components.createCopy());
    }

    public void addComponent(GameComponent component) {
        components.add(component);
    }

    public <T> Stream<T> getComponentsOfType(Class<T> compClass) {
        return (Stream<T>) components.stream().filter(gc -> compClass.isInstance(gc));
//        return components.get(compClass);
    }

    public <T> T getComponentOfType(Class<T> compClass) {
        return getComponentsOfType(compClass).findFirst().get();
//        return components.first(compClass);
    }

    public Transform getTransform() {
        return getComponentOfType(Transform.class);
    }

    public void setTransform(Transform transform) {
        components.set(components.indexOf(getTransform()), transform);
//        components.replace(getTransform(), transform);
    }

    public void render(Graphics2D graphics, AffineTransform worldToScreen) {
        var transform = getTransform();
        var position = transform.getPosition();
        var rotation = transform.getRotation();
        var scale = transform.getScale();
        var localToScreen = new AffineTransform(worldToScreen);

        // bring to local position rotation and scale
        localToScreen.translate(position.x, position.y);
        localToScreen.scale(scale.x, scale.y);
        localToScreen.rotate(2 * Math.PI * rotation / 360);

        getComponentsOfType(SpriteRenderer.class).forEach(spriteRenderer -> spriteRenderer.render(graphics, localToScreen));
    }
}

//class ComponentsMap {
//    private Map<Class<GameComponent>, List<GameComponent>> components;
//
//    public ComponentsMap() {
//        components = new HashMap<>();
//    }
//
//    public ComponentsMap(Map<Class<GameComponent>, List<GameComponent>> components) {
//        this.components = components;
//    }
//
//    public ComponentsMap createCopy() {
//        return new ComponentsMap(components
//            .entrySet()
//            .stream()
//            .collect(Collectors.toMap(
//                Entry::getKey,
//                e -> e.getValue()
//                    .stream()
//                    .map(gc -> gc.createCopy())
//                    .collect(Collectors.toList())
//            )));
//    }
//
//    public void add(GameComponent component) {
//        Class compClass = component.getClass();
//        List<GameComponent> componentsList = components.get(compClass);
//        if (componentsList == null) {
//            componentsList = new ArrayList<GameComponent>();
//            components.put(compClass, componentsList);
//        }
//        componentsList.add(component);
//    }
//
//    public void replace(GameComponent componentToReplace, GameComponent replacement) {
//        List<GameComponent> componentsList = get(replacement.getClass()).collect(Collectors.toList());
//        componentsList.set(componentsList.indexOf(componentToReplace), replacement);
//    }
//
//    public <T> Stream<T> get(Class<T> compClass) {
//        return (Stream<T>) components.get(compClass).stream();
//    }
//
//    public <T> T first(Class<T> compClass) {
//        return get(compClass).findFirst().get();
//    }
//}
