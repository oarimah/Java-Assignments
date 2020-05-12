/**
 * Ositadinma Arijmah
 * oarimah@uwo.ca
 * 250 981 235
 * CS3340 Assignment 1
 */
public class asn1_a {

    // fib method.
    private static int fib(int number){
        // if the parameter is 0, return 0
        if(number == 0) {
            return 0;
        }
        // if paramter is 1 then return 1
        else if(number == 1) {
            return 1;
        }
        // if the number is not one or zero then calculate the fibonacci method
        else {
            return fib(number-1) + fib(number-2);
        }
    }

    public static void main(String[] args) {
        //calculate the for loop for the fib up to F(40)
        for(int i = 0; i <= 10; i++) {
            System.out.println("F(" + i*5 + ") = " + fib(i*5));
        }
    }
}
