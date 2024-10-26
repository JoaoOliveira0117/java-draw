package components.modals;

import components.ProcessableImage;
import components.SliderModal;
import interfaces.SliderModalListener;

public class EdgeDetectionFilterModal extends SliderModal {  
  public EdgeDetectionFilterModal(ProcessableImage image, boolean isRoberts) {
    super(isRoberts ? "Robert's" : "Sobel's" + " Edge Detection Filter", createListener(image, isRoberts), 0, 128, 16);
  }

  private static SliderModalListener createListener(ProcessableImage image, boolean isRoberts) {
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
        commitableImage.thresholdFilter((int) value);

        if (isRoberts) {
          commitableImage.EdgeDetectionRoberts((int) value);
        } else {
          commitableImage.EdgeDetectionSobel((int) value);
        }
        
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
