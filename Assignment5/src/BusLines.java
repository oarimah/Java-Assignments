/**
 * BusLines is a class that extracts a maze from a text file, constructs it and save it into a Graph, and produces a
 * solution for it.
 *
 * @author Ositadinma Arimah
 * Student #: 250 981 235
 * Mail: oarimah@uwo.ca
 * Course #: CS2210
 * Assignment #5
 *
 *
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BusLines {
    /**
     * Creates the instant variables
     */
    private Graph maze;
    private int maximumBusChanges;
    private GraphNode startNode;
    private GraphNode destinationNode;

    /**
     * BusLines is the constructor of the class BusLine. It creates the Graph database that will contain the information of the
     * map, it will read the contents of a text file that will contains the information about the map.
     *
     * @param		inputFile		is a String that contains the location of the text file containing the  map's information.
     *
     */
    public BusLines(String inputFile) throws MapException {
        int width, height;
        try {
            //reads inputFile
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line = br.readLine(); // read in first line of file
            StringTokenizer token = new StringTokenizer(line);
            token.nextToken(); // ignore scale factor, initialize other variables
            width = Integer.parseInt(token.nextToken()); // set the width of the graph from file (2nd token)
            height = Integer.parseInt(token.nextToken()); // set the height of the graph from file (3rd token)
            maze = new Graph(width * height); // create maze with size width * height
            maximumBusChanges = Integer.parseInt(token.nextToken()); // set the max number of bus changes from file(4th token)
            int count = 0;
            String succLine; // intialize succeeding line
            while (count < (width * height)) { // while counter is less than the size of the graph
                line = br.readLine(); // read in first line
                succLine = br.readLine(); // read in succeeding line
                for (int i = 0; i < line.length(); i++) { // use for loop to loop through the entire first line
                    if (i % 2 != 0) { // if i is odd
                        if (line.charAt(i) != ' ') // if the character is a character not a space insert an edge in the graph
                            maze.insertEdge(maze.getNode(count - 1), maze.getNode(count), line.charAt(i));
                    } else {
                        // if the current character is S, create StartNode
                        if (line.charAt(i) == 'S')
                            startNode = maze.getNode(count);
                        // if the current character is D, create destinationNode
                        if (line.charAt(i) == 'D')
                            destinationNode = maze.getNode(count);
                        if (succLine != null) // if the succeeding line is not null
                            // if the character at the current index is not empty
                            if (succLine.charAt(i) != ' ')
                                // insert an edge
                                maze.insertEdge(maze.getNode(count), maze.getNode(count + width),
                                        succLine.charAt(i));
                        count++; // increment count
                    }
                }
            }
            br.close(); // close buffer reader
        } catch (FileNotFoundException e) {// catch exceptions
            throw new MapException("The map does not exist");
        } catch (IOException e) {
            throw new MapException("Error, reading in from file");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * A getter method for the graph.
     *
     * @return					returns Graph database that contains the maze.
     *
     */
    public Graph getGraph() throws MapException {
        if (maze == null) // if there is a maze return the maze else throw an exception
            throw new MapException("Map could not be created");
        return maze;
    }

    /**
     * trip() is a method that returns the path from the starting point to the end point
     * in terms of nodes.
     *
     * @return the path from starting to end point.
     */
    public Iterator<GraphNode> trip() throws MapException {
        if (maze == null) // if there is no maze throw an exception
            throw new MapException("Graph could not be created when bus lines was initialized!");
        LinkedList<GraphNode> graphList = new LinkedList<>(); // else initialize a list
        if (path(startNode, destinationNode, maximumBusChanges, ' ', graphList))//if path returns true(exists)
            return graphList.iterator(); // return iterator of graphList
        return null; // if false return null as there is no path
    }
    /**
     * path() is a helper  method that returns the true or false if there is a path between a starting point and ending
     * point.
     *
     * @param currentNode
     *            the graph of the map
     * @param destinationNode
     *            end point
     * @param changes
     *             point
     * @param character
     *              busLine character
     * @param graphList
     *              Linked List for graph
     * @return  true or false
     *
     */
    private boolean path(GraphNode currentNode, GraphNode destinationNode, int changes, char character,
                               LinkedList<GraphNode> graphList) {
        currentNode.setMark(true); // mark the currentNode
        if (currentNode.getName() == destinationNode.getName()) { // if the currentNode is the destinationNode
            graphList.add(currentNode); // add it to the graphList and return true
            return true;
        }
        try {
            Iterator<GraphEdge> iterator = maze.incidentEdges(currentNode); // get the incident edges with method made on graph class
            while (iterator.hasNext()) { // loop through all the incident edge
                boolean change = false; // set boolean change to false
                GraphEdge graphEdge = iterator.next(); // get the incident edge
                GraphNode graphNode = graphEdge.secondEndpoint(); // get the graphNode at the other end of the edge
                char newChar = graphEdge.getBusLine(); // get the busLine of the edge
                if (character != ' ' && character != newChar)
                    change = true;// set to true after the if condition
                if (!graphNode.getMark()) { // if node is not marked set new changes to  changes
                    int newChanges = changes;
                    if (change) // if change is true
                        newChanges--; // decrement the new changes
                    if (newChanges >= 0) // if the new changes is still greater than or equal to 0, call path recursively
                        // if path returns true with the new variables in place add the current node to the graphList and return true
                        if (path(graphNode, destinationNode, newChanges, newChar, graphList)) {
                            graphList.add(currentNode);
                            return true;
                        }
                }
            }
        } catch (GraphException e) {// catch exceptions
            System.out.println(e.getMessage());
        }
        currentNode.setMark(false); // mark the currentNode to false and return false
        return false;
    }
}
