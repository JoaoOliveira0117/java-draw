package components;

import java.awt.image.BufferedImage;

import dip.MirrorHorizontally;
import dip.MirrorVertically;
import dip.Scale;
import dip.Translate;

public class ProcessableImage {
  private BufferedImage image = null;

  public ProcessableImage(BufferedImage image) {
    this.image = image;
  }

  public BufferedImage getImage() {
    return image;
  }

  public void setImage(BufferedImage image) {
    this.image = image;
  }

  public ProcessableImage mirrorHorizontally() {
    image = new MirrorHorizontally(image).getOutput();

    return this;
  }

  public ProcessableImage mirrorVertically() {
    image = new MirrorVertically(image).getOutput();

    return this;
  }

  public ProcessableImage translate(int x, int y) {
    image = new Translate(image, x, y).getOutput();

    return this;
  }

  public ProcessableImage scale(double factorX, double factorY) {
    image = new Scale(image, factorX, factorY).getOutput();

    return this;
  }
}
