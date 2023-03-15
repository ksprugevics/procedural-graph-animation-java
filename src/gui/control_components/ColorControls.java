package gui.control_components;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Dimension;

import static gui.panels.AnimationPanel.BACKGROUND_COLOR;
import static gui.panels.AnimationPanel.LINE_COLOR;
import static gui.panels.AnimationPanel.NODE_COLOR;

public class ColorControls extends JPanel {

    private final String BACKGROUND_STRING = "Background";
    private final String NODE_STRING = "Nodes";
    private final String LINE_STRING = "Lines";

    private ButtonGroup colorButtons;

    public ColorControls() {
        TitledBorder border = new TitledBorder(new LineBorder(Color.black), "Colors",
            TitledBorder.CENTER, TitledBorder.BELOW_TOP);
        border.setTitleColor(Color.black);
        border.setTitlePosition(TitledBorder.DEFAULT_POSITION);
        this.setBorder(border);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(70, 100));
        this.add(createColorModeRadioButtons());
        this.add(createNodeColorChooser());
    }

    private JPanel createColorModeRadioButtons() {
        JRadioButton backgroundButton = new JRadioButton(BACKGROUND_STRING);
        backgroundButton.setActionCommand(BACKGROUND_STRING);
        backgroundButton.setSelected(true);

        JRadioButton nodeButton = new JRadioButton(NODE_STRING);
        nodeButton.setActionCommand(NODE_STRING);

        JRadioButton lineButton = new JRadioButton(LINE_STRING);
        lineButton.setActionCommand(LINE_STRING);

        ButtonGroup group = new ButtonGroup();
        group.add(backgroundButton);
        group.add(nodeButton);
        group.add(lineButton);

        JPanel panel = new JPanel();
        panel.add(backgroundButton);
        panel.add(nodeButton);
        panel.add(lineButton);
        colorButtons = group;
        return panel;
    }

    private JColorChooser createNodeColorChooser() {
        JColorChooser chooser = new JColorChooser(NODE_COLOR);
        chooser.getSelectionModel().addChangeListener(e -> {
            switch (colorButtons.getSelection().getActionCommand()) {
                case BACKGROUND_STRING -> BACKGROUND_COLOR = chooser.getColor();
                case NODE_STRING -> NODE_COLOR = chooser.getColor();
                case LINE_STRING -> LINE_COLOR = chooser.getColor();
            }
        });
        return chooser;
    }
}
