package dip;

import java.awt.image.BufferedImage;

public class Erosion extends SimpleConvolution {
  public Erosion(BufferedImage image) {
    super(image);
    this.processPixels();
  }

  @Override
  protected int computeCenter(int[][] neighbours) {
      int smallest = Integer.MAX_VALUE;
      for (int x = 0; x < neighbours.length; x++) {
          for (int y = 0; y < neighbours[x].length; y++) {
            if (neighbours[x][y] < smallest) {
                smallest = neighbours[x][y];
            }
          }
      }
      return smallest;
  }  
}
