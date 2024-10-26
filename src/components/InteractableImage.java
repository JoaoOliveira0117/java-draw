package components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import interfaces.ProcessableImageListener;

public class InteractableImage extends InteractableComponent {
    private ProcessableImage image;

    private Point prevPoint;


    public InteractableImage(BufferedImage image) {
      this.image = new ProcessableImage(image, new ProcessableImageListener() {
        @Override
        public void triggerRerender() {
          repaint();
        }
      });

      setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
    }

    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      g.drawImage(image.getImage(), 0, 0, this);
    }

    @Override
    public void onMousePressed(MouseEvent e) {
      prevPoint = e.getPoint();
    }

    @Override
    public void onMouseReleased(MouseEvent e) {
      int dx = e.getPoint().x - prevPoint.x;
      int dy = e.getPoint().y - prevPoint.y;

      repaint();
    }

		@Override
		public void onMouseRightClick(MouseEvent e) {
			ImageContextMenu.show(image, this, e.getX(), e.getY());
		}
}
