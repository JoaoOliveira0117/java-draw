package dip;

import java.awt.image.BufferedImage;

public class TransformFilter extends Image {
  private BufferedImage output;

  public TransformFilter(BufferedImage image) {
    super(image);
    this.output = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
    super.processPixels(image);
  }

  protected int filterProcess(BufferedImage image, int x, int y) {
    return 0;
  }

  @Override
  public void process(BufferedImage image, int x, int y) {
    output.setRGB(x, y, filterProcess(image, x, y));
  }

  public BufferedImage getOutput() {
    return output;
  }
}
