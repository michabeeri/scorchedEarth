package engine;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class GameObject {
    private List<GameComponent> components = new ArrayList<>();
    private Transform transform = new Transform();

    public GameObject clone() {
        var clonedGameComponent = new GameObject();
        clonedGameComponent.components = components.stream()
                .map(gc -> gc.clone())
                .collect(Collectors.toList());

        clonedGameComponent.transform = transform.clone();
        return clonedGameComponent;
    }

    public void addComponent(GameComponent component) {
        components.add(component);
    }

    public <T> Stream<T> getComponentsOfType(Class<T> compClass) {
        return (Stream<T>) components.stream().filter(gc -> compClass.isInstance(gc));
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public void render(Graphics2D graphics, AffineTransform worldToScreen) {
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
