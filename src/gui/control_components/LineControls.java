package gui.control_components;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;

import static gui.AnimationPanel.LINE_MAX_DISTANCE;
import static gui.AnimationPanel.LINE_MAX_WIDTH;
import static gui.ControlPanel.TRANSPARENT;

public class LineControls extends JPanel {

    public LineControls() {
        TitledBorder border = new TitledBorder(new LineBorder(Color.black), "Line options",
                TitledBorder.CENTER, TitledBorder.BELOW_TOP);
        border.setTitleColor(Color.black);
        border.setTitlePosition(TitledBorder.DEFAULT_POSITION);

        this.setBorder(border);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.add(createMaximumDistanceSlider());
        this.add(createMaximumWidthSlider());
    }

    private JSlider createMaximumDistanceSlider() {
        TitledBorder border = new TitledBorder(new LineBorder(TRANSPARENT), "Maximum distance",
                TitledBorder.CENTER, TitledBorder.BELOW_TOP);
        border.setTitleColor(Color.DARK_GRAY);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 25 , 900, 250);
        slider.setMajorTickSpacing(175);
        slider.setMinorTickSpacing(25);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            LINE_MAX_DISTANCE = source.getValue();
        });
        slider.setBorder(border);
        return slider;
    }

    private JSlider createMaximumWidthSlider() {
        TitledBorder border = new TitledBorder(new LineBorder(TRANSPARENT), "Maximum width",
                TitledBorder.CENTER, TitledBorder.BELOW_TOP);
        border.setTitleColor(Color.DARK_GRAY);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0 , 80, 10);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            LINE_MAX_WIDTH = source.getValue();
        });
        slider.setBorder(border);
        return slider;
    }
}
