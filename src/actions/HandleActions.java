package actions;

import components.GraphicComponent;
import interfaces.IMouseMotion;

public class HandleActions {
  private GraphicComponent graphic;

  public HandleActions(GraphicComponent graphic) {
    this.graphic = graphic;
  }

  public static IMouseMotion onTopLeftDrag = (initialPoint, finalPoint) -> {
    System.out.println("Top left drag");
  };

  public static IMouseMotion onTopRightDrag = (initialPoint, finalPoint) -> {
    System.out.println("Top right drag");
  };

  public static IMouseMotion onBottomLeftDrag = (initialPoint, finalPoint) -> {
    System.out.println("Bottom left drag");
  };

  public static IMouseMotion onBottomRightDrag = (initialPoint, finalPoint) -> {
    System.out.println("Bottom right drag");
  };
}
