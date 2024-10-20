package components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class DrawableCanvas extends JPanel {

    public DrawableCanvas(int width, int height) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE);
    }

    public void addImage(BufferedImage image) {
        InteractableImage imageComponent = new InteractableImage(image);

        this.add(imageComponent, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
