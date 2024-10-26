package components;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Modal extends JDialog {
    protected JButton confirmButton;
    protected JButton cancelButton;

    public Modal(String title, String hint) {
        setTitle(title);
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);
        setLayout(new BorderLayout());

        confirmButton = new JButton("Confirm");
        cancelButton = new JButton("Close");
        
        add(hintText(hint), BorderLayout.NORTH);
        add(buttons(), BorderLayout.SOUTH);
    }

    private JComponent hintText(String text) {
        JLabel hintText = new JLabel(text, JLabel.CENTER);
        hintText.setBorder(new EmptyBorder(5, 10, 5, 10));
        return hintText;
    }

    protected JComponent form() {
        return new JPanel();
    };

    private JComponent buttons() {
        JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER));

        cancelButton.addActionListener(e -> {
            dispose();
        });

        confirmButton.setMnemonic('C');
        cancelButton.setMnemonic('X');

        container.add(confirmButton);
        container.add(cancelButton);

        return container;
    }

    protected void display() {
      pack();
      setLocationRelativeTo(null);
      setVisible(true);
    }
}
