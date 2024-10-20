package components;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import builders.ContextMenuBuilder;
import interfaces.TranslationModalListener;

public class ImageContextMenu {
  private JComponent component;
  private ContextMenuBuilder context;

  public ImageContextMenu(ProcessableImage image, JComponent component) {
    this.component = component;
    this.context = createContextMenu(image);
  }

  private ContextMenuBuilder createContextMenu(ProcessableImage image) {
    ContextMenuBuilder context = new ContextMenuBuilder()
      .addItem("Mirror Horizontally", e -> {
        image.mirrorHorizontally();
        component.repaint();
      })
      .addItem("Mirror Vertically", e -> {
        image.mirrorVertically();
        component.repaint();
      })
      .addItem("Translate", e -> 
        showTranslationModal(image)
      );

    return context;
  }

  public void show(int x, int y) {
    context.show(component, x, y);
  }

  private void showTranslationModal(ProcessableImage image) {
    new TranslationModal().addListener(new TranslationModalListener() {
      @Override
      public void onConfirm(int x, int y) {
        image.translate(x, y);
        component.repaint();
      }
    });
  }
}
