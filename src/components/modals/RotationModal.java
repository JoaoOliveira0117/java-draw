package components.modals;

import java.awt.FlowLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;

import components.Modal;
import components.ProcessableImage;
import formatters.NumberFormatter;
import interfaces.RotationModalListener;

public class RotationModal extends Modal {
    private int degrees = 0;

    public RotationModal(ProcessableImage image) {
        super("Rotate Image", "Enter the rotation degrees");

        RotationModalListener listener = createListener(image);

        this.confirmButton.addActionListener(e -> {
            listener.onConfirm(degrees);
            dispose();
        });

        add(form());

        display();
    }

    private JTextField numberField() {
        JTextField textField = new JTextField(10);
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new NumberFormatter());
        return textField;
    }

    @Override
    protected JComponent form() {
        JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JTextField field = numberField();

        addDocumentListener(field);

        container.add(new JLabel("Degrees:"));
        container.add(field);

        return container;
    }

    private void addDocumentListener(JTextField textField) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateValue(textField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateValue(textField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateValue(textField);
            }
        });
    }

    private void updateValue(JTextField textField) {
        try {
            degrees = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            degrees = 0;
        }
    }
    
  private static RotationModalListener createListener(ProcessableImage image) {
    ProcessableImage originalImage = new ProcessableImage(image.getImage());
    ProcessableImage commitableImage = new ProcessableImage(image.getImage());

    return new RotationModalListener() {
      @Override
      public void onConfirm(double degrees) {
        image.setImage(commitableImage.rotate(degrees).getImage());
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
