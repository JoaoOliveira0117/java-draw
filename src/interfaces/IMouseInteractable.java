package interfaces;

import java.awt.event.MouseEvent;

public interface IMouseInteractable extends IMouseMotion {
  void onMousePressed(MouseEvent e);
  void onMouseReleased(MouseEvent e);
  void onMouseRightClick(MouseEvent e);
  void onMouseLeftClick(MouseEvent e);
}
