package components.modals;

import components.Modal;
import components.ProcessableImage;
import interfaces.ConfirmationModalListener;

public class ConfirmationModal extends Modal {
  public ConfirmationModal(ProcessableImage image) {
    super("Confirm?", "This action is irreversible. Do you want to procceed?");

    ConfirmationModalListener listener = createListener(image);
    
    add(form());

    this.confirmButton.addActionListener(e -> {
      listener.onConfirm();
    });

    this.cancelButton.addActionListener(e -> {
      listener.onCancel();
    });
    
    display();
  }

  private static ConfirmationModalListener createListener(ProcessableImage image) {
    ProcessableImage originalImage = new ProcessableImage(image.getImage());
    ProcessableImage commitableImage = new ProcessableImage(image.getImage());

    return new ConfirmationModalListener() {
      @Override
      public void onConfirm() {
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
