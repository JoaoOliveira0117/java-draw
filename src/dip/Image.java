package dip;

import java.awt.image.BufferedImage;

public class Image {
  protected BufferedImage image;
  protected BufferedImage output;
  
  public Image(BufferedImage image) {
    this.image = image;
    this.output = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
  }

  protected void processPixels() {
    int width = this.image.getWidth();
    int height = this.image.getHeight();

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        process(this.image, i, j);
      }
    }
  }
  
  protected void process(BufferedImage image, int x, int y) {}

  public BufferedImage getOutput() {
    return output;
  }
}
