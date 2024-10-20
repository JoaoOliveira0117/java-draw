package components;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class MovableComponent extends InteractableComponent {
  protected boolean isSelected = false;
  @Override
  public void onMouseLeftClick(MouseEvent e) {
    this.isSelected = true;
  }

  @Override
  public void onDrag(Point initialPoint, Point finalPoint) {
    if (isSelected) {
      int deltaX = finalPoint.x - initialPoint.x;
      int deltaY = finalPoint.y - initialPoint.y;
      setLocation(getX() + deltaX, getY() + deltaY);
    }
  }
}
