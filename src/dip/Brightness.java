package dip;

import java.awt.image.BufferedImage;

public class Brightness extends TransformPixel {
  private int strength = 0;

  public Brightness(BufferedImage image, int strength) {
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

    int newR = Math.max(Math.min(r + strength, 255), 0);
    int newG = Math.max(Math.min(g + strength, 255), 0);
    int newB = Math.max(Math.min(b + strength, 255), 0);

    return (alpha << 24) | (newR << 16) | (newG << 8) | newB;
  }
}
