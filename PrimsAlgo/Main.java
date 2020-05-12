/**
 * Written by:
 * Ositadinma Arimah
 * 250 981 235
 * oarimah@uwo.ca
 * CS3340b Assignment 3
 **/
//main method to print the wanted output
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;
public class Main {
    static int arg = -1;

    // MST primsAlgorithm= null;
    //Scanner scanner;
    public static void main(String[] args) {
        //MST primsAlgoritm=null;
        if(args.length != 1) {
            // Print Out Warning Message
            System.out.println();
           // System.out.println("Error: Arguments must be typed as such: asn3.sh < file");
            System.out.println();
            arg = 1;
        }
        MST primsAlgorithm= null;
        try{
            //MST primsAlgorithm= null;
            Scanner scanner;
            if(arg == 1){
                scanner = new Scanner(System.in);
            }
            else{
                scanner = new Scanner(new FileReader(args[0]));

            }
            int nodes = scanner.nextInt();
            primsAlgorithm = new MST(nodes);
            while (!scanner.hasNextLine() == false) {
                int u = scanner.nextInt();//The first line of the input file contains an integer indicating the number of vertices the input gra
                //Each of the remaining lines contains a triple ′′i j w′′ indicating an edge between vertex i and vertex j with cost w.
                int  v = scanner.nextInt();
                int w = scanner.nextInt();
                primsAlgorithm.addToEdgeList(u, v, w);
            }
        } catch (IOException e) {
            System.out.println("File not Found");
        }
        // Print the input graph in adjacency list representation format listing each edge with its
        //weight
        primsAlgorithm.printAdjacencyList();

        //create keys for the nodes
        int[] keys;
        keys = primsAlgorithm.createKeys(primsAlgorithm.numOfNodes);

        // create a minHeap
        Heap<Integer> minHeap = new Heap<>(keys, primsAlgorithm.numOfNodes);

        //initialize a heap with the array keys of numofNodes
        minHeap.heap(keys, primsAlgorithm.numOfNodes);

        //perform prims algoritm on the heap
        primsAlgorithm.performPrimsAlgo(minHeap);


        System.out.println();
        primsAlgorithm.printMSTree();
        System.out.println();
        primsAlgorithm.printFinsih();
    }
}
