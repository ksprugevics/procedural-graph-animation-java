package gui.event_handlers;

import components.Node;
import components.NodeManager;
import gui.control_components.SelectionControls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static gui.panels.AnimationPanel.PADDING;

public class NodeSelector implements MouseListener {

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
                SelectionControls.updateSpinnerValues(node);
            } else {
                node.setSelected(false);
                SelectionControls.clearInfoLabels();
            }
        }
    }

}
