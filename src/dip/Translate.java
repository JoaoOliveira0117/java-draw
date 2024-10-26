package dip;

import java.awt.image.BufferedImage;

public class Translate extends TransformImage {  
  public Translate(BufferedImage image, int tx, int ty) {
    super(image, new double[][] {
      {1, 0, -tx},
      {0, 1, -ty},
      {0, 0, 1}
    });
    this.processPixels();
  }
}
