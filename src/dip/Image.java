package dip;

import java.awt.image.BufferedImage;

public class Image {
  public Image(BufferedImage image) {}

  protected void processPixels(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        process(image, i, j);
      }
    }
  }
  
  public void process(BufferedImage image, int x, int y) {}
}
