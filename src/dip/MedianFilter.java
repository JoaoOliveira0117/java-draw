package dip;

import java.awt.image.BufferedImage;

public class MedianFilter extends TransformFilter {
  private int windowSize = 3;

  public MedianFilter(BufferedImage image, int windowSize) {
    super(image);
    this.windowSize = windowSize;
    this.processPixels();
  }

  @Override
  protected int filterProcess(BufferedImage image, int x, int y) {
    int halfWindow = windowSize / 2;
    int[] colorValues = new int[windowSize * windowSize];
    int count = 0;

    for (int i = -halfWindow; i <= halfWindow; i++) {
      for (int j = -halfWindow; j <= halfWindow; j++) {
        int imageX = Math.min(image.getWidth() - 1, Math.max(0, x + i));
        int imageY = Math.min(image.getHeight() - 1, Math.max(0, y +j));

        int rgb = image.getRGB(imageX, imageY);
        int r = (rgb >> 16) & 0xFF;

        colorValues[count++] = r;
      }
    }

    java.util.Arrays.sort(colorValues);

    int alpha = (image.getRGB(x, y) >> 24) & 0xFF;
    int newColor = colorValues[colorValues.length / 2];
    int newRgb = (alpha << 24) | (newColor << 16) | (newColor << 8) | newColor;

    return newRgb;
  }
}
