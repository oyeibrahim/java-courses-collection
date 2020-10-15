
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    
    //fields
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    //constructor
    public CharactersInPlay() {
        //initialises the field to ArrayList
        //to hold the words
        myWords = new ArrayList<String>();
        //to hold the number of occurrence
        myFreqs = new ArrayList<Integer>();
    }
    
    //count occurences
    public void update(StorageResource res){
        //clear the ArrayList of any element
        myWords.clear();
        myFreqs.clear();
        
        //iterate over each word in the chosen file
        //its because of this iteration that i choose to use StorageResource
        for(String s : res.data()){
            
            //change the words to lowercase... this will help count words that are the same but has
            //different case as the same, if we didnt put this, they will be counted seperately
            //e.g. if we didnt put it, The and the will be counted as different words. thats why we 
            //are converting all words to lowercase... not needed here
            //s = s.toLowerCase();
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
            }//else (if the word is already there)... This if statement will count number of 
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
    
    //choose file and process as string then call update to count on the data
    public void findAllCharacters(){
        //choose file
        FileResource resource = new FileResource();
        //i use storage resource so that i will be able to iterate over the data in the method 
        //update.. if i am not able to iterate, the method will process just one string data
        //and not all the data
        StorageResource res = new StorageResource();
        //iterate over each word in the chosen file
        for(String s : resource.lines()){
            //assign the index of fullstop to period
            int period = s.indexOf(".");
            //if fullstop is there. if we didnt do this, a line may not have fullstop and there 
            //will be an index out of bound error
            if (period != -1){
                //get the word before the fullstop
                String person = s.substring(0, period);
                //test printing
                //System.out.println(person);
                //add the word to StorageResource res
                res.add(person);
                //call the method update on res
                update(res);
                //System.out.println(res.size());
            }
            
        }
        
    }
    
    //prints those greater than or equal to num1 and less than or equal to num2
    public void charactersWithNumParts(int num1, int num2){
        findAllCharacters();
        for(int k=0; k < myFreqs.size(); k++){
            
            //assign the values at each index in myFreqs to indexN... this must be different in 
            //name from the name of this in the code that print under condition in method tester
            int indexN = myFreqs.get(k);
            //check if each value is more than 1
            if (indexN >= num1 && indexN <= num2){
                //if so, record k in maxi... maxi will therefore be the index of those values 
                //greater than 1... this must be different in name from the name of this in the 
                //code that print under condition in method tester
                int maxi = k;
                //print myWords and myFreqs at index maxi
                System.out.println(myWords.get(maxi)+" : "+ myFreqs.get(maxi));
            }
            
        }
        
    }
    
    
    public void tester(){
        //call the method findAllCharacters... it has no parameter
        findAllCharacters();
        
        //print the size of the ArrayList myWords
        System.out.println(myWords.size());
        
        //empty line print
        System.out.println();
        
        //to print those with value above 1
        for(int k=0; k < myFreqs.size(); k++){
            //assign the values at each index in myFreqs to index
            int index = myFreqs.get(k);
            //check if each value is more than 1
            if (index > 20){
                //if so, record k in max... max will therefore be the index of those values 
                //greater than 1
                int max = k;
                //print myWords and myFreqs at index max
                System.out.println(myWords.get(max)+" : "+ myFreqs.get(max));
            }
            
        }
        
        
        //to print all
        //for(int k=0; k < myFreqs.size(); k++){
        //    System.out.println(myWords.get(k)+" : "+ myFreqs.get(k));
        //}
       
        //empty line print
        System.out.println();
        System.out.println();
        System.out.println();
        //call method charactersWithNumParts
        charactersWithNumParts(10, 15);
    }
    
    
    
}
