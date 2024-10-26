package components.modals;

import components.ProcessableImage;
import components.SliderModal;
import interfaces.SliderModalListener;

public class ContrastFilterModal extends SliderModal {
  public ContrastFilterModal(ProcessableImage image) {
    super("Contrast Filter", createListener(image), 0, 255, 32);
    
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
        double newValue = ( (int) value + 100 ) * 0.01;
        System.out.println(newValue);

        commitableImage.setImage(originalImage.getImage());
        commitableImage.contrast(newValue);
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
