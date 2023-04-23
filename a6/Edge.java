package a6;

public interface Edge {
    /* You will include the method signatures (return type, name, and arg types) for any edge methods you
    need in this file. */

    /*Hint: Make sure you update the Edge interface in Edge.java when you add a new method implementation
    in EdgeImpl.java, and vice-versa.  getName() in Node.java and NodeImpl.java is an example.  Also, files in
    previous homeworks (e.g., BST.java and BSTImpl.java in homework 3) are good examples of
    interfaces and their implementations.
     */

    Node getSourceNode();

    Node getDestinationNode();

    double getWeight();

    void setWeight(int weight);
}
