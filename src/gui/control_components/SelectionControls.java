package gui.control_components;

import components.NodeManager;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Dimension;

import static gui.panels.ControlPanel.TRANSPARENT;

public class SelectionControls extends JPanel  {

    public static final JLabel POSITION_LABEL = new JLabel();
    public static final JLabel VELOCITY_LABEL = new JLabel();
    public static final JLabel LINE_EQUATION_LABEL = new JLabel();

    public SelectionControls () {
        TitledBorder border = new TitledBorder(new LineBorder(Color.black), "Selected Node",
                TitledBorder.CENTER, TitledBorder.BELOW_TOP);
        border.setTitleColor(Color.black);
        border.setTitlePosition(TitledBorder.DEFAULT_POSITION);

        this.setBorder(border);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        createNodeInfoPanel();
        this.add(createSelectedNodeVelocitySlider());
        this.add(createLineEquationSliders());
    }

    private JSlider createSelectedNodeVelocitySlider() {
        TitledBorder border = new TitledBorder(new LineBorder(TRANSPARENT), "Velocity",
                TitledBorder.CENTER, TitledBorder.BELOW_TOP);
        border.setTitleColor(Color.DARK_GRAY);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, -50 , 50, 1);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            if (NodeManager.getSelectedNode() != null) {
                NodeManager.getSelectedNode().setVelocity(source.getValue());
            }
        });
        slider.setBorder(border);
        return slider;
    }

    private JPanel createLineEquationSliders() {
        TitledBorder border = new TitledBorder(new LineBorder(TRANSPARENT), "Line equation",
                TitledBorder.CENTER, TitledBorder.BELOW_TOP);
        border.setTitleColor(Color.DARK_GRAY);

        JPanel panel = new JPanel();
        panel.setBorder(border);

        SpinnerModel spinnerA = new SpinnerNumberModel(1, -10, 10, 0.01);
        JSpinner controlsA = new JSpinner(spinnerA);
        controlsA.setPreferredSize(new Dimension(50, 20));
        controlsA.addChangeListener(e -> {
            JSpinner source = (JSpinner) e.getSource();
            if (NodeManager.getSelectedNode() != null) {
                double numberVal = (double) source.getValue();
                NodeManager.getSelectedNode().setLineEquation(
                        new float[]{(float) numberVal, NodeManager.getSelectedNode().getLineEquation()[1]});
            }
        });

        SpinnerModel spinnerB = new SpinnerNumberModel(0, -10000, 10000, 10);
        JSpinner controlsB = new JSpinner(spinnerB);
        controlsB.setPreferredSize(new Dimension(100, 20));
        controlsB.addChangeListener(e -> {
            JSpinner source = (JSpinner) e.getSource();
            if (NodeManager.getSelectedNode() != null) {
                int numberVal = (int) source.getValue();
                NodeManager.getSelectedNode().setLineEquation(
                        new float[]{NodeManager.getSelectedNode().getLineEquation()[0], (float) numberVal});
            }
        });

        panel.add(new JLabel("y="));
        panel.add(controlsA);
        panel.add(new JLabel(" x + "));
        panel.add(controlsB);
        return panel;
    }

    private void createNodeInfoPanel() {
        POSITION_LABEL.setText("x:  y: ");
        VELOCITY_LABEL.setText("velocity: ");
        LINE_EQUATION_LABEL.setText("y=ax + b");
        this.add(POSITION_LABEL);
        this.add(VELOCITY_LABEL);
        this.add(LINE_EQUATION_LABEL);
    }
}
