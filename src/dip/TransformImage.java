package dip;

import java.awt.image.BufferedImage;

public class TransformImage extends Image {
  private final double[][] matrix;

  public TransformImage(BufferedImage image, double[][] matrix) {
    super(image);
    this.matrix = matrix;
  }

  @Override
  public void process(BufferedImage image, int x, int y) {
    int halfX = image.getWidth() / 2;
    int halfY = image.getHeight() / 2;
    int tmpX = x - halfX;
    int tmpY = y - halfY;
    int newX = (int) Math.round(tmpX * matrix[0][0] + tmpY * matrix[0][1] + 1 * matrix[0][2]);
    int newY = (int) Math.round(tmpX * matrix[1][0] + tmpY * matrix[1][1] + 1 * matrix[1][2]);
    newX += halfX;
    newY += halfY;
    
    if (newX < image.getWidth() && newY < image.getHeight() && newX >= 0 && newY >= 0) {
      output.setRGB(x, y, image.getRGB(newX, newY));
    }
  }
}
