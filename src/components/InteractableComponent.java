package components;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import interfaces.IMouseInteractable;

public class InteractableComponent extends JComponent implements IMouseInteractable {
  private Point initialClick;

  public InteractableComponent() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
        onMousePressed(e);
        
				if (SwingUtilities.isRightMouseButton(e)) {
					onMouseRightClick(e);
				} else {
          onMouseLeftClick(e);
				}

				initialClick = e.getPoint();
			}

      @Override
      public void mouseReleased(MouseEvent e) {
        onMouseReleased(e);
      }
		});

    addMouseMotionListener(new MouseAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        if(initialClick != null) {
          onDrag(initialClick, e.getPoint());
        }
      }
    });
  }

  @Override
  public void onMousePressed(MouseEvent e) {}

  @Override
  public void onMouseReleased(MouseEvent e) {}

  @Override
  public void onDrag(Point initialPoint, Point finalPoint) {}

  @Override
  public void onMouseRightClick(MouseEvent e) {}

  @Override
  public void onMouseLeftClick(MouseEvent e) {}
}
