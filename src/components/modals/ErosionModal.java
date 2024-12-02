package components.modals;

import components.ConfirmationModal;
import components.ProcessableImage;
import interfaces.ConfirmationModalListener;

public class ErosionModal extends ConfirmationModal {
  public ErosionModal(ProcessableImage image) {
    super(image, createListener(image));
    
    display();
  }

  private static ConfirmationModalListener createListener(ProcessableImage image) {
    ProcessableImage originalImage = new ProcessableImage(image.getImage());
    ProcessableImage commitableImage = new ProcessableImage(image.getImage());

    return new ConfirmationModalListener() {
      @Override
      public void onConfirm() {
        image.setImage(commitableImage.Erosion().getImage());
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
