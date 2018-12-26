/**
 * Graph is a java class that implements GraphADT, this class uses the GraphNode and GraphEdge classes to construct a graph database.
 *
 * @author Ositadinma Arimah
 * Student #: 250 981 235
 * Mail: oarimah@uwo.ca
 * Course #: CS2210
 * Assignment #5
 *
 *
 */

import java.util.*;

public class Graph implements GraphADT
{
    /**
     * Creates the instant variables graph, graphEdges and n that will contain the a GraphNodes array, and GraphEdge matrix of adjacency, and an
     * integer that will contain length of the GraphNodes array.
     */
    private GraphNode [] Graph;
    private GraphEdge [][] graphEdges;
    private int n;
    /**
     * Graph is the constructor of the class Graph. It initializes the values of n, graph, and graphEdges, and gives n and graph values.
     *
     * @param		n			is an integer that will contain the length of the graph, the number of GraphNodes.
     *
     */
    public Graph (int n)
    {
        this.n=n;
        Graph = new GraphNode [n];
        for (int i=0; i<n; i++) Graph[i]=new GraphNode(i);
        graphEdges = new GraphEdge [n][n];
    }
    /**
     * insertEdge is a method that will insert a GraphEdge into the adjacency matrix defined by edges.
     *
     * @param		u			is a GraphNode that contains the information of the first end point of the edge.
     * @param		v			is a GraphNode that contains the information of the second end point of the edge.
     * @param		busLine		is a character that contains the information of the to which the edge belongs.
     *
     */
    public void insertEdge(GraphNode u, GraphNode v, char busLine) throws GraphException
    {
        //Creates and initializes the values of GraphNodeU and GraphNodeV.
        int GraphNodeU=u.getName();
        int GraphNodeV=v.getName();
        //Checks if GraphNodeU and GraphNodeV exist in the graph, if not, throws a GraphException.
        if (GraphNodeU<0||GraphNodeU>n-1||GraphNodeV<0||GraphNodeV>n-1)
            throw new GraphException("Insertion failed, GraphNode does not exist.");
        //Checks if the edge at GraphNodeU and GraphNodeV exits in the graph, if so, throws a GraphException.
        if (graphEdges[GraphNodeU][GraphNodeV]!=null)
            throw new GraphException("The GraphEdge connecting u and v already exists.");
        //Adds the edge to the adjacency matrix.
        graphEdges[GraphNodeU][GraphNodeV]=new GraphEdge(u, v, busLine);
        graphEdges[GraphNodeV][GraphNodeU]=new GraphEdge(v, u, busLine);
    }
    /**
     * getNode is a getter method for a GraphNode in the graph.
     *
     * @param		u			is an integers that may refer to the name of an existing GraphNode.
     *
     * @return					returns a GraphNode with name of u, else produces a GraphException.
     *
     */
    public GraphNode getNode(int u) throws GraphException
    {
        //Checks if a GraphNode of the name u exist in the graph, if not, throws a GraphException.
        if (u<0||u>n-1)
            throw new GraphException("GraphNode does not exist");
        //returns the Node of the name u.
        return Graph[u];
    }
    public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException
    {
        //Creates an integer GraphNodeU that will hold the name of u.
        int GraphNodeU=u.getName();
        //Checks if u exist in the graph, if not, throws a GraphException.
        if (GraphNodeU<0||GraphNodeU>n-1)
            throw new GraphException("GraphNode does not exist");

        //Creates an ArrayList, graphEdgeIterator, that will hold the list of possible edges that connect with Node u.
        List<GraphEdge> graphEdgeIterator= new ArrayList<>();
        //Iterates throw the adjacency matrix, checks if there are any incident edges for u.
        for (int i=0; i<n; i++)if (graphEdges[GraphNodeU][i]!=null) graphEdgeIterator.add(graphEdges[GraphNodeU][i]);
        //Checks if graphEdgeIterator is empty, if it is not, return an iterator of graphEdgeIterator.
        if (!graphEdgeIterator.isEmpty())
            return graphEdgeIterator.iterator();
        //If it is empty, return null.
        return null;
    }/**
 * getEdge is a getter for edges in Graph.
 *
 * @param		u			is a GraphNode that contains the information of the first end point of the edge.
 * @param		v			is a GraphNode that contains the information of the second end point of the edge.
 *
 * @return					returns the graphEdge connecting Nodes u and v.
 *
 */
public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException
{
    //Creates and initializes the values of GraphNodeU and GraphNodeV.
    int GraphNodeU=u.getName();
    int GraphNodeV=v.getName();
    //Checks if either GraphNodes u or v do not exist, if one or both don't exist, throws a GraphException.
    if (GraphNodeU<0||GraphNodeU>n-1||GraphNodeV<0||GraphNodeV>n-1)
        throw new GraphException("GraphNodes u and/or v do not exist");
    //Checks if there is an Edge connecting u and v, if not, throws a GraphException.
    if (!areAdjacent(u, v))
        throw new GraphException("There is no edge between u and v.");
    //returns the GraphEdge connecting u an v.
    return graphEdges[GraphNodeU][GraphNodeV];
}

    /**
     * areAdjacent
     *
     * @param		u			is a GraphNode that contains the information of the first end point of the graphedge.
     * @param		v			is a GraphNode that contains the information of the second end point of the graphedge.
     *
     * @return					returns a boolean to wither the nodes are adjacent or not.
     *
     */
    public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException
    {
        //Creates and initializes the values of GraphNodeU and GraphNodeV.
        int GraphNodeU=u.getName();
        int GraphNodeV=v.getName();
        //Checks if either GraphNodes u or v do not exist, if one or both don't exist, throws a GraphException.
        if (GraphNodeU<0||GraphNodeU>n-1||GraphNodeV<0||GraphNodeV>n-1)
            throw new GraphException("Graph Nodes u and/or v do not exist");
        //Checks if there exist an graphEdge connecting u and v, if not, return false.
        if (graphEdges[GraphNodeU][GraphNodeV]==null)
            return false;
        //return true;
        return true;
    }
}
