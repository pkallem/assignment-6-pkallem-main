package a6;

public class EdgeImpl implements a6.Edge {
    /* You will include the implementations for any edge methods you need in this file. */

    /*Hint: Make sure you update the Edge interface in Edge.java when you add a new method implementation
    in EdgeImpl.java, and vice-versa.  getName() in Node.java and NodeImpl.java is an example.  Also, files in
    previous homeworks (e.g., BST.java and BSTImpl.java in homework 3) are good examples of
    interfaces and their implementations.
     */

    /*Also, any edge fields you want to add for the object should go in this file.  */

    Node sourceNode;
    Node destinationNode;
    double weight;

    public EdgeImpl(Node start, Node end, double weight) {
        this.sourceNode = start;
        this.destinationNode = end;
        this.weight = weight;
    }
    @Override
    public Node getSourceNode() {
        return this.sourceNode;
    }

    @Override
    public Node getDestinationNode() {
        return this.destinationNode;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }
}
