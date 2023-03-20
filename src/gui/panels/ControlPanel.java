package gui.panels;

import gui.control_components.AnimationControls;
import gui.control_components.ColorControls;
import gui.control_components.LineControls;
import gui.control_components.NodeControls;
import gui.control_components.SelectionControls;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Color;


public class ControlPanel extends JPanel {

    public static final Color TRANSPARENT = new Color(0, 0, 0, 0);

    public ControlPanel() {

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel animationSettings = new JPanel();
        animationSettings.setLayout(new BoxLayout(animationSettings, BoxLayout.Y_AXIS));
        animationSettings.add(new AnimationControls());
        animationSettings.add(new NodeControls());
        animationSettings.add(new LineControls());
        animationSettings.add(new SelectionControls());

        tabbedPane.addTab("Animation", new ImageIcon(), animationSettings, "Animation settings");
        tabbedPane.addTab("Colors", new ImageIcon(), new ColorControls(), "Color settings");
        this.add(tabbedPane);
    }
}



