package engine;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.util.List;
import java.util.Map.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class GameObject {
    private ComponentsMap components;

    public GameObject() {
        this.components = new ComponentsMap();
        this.components.add(new Transform());
    }

    public GameObject(ComponentsMap components) {
        this.components = components;
    }

    public GameObject createCopy() {
        return new GameObject(components.createCopy());
    }

    public void addComponent(GameComponent component) {
        components.add(component);
    }

    public void addScript(Script script) {
        components.add(script, Script.class);
    }

    public <T> Stream<T> getComponentsOfType(Class<T> compClass) {
        return components.get(compClass);
    }

    public <T> T getComponentOfType(Class<T> compClass) {
        return components.first(compClass);
    }

    public Transform getTransform() {
        return getComponentOfType(Transform.class);
    }

    public void setTransform(Transform transform) {
        components.replace(getTransform(), transform);
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

class ComponentsMap {
    private Map<Class<GameComponent>, List<GameComponent>> components;

    public ComponentsMap() {
        components = new HashMap<>();
    }

    public ComponentsMap(Map<Class<GameComponent>, List<GameComponent>> components) {
        this.components = components;
    }

    public ComponentsMap createCopy() {
        return new ComponentsMap(components
            .entrySet()
            .stream()
            .collect(Collectors.toMap(
                Entry::getKey,
                e -> e.getValue()
                    .stream()
                    .map(gc -> gc.createCopy())
                    .collect(Collectors.toList())
            )));
    }

    public void add(GameComponent component, Class compClass) {
        List<GameComponent> componentsList = components.get(compClass);
        if (componentsList == null) {
            componentsList = new ArrayList<>();
            components.put(compClass, componentsList);
        }
        componentsList.add(component);
    }

    public void add(GameComponent component) {
        Class compClass = component.getClass();
        this.add(component, compClass);
    }

    public void replace(GameComponent componentToReplace, GameComponent replacement) {
        List<GameComponent> componentsList = components.get(replacement.getClass());
        componentsList.set(componentsList.indexOf(componentToReplace), replacement);
    }

    public <T> Stream<T> get(Class<T> compClass) {
        var comps = components.get(compClass);
        return comps != null ? (Stream<T>)comps.stream() : Stream.empty();
    }

    public <T> T first(Class<T> compClass) {
        return get(compClass).findFirst().get();
    }

    public void remove(GameComponent component) {
        components.get(component.getClass()).remove(component);
    }
}
