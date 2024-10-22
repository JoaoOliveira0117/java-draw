package dip;

import java.awt.image.BufferedImage;

public class MedianFilter extends TransformFilter {
  public MedianFilter(BufferedImage image) {
    super(image);
  }

  @Override
  protected int filterProcess(BufferedImage image, int x, int y) {
    if (x == 0 || y == 0) {
      return image.getRGB(x, y);
    }

    if (x > image.getWidth() - 2 || y > image.getHeight() - 2) {
      return image.getRGB(x, y);
    }

    int[] rgbValues = new int[9];
    int count = 0;
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        rgbValues[count++] = image.getRGB(x + i, y + j);
      }
    }

    java.util.Arrays.sort(rgbValues);
    return rgbValues[rgbValues.length / 2];
  }
}
