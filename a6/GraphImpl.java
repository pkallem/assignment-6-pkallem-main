package a6;

import java.util.*;

public class GraphImpl implements Graph {
    Map<String, Node> nodes; //Do not delete.  Use this field to store your nodes.
    // key: name of node. value: a5.Node object associated with name

    public GraphImpl() {
        nodes = new HashMap<>();
    }

    @Override
    public boolean addNode(String name) {
        if (name == null || nodes.containsKey(name)) {
            return false;
        }
        NodeImpl node = new NodeImpl(name);
        nodes.put(name, node);
        return true;
    }

    @Override
    public boolean addEdge(String source, String destination, double weight) {
        if (!nodes.containsKey(source) || !nodes.containsKey(destination) || weight < 0.0) {
            return false;
        }

        if (nodes.get(source).isAdjacent(nodes.get(destination))) {
            return false;
        }

        nodes.get(source).addEdge(nodes.get(destination), weight);
        return true;
    }

    @Override
    public boolean deleteNode(String name) {
        if (!nodes.containsKey(name)) {
            return false;
        }
        Node delNode = nodes.get(name);
        for (Node value : nodes.values()) {
            if (delNode.isAdjacent(value)) {
                delNode.deleteEdge(value);
            }

            if (value.isAdjacent(delNode)) {
                value.deleteEdge(delNode);
            }
        }

        nodes.remove(name);
        return true;
    }

    @Override
    public boolean deleteEdge(String source, String destination) {
        if (!nodes.containsKey(source) || !nodes.containsKey(destination)) {
            return false;
        }

        if (!nodes.get(source).isAdjacent(nodes.get(destination))) {
            return false;
        }
        nodes.get(source).deleteEdge(nodes.get(destination));
        return true;
    }

    @Override
    public int numNodes() {
        return nodes.size();
    }

    @Override
    public int numEdges() {
        int[] edges = new int[1];

        for (Node value : nodes.values()) {
            edges[0] += value.getNumEdges();
        }
        return edges[0];
    }

    @Override
    public Stack<String> topoSort() {
        Stack<String> sorted = new Stack<>();
        Stack<String> sort = new Stack<>();
        int count = nodes.size();

        nodes.forEach((key, value) -> {
            if(value.getNumEdges() == 0) {
                sorted.push(key);
                sort.push(key);
            }
        });

        while(!sort.empty()) {
            String node = sort.pop();

            for(Edge edge : nodes.get(node).getAdjacentEdges()) {
                Node destinationNode = edge.getDestinationNode();
                destinationNode.decrementNumEdges();

                if(destinationNode.getNumEdges() == 0) {
                    sorted.push(destinationNode.getName());
                    sort.push(destinationNode.getName());
                }
            }
        }

        if(sorted.size() != count) {
            return new Stack<>();
        }

        for(Node value : nodes.values()) {
            for(Edge edge : value.getAdjacentEdges()) {
                Node destinationNode = edge.getDestinationNode();
                destinationNode.incrementNumEdges();
            }
        }

        return sorted;
    }

    @Override
    public Map<String, Double> dijkstra(String startNode) {
        Map<String, Double> distances = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Comparator<ShortestPathQueueObject> compare = (a, b) -> Double.compare(a.distance, b.distance);
        PriorityQueue<ShortestPathQueueObject> queue = new PriorityQueue<>(compare);

        // Set distances to all nodes to infinity except the starting node, which has distance 0.
        for (String node : nodes.keySet()) {
            if (node.equals(startNode)) {
                distances.put(node, 0.0);
            } else {
                distances.put(node, Double.POSITIVE_INFINITY);
            }
        }

        // Add the starting node to the priority queue.
        queue.add(new ShortestPathQueueObject(startNode, 0.0));

        // While there are still nodes to visit, keep visiting them.
        while (!queue.isEmpty()) {
            ShortestPathQueueObject curr = queue.poll();
            String currNode = curr.label;

            // If we've already visited this node, skip it.
            if (visited.contains(currNode)) {
                continue;
            }

            visited.add(currNode);

            // Update the distances to all neighbors of the current node.
            for (Edge edge : nodes.get(currNode).getAdjacentEdges()) {
                Node neighbor = edge.getDestinationNode();
                String neighborName = neighbor.getName();
                double neighborDistance = distances.get(currNode) + edge.getWeight();

                if (!visited.contains(neighborName) && neighborDistance < distances.get(neighborName)) {
                    distances.put(neighborName, neighborDistance);
                    queue.add(new ShortestPathQueueObject(neighborName, neighborDistance));
                }
            }
        }

        return distances;
    }

}
