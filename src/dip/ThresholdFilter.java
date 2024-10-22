package dip;

import java.awt.image.BufferedImage;

public class ThresholdFilter extends TransformFilter {
  public ThresholdFilter(BufferedImage image) {
    super(image);
  }

  @Override
  protected int filterProcess(BufferedImage image, int x, int y) {
    int rgb = image.getRGB(x, y);
    int r = (rgb >> 16) & 0xFF;

    int newColor = (r > 200)? 0xFFFFFFFF : 0xFF000000;
    return newColor;
  }
}
