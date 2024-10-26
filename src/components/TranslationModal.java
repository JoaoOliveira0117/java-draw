package components;

import java.awt.FlowLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;

import formatters.NumberFormatter;
import interfaces.ITranslationModalListener;

public class TranslationModal extends Modal {
    private int xValue = 0;
    private int yValue = 0;

    public TranslationModal(ITranslationModalListener listener) {
        super("Translate Image", "Enter the X and Y distance");

        this.confirmButton.addActionListener(e -> {
            listener.onConfirm(xValue, yValue);
        });

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

        JTextField xField = numberField();
        JTextField yField = numberField();

        addDocumentListener(xField, true); 
        addDocumentListener(yField, false);

        container.add(new JLabel("X:"));
        container.add(xField);
        container.add(new JLabel("Y:"));
        container.add(yField);

        return container;
    }

    private void addDocumentListener(JTextField textField, boolean isXValue) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateValue(textField, isXValue);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateValue(textField, isXValue);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateValue(textField, isXValue);
            }
        });
    }

    private void updateValue(JTextField textField, boolean isXValue) {
        try {
            int value = Integer.parseInt(textField.getText());
            if (isXValue) {
                xValue = value;
            } else {
                yValue = value;
            }
        } catch (NumberFormatException e) {
            if (isXValue) {
                xValue = 0;
            } else {
                yValue = 0;
            }
        }
    }
}
