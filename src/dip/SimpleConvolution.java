package dip;

import java.awt.image.BufferedImage;

public abstract class SimpleConvolution extends TransformFilter {

  public SimpleConvolution(BufferedImage image) {
    super(image);
  }
  @Override
protected int filterProcess(BufferedImage image, int x, int y) {
    int[][] neighbours = new int[3][3];

    // Coleta os vizinhos em torno do pixel (x, y)
    for (int nx = 0; nx < 3; nx++) {
        for (int ny = 0; ny < 3; ny++) {
            int newX = Math.min(image.getWidth() - 1, Math.max(0, x + nx - 1));
            int newY = Math.min(image.getHeight() - 1, Math.max(0, y + ny - 1));
            int rgb = image.getRGB(newX, newY);

            // Pega a intensidade de cinza (assumindo que a imagem é em escala de cinza)
            int gray = (rgb >> 16) & 0xFF; // Ou pode usar qualquer componente (R, G ou B) já que são iguais
            neighbours[nx][ny] = gray;
        }
    }

    // Obtém o valor máximo (dilatação)
    int color = computeCenter(neighbours);
    int alpha = (image.getRGB(x, y) >> 24) & 0xFF; // Mantém o canal alpha

    // Cria o novo valor RGB com a intensidade máxima
    return (alpha << 24) | (color << 16) | (color << 8) | color;
}

  protected abstract int computeCenter(int[][] neighbours);
}
