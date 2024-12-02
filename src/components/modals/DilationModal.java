package components.modals;

import components.ConfirmationModal;
import components.ProcessableImage;
import interfaces.ConfirmationModalListener;

public class DilationModal extends ConfirmationModal {
  public DilationModal(ProcessableImage image) {
    super(image, createListener(image));
    
    display();
  }

  private static ConfirmationModalListener createListener(ProcessableImage image) {
    ProcessableImage originalImage = new ProcessableImage(image.getImage());
    ProcessableImage commitableImage = new ProcessableImage(image.getImage());

    return new ConfirmationModalListener() {
      @Override
      public void onConfirm() {
        image.setImage(commitableImage.Dilation().getImage());
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
