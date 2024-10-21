package dip;

import java.awt.image.BufferedImage;

public class Brightness extends TransformPixel {
  private static int value = 0;

  public Brightness(BufferedImage image) {
    super(image);
  }

  @Override
  protected int pixelProcess(int rgb) {
    int alpha = (rgb >> 24) & 0xFF;
    int r = (rgb >> 16) & 0xFF;
    int g = (rgb >> 8) & 0xFF;
    int b = rgb & 0xFF;

    int newR = r + value;
    int newG = g + value;
    int newB = b + value;

    if (newR > 255) {
      newR = 255;
    }

    if (newG > 255) {
      newG = 255;
    }

    if (newB > 255) {
      newB = 255;
    }

    return (alpha << 24) | (newR << 16) | (newG << 8) | newB;
  }
}
