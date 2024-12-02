package dip;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import components.Circle;
import components.ProcessableImage;

public class MoedaDetector {
  private BufferedImage result;

  public MoedaDetector(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();

    ProcessableImage processableImage = new ProcessableImage(image);

    BufferedImage edgeImage = processableImage.gaussianFilter().medianFilter(7).thresholdFilter(240)
        .EdgeDetectionSobel(240).getImage();

    int minRadius = 8;
    int maxRadius = Math.min(width, height) / 2;

    int[][][] houghSpace = new int[width][height][maxRadius];

    for (int y = 1; y < height - 1; y++) {
      for (int x = 1; x < width - 1; x++) {
        if ((edgeImage.getRGB(x, y) & 0xFF) == 0) {
          for (int r = minRadius; r < maxRadius; r++) {
            for (int angle = 0; angle < 360; angle += 10) {
              int a = (int) Math.floor(x - r * Math.cos(angle * Math.PI / 180));
              int b = (int) Math.floor(y - r * Math.sin(angle * Math.PI / 180));
              if (a >= 0 && a < width && b >= 0 && b < height) {
                houghSpace[a][b][r]++;
              }
            }
          }
        }
      }
    }

    List<Circle> circles = new ArrayList<>();
    int threshold = 36;
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        for (int r = minRadius; r < maxRadius; r++) {
          if (houghSpace[x][y][r] >= threshold) {
            boolean isValid = true;

            for (Circle circle : circles) {
              if (Math.hypot(circle.getX() - x, circle.getY() - y) < r / 2) {
                isValid = false;
                break;
              }
            }

            if (isValid) {
              circles.add(new Circle(x, y, r));
            }
          }
        }
      }
    }

    for (Circle circle : circles) {
      System.out.println(
          "Círculo detectado em: (" + circle.getX() + ", " + circle.getY() + ") com raio: " + circle.getRadius());
    }

    this.result = drawGroupedCircles(edgeImage, circles);
  }

  public BufferedImage getResult() {
    return result;
  }

  private BufferedImage drawGroupedCircles(BufferedImage image, List<Circle> circles) {
    BufferedImage output = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

    Graphics2D g = output.createGraphics();
    g.drawImage(image, 0, 0, null);

    String[] labels = { "1 real", "50 centavos", "25 centavos", "10 centavos", "5 centavos" };
    Color[] colors = { Color.YELLOW, Color.CYAN, Color.PINK, Color.GREEN, Color.ORANGE };

    List<List<Circle>> groups = new ArrayList<>();
    double groupingDistance = 20;

    for (Circle circle : circles) {
        boolean addedToGroup = false;

        for (List<Circle> group : groups) {
            for (Circle other : group) {
                if (Math.hypot(circle.getX() - other.getX(), circle.getY() - other.getY()) <= groupingDistance) {
                    group.add(circle);
                    addedToGroup = true;
                    break;
                }
            }
            if (addedToGroup) break;
        }

        if (!addedToGroup) {
            List<Circle> newGroup = new ArrayList<>();
            newGroup.add(circle);
            groups.add(newGroup);
        }
    }

    List<Circle> finalCircles = new ArrayList<>();
    for (List<Circle> group : groups) {
        int sumX = 0, sumY = 0;
        for (Circle c : group) {
            sumX += c.getX();
            sumY += c.getY();
        }

        int groupSize = group.size();
        int avgX = sumX / groupSize;
        int avgY = sumY / groupSize;

        double maxDistance = 0;
        for (Circle c : group) {
            double distance = Math.hypot(c.getX() - avgX, c.getY() - avgY) + c.getRadius();
            maxDistance = Math.max(maxDistance, distance);
        }

        int finalRadius = (int) Math.ceil(maxDistance);
        finalCircles.add(new Circle(avgX, avgY, finalRadius));
    }

    finalCircles.sort((c1, c2) -> Integer.compare(c2.getRadius(), c1.getRadius()));

    int maxLabels = Math.min(labels.length, finalCircles.size());

    for (int i = 0; i < maxLabels; i++) {
        Circle circle = finalCircles.get(i);
        String label = labels[i];
        Color color = colors[i];

        g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 128));
        g.fillOval(circle.getX() - circle.getRadius(), circle.getY() - circle.getRadius(), circle.getRadius() * 2, circle.getRadius() * 2);

        g.setColor(Color.BLACK);
        g.drawString(label, circle.getX() - (label.length() * 3), circle.getY());
    }

    g.setColor(Color.GRAY);
    for (int i = maxLabels; i < finalCircles.size(); i++) {
        Circle circle = finalCircles.get(i);
        g.drawOval(circle.getX() - circle.getRadius(), circle.getY() - circle.getRadius(), circle.getRadius() * 2, circle.getRadius() * 2);
    }

    g.dispose();
    return output;
}

}
