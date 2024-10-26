package builders;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
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

  public ContextMenuBuilder addLineSeparator() {
    addSeparator();
    return this;
  }
}
