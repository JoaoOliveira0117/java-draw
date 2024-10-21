package dip;

import java.awt.image.BufferedImage;

public class TransformPixel extends Image {
  private BufferedImage output;

  public TransformPixel(BufferedImage image) {
    super(image);
    this.output = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
    super.processPixels(image);
  }

  protected int pixelProcess(int rgb) {
    return rgb;
  }

  @Override
  public void process(BufferedImage image, int x, int y) {
    int rgb = image.getRGB(x, y);

    output.setRGB(x, y, pixelProcess(rgb));
  }

  public BufferedImage getOutput() {
    return output;
  }
}
