package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.BorderLayout;
import java.awt.EventQueue;

public class AnimationFrame {

    public AnimationFrame() {
        // Thread safe - https://stackoverflow.com/a/27475129
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }

            JFrame frame = new JFrame("Graph animation");
            JPanel containerPanel = new JPanel();
            containerPanel.setLayout(new BorderLayout());

            AnimationPanel animationPanel = new AnimationPanel();
            ControlPanel controlPanel = new ControlPanel();
            containerPanel.add(animationPanel, BorderLayout.EAST);
            containerPanel.add(controlPanel, BorderLayout.WEST);

            frame.add(containerPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
