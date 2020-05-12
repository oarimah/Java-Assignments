/**
 * Written by:
 * Ositadinma Arimah
 * 250 981 235
 * oarimah@uwo.ca
 * CS3340b Assignment 3
 **/

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.*;
import java.util.*;


public class MST {
    //access specifiers
    static File file;
    int numOfNodes;
    String edge = null;
    private HashMap<String, Integer> weightList;
    private HashMap<Integer, ArrayList<Integer>> adjacencyList;
    private HashMap<Integer, Node> nodesList;
    private int i;


    //Initialize Prim's algorithm with a set number of nodes
    public MST(int numOfNodes) {
        this.numOfNodes = numOfNodes;
        this.nodesList = new HashMap<>(numOfNodes);
        this.adjacencyList = new HashMap<>();
        this.weightList = new HashMap<>();
    }
    //set the key to the node
    private void setKey(Node node, int key) {
        node.key = key;// set the key of first node to
    }

    //put entries into the nodeList
    private void putNodeEntry(int n, Node node) {
        this.nodesList.put(n, node);
    }

    //put entries into the adjacencylist
    private void putAdjEntry(int n, ArrayList adjList) {
        this.adjacencyList.put(n, adjList);
    }

    // add add to set of nodes
    private void addToNodesList(Node nodeEntry, int n) {
        if (this.nodesList.size() == 0) {
            setKey(nodeEntry, 0);
            putNodeEntry(n, nodeEntry);
        } else if (this.nodesList.containsKey(n) == false) {
            putNodeEntry(n, nodeEntry);
        }
    }
    private void setAdjacencyList(int u,int v){
        ArrayList adjList;
        adjList = this.adjacencyList.get(u);
        adjList.add(v);
        putAdjEntry(u, adjList);
    }

    // Adds to adjacency list
    private void addToAdjacencyList(int u, int v) {
        // Adds first node
        if (this.adjacencyList.containsKey(u)) {
            setAdjacencyList(u,v);
        } else {
            ArrayList<Integer> newAdjList = new ArrayList<>();
            newAdjList.add(v);
            putAdjEntry(u, newAdjList);
        }
        // Adds second node
        if (this.adjacencyList.containsKey(v)) {
            setAdjacencyList(v,u);
        } else {
            ArrayList<Integer> newAdjList = new ArrayList<>();
            newAdjList.add(u);
            putAdjEntry(v, newAdjList);
        }
    }

    //add to the edge list
    public void addToEdgeList(int u, int v, int w) {//w represents the weight
        edge = u + "-" + v; //let u represent the start, v the end
        this.weightList.put(edge, w);
        Node startNode;
        Node endNode;
        startNode = new Node(u);
        endNode = new Node(v);
        addToNodesList(startNode, u);// add the nodes to the list if it is not there
        addToNodesList(endNode, v);// add the nodes to the list if it is not there
        addToAdjacencyList(u, v); // add the nodes to the adjacenecy list
    }

    //check if an edge exists, if it does return that edge
    private String checkEdge(int u, int v) { //based on the input graph, check if the edge exists
        String check_one, check_two;
        check_one = u + "-" + v;
        check_two = v + "-" + u;
        if (this.weightList.containsKey(check_one)) {
            return check_one;
        } else if (this.weightList.containsKey(check_two)) {
            return check_two;
        }
        return null;
    }

    //this function will loop through the entire map for nodes and generate their keys
    public int[] createKeys(int n) {
        int[] keys;
        keys = new int[n];
        for (Map.Entry<Integer, Node> nodeEntry : this.nodesList.entrySet()) {
            keys[i] = nodeEntry.getKey();
            i++;
        }
        return keys;
    }

    // get the weight of the edge which has been passed through
    private int getEdgeWeight(int u, int v) {
        int w = 0;// initialize weight to 0
        String edge = checkEdge(u, v);
        if (edge != null) {
            w = this.weightList.get(edge);
        }
        return w;
    }

    // Print the input graph in adjacency list representation format listing each edge with its weight.
    public void printAdjacencyList() {
        // print the headers for the output
        System.out.println('\n');
        System.out.println("-------------------------------------------------------------");
        System.out.println("A Print Of The Adjacency List From Input Graph");
        System.out.println("-------------------------------------------------------------");
        //System.out.println("-----------------------------------------------");
        // System.out.println('\n');
        System.out.println("Edges are represented as : 'u-v', 'u=startNode-v=endNode' ");
        System.out.println("-------------------------------------------------------------");
        // System.out.println("-----------------------------------------------");
        System.out.println('\n');
        for (Map.Entry<String, Integer> entry : weightList.entrySet()) {
            System.out.println("Edge: " + entry.getKey() + " and Edge's Weight: " + entry.getValue());
        }
    }

    public void printMSTree() {
        // print the headers for the output
        //System.out.println("--------------------------------------------------");
        System.out.println("-------------------------------------------------------------");
        System.out.println("Edges and Their Weights: Minimum Spanning Tree ");
        System.out.println("-------------------------------------------------------------");
        // System.out.println("--------------------------------------------------");
        System.out.println("Edges are represented as : 'u-v', 'u=startNode-v=endNode' ");
        System.out.println("-------------------------------------------------------------");
        System.out.println();
        for (Map.Entry<Integer, Node> nodeEntry : this.nodesList.entrySet()) { //loop through the node list
            if (nodeEntry.getValue().node != null) {// if the node list is not empty, then get an edge between the verticies
                int u;
                u = nodeEntry.getKey();
                int v;
                v = nodeEntry.getValue().node.name;
                String edge;
                edge = checkEdge(u, v);// check if there is an edge between the two nodes
                if (edge == null) {
                    System.out.println("Error: Edge Does Not Exist");
                } else {// if the edge is not null then...
                    System.out.println("Edge: " + edge + " and Edge's Weight: " + this.weightList.get(edge));
                    // print the edges (with their weights) of the minimum spanning tree, in the order in which
                    //they are produced by the Prim’s algorithm.

                }
            }
        }
    }
    public void printFinsih(){
        System.out.println("-------------------------------------------------------------");
        System.out.println("Output Finished: Ositadinma Arimah");
        System.out.println("-------------------------------------------------------------");
    }

    //perform the prims mst aglorithm
    public void performPrimsAlgo(Heap<Integer> minimumHeap) {
        while (minimumHeap.currentSize != 0) {
            int minNode = minimumHeap.delete_min();//remove the minimum node, just like how the minHeap acts
            ArrayList<Integer> adjacentNodesList;//set up the array list for the nodes
            adjacentNodesList = this.adjacencyList.get(minNode);
            for (int secNode : adjacentNodesList) {//loop thorugh nodes in adjlist
                if (minimumHeap.in_heap(secNode)) {
                    if (this.nodesList.get(secNode).key > getEdgeWeight(minNode, secNode)) {
                        Node u;
                        u = this.nodesList.get(minNode);
                        Node v;
                        v = this.nodesList.get(secNode);
                        int weight;
                        weight = getEdgeWeight(minNode, secNode);
                        v.setNode(u);
                        setKey(v, weight);
                        putNodeEntry(secNode, v);
                        minimumHeap.decrease_key(secNode, weight);
                    }
                }

            }
        }
    }
}
