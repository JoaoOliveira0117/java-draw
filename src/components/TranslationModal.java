package components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;

import formatters.NumberFormatter;

import interfaces.TranslationModalListener;

public class TranslationModal extends JDialog {
  private static final int WIDTH = 300;
  private static final int HEIGHT = 180;

  private int xValue;
  private int yValue;
  
  private JFormattedTextField xInput;
  private JFormattedTextField yInput;
  private TranslationModalListener listener;

  public TranslationModal() {
    setModal(true);
    setTitle("Translate");
    setSize(WIDTH, HEIGHT);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout(10, 10));

    add(createHintText(), BorderLayout.NORTH);
    add(createInputsForm(), BorderLayout.CENTER);
    add(createButtons(), BorderLayout.SOUTH);

  }

  private JComponent createHintText() {
    JLabel hintText = new JLabel("Insira os valores de translação", JLabel.CENTER);
    hintText.setBorder(new EmptyBorder(5, 10, 5, 10));
    return hintText;
  }

  private JComponent createInputsForm() {
    JPanel container = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 10, 5, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    gbc.gridx = 0;
    gbc.gridy = 0;
    container.add(createNumberInput("X"), gbc);

    gbc.gridx = 1;
    container.add(createNumberInput("Y"), gbc);

    return container;
  }

  private JComponent createNumberInput(String label) {
    JPanel container = new JPanel(new BorderLayout(5, 5));
    JLabel labelComponent = new JLabel(label);

    NumberFormatter format = new NumberFormatter();

    JFormattedTextField input = new JFormattedTextField();
    ((AbstractDocument) input.getDocument()).setDocumentFilter(format);

    input.setPreferredSize(new Dimension(80, 30));

    if (label.equals("X")) {
      xInput = input;
    } else {
      yInput = input;
    }

    container.add(labelComponent, BorderLayout.WEST);
    container.add(input, BorderLayout.CENTER);

    return container;
  }

  private JComponent createButtons() {
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JButton okButton = new JButton("OK");
    JButton cancelButton = new JButton("CANCELAR");

    okButton.addActionListener(e -> {
      xValue = Integer.valueOf(xInput.getText());
      yValue = Integer.valueOf(yInput.getText());

      if (listener != null) {
        listener.onConfirm(xValue, yValue);
      }
      
      dispose();
    });

    cancelButton.addActionListener(e -> dispose());

    buttonPanel.add(okButton);
    buttonPanel.add(cancelButton);

    return buttonPanel;
  }

  public void addListener(TranslationModalListener listener) {
    this.listener = listener;
    setVisible(true);
  }

  public int getXValue() {
    return xValue;
  }

  public int getYValue() {
    return yValue;
  }
}
