package components.modals;

import components.ProcessableImage;
import components.SliderModal;
import interfaces.SliderModalListener;

public class MedianFilterModal extends SliderModal {
  public MedianFilterModal(ProcessableImage image) {
    super("Median Filter", createListener(image), 3, 7, 2);
  }

  private static SliderModalListener createListener(ProcessableImage image) {
    return new SliderModalListener() {
      private ProcessableImage originalImage = new ProcessableImage(image.getImage());
      private ProcessableImage commitableImage = new ProcessableImage(image.getImage());

      @Override
      public void onConfirm() {
        image.setImage(commitableImage.getImage());
      }

      @Override
      public void onChange(Object value) {
        commitableImage.setImage(originalImage.getImage());
        commitableImage.medianFilter((int) value);
        image.setImage(commitableImage.getImage());
        image.triggerRerender();
      }

      @Override
      public void onCancel() {
        image.setImage(originalImage.getImage());
        image.triggerRerender();
      }
      
    };
  }
}
