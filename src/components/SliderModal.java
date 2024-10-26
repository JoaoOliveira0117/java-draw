package components;

import java.awt.FlowLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JPanel;

import interfaces.SliderModalListener;

public class SliderModal extends Modal {
    private int minValue = 0;
    private int maxValue = 100;
    private int step = 10;

    private int strength = 0;
    private JSlider slider;

    public SliderModal(String effectName, SliderModalListener listener) {
        super(effectName, "Enter the strength of the " + effectName + " effect");

        this.confirmButton.addActionListener(e -> {
            listener.onConfirm();
            dispose();
        });

        this.slider.addChangeListener(e -> {
            strength = slider.getValue();
            listener.onChange(strength);
        });

        this.cancelButton.addActionListener(e -> {
            listener.onCancel();
            dispose();
        });

        add(form());
    }

    public SliderModal(String effectName, SliderModalListener listener, int minValue, int maxValue, int step) {
        super(effectName, "Enter the strength of the " + effectName + " effect");

        this.minValue = minValue;
        this.maxValue = maxValue;
        this.step = step;

        add(form());

        this.confirmButton.addActionListener(e -> {
            listener.onConfirm();
        });

        this.slider.addChangeListener(e -> {
            strength = slider.getValue();
            listener.onChange(strength);
        });

        this.cancelButton.addActionListener(e -> {
            listener.onCancel();
        });

        display();
    }

    @Override
    protected JComponent form() {
        JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JSlider slider = new JSlider(minValue, maxValue, (minValue + maxValue) / 2);

        if(step != 0) {
            slider.setMajorTickSpacing(step);
            
            if (step >= 4) {
                slider.setMinorTickSpacing(step / 2);
            }

            slider.setPaintTicks(true);
            slider.setSnapToTicks(true);
        }

        this.slider = slider;

        container.add(new JLabel("Strength:"));
        container.add(slider);

        return container;
    }
}
