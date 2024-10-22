package dip;

import java.awt.image.BufferedImage;

public class EdgeDetectionSobel extends TransformFilter {
  public EdgeDetectionSobel(BufferedImage image) {
    super(image);
  }

  @Override
  protected int filterProcess(BufferedImage image, int x, int y) {
    double[][] xMatrix = {
      {1, 0, -1},
      {2, 0, -2},
      {1, 0, -1}
    };

    double[][] yMatrix = {
      {1, 2, 1},
      {0, 0, 0},
      {-1, -2, -1}
    };

    if (x == 0 || y == 0) {
      return image.getRGB(x, y);
    }

    if (x > image.getWidth() - 2 || y > image.getHeight() - 2) {
      return image.getRGB(x, y);
    }

    int gx = 0;
    int gy = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int rgb = image.getRGB(x + (i-1), y + (j-1));
        gx += rgb * xMatrix[i][j];
        gy += rgb * yMatrix[i][j];
      }
    }

    int g = (int) Math.sqrt(Math.pow(gx, 2.0) + Math.pow(gy, 2.0));
    int p = 0;

    if (g > 2000000) {
      p = 255;
    }

    int alpha = (image.getRGB(x, y) >> 24) & 0xFF;
    int newRgb = (alpha << 24) | (p << 16) | (p << 8) | p;

    return newRgb;
  }
}
