package gui.control_components;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;

import static gui.panels.AnimationPanel.ANIMATION_PAUSED;
import static gui.panels.AnimationPanel.ANIMATION_TIMER;
import static gui.panels.ControlPanel.TRANSPARENT;

public class AnimationControls extends JPanel {

    public AnimationControls() {
        TitledBorder border = new TitledBorder(new LineBorder(Color.black), "Animation options",
                TitledBorder.CENTER, TitledBorder.BELOW_TOP);
        border.setTitleColor(Color.black);
        border.setTitlePosition(TitledBorder.DEFAULT_POSITION);
        this.setBorder(border);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(createAnimationToggleButton());
        this.add(createAnimationSpeedSlider());
    }

    private JSlider createAnimationSpeedSlider() {
        TitledBorder border = new TitledBorder(new LineBorder(TRANSPARENT), "Animation speed",
                TitledBorder.CENTER, TitledBorder.BELOW_TOP);
        border.setTitleColor(Color.DARK_GRAY);
        int maxValue = 100;
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0 , maxValue, 40);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            ANIMATION_TIMER.setDelay(maxValue - source.getValue());
        });
        slider.setBorder(border);
        return slider;
    }

    private JPanel createAnimationToggleButton() {
        JButton button = new JButton("Stop");
        button.addActionListener(e -> {
            if (!ANIMATION_PAUSED) {
                ANIMATION_PAUSED = true;
                button.setText("Play");
            } else {
                ANIMATION_PAUSED = false;
                button.setText("Stop");
            }
        });
        JPanel panel = new JPanel();
        panel.add(button);
        return panel;
    }

}
