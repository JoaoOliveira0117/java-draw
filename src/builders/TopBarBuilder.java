package builders;

import javax.swing.*;

import components.TopBarItem;

import java.util.function.Consumer;

public class TopBarBuilder {
    private JMenuBar menuBar = new JMenuBar();

    public TopBarBuilder addItem(String name, Consumer<TopBarItem> itemBuilder) {
        TopBarItem topBarItem = new TopBarItem(name);

        itemBuilder.accept(topBarItem);

        menuBar.add(topBarItem);
        return this;
    }

    public JMenuBar build() {
        return menuBar;
    }
}
