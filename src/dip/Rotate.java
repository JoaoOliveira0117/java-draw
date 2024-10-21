package dip;

import java.awt.image.BufferedImage;

public class Rotate extends TransformImage {
  public Rotate(BufferedImage image, double angle) {
    super(image, new double[][] {
      {Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle)), 0},
      {Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)), 0},
      {0, 0, 1}
    });
  }
}
