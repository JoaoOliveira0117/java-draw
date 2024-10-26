package dip;

import java.awt.image.BufferedImage;

public class Grayscale extends TransformPixel {
  public Grayscale(BufferedImage image) {
    super(image);
    this.processPixels();
  }

  @Override
  protected int pixelProcess(int rgb) {
    int alpha = (rgb >> 24) & 0xFF;
    int r = (rgb >> 16) & 0xFF;
    int g = (rgb >> 8) & 0xFF;
    int b = rgb & 0xFF;

    int gray = (int) (r + g + b) / 3;

    return (alpha << 24) | (gray << 16) | (gray << 8) | gray;
  }
}
