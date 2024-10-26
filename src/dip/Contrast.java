package dip;

import java.awt.image.BufferedImage;

public class Contrast extends TransformPixel {
  private double strength = 0;

  public Contrast(BufferedImage image, double strength) {
    super(image);
    this.strength = strength;
    this.processPixels();
  }

  @Override
  protected int pixelProcess(int rgb) {
    int alpha = (rgb >> 24) & 0xFF;
    int r = (rgb >> 16) & 0xFF;
    int g = (rgb >> 8) & 0xFF;
    int b = rgb & 0xFF;

    int newR = (int) Math.min(r * strength, 255);
    int newG = (int) Math.min(g * strength, 255);
    int newB = (int) Math.min(b * strength, 255);

    return (alpha << 24) | (newR << 16) | (newG << 8) | newB;
  }
}
