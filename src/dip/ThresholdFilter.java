package dip;

import java.awt.image.BufferedImage;

public class ThresholdFilter extends TransformFilter {
  private int threshold = 128;

  public ThresholdFilter(BufferedImage image, int threshold) {
    super(image);
    this.threshold = threshold;
    this.processPixels();
  }

  @Override
  protected int filterProcess(BufferedImage image, int x, int y) {
    int rgb = image.getRGB(x, y);
    int r = (rgb >> 16) & 0xFF;

    int newColor = (r > threshold)? 0xFFFFFF : 0xFF000000;
    return newColor;
  }
}
