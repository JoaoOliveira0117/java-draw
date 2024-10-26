package dip;

import java.awt.image.BufferedImage;

public class Roberts extends EdgeDetection {
  protected static double[][] xMatrix = {
    {0, 0, 0},
    {0, -1, 0},
    {0, 0, 1}
  };

  protected static double[][] yMatrix = {
    {0, 0, 0},
    {0, -1, 0},
    {0, 0, 1}
  };

  public Roberts(BufferedImage image, int threshold) {
    super(image, threshold, xMatrix, yMatrix);
    this.processPixels();
  }
}
