/**
 * Ositadinma Arimah
 * 250 981 235
 * oarimah@uwo.ca
 * CS3340b Assignment 2
 * binaryImage.java
 */
//import necesary libraries
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;

public class binaryImage {
    public static void main(String[] args){
        int[][] girl_image = null; //initialize the 2d array for the girl.img
        girl_image = new int[71][71];//intialize the size of the 2d array
        boolean[][] flag; //initialize the flags for the img
        flag = new boolean[71][71];//intialize the size of the 2d array
        int[] finalSets,characterArray;
        int character;
        String line=null;

        try {//attempt to load the image in the try statement

            //InputStream inputStream = new FileInputStream("C:\\Users\\oarim\\IdeaProjects\\3340assn\\girl.img");
            //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            for(int i = 0; bufferedReader.ready(); i++) {
                line = bufferedReader.readLine();//save each line in the file which is passed as the parameter
                for (int j = 0; j < line.length(); j++) {
                    if (line.charAt(j) == '+') {// if the character is a plus then save a one and set the flag to true
                        flag[i][j] = true;
                        girl_image[i][j] = 1;
                    }else{}// if its not a + then do nothing
                }
            }bufferedReader.close();//close the file
        }
        catch (IOException exception){// catch statement
            System.out.println("Can not read file");
        }
        //print out part 1
        // the input binary image
        System.out.print("-----------------------------------------------------------");
        System.out.print('\n');
        System.out.print("PART 1 - INPUT IMAGE IN BINARY");
        System.out.print('\n');
        System.out.print("-----------------------------------------------------------");
        System.out.print('\n');
        for(int i = 0; i < girl_image.length; i++){
            for(int j = 0; j < girl_image.length; j++){
                System.out.print(girl_image[i][j]);//print out the binary image for the image
            }System.out.print('\n');
        }System.out.print('\n');
        System.out.print('\n');

        //create the union find and the connected components for part 2
        uandf girlImageSet = new uandf((int)Math.pow(girl_image.length,2+1));
        for(int i = 0; i < girl_image.length; i++) {
            for (int j = 0; j < girl_image.length; j++) {
                if (girl_image[i][j] == 1) {//if this location is a one then make the set
                    girlImageSet.makeSet((i * girl_image.length + j) + 1);
                    if (j > 0 && girl_image[i][j - 1] == 1) { girlImageSet.unionSets(i * girl_image.length + (j - 1) + 1, i * girl_image.length + j + 1);
                    }
                    if (i > 0 && girl_image[i - 1][j] == 1) { girlImageSet.unionSets((i - 1) * girl_image.length + j + 1, i * girl_image.length + j + 1);
                    }
                }
            }
        }
        finalSets = new int[girlImageSet.finalSets()];// set the size of both these variables
        characterArray = new int[finalSets.length];

        //print out part two
        //The connected component image where each component is labelled with a unique character.
        System.out.print("-----------------------------------------------------------");
        System.out.print('\n');
        System.out.print("PART 2 - CONNECTED COMPONENT IMAGE WITH UNIQUE CHARACTER");
        System.out.print('\n');
        System.out.print("-----------------------------------------------------------");
        System.out.print('\n');

        for(int i = 0; i < girl_image.length; i++){
            for(int j = 0; j < girl_image.length; j++){
                String space= " ";
                character = girlImageSet.findSet(i * girl_image.length + j + 1) + 96;// give a character to character variable
                if ( character== 96){ System.out.print(space);//the the character is 96 then print out a space
                }
                else{
                    System.out.print((char)character);//if the character is not 96, print out the character and increment the array
                    finalSets[character - 97]++;
                }
            }System.out.print('\n');
        }System.out.print('\n');

        int[] labelsArray = new int[finalSets.length];//sort the arrays for part 3
        for(int i = 0; i < finalSets.length; i++){ labelsArray[i] = finalSets[i];
        }
        for(int i = 0; i < labelsArray.length; i++){ characterArray[i] = labelsArray[i];
        }
        Arrays.sort(labelsArray);

        // Print part 3
        //a list sorted by component size, where each line of the list contains the size and the
        //label of a component,
        System.out.print("-----------------------------------------------------------");
        System.out.print('\n');
        System.out.print("PART 3 - LIST OF COMPONENT SIZE");
        System.out.print('\n');
        System.out.print("-----------------------------------------------------------");
        System.out.print('\n');
        for(int i = 0; i < labelsArray.length; i++){
            for(int j = 0; j < characterArray.length; j++){
                if(labelsArray[i] == characterArray[j]){
                    System.out.println((char)(j + 97) + ":\t" + labelsArray[i]);//print out the characters and it's label
                    characterArray[j] = characterArray[j] -1;
                    break;
                }
            }
        }
        System.out.print('\n');
        System.out.print('\n');

        //print out part4
        //same as 2 with the connected components whose sizes are less than four remove
        System.out.print("-----------------------------------------------------------");
        System.out.print('\n');
        System.out.print("PART 4 - CONNECTED COMPONENTS WITH SIZES LESS THAN 4 REMOVED");
        System.out.print('\n');
        System.out.print("-----------------------------------------------------------");
        System.out.print('\n');
        for (int i = 0; i < girl_image.length; i++) {
            for (int j = 0; j < girl_image.length; j++) {
                character = girlImageSet.findSet(i * girl_image.length + j + 1) + 96;//assign a character to the variable character
                //if the size is one or two then print out a space. ie, remove it
                if (character == 96 || finalSets[character - 97] == 1 || finalSets[character - 97] == 2){ System.out.print(' ');}
                else {System.out.print((char)character);}
            }
            System.out.print('\n');
        }
        System.out.print('\n');
        System.out.print("-----------------------------------------------------------");
        System.out.print('\n');
        System.out.print("PPOGRAM FINISHED");
        System.out.print('\n');
        System.out.print("-----------------------------------------------------------");
        System.out.print('\n');
    }
}