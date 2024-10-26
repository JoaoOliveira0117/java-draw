package dip;

import java.awt.image.BufferedImage;

public class Sobel extends EdgeDetection {
  protected static double[][] xMatrix = {
    {1, 0, -1},
    {2, 0, -2},
    {1, 0, -1}
  };

  protected static double[][] yMatrix = {
    {1, 2, 1},
    {0, 0, 0},
    {-1, -2, -1}
  };

  public Sobel(BufferedImage image, int threshold) {
    super(image, threshold, xMatrix, yMatrix);
    this.processPixels();
  }
}
