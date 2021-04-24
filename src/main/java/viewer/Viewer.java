package viewer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.*;

public class Viewer {
    private Canvas canvas;
    private Dimension dimension;

    public Viewer(Dimension dimension) {
        this.dimension = dimension;
        JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas = new Canvas(dimension);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public void setImage(BufferedImage bi) {
        canvas.setImage(bi);
    }

    public Dimension getDimention() {
        return dimension;
    }
}

class Canvas extends JPanel {
    BufferedImage image;

    public Canvas(Dimension dimension) {
        this.setPreferredSize(dimension);
    }

    protected void paintComponent(Graphics g) {
        System.out.println("viewer::paintComponent " + this.image);
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public void setImage(BufferedImage bi) {
        image = bi;
        repaint();
    }
}
