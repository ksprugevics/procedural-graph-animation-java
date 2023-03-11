package components;

import java.util.ArrayList;
import java.util.List;

// Singleton - manager class
public final class NodeManager {

    private static NodeManager INSTANCE;

    private static final int NODE_COUNT = 10;
    private static final List<Node> NODES = new ArrayList<>();

    private NodeManager() {
        for (int i = 0; i < NODE_COUNT; i++) {
            NODES.add(new Node());
        }
    }

    public static NodeManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NodeManager();
        }
        return INSTANCE;
    }

    public List<Node> getNodes() {
        return NODES;
    }
}
