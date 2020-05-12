/**
 * Ositadinma Arijmah
 * oarimah@uwo.ca
 * 250 981 235
 * CS3340 Assignment 1
 */
public class asn1_b {
    //boolean zerosRemoved = true;
    //array size should be large enough to hold F(300)
    private static final int ARRAY_SIZE = 300;
    // fibonacci method
    private static int[] fib(double number){
        // Create array to store fibonacci number and f(n-1) and f(n-2)
        int[] result = new int[ARRAY_SIZE];
        int[] arrayOne = new int[ARRAY_SIZE];//f(n-1)
        int[] arrayTwo = new int[ARRAY_SIZE];//f(n-2)
        result[ARRAY_SIZE-1]=1;
        arrayOne[ARRAY_SIZE-1]=1;
        if(number == 0)
            result = new int[ARRAY_SIZE];
        else{
            for(int i = 2; i <= number; i++) {
                result = addArrayContents(arrayOne, arrayTwo);//f(n)=f(n-1)+f(n-2)
                //swapArrayContents(arrayOne,arrayTwo);
                arrayTwo = arrayOne;
                //swapArrayContents(arrayOne,result);
                arrayOne = result;
            }
        }
        return result; //f(n)
    }
    private static void swapArrayContents(int[]array, int[] arrayTwo){
       array=arrayTwo;
    }
    private static int[] addArrayContents(int[] array_one, int[] array_two) {
        boolean carryValue = false;//similar to simple math, keep track of a carry value
        //int sumOfIndex = 0;
        int[] sumArray = new int[ARRAY_SIZE];// unlimited size
        for(int i = ARRAY_SIZE; i > 0; i--){// use for loop to loop through the array backwards
            int sumOfIndex = 0;// this is the value of the sum of the index
            if(carryValue==true){// if the carry value is true, set the index sum to one
                carryValue = false;
                sumOfIndex = 1;
            }
            sumOfIndex = sumOfIndex + array_one[i-1] + array_two[i-1];//get the new sum of index from the two arrays at i-1
            if(sumOfIndex > 9) carryValue = true;//if the index is double digits there is a carry value, therefore set the flag to true
            sumArray[i-1] = sumOfIndex % 10;
        }
        return sumArray;
    }
    private static void printArrayContents(int[] array) {
        boolean zerosRemoved = true; //flag for removing leading zeros
        for (int i = 0; i < ARRAY_SIZE; i++){ //print the numbers in the array using a for loop
            while(zerosRemoved==true) {
                if(i < ARRAY_SIZE && array[i] == 0){ // if the number is zero then increment i
                    i++;
                    zerosRemoved= true;
                }
                else zerosRemoved= false; // if it is not a zero then set the flag to false
            }
            if(i < ARRAY_SIZE){// if the array is not all zeros then print out the elements
                System.out.print(array[i]);
            }
            else System.out.print("0");
        }System.out.print("\n");
    }

    public static void main(String[] args) {
        for(int i = 0; i <= 30; i++) {// print all fib numbers to f(300)
            System.out.println("F(" + i*10 + ") = ");
            printArrayContents(fib(i*10));
        }
    }
}