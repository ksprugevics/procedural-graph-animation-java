package gui.event_handlers;

import components.Node;
import components.NodeManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static gui.control_components.SelectionControls.LINE_EQUATION_LABEL;
import static gui.control_components.SelectionControls.POSITION_LABEL;
import static gui.control_components.SelectionControls.VELOCITY_LABEL;
import static gui.panels.AnimationPanel.PADDING;

public class NodeSelector implements MouseListener {

    private static NodeManager MANAGER = NodeManager.getInstance();

    @Override
    public void mouseClicked(MouseEvent e) {
        selectNode(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        selectNode(e.getX(), e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}


    private void selectNode(int x, int y) {
        for (Node node : NodeManager.getNodes()) {
            if (x >= node.getxPos() - PADDING &&  x <= node.getxPos() + PADDING &&
            y >= node.getyPos() - PADDING && y <= node.getyPos() + PADDING) {
                node.setSelected(true);
                NodeManager.setSelectedNode(node);
            } else {
                node.setSelected(false);
                POSITION_LABEL.setText("x:  y: ");
                VELOCITY_LABEL.setText("velocity: ");
                LINE_EQUATION_LABEL.setText("y=ax + b");
            }
        }
    }
}
