package dip;

import java.awt.image.BufferedImage;

public class TransformPixel extends Image {
  public TransformPixel(BufferedImage image) {
    super(image);
  }

  protected int pixelProcess(int rgb) {
    return rgb;
  }

  @Override
  public void process(BufferedImage image, int x, int y) {
    int rgb = image.getRGB(x, y);
    this.output.setRGB(x, y, pixelProcess(rgb));
  }
}
