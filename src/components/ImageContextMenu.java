package components;

import javax.swing.JComponent;

import builders.ContextMenuBuilder;
import components.modals.ApplyCloseModal;
import components.modals.ApplyOpenModal;
import components.modals.BrightnessFilterModal;
import components.modals.ContrastFilterModal;
import components.modals.DilationModal;
import components.modals.EdgeDetectionFilterModal;
import components.modals.ErosionModal;
import components.modals.GaussianFilterModal;
import components.modals.GrayscaleModal;
import components.modals.MedianFilterModal;
import components.modals.RotationModal;
import components.modals.ThresholdFilterModal;
import components.modals.TranslationModal;

public class ImageContextMenu {
  public static void show(ProcessableImage image, JComponent component, int x, int y) {
    new ContextMenuBuilder()
      .addItem("Mirror Horizontally", e -> image.mirrorHorizontally().triggerRerender())
      .addItem("Mirror Vertically", e -> image.mirrorVertically().triggerRerender())
      .addItem("Translate", e -> new TranslationModal(image))
      .addItem("Rotate", e -> new RotationModal(image))
      .addLineSeparator()
      .addItem("Grayscale", e -> new GrayscaleModal(image))
      .addItem("Brightness", e -> new BrightnessFilterModal(image))
      .addItem("Contrast", e -> new ContrastFilterModal(image))
      .addLineSeparator()
      .addItem("Median Filter", e -> new MedianFilterModal(image))
      .addItem("Gaussian Filter", e -> new GaussianFilterModal(image))
      .addItem("Threshold Filter", e -> new ThresholdFilterModal(image))
      .addItem("Roberts Edge Detection", e -> new EdgeDetectionFilterModal(image, true))
      .addItem("Sobel Edge Detection", e -> new EdgeDetectionFilterModal(image, false))
      .addLineSeparator()
      .addItem("Dilation", e -> new DilationModal(image))
      .addItem("Erosion", e -> new ErosionModal(image))
      .addItem("Open Filter", e -> new ApplyOpenModal(image))
      .addItem("Close Filter", e -> new ApplyCloseModal(image))
      .show(component, x, y);
  }
}
