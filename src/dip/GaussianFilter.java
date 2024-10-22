package dip;

import java.awt.image.BufferedImage;

public class GaussianFilter extends TransformFilter {
  public GaussianFilter(BufferedImage image) {
    super(image);
  }

  @Override
  protected int filterProcess(BufferedImage image, int x, int y) {
    double[][] matrix = {
      {1, 2, 1},
      {2, 4, 2},
      {1, 2, 1}
    };

    if (x == 0 || y == 0) {
      return image.getRGB(x, y);
    }

    if (x > image.getWidth() - 2 || y > image.getHeight() - 2) {
      return image.getRGB(x, y);
    }

    int newColor = 0;
    for (int i = 0; i <= 2; i++) {
      for (int j = 0; j <= 2; j++) {
        int rgb = image.getRGB(x + (i-1), y + (j-1));
        int r = (rgb >> 16) & 0xFF;
        newColor += r * matrix[i][j];
      }
    }

    // Convert red to rgba
    int alpha = (image.getRGB(x, y) >> 24) & 0xFF;
    int gray = (int) Math.round(newColor / 16);
    int newRgb = (alpha << 24) | (gray << 16) | (gray << 8) | gray;

    return newRgb;
  }
}
