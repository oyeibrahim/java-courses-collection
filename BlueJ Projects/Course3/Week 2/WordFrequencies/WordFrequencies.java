
/**
 * Find out how many times each word occurs, and
 * in particular the most frequently occurring word.
 * 
 * @author Duke Software Team
 * @version 1.0
 */
import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies{
    
    //fields
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    //constructor
    public WordFrequencies() {
        //initialises the field to ArrayList
        //to hold the words
        myWords = new ArrayList<String>();
        //to hold the number of occurrence
        myFreqs = new ArrayList<Integer>();
    }
    
    //find new words and count number of occurrences
    public void findUnique(){
        //clear the ArrayList of any element
        myWords.clear();
        myFreqs.clear();
        //choose file
        FileResource resource = new FileResource();
        //iterate over each word in the chosen file
        for(String s : resource.words()){
            //change the words to lowercase... this will help count words that are the same but has
            //different case as the same, if we didnt put this, they will be counted seperately
            //e.g. if we didnt put it, The and the will be counted as different words. thats why we 
            //are converting all words to lowercase
            s = s.toLowerCase();
            //look at the index of the current word in the ArrayList myWords
            int index = myWords.indexOf(s);
            //if the word is not there before
            if (index == -1){
                //NOTE-- this if statement just gets new word, its not counting number of 
                //occurrences its just finding the unique words, i.e. new words
                //add the word to the ArrayList myWords
                myWords.add(s);
                //add just one to the corresponding index in myFreqs
                myFreqs.add(1);
            }//else (if the word is al ready there)... This if statement will count number of 
            //occurrences.
            else {
                //freq = the index of the current word and this current word is already in 
                //myWords so its getting its index in myWords which will correspond to its 
                //index in myFreqs
                int freq = myFreqs.get(index);
                //so, add one to the index of that word
                myFreqs.set(index,freq+1);
            }
        }
    }
    
    //to test the methods
    public void tester(){
        //call the method findUnique... it has no parameter
        findUnique();
        //print the size of the ArrayList myWords
        System.out.println("Number of unique words: "+myWords.size());
        //empty line print
        System.out.println();
        
        //print all words in myWords and all values in myFreqs corresponding to ech other
        for(int k=0; k < myFreqs.size(); k++){
            System.out.println(myWords.get(k)+" : "+ myFreqs.get(k));
        }
        //empty line print
        System.out.println();
        
        ////call the method findMax... it has no parameter
        int index = findMax();
        //print the word at the index in myFreqs with the greatest value. since the index of a word 
        //in myWords and myFreqs correspond... Then print the value at that index
        //so this will print the word with highest ocurrence and its value
        System.out.println("The word that occurs most often is: "+myWords.get(index)+ " : "
                                +myFreqs.get(index));
    }
    
    //to find the index with maximum counts
    public int findMax(){
        //initialise variable max to be equal to the first element in the ArrayList
        //We can use myFreqs here without declaring it because it is a field in this class
        int max = myFreqs.get(0);
        //this will hold the index
        int maxIndex = 0;
        for(int k=0; k < myFreqs.size(); k++){
            //if the value of myFreqs at index k is greater than that at the first index which
            //is set to variable max
            if (myFreqs.get(k) > max){
                //then max willbe equal to the value at that index (with the greatest value)
                max = myFreqs.get(k);
                //this variable will be equal to k which will stand for the index in myFreq that
                //has the greatest value
                //NOTE-- we have to use this because max is equal to the VALUE at that index and 
                //what we want to return is the index, so, this one will help us return the index
                maxIndex = k;
            }
        }
        //return maxIndex
        return maxIndex;
    }
}
