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
import interfaces.InputModalListener;

public class InputModal extends Modal {
    private static int strength = 0;

    public InputModal(String effectName, InputModalListener listener) {
        super(effectName, "Enter the strength of the " + effectName + " effect", form());

        this.confirmButton.addActionListener(e -> {
            listener.onConfirm(strength);
        });

        display();
    }

    private static JTextField numberField() {
        JTextField textField = new JTextField(10);
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new NumberFormatter());
        return textField;
    }

    private static JComponent form() {
        JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JTextField field = numberField();

        addDocumentListener(field);

        container.add(new JLabel("Strength:"));
        container.add(field);

        return container;
    }

    private static void addDocumentListener(JTextField textField) {
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

    private static void updateValue(JTextField textField) {
        try {
            strength = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            strength = 0;
        }
    }
}
