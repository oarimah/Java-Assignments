/**
 * Written by:
 * Ositadinma Arimah
 * 250 981 235
 * oarimah@uwo.ca
 * CS3340b Assignment 3
 **/

import java.lang.*;

public class Heap<T> {

    int currentSize;
    //access specifiers
    private int[] originalArray, heapArray;
    private int maximumSize;

    // initializes a heap with the array keys of n elements.
    public Heap(int[] keys, int n) {
        this.originalArray = new int[n + 1];
        //int i=1;
        for (int i = 1; i < originalArray.length; i++) {
            this.originalArray[i] = keys[i - 1];
        }
        this.maximumSize = n;
        this.currentSize = n;
        this.heapArray = new int[(2 * n)];
    }

    //initalize a heap with keys of n elements
    public void heap(int[] keys, int n) {
        for (int i = n; i <= (2 * n) - 1; i++) {
            this.heapArray[i] = i - n + 1;
        }
        for (int i = n - 1; i >= 1; i--) {//for the original array, perform heapify
            heapify(i);
        }
    }

    // returns true if the element whose id is id is in the heap;
    public boolean in_heap(int id) {
        int key;
        key = this.key(id);
        for (int i = 1; i < originalArray.length; i++) {
            if (originalArray[i] == key) {
                return true;
            }
        }
        return false;
    }

    //  returns the minimum key of the heap;
    public int min_key() {
        return this.originalArray[this.heapArray[1]];
    }

    // returns the id of the element with minimum key in the heap;
    public int min_id() {
        return this.heapArray[1];
    }

    // returns the key of the element whose id is id in the heap;
    public int key(int id) {
        return this.originalArray[id];
    }

    // deletes the element with minimum key from the heap;
    public int delete_min() {
        this.currentSize = currentSize - 1;// decrement the counter bc the min key is being removed
        this.originalArray[0] = Integer.MAX_VALUE;//use max value to store first element
        int temp = this.heapArray[1] + this.maximumSize - 1;
        this.heapArray[temp] = 0;
        int minimum;
        minimum = this.originalArray[this.heapArray[1]];
        int index;
        index = temp / 2;
        while (index >= 1) {
            heapify(index);//perform heapify while the index is less than or equal to one
            index = index / 2;
        }
        return minimum;
    }

    //sets the key of the element whose id is id to new key if its
    //current key is greater than new key.
    public void decrease_key(int id, int new_key) {
        if (this.key(id) > new_key) {
            this.originalArray[id] = new_key;
            int i = (id + maximumSize - 1) / 2;
            while (i >= 1) {
                heapify(i);
                i = i / 2;
            }
        }
    }

    //helper method which performs heapify
    private void heapify(int i) {
        if (this.originalArray[this.heapArray[(2 * i) + 1]] > this.originalArray[this.heapArray[2 * i]]) {
            this.heapArray[i] = this.heapArray[2 * i];
        } else {
            this.heapArray[i] = this.heapArray[(2 * i) + 1];
        }
    }
}
