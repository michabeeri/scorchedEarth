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

    public void render(Graphics2D graphics, AffineTransform localToScreen) {
        var spriteToScreen = new AffineTransform(localToScreen);

        // normalize image, center and resize height to 1
        spriteToScreen.scale(1 / imageSpace.height, 1 / imageSpace.height);
        spriteToScreen.translate( - imageSpace.width / 2,  - imageSpace.height / 2);

        sprite.render(graphics, spriteToScreen);
    }
}

