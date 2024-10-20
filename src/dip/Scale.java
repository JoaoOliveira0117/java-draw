package dip;

import java.awt.image.BufferedImage;

public class Scale extends TransformImage {  
  public Scale(BufferedImage image, double sx, double sy) {
    super(image, new double[][] {
      {sx, 0, 0},
      {0, sy, 0},
      {0, 0, 1}
    });
  }
}
