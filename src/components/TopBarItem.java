package components;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class TopBarItem extends JMenu {
    public TopBarItem(String name) {
        super(name);
    }

    public void addItem(String name, Consumer<TopBarItem> itemBuilder) {
        TopBarItem subItem = new TopBarItem(name);

        itemBuilder.accept(subItem);

        this.add(subItem);
    }

    public JMenuItem createItem(String name, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.addActionListener(listener);
        this.add(menuItem);
        return menuItem;
    }
}
