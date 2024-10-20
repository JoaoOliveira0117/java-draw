package builders;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class ContextMenuBuilder extends JPopupMenu {
  
  public ContextMenuBuilder() {
  }

  public ContextMenuBuilder addItem(String name, ActionListener listener ) {
    JMenuItem item = new JMenuItem(name);
    item.addActionListener(listener);
    add(item);
    
    return this;
  }

  public ContextMenuBuilder addItemWithConfirmation(String name, ActionListener listener ) {
    JMenuItem item = new JMenuItem(name);
    item.addActionListener(e -> {
      int choice = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm Action", JOptionPane.YES_NO_OPTION);
      if (choice == JOptionPane.YES_OPTION) {
        listener.actionPerformed(e);
      }
    });
    add(item);
    
    return this;
  }
}
