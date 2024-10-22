package components;

import java.awt.image.BufferedImage;

import dip.*;

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
  
  public ProcessableImage rotate(double angle) {
    image = new Rotate(image, angle).getOutput();

    return this;
  }

  public ProcessableImage grayscale() {
    image = new Grayscale(image).getOutput();

    return this;
  }
  
  public ProcessableImage brightness() {
    image = new Brightness(image).getOutput();
    
    return this;
  }

  public ProcessableImage contrast() {
    image = new Contrast(image).getOutput();
    
    return this;
  }

  public ProcessableImage medianFilter() {
    image = new MedianFilter(image).getOutput();

    return this;
  }

  public ProcessableImage gaussianFilter() {
    image = new GaussianFilter(image).getOutput();

    return this;
  }

  public ProcessableImage thresholdFilter() {
    image = new ThresholdFilter(image).getOutput();

    return this;
  }

  public ProcessableImage EdgeDetectionRoberts() {
    image = new EdgeDetectionRoberts(image).getOutput();

    return this;
  }

  public ProcessableImage EdgeDetectionSobel() {
    image = new EdgeDetectionSobel(image).getOutput();

    return this;
  }
}
