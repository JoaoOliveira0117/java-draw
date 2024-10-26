package dip;

import java.awt.image.BufferedImage;

public class MirrorVertically extends TransformImage {  
  public MirrorVertically(BufferedImage image) {
    super(image, new double[][] {
      {1, 0, 0},
      {0, -1, 0},
      {0, 0, 1}
    });
    this.processPixels();
  }
}
