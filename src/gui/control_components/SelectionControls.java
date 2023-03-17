package gui.control_components;

import components.Node;
import components.NodeManager;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class SelectionControls extends JPanel  {

    private static JSpinner SELECTED_VELOCITY_SPINNER;
    private static JSpinner SELECTED_A_VALUE_SPINNER;
    private static JSpinner SELECTED_B_VALUE_SPINNER;

    private static JLabel SELECTED_X_POS_LABEL;
    private static JLabel SELECTED_Y_POS_LABEL;
    private static JLabel SELECTED_VELOCITY_LABEL;
    private static JLabel SELECTED_A_VAL_LABEL;
    private static JLabel SELECTED_B_VAL_LABEL;

    public SelectionControls () {
        TitledBorder border = new TitledBorder(new LineBorder(Color.black), "Selected Node",
                TitledBorder.CENTER, TitledBorder.BELOW_TOP);
        border.setTitleColor(Color.black);
        border.setTitlePosition(TitledBorder.DEFAULT_POSITION);

        this.setBorder(border);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(createNodeInfoPanel());
        this.add(createNodeSliders());
    }

    private JPanel createNodeSliders() {
        JPanel panel = new JPanel();

        SpinnerModel spinnerA = new SpinnerNumberModel(1, -10, 10, 0.01);
        JSpinner controlsA = new JSpinner(spinnerA);
        controlsA.setPreferredSize(new Dimension(75, 20));
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
        controlsB.setPreferredSize(new Dimension(75, 20));
        controlsB.addChangeListener(e -> {
            JSpinner source = (JSpinner) e.getSource();
            if (NodeManager.getSelectedNode() != null) {
                int numberVal = (int) source.getValue();
                NodeManager.getSelectedNode().setLineEquation(
                        new float[]{NodeManager.getSelectedNode().getLineEquation()[0], (float) numberVal});
            }
        });

        SpinnerModel spinnerVelocity = new SpinnerNumberModel(1, -50, 50, 0.01);
        JSpinner controlsVelocity = new JSpinner(spinnerVelocity);
        controlsVelocity.setPreferredSize(new Dimension(75, 20));
        controlsVelocity.addChangeListener(e -> {
            JSpinner source = (JSpinner) e.getSource();
            if (NodeManager.getSelectedNode() != null) {
                double numberVal = (double) source.getValue();
                NodeManager.getSelectedNode().setVelocity((float) numberVal);
            }
        });

        panel.add(new JLabel("y="));
        panel.add(controlsA);
        panel.add(new JLabel(" x + "));
        panel.add(controlsB);
        panel.add(new JLabel("Velocity:"));
        panel.add(controlsVelocity);
        SELECTED_A_VALUE_SPINNER = controlsA;
        SELECTED_B_VALUE_SPINNER = controlsB;
        SELECTED_VELOCITY_SPINNER = controlsVelocity;
        return panel;
    }

    private JPanel createNodeInfoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(200, 50)); // set preferred size of panel

        JLabel labelX1 = new JLabel("x");
        JLabel labelY1 = new JLabel("y");
        JLabel labelA1 = new JLabel("a");
        JLabel labelB1 = new JLabel("b");
        JLabel labelVelocity1 = new JLabel("velocity");

        SELECTED_X_POS_LABEL = new JLabel(" ");
        SELECTED_Y_POS_LABEL = new JLabel(" ");
        SELECTED_A_VAL_LABEL = new JLabel(" ");
        SELECTED_B_VAL_LABEL = new JLabel(" ");
        SELECTED_VELOCITY_LABEL = new JLabel(" ");

        // create constraints for centering horizontally and vertically
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;

        // add labels for first row to the panel
        panel.add(labelX1, c);
        c.gridx++;
        panel.add(labelY1, c);
        c.gridx++;
        panel.add(labelA1, c);
        c.gridx++;
        panel.add(labelB1, c);
        c.gridx++;
        panel.add(labelVelocity1, c);

        // reset gridx and update gridy for second row
        c.gridx = 0;
        c.gridy++;

        // add labels for second row to the panel
        panel.add(SELECTED_X_POS_LABEL, c);
        c.gridx++;
        panel.add(SELECTED_Y_POS_LABEL, c);
        c.gridx++;
        panel.add(SELECTED_A_VAL_LABEL, c);
        c.gridx++;
        panel.add(SELECTED_B_VAL_LABEL, c);
        c.gridx++;
        panel.add(SELECTED_VELOCITY_LABEL, c);

        return panel;
    }

    public static void updateSpinnerValues(Node node) {
        swapSpinnerValue(SELECTED_VELOCITY_SPINNER, node.getVelocity());
        swapSpinnerValue(SELECTED_A_VALUE_SPINNER, node.getLineEquation()[0]);
        swapSpinnerValue(SELECTED_B_VALUE_SPINNER, node.getLineEquation()[1]);
    }

    private static void swapSpinnerValue(JSpinner spinner, float value) {
        ChangeListener listener = spinner.getChangeListeners()[0];
        spinner.removeChangeListener(listener);
        spinner.setValue(value);
        spinner.addChangeListener(listener);
    }

    public static void clearInfoLabels() {
        SELECTED_X_POS_LABEL.setText(" ");
        SELECTED_Y_POS_LABEL.setText(" ");
        SELECTED_VELOCITY_LABEL.setText(" ");
        SELECTED_A_VAL_LABEL.setText(" ");
        SELECTED_B_VAL_LABEL.setText(" ");
    }

    public static void updateInfoLabels(Node node) {
        SELECTED_X_POS_LABEL.setText(String.valueOf((int) node.getxPos()));
        SELECTED_Y_POS_LABEL.setText(String.valueOf((int) node.getyPos()));
        SELECTED_VELOCITY_LABEL.setText(String.format("%.2f", node.getVelocity()));
        SELECTED_A_VAL_LABEL.setText(String.format("%.3f", node.getLineEquation()[0]));
        SELECTED_B_VAL_LABEL.setText(String.format("%.3f", node.getLineEquation()[1]));
    }
}
