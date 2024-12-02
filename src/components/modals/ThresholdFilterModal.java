package components.modals;

import components.ProcessableImage;
import components.SliderModal;
import interfaces.SliderModalListener;

public class ThresholdFilterModal extends SliderModal {
  public ThresholdFilterModal(ProcessableImage image) {
    super("Threshold Filter", createListener(image), 0, 128, 1);
    
    display();
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
        double newValue = Math.min(255, Math.max(0, (int) value)) * 2.55;
        System.out.println(newValue);
        commitableImage.setImage(originalImage.getImage());
        commitableImage.thresholdFilter((int) newValue);
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
