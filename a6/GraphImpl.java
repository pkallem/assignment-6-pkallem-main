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
    public Map<String, Double> dijkstra(String start) {
        // Initialize distance map with all nodes and infinite distances except for the start node
        Map<String, Double> distanceMap = new HashMap<>();

        for (String nodeName : nodes.keySet()) {
            if (nodeName.equals(start)) {
                distanceMap.put(nodeName, 0.0);
            } else {
                distanceMap.put(nodeName, Double.POSITIVE_INFINITY);
            }
        }

        // Initialize priority queue with start node and distance of 0.0
        Comparator<ShortestPathQueueObject> compare = (a, b) -> Double.compare(a.distance, b.distance);
        PriorityQueue<ShortestPathQueueObject> queue = new PriorityQueue<>(compare);
        queue.offer(new ShortestPathQueueObject(start, 0.0));

        // Use Dijkstra's algorithm to find shortest paths
        while (!queue.isEmpty()) {
            ShortestPathQueueObject nodeObj = queue.poll();
            Node node = nodes.get(nodeObj.label);
            double nodeDist = nodeObj.distance;

            // Update distances of adjacent nodes
            for (Edge edge : node.getAdjacentEdges()) {
                Node adjNode = edge.getDestinationNode();
                double newDist = nodeDist + edge.getWeight();

                if (newDist < distanceMap.get(adjNode.getName())) {
                    distanceMap.put(adjNode.getName(), newDist);
                    queue.offer(new ShortestPathQueueObject(adjNode.getName(), newDist));
                }
            }
        }

        return distanceMap;
    }


}
