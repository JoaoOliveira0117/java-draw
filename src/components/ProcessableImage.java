package components;

import java.awt.image.BufferedImage;

import dip.*;
import interfaces.ProcessableImageListener;

public class ProcessableImage {
  private BufferedImage image = null;
  private ProcessableImageListener listener;

  public ProcessableImage(BufferedImage image) {
    this.image = image;
  }
  
  public ProcessableImage(BufferedImage image, ProcessableImageListener listener) {
    this.image = image;

    this.listener = listener;
  }

  public void triggerRerender() {
    if (listener!= null) {
      listener.triggerRerender();
    }
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
  
  public ProcessableImage brightness(int strength) {
    image = new Brightness(image, strength).getOutput();
    
    return this;
  }

  public ProcessableImage contrast(double strength) {
    image = new Contrast(image, strength).getOutput();
    
    return this;
  }

  public ProcessableImage medianFilter(int windowSize) {
    image = new MedianFilter(image, windowSize).getOutput();

    return this;
  }

  public ProcessableImage gaussianFilter() {
    image = new GaussianFilter(image).getOutput();

    return this;
  }

  public ProcessableImage thresholdFilter(int threshold) {
    image = new ThresholdFilter(image, threshold).getOutput();

    return this;
  }

  public ProcessableImage EdgeDetectionRoberts(int threshold) {
    image = new Roberts(image, threshold).getOutput();

    return this;
  }

  public ProcessableImage EdgeDetectionSobel(int threshold) {
    image = new Sobel(image, threshold).getOutput();

    return this;
  }
}
