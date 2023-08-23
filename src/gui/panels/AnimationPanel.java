package gui.panels;

import components.Node;
import components.NodeManager;
import gui.control_components.SelectionControls;
import gui.event_handlers.NodeSelector;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import static utils.Calculations.calculateAlphaByDistance;
import static utils.Calculations.calculateLineEquation;
import static utils.Calculations.calculateStrokeThicknessByDistance;
import static utils.Calculations.euclideanDistanceBetweenNodes;

public class AnimationPanel extends JPanel {

    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 900;

    public static Color BACKGROUND_COLOR = new Color(168, 211, 255);
    public static Color NODE_COLOR = new Color(0, 178, 255);
    public static Color LINE_COLOR = new Color(0, 0, 0);

    public static final Color SELECTED_NODE_LINE_COLOR = new Color(231, 108, 252, 125);
    public static final Color SELECTED_NODE_COLOR = new Color(224, 8, 23);

    public static int NODE_SIZE = 50;
    public static int PADDING = NODE_SIZE / 2;
    public static float LINE_MAX_WIDTH = 5;
    public static int LINE_MAX_DISTANCE = 200;

    public static final int INITIAL_TIMER_DELAY = 20;
    public static Timer ANIMATION_TIMER;
    public static boolean ANIMATION_PAUSED = false;

    public AnimationPanel() {
        ANIMATION_TIMER = new Timer(INITIAL_TIMER_DELAY, e -> {
            if (!ANIMATION_PAUSED) {
                NodeManager.getNodes().forEach(Node::move);
                repaint();
            }
        });
        ANIMATION_TIMER.start();
        addMouseListener(new NodeSelector());
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
        for (Node node : NodeManager.getNodes()) {
            g2d.setColor(NODE_COLOR);
            if (node.isSelected()) {
                drawSelectedNodeLine(g2d, node);
                g2d.setColor(SELECTED_NODE_COLOR);
                SelectionControls.updateInfoLabels(node);
            }
            g2d.fillOval((int) node.getxPos() - PADDING, (int) node.getyPos() - PADDING, NODE_SIZE, NODE_SIZE);
        }
    }

    private static void drawLinesBetweenNodes(Graphics2D g2d) {
        for (int i = 0; i < NodeManager.getNodes().size(); i++) {
            List<Node> neighboringNodes = new ArrayList<>();
            for (int j = 1; j < NodeManager.getNodes().size(); j++) {
                Node p1 = NodeManager.getNodes().get(i);
                Node p2 = NodeManager.getNodes().get(j);

                float dist = euclideanDistanceBetweenNodes(p1, p2);
                if (dist > LINE_MAX_DISTANCE) {
                    continue;
                }
                neighboringNodes.add(p2);
                g2d.setStroke(new BasicStroke(calculateStrokeThicknessByDistance(dist)));
                g2d.setColor(new Color(LINE_COLOR.getRed(), LINE_COLOR.getGreen(), LINE_COLOR.getBlue(),
                        calculateAlphaByDistance(dist)));
                g2d.draw(new Line2D.Float(p1.getxPos(), p1.getyPos(),
                        p2.getxPos(), p2.getyPos()));
            }

            int[] xPoss = neighboringNodes.stream().mapToInt(node -> (int) node.getxPos()).toArray();
            int[] yPoss = neighboringNodes.stream().mapToInt(node -> (int) node.getyPos()).toArray();
            Polygon polygon = new Polygon(xPoss, yPoss, xPoss.length);

            g2d.setColor(new Color(Math.max(BACKGROUND_COLOR.getRed() - 50, 0), Math.max(BACKGROUND_COLOR.getGreen() - 50, 0), Math.max(BACKGROUND_COLOR.getBlue() - 50, 0), 50));
            g2d.fill(polygon);

        }
    }

    private static void drawSelectedNodeLine(Graphics2D g2d, Node node) {
        g2d.setStroke(new BasicStroke(LINE_MAX_WIDTH, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{20}, 0));
        g2d.setColor(SELECTED_NODE_LINE_COLOR);
        g2d.draw(new Line2D.Float(-WINDOW_WIDTH * 2, calculateLineEquation(-WINDOW_WIDTH * 2, node.getLineEquation()),
                WINDOW_WIDTH * 2, calculateLineEquation(WINDOW_WIDTH * 2, node.getLineEquation())));
    }
}
