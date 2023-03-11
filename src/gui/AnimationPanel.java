package gui;

import components.Node;
import components.NodeManager;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import static utils.Calculations.euclideanDistanceBetweenNodes;

public class AnimationPanel extends JPanel {

    private static final NodeManager MANAGER = NodeManager.getInstance();

    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 900;

    public static final Color BACKGROUND_COLOR = new Color(131, 193, 131);
    public static final Color NODE_COLOR = new Color(224, 251, 252);
    public static final Color LINE_COLOR = new Color(31, 198, 204);

    public static final int NODE_SIZE = 50;
    public static final int PADDING = NODE_SIZE / 2;
    public static final int LINE_WIDTH = 7;
    public static final int LINE_MAX_DISTANCE = 250;


    public AnimationPanel() {
        Timer timer = new Timer(40, e -> {
            MANAGER.getNodes().forEach(Node::move);
            repaint();
        });
        timer.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        setBackground(BACKGROUND_COLOR);
        Graphics2D g2d = (Graphics2D) graphics.create();

        drawLinesBetweenNodes(g2d);
        drawNodes(g2d);

        g2d.dispose();
    }

    private static void drawNodes(Graphics2D g2d) {
        g2d.setColor(NODE_COLOR);
        for (Node node : MANAGER.getNodes()) {
            g2d.fillOval((int) node.getxPos(), (int) node.getyPos(), NODE_SIZE, NODE_SIZE);
        }
    }

    private static void drawLinesBetweenNodes(Graphics2D g2d) {

        for (int i = 0; i < MANAGER.getNodes().size(); i++) {
            for (int j = 1; j < MANAGER.getNodes().size(); j++) {
                Node p1 = MANAGER.getNodes().get(i);
                Node p2 = MANAGER.getNodes().get(j);

                float dist = euclideanDistanceBetweenNodes(p1, p2);
                if (dist > LINE_MAX_DISTANCE) {
                    continue;
                }
                g2d.setStroke(new BasicStroke(LINE_WIDTH));
                g2d.setColor(LINE_COLOR);
                g2d.draw(new Line2D.Float(p1.getxPos() + PADDING, p1.getyPos() + PADDING,
                        p2.getxPos() + PADDING, p2.getyPos() + PADDING));
            }
        }

    }
}
