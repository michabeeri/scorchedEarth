package engine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {
    private String filename;
    private BufferedImage image;

    public Sprite(String filename) {
        this.filename = filename;
    }

    public void render(Graphics2D graphics, AffineTransform transform) {
        graphics.drawRenderedImage(this.image, transform);
    }

    public void load() throws SpriteLoadException {
        if (image != null) {
            throw new SpriteLoadException("Sprite already loaded");
        }

        var file = new File(filename);
        if (!file.exists()) {
            throw new SpriteLoadException("File does not exist");
        }
        if (!file.canRead()) {
            throw new SpriteLoadException("Failed to read file");
        }

        try {
            image = ImageIO.read(file); //new FileInputStream(file) is not required ?, maybe happens by default
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

