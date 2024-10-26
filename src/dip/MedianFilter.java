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

    int[] redValues = new int[9];
    int count = 0;
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        int rgb = image.getRGB(x + i, y + j);
        int r = (rgb >> 16) & 0xFF;
        redValues[count++] = r;
      }
    }

    java.util.Arrays.sort(redValues);
    for(int a: redValues) {
      System.out.println(a);
    }

    int newR = redValues[redValues.length / 2];
    // Convert red to rgba
    int alpha = (image.getRGB(x, y) >> 24) & 0xFF;
    int gray = newR;
    int newRgb = (alpha << 24) | (gray << 16) | (gray << 8) | gray;

    System.out.println((newRgb >> 16 ) & 0xFF);
    return newRgb;
  }
}
