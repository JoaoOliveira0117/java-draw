package components;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuBar extends JMenuBar {
	CanvasWrapper canvasWrapper;
	public MenuBar() {
		canvasWrapper = CanvasWrapper.getInstance();
		add(fileMenu());
		add(imageMenu());
	}
	
	private JMenu fileMenu() {
		JMenu file = new JMenu("file");
		
		JMenuItem newFile = new JMenuItem("new");
		JMenuItem removeFile = new JMenuItem("remove");
		
		newFile.addActionListener(e -> {
			canvasWrapper.createDrawableCanvas(800, 800);
		});
		
		removeFile.addActionListener(e -> {
			canvasWrapper.removeDrawableCanvas();
		});
		
		file.add(removeFile);
		file.add(newFile);
		return file;
	}
	
	private static BufferedImage loadImage(File file) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
	}
	
	private JMenu imageMenu() {
		JMenu image = new JMenu("image");
		
		JMenuItem addImage = new JMenuItem("add");
		
		addImage.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
                    "Image files", "jpg", "png", "jpeg", "gif", "bmp"
            );
            fileChooser.setFileFilter(imageFilter);

            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                BufferedImage bufferedImage = loadImage(selectedFile);
                
                DrawableCanvas drawableCanvas = canvasWrapper.getDrawableCanvas();
                
                if (drawableCanvas != null) {
                	drawableCanvas.addImage(bufferedImage);
                }
            }
        });
		
		image.add(addImage);
		return image;
	}
}
