package gui.control_components;

import components.NodeManager;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;

import static gui.AnimationPanel.NODE_SIZE;
import static gui.AnimationPanel.PADDING;
import static gui.ControlPanel.TRANSPARENT;

public class NodeControls extends JPanel {

    public NodeControls() {
        TitledBorder border = new TitledBorder(new LineBorder(Color.black), "Node options",
                TitledBorder.CENTER, TitledBorder.BELOW_TOP);
        border.setTitleColor(Color.black);
        border.setTitlePosition(TitledBorder.DEFAULT_POSITION);

        this.setBorder(border);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.add(createNodeCountSlider());
        this.add(createNodeSizeSlider());
    }

    private JSlider createNodeCountSlider() {
        TitledBorder border = new TitledBorder(new LineBorder(TRANSPARENT), "Node count",
                TitledBorder.CENTER, TitledBorder.BELOW_TOP);
        border.setTitleColor(Color.DARK_GRAY);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0 , 300, 10);
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(25);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            NodeManager.setNodeCount(source.getValue());
            NodeManager.resetNodes();
        });
        slider.setBorder(border);
        return slider;
    }

    private JSlider createNodeSizeSlider() {
        TitledBorder border = new TitledBorder(new LineBorder(TRANSPARENT), "Node size",
                TitledBorder.CENTER, TitledBorder.BELOW_TOP);
        border.setTitleColor(Color.DARK_GRAY);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 10 , 100, 50);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            NODE_SIZE = source.getValue();
            PADDING = NODE_SIZE / 2;
        });
        slider.setBorder(border);
        return slider;
    }
}
