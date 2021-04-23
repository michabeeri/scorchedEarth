package viewer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.*;

public class Viewer {
    private Canvas canvas;

    public Viewer(int width, int height) {
        JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas = new Canvas(width, height);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public void setImage(BufferedImage bi) {
        canvas.setImage(bi);
    }
}

class Canvas extends JPanel {
    BufferedImage image;

    public Canvas(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
    }

    protected void paintComponent(Graphics g) {
        System.out.println(this.image);
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public void setImage(BufferedImage bi) {
        image = bi;
        repaint();
    }
}
