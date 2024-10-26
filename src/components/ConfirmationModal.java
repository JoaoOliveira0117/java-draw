package components;

import interfaces.ConfirmationModalListener;

public class ConfirmationModal extends Modal {
  public ConfirmationModal(ProcessableImage image, ConfirmationModalListener listener) {
    super("Confirm?", "This action is irreversible. Do you want to procceed?");
    
    this.confirmButton.addActionListener(e -> {
      listener.onConfirm();
      dispose();
    });

    this.cancelButton.addActionListener(e -> {
      listener.onCancel();
      dispose();
    });

    add(form());
  }
}
