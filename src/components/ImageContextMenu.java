package components;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import builders.ContextMenuBuilder;
import components.modals.BrightnessFilterModal;
import components.modals.ContrastFilterModal;
import components.modals.EdgeDetectionFilterModal;
import components.modals.MedianFilterModal;
import components.modals.ThresholdFilterModal;
import interfaces.ITranslationModalListener;

public class ImageContextMenu {
  private JComponent component;
  private ContextMenuBuilder context;

  public ImageContextMenu(ProcessableImage image, JComponent component) {
    this.component = component;
    this.context = createContextMenu(image);
  }

  private ContextMenuBuilder createContextMenu(ProcessableImage image) {
    ContextMenuBuilder context = new ContextMenuBuilder()
      .addItem("Mirror Horizontally", e -> {
        image.mirrorHorizontally();
        component.repaint();
      })
      .addItem("Mirror Vertically", e -> {
        image.mirrorVertically();
        component.repaint();
      })
      .addItem("Translate", e -> {
        new TranslationModal(new ITranslationModalListener() {
          @Override
          public void onConfirm(int x, int y) {
            image.translate(x, y);
            component.repaint();
          };

          @Override
          public void onCancel() {
          }
        });
      })
      .addItem("Rotate", e -> {
        String angle = JOptionPane.showInputDialog("Enter the angle to rotate the image");
        image.rotate(Double.parseDouble(angle));
        component.repaint();
      })
      .addItem("Grayscale", e -> {
        int choice = JOptionPane.showConfirmDialog(component, "This action is irreversible. Do you want to procceed?");

        if (choice == JOptionPane.YES_OPTION) {
          image.grayscale();
          component.repaint();
        }
      })
      .addItem("Brightness", e -> new BrightnessFilterModal(image))
      .addItem("Contrast", e -> new ContrastFilterModal(image))
      .addItem("Median Filter", e -> new MedianFilterModal(image))
      .addItem("Gaussian Filter", e -> {
        image.grayscale();
        image.gaussianFilter();
        component.repaint();
      })
      .addItem("Threshold Filter", e -> new ThresholdFilterModal(image))
      .addItem("Roberts Edge Detection", e -> new EdgeDetectionFilterModal(image, true))
      .addItem("Sobel Edge Detection", e -> new EdgeDetectionFilterModal(image, false));

    return context;
  }

  public void show(int x, int y) {
    context.show(component, x, y);
  }
}
