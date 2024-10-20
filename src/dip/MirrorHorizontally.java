package dip;

import java.awt.image.BufferedImage;

public class MirrorHorizontally extends TransformImage {  
  public MirrorHorizontally(BufferedImage image) {
    super(image, new double[][] {
      {-1, 0, 0},
      {0, 1, 0},
      {0, 0, 1}
    });
  }
}
