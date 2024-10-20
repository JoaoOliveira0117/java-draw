package components;

import interfaces.IMouseMotion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class HandleComponent extends InteractableComponent {
  private static final int HANDLE_SIZE = 10;
  private IMouseMotion listener;

  public HandleComponent() {
    setSize(HANDLE_SIZE, HANDLE_SIZE);
    setOpaque(true);
    setBackground(Color.RED);
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(getBackground());
    g.fillOval(0, 0, getWidth(), getHeight());
  }

  public static int getHandleSize() {
    return HANDLE_SIZE;
  }

  public void setMouseMotionListener(IMouseMotion listener) {
    this.listener = listener;
  }

  @Override
  public void onDrag(Point initialPoint, Point finalPoint) {
    listener.onDrag(initialPoint, finalPoint);
  }
}
