/**
 * GraphNode is java class that will be used to store information about the nodes of the graph.
 *
 * @author Ositadinma Arimah
 * Student #: 250 981 235
 * Mail: oarimah@uwo.ca
 * Course #: CS2210
 * Assignment #5
 *
 *
 */
public class GraphNode
{
    //creates private integer and boolean that will store the name of the GraphNode and the mark of GraphNode, respectively.
    private int name;
    private boolean mark;

    //The constructor for the class, initializes the value of the variable name.
    public GraphNode (int name){

        this.name = name;
    }

    //A setter for the variable mark.
    public void setMark (boolean mark){

        this.mark=mark;
    }

    //A getter for the variable mark.
    public boolean getMark(){

        return mark;
    }

    //A getter for the variable name.
    public int getName(){

        return name;
    }

}
