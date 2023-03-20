package components;

import java.util.ArrayList;
import java.util.List;

// Singleton - manager class
public final class NodeManager {

    private static NodeManager INSTANCE;

    private static int NODE_COUNT = 50;
    private static final List<Node> NODES = new ArrayList<>();
    private static Node selectedNode;

    private NodeManager() {
        resetNodes();
    }

    public static NodeManager getInstance() {
        if (INSTANCE == null) {
            //noinspection InstantiationOfUtilityClass
            INSTANCE = new NodeManager();
        }
        return INSTANCE;
    }

    public static int getNodeCount() {
        return NODE_COUNT;
    }

    public static void setNodeCount(int nodeCount) {
        NODE_COUNT = nodeCount;
    }

    public static List<Node> getNodes() {
        return NODES;
    }

    public static Node getSelectedNode() {
        return selectedNode;
    }

    public static void setSelectedNode(Node selectedNode) {
        NodeManager.selectedNode = selectedNode;
    }

    public static void resetNodes() {
        NODES.clear();
        for (int i = 0; i < NODE_COUNT; i++) {
            NODES.add(new Node());
        }
    }
}
