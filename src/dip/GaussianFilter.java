package dip;

import java.awt.image.BufferedImage;

public class GaussianFilter extends TransformFilter {
  private double[][] matrix = {
    {1, 2, 1},
    {2, 4, 2},
    {1, 2, 1}
  };

  public GaussianFilter(BufferedImage image) {
    super(image);
    this.processPixels();
  }

  @Override
  protected int filterProcess(BufferedImage image, int x, int y) {
    int newColor = 0;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        int imageX = Math.min(image.getWidth() - 1, Math.max(0, x + i));
        int imageY = Math.min(image.getHeight() - 1, Math.max(0, y +j));

        int rgb = image.getRGB(imageX, imageY);
        int r = (rgb >> 16) & 0xFF;

        newColor += r * matrix[i][j];
      }
    }

    int alpha = (image.getRGB(x, y) >> 24) & 0xFF;
    int gray = (int) Math.round(newColor / 16);
    int newRgb = (alpha << 24) | (gray << 16) | (gray << 8) | gray;

    return newRgb;
  }
}
