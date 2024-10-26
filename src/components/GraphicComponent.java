package components;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import actions.HandleActions;
import interfaces.IMouseMotion;

public class GraphicComponent extends MovableComponent {
  private ArrayList<HandleComponent> handles = new ArrayList<HandleComponent>();
  private InteractableImage graphic;
  private HandleActions handleActions;

  public GraphicComponent(InteractableImage graphic) {
    super();

    this.graphic = graphic;
    handleActions = new HandleActions(this);
    
    setSize(getGraphicPreferredSize());
    graphic.setLocation(getGraphicLocation());

    updateHandles();
    add(graphic);
    repaint();
  }

  private int getHandleSize() {
    return HandleComponent.getHandleSize();
  }

  private Point getGraphicLocation() {
    int HANDLE_OFFSET = getHandleSize() / 2;
    return new Point(HANDLE_OFFSET, HANDLE_OFFSET);
  }

  private Dimension getGraphicPreferredSize() {
    int HANDLE_OFFSET = getHandleSize();
    return new Dimension(graphic.getWidth() + HANDLE_OFFSET, graphic.getHeight() + HANDLE_OFFSET);
  }

  private HandleComponent createHandle(int x, int y, IMouseMotion action) {
    HandleComponent handle = new HandleComponent();
    handle.setLocation(x, y);
    handle.setMouseMotionListener(action);
    return handle;
  }

  @SuppressWarnings("static-access")
  private void updateHandles() {
    int handleSize = getHandleSize();
    int width = getWidth();
    int height = getHeight();

    
    handles.clear();
    handles.add(createHandle(0, 0, handleActions.onTopLeftDrag));
    handles.add(createHandle(width - handleSize, 0, handleActions.onTopRightDrag));
    handles.add(createHandle(0, height - handleSize, handleActions.onBottomLeftDrag));
    handles.add(createHandle(width - handleSize, height - handleSize, handleActions.onBottomRightDrag));

    for (HandleComponent handle : handles) {
      add(handle);
    }
  }
}
