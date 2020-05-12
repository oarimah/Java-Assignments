/**
 * Ositadinma Arimah
 * 250 981 235
 * oarimah@uwo.ca
 * CS3340b Assignment 2
 * uandf.java
 */

public class uandf {
    private int[] parent,rank;
    private boolean isFinalSet = false;
    private int temp;
    //constructs an union-find data type with n elements, 1, 2, . . . , n.
    public uandf(int n){//init the variables
        parent = new int[n];
        rank = new int[n];
    }
    // creates a new set whose only member (and thus representative) is i.
    public void makeSet(int i){
        parent[i] = i;
    }
    //unites the dynamic sets that contains i and j, respectively, into a new
    //set that is the union of these two sets.
    public void unionSets(int i, int j){
        i = findSet(i);//find the two sets
        j = findSet(j);
        if(rank[i] > rank[j]){
            parent[j] = parent[i];
        }
        else if(rank[i] < rank[j]){
             parent[i] = parent[j];
        }
        else if(rank[i]==rank[j]){
            parent[j] = parent[i];
            rank[i]++;
        }
    }
    // returns the representative of the set containing i.
    public int findSet(int i){
        if(!isFinalSet){
            if(parent[i] != i){
                return (parent[i] = findSet(parent[i]));
            }
            else{
                return i;
            }
        }
        else{
            return parent[i];
        }
    }

  //returns the total number of current sets and finalizes the current sets:
  //(i) make set() and union sets() will have no effect after this operation and (ii) resets
  //the representatives of the sets so that integers from 1 to f inal sets() will be used as
  //representatives.
    public int finalSets(){
        for(int i = 1; i < parent.length; i++){ //loop through the parent
            if(parent[i] != 0){//if the parent at i is not 0
                findSet(i);// then find the set that 'i' belongs to
            }
        }
        temp = 1;//init the counter
        for(int i = 1; i < parent.length; i++){//loop through the parent
            if (parent[i] == i){// if the parent of i is i, then the parent is now the incremented temp value
                parent[i] = temp++;
                rank[i] = 1;//set rank of i to 1
            }
            else{// else the rank of i is 0
                rank[i] = 0;
            }
        }
        for(int i = 1; i < parent.length; i++){//loop thru the parent
            if(rank[i] == 0 && parent[i] > 0){
                parent[i] = parent[parent[i]];//if rank of i is 0 and the parent of i is 0, set parent of i to its parent
            }
        }
        //finally reset the flag
        //return temp value
        isFinalSet = true;
        return temp-1;
    }
}