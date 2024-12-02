package components;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import dip.MoedaDetector;

public class TopBarOptions {
  static CanvasWrapper canvasWrapper = CanvasWrapper.getInstance();

	private static BufferedImage loadImage(File file) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading image!");
        }
        return image;
	}

  public static void RemoveCanvas(ActionEvent e) {
    if (canvasWrapper != null) {
      int confirm = JOptionPane.showConfirmDialog(
          null,
          "Are you sure you want to remove the canvas?",
          "Confirmation",
          JOptionPane.YES_NO_OPTION
      );
  
      if (confirm == JOptionPane.YES_OPTION) {
          canvasWrapper.removeDrawableCanvas();
      }
    }
  }

  public static void ImportImage(ActionEvent e) {
    DrawableCanvas drawableCanvas = canvasWrapper.getDrawableCanvas();

    if (drawableCanvas != null) {
      canvasWrapper.removeDrawableCanvas();
    }

    JFileChooser fileChooser = new JFileChooser();

    FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
            "Image files", "jpg", "png", "jpeg", "gif", "bmp"
    );
    fileChooser.setFileFilter(imageFilter);

    int result = fileChooser.showOpenDialog(null);

    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        BufferedImage bufferedImage = loadImage(selectedFile);

        DrawableCanvas canvas = canvasWrapper.createDrawableCanvas(bufferedImage.getWidth(), bufferedImage.getHeight());
        
        canvas.addImage(bufferedImage);
    }
  }

  public static void ExportCanvas(ActionEvent e) {
    DrawableCanvas canvas = canvasWrapper.getDrawableCanvas();

    BufferedImage image = new BufferedImage(
        canvas.getWidth(), 
        canvas.getHeight(), 
        BufferedImage.TYPE_INT_ARGB
    );

    Graphics2D g2d = image.createGraphics();
    canvas.paint(g2d);
    g2d.dispose();

    if (canvasWrapper.getDrawableCanvas() == null) {
      JOptionPane.showMessageDialog(null, "No canvas to export image from!");
      return;
    }

    JFileChooser fileChooser = new JFileChooser();

    FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
            "Image files", "jpg", "png", "jpeg", "gif", "bmp"
    );
    fileChooser.setFileFilter(imageFilter);

    int result = fileChooser.showSaveDialog(null);

    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();

        if (!selectedFile.getName().toLowerCase().endsWith(".jpg") && !selectedFile.getName().toLowerCase().endsWith(".png")) {
            selectedFile = new File(selectedFile.toString() + ".png");
        }

        try {
            ImageIO.write(image, "png", selectedFile);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error exporting image!");
        }
    }
  }

  public static void DesafioMoedas(ActionEvent e) {
    DrawableCanvas drawableCanvas = canvasWrapper.getDrawableCanvas();

    if (drawableCanvas != null) {
      canvasWrapper.removeDrawableCanvas();
    }

    JFileChooser fileChooser = new JFileChooser();

    FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
            "Image files", "jpg", "png", "jpeg", "gif", "bmp"
    );
    fileChooser.setFileFilter(imageFilter);

    int result = fileChooser.showOpenDialog(null);

    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        BufferedImage bufferedImage = loadImage(selectedFile);

        

        DrawableCanvas canvas = canvasWrapper.createDrawableCanvas(bufferedImage.getWidth(), bufferedImage.getHeight());
        
        canvas.addImage(new MoedaDetector(bufferedImage).getResult());
    }
  }
 }
