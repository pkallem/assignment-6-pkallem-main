# A6-Dijkstra

For this assignment you will be performing Dijkstra's Algorithm
on the directed graph you created in Assignment 5. You can copy over all of the graph code (GraphImpl, NodeImpl, EdgeImpl, etc) that you wrote in Assignment 5 and paste it in this assignment before getting started. Your only task is to implement the Dijkstras method but the graph code is necessary to do so. The graded portion of the assignment will be the GraphImpl.java class and only be testing the Djkstra's method you write. The behind the scenes of your graph are not tested, as long as the methods in GraphImpl.java function correctly. You are encouraged to create tests in Main.java to test your code. 

## Task: Implement the Dijkstra's method in Graph.java
```
    Map<String, Double> dijkstra(String start);
```   
 
### Node.java

Think critically here about what fields you can add to a Node to implement Dijkstra's algorithm. 
>**Hint:** You will have to enhance the Node class for Dijkstra's

# Getting Started
In previous semesters, many students have had difficulty starting with this assignment, so we wanted to devote a section to helping everyone get started. If you believe you already have an idea of what to do, then you are by no means required to structure your code as we recommend below. There are many correct ways to implement a graph, so your intuition may be easier to follow in certain cases.

You will also notice two different Gradescope assignments online. The first assignment is 9 points and tests the functionality of your code. The second assignment is 1 point and measures the efficiency of your code. The scores of the two assignments will be combined for a total possible score of 10 points.

You will also, at some point, find it useful to implement an algorithm to print out your graph for debugging purposes. Since there are many ways to implement the classes for this assignment, we can't give one print method that will work for everybody. Instead, we will give an algorithm below that will be useful for printing graphs.
```
For each Node n in the graph
    Print the name of n
    For each edge e that leaves n (i.e. edges whose source is n)
        print("\t" + n.name + "-->" + e.dest.name)
```
This will end up printing each node and then for each node printing all of the edges that leave that node.

Before you start writing code for Dijkstra's, it will be useful to write down some examples and trace through your logic to make sure it covers every case. Once you are confident in your intuition, then go ahead and try implement the algorithm.

Dijkstra's Priority Queue and Comparator function
```
Comparator<ShortestPathQueueObject> compare = (a, b) -> Double.compare(a.distance, b.distance);
 PriorityQueue<ShortestPathQueueObject> queue = new PriorityQueue<ShortestPathQueueObject>(compare);
 ```
Above is code to create a priority queue and the function to compare elements in the priority queue in Java, which is needed for Dijkstras algorithm. This is using the Comparator interface to make a comparison function via Java lambda functions (you don't need to know this or worry about this) and pass it in as a parameter when the priority queue is initialized. Once you do this, you can use the priority queue like normal in the rest of your code and assignment and it will function like a basic minimum priority queue that you learned in class.

>**NOTE:** ShortestPathQueueObject is just an arbitrary object I made that has two fields: 1) a nodes string name, 2) the nodes distance from the source node passed into dijsktras. I recommend people make an object like this to use in the priority queue because it will make it much simpler to use the priority queue, get shortest distances, and add to the hash map, etc. If you don't want to make this object, you can simply do this with Node by adding some "distance" field in your Node class. This is the object below for reference. 
```
public class ShortestPathQueueObject {
 public String label;
 public double distance; 
 
 public ShortestPathQueueObject(String label, double distance) {
  this.label = label;
  this.distance = distance;
 }
}
```
