package a6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeImpl implements Node {

    /* You will include the method signatures (return type, name, and arg types) for any node methods you
    need in this file. */

    /*Hint: Make sure you update the Node interface in Node.java when you add a new method implementation
    in NodeImpl.java, and vice-versa.  getName() in Node.java and NodeImpl.java is an example.  Also, files in
    previous homeworks (e.g., BST.java and BSTImpl.java in homework 3) are good examples of
    interfaces and their implementations.
     */

    /*Also, any node fields you want to add for the object should go in this file.  */

    String name;
    List<Edge> edges;
    int numEdges;

    public NodeImpl(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
        numEdges = 0;
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public List<Edge> getAdjacentEdges() {
        return this.edges;
    }
    @Override
    public int getNumEdges() {
        return this.numEdges;
    }
    @Override
    public void incrementNumEdges() {
        this.numEdges += 1;
    }
    @Override
    public void decrementNumEdges() {
        this.numEdges -= 1;
    }
    @Override
    public boolean addEdge(Node other, double weight) {
        if(other == this) {
            return false;
        }
        Edge temp = new EdgeImpl(this, other, weight);
        edges.add(temp);
        other.incrementNumEdges();
        return true;
    }

    @Override
    public boolean isAdjacent(Node other) {
        for(Edge edge : edges) {
            if(edge.getDestinationNode() == other) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteEdge(Node other) {
        if(!isAdjacent(other)) {
            return false;
        }
        other.decrementNumEdges();
        for(Edge edge : edges) {
            if(edge.getDestinationNode() == other) {
                edges.remove(edge);
                break;
            }
        }
        return true;
    }

}
