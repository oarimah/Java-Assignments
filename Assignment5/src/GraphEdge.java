/**
 * GraphEdge is java class that will contain the information about the edges of the graph, this class will contain the values of the
 * first and second end points of the edge, the edge's type and any labels for that edge.
 *
 * @author Ositadinma Arimah
 * Student #: 250 981 235
 * Mail: oarimah@uwo.ca
 * Course #: CS2210
 * Assignment #5
 *
 *
 */
public class GraphEdge
{
    /**
     * Creates the instance variables busLine, type u, v that will hold the values of the busLine, the type and first end point and
     * second end point of the edge.
     */
    private char busLine;
    private GraphNode u, v;

    //The constructor for the class, initializes the values of the first end point, the second end point, and the busLine.
    public GraphEdge (GraphNode u, GraphNode v, char busLine)
    {
        this.u=u;
        this.v=v;
        this.busLine=busLine;
    }

    //A getter for the first end point GraphNode.
    public GraphNode firstEndpoint(){

        return u;
    }

    //A getter for the second end point GraphNode.
    public GraphNode secondEndpoint(){

        return v;
    }
    //A getter for the busLine character.
    public char getBusLine(){

        return busLine;
    }
}