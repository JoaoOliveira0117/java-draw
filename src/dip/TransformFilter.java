package dip;

import java.awt.image.BufferedImage;

public class TransformFilter extends Image {
  public TransformFilter(BufferedImage image) {
    super(image);
  }

  protected int filterProcess(BufferedImage image, int x, int y) {
    return image.getRGB(x, y);
  }

  @Override
  public void process(BufferedImage image, int x, int y) {
    this.output.setRGB(x, y, filterProcess(image, x, y));
  }
}
