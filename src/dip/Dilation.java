package dip;

import java.awt.image.BufferedImage;

public class Dilation extends SimpleConvolution {
  public Dilation(BufferedImage image) {
    super(image);
    this.processPixels();
  }

  @Override
  protected int computeCenter(int[][] neighbours) {
      int largest = 0;
      for (int x = 0; x < neighbours.length; x++) {
          for (int y = 0; y < neighbours[x].length; y++) {
            if (neighbours[x][y] > largest) {
                largest = neighbours[x][y];
            }
          }
      }
      return largest;
  }  
}
