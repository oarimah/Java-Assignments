/**
 * Written by:
 * Ositadinma Arimah
 * 250 981 235
 * oarimah@uwo.ca
 * CS3340b Assignment 3
 **/

//Node.java is used to store generic node
public class Node {
    // Attributes
    public int key, name = 0;
    public Node node;


    public Node(int name) {
        this.name = name;
        this.node = null;
        this.key = Integer.MAX_VALUE;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}