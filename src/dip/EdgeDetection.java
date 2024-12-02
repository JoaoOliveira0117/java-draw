package dip;

import java.awt.image.BufferedImage;

public class EdgeDetection extends TransformFilter {
  protected int threshold = 128;

  protected double[][] xMatrix = {
    {0, 0, 0},
    {0, 0, 0},
    {0, 0, 0}
  };

  protected double[][] yMatrix = {
    {0, 0, 0},
    {0, 0, 0},
    {0, 0, 0}
  };

  public EdgeDetection(BufferedImage image, int threshold, double[][] xMatrix, double[][] yMatrix) {
    super(image);
    System.out.println(threshold);
    this.threshold = threshold;
    this.xMatrix = xMatrix;
    this.yMatrix = yMatrix;
  }

  @Override
  protected int filterProcess(BufferedImage image, int x, int y) {
    if (x == 0 || y == 0) {
      return image.getRGB(x, y);
    }

    if (x > image.getWidth() - 2 || y > image.getHeight() - 2) {
      return image.getRGB(x, y);
    }

    int gx = 0;
    int gy = 0;
    for (int i = 0; i < xMatrix.length; i++) {
      for (int j = 0; j < yMatrix.length; j++) {
        int imageX = Math.min(image.getWidth() - 1, Math.max(0, x + i));
        int imageY = Math.min(image.getHeight() - 1, Math.max(0, y + j));

        int rgb = image.getRGB(imageX, imageY);
        gx += rgb * xMatrix[i][j];
        gy += rgb * yMatrix[i][j];
      }
    }

    int g = (int) Math.sqrt(Math.pow(gx, 2.0) + Math.pow(gy, 2.0));
    int p = 0;

    if (g > threshold * 1000) {
      p = 255;
    }

    int alpha = (image.getRGB(x, y) >> 24) & 0xFF;
    int newRgb = (alpha << 24) | (p << 16) | (p << 8) | p;

    return newRgb;
  }
}
