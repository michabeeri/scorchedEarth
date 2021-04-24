package engine;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class SpriteRenderer extends GameComponent{
    private Sprite sprite;
    private Rectangle2D.Double imageSpace;

    public SpriteRenderer(Sprite sprite, Rectangle2D.Double imageSpace) {
        super();
        this.sprite = sprite;
        this.imageSpace = imageSpace;
    }

    public SpriteRenderer clone() {
        return new SpriteRenderer(
            sprite,
            new Rectangle2D.Double(imageSpace.x, imageSpace.y, imageSpace.width, imageSpace.height)
        );
    }

    public void render(Graphics2D graphics, Rectangle2D.Double userSpace) {
        var transform = new AffineTransform();
        transform.scale(userSpace.width / imageSpace.width, userSpace.height / imageSpace.height);
        transform.translate(userSpace.x - imageSpace.x, userSpace.y - imageSpace.y);
        sprite.render(graphics, transform);
    }
}

