package engine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * represents a sprite sheet image
 */
public class Sprite {
    private BufferedImage image;

    private Sprite(BufferedImage image) {
        this.image = image;
    }

    /**
     * Renders from the sprite sheet to a graphics2D
     * @param graphics - Graphics2D to render into
     * @param transform - manipulate the rendering
     */
    public void render(Graphics2D graphics, AffineTransform transform) {
        graphics.drawRenderedImage(this.image, transform);
    }

    public static Sprite load(String filename) throws SpriteLoadException {
        var file = new File(filename);
        if (!file.exists()) {
            throw new SpriteLoadException("File does not exist");
        }
        if (!file.canRead()) {
            throw new SpriteLoadException("Failed to read file");
        }

        try {
            BufferedImage image = ImageIO.read(file);
            return new Sprite(image);
        } catch (IOException e) {
            throw new SpriteLoadException(e);
        }
    }

//    https://stackoverflow.com/questions/3514158/how-do-you-clone-a-bufferedimage
//    public static BufferedImage copyImage(BufferedImage source){
//        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
//        Graphics g = b.getGraphics();
//        g.drawImage(source, 0, 0, null);
//        g.dispose();
//        return b;
//    }

}

class SpriteLoadException extends IOException {
    public SpriteLoadException(String message) {
        super(message);
    }

    public SpriteLoadException(IOException e) {
        super(e);
    }
}

