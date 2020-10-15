
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Random;


//SINCE we are now using an abstract class, changes will be made here, prominent one being the deletion
//of methods and variables that are already declared in the abstract class... for better understanding,
//i will comment them out instead of deleting and then identify them as changes due to abstract class 
//creation... the indication is /**/  then ///////////////////////
/*
 * Can be used to implement any order as the order you wnt is passed in through the constructor
 * so you can pass in any order
 */
public class MarkovModel extends AbstractMarkovModel {
    
    /*//////////////////////////////////////////
    //fields
    private String myText;
    private Random myRandom;
    private int myOrder;
    ////////////////////////////////////////////
    */
    
    /**/
    /*//////////////////////////////////////////
    //constructor
    public MarkovModel(int n) {
        //initialize my Random
        myRandom = new Random();
        myOrder = n;
    }
    //////////////////////////////////////////////
    */
   /**/
   //myOrder and myRandom here gotten from the abstract class
   	public MarkovModel(int n) {
        myRandom = new Random();
        myOrder = n;
    }
    
    /**/
    //METHODS
    
    
    /*/////////////////////////////////////////////
    //help generate the same random text every time... useful for debugging
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    ///////////////////////////////////////////////
    */
    
   
   /**/
   
   /*///////////////////////////////////////////////
    //initialize training text
    public void setTraining(String s){
        //add the text passed in to myText... so myText will be used as training text
        myText = s.trim();
    }
    ////////////////////////////////////////////////
    */
    
    /**/
    /*//////////////////////////////////////////////////////////////////////
    //find all the characters from the private variable myText that follow @param key and put 
    //all these characters into an ArrayList and then return this ArrayList
    public ArrayList<String> getFollows(String key){
        //get the lenght of the @param key... this is because we are not to take lenght into 
        //consideration... so if the @param key is 3 letters word, get the follow chaacter and if 
        //the @param key is just a character get the follow character
        int len = key.length();
        //hold all the characters that follow @param key
        ArrayList<String> result = new ArrayList<String>();
        //we use i<myText.length()-len  because the last characters (denoted by len here) will not
        //have a folow character
        for(int i=0; i<myText.length()-len; i++) {
            //to handle cases where the key is not in the text or the key cant have a follow 
            //character
            if(myText.indexOf(key)== -1 || myText.indexOf(key) > myText.length()-len){
                break;
            }
            //if the @param key is equal to character at between i and i+len (len may be just 1) in 
            //the training text myText
            //e.g if the @param is "a", check myText and find wherever you get "a" in myText
            if (key.equals(myText.substring(i, i+len))){
                //add the character that follows to the ArrayList result
                //the follow character will be at i+len, i+len+1
                result.add(myText.substring(i+len, i+len+1));
            }
        }
        //return the ArrayList
        return result;
    }
    ////////////////////////////////////////////////////////////////////////
    */
    
    /**/
    //generates and returns random text that is @param numChars long
    //@param numChars is the number of alphabets plus space it is will generate randomly... if you 
    //put 50, it means if you count everything in the output text including space and everything, it
    //will be 50
    public String getRandomText(int numChars){
        //if the training text myText has no word in it
        if (myText == null){
            //if so
            //return an empty String... since there is no training text... this will prevent the code
            //from returning error message if the training text actually has no word in it
            return "";
        }
        //hold the random text generated
        StringBuilder sb = new StringBuilder();
        //myOrder here gotten from the abstract class
        int index = myRandom.nextInt(myText.length()-myOrder);
        String key = myText.substring(index, index+myOrder);
        //add that key to the StringBuilder sb
        sb.append(key);
        
        for(int k=0; k < numChars-myOrder; k++){
            //get the follows for the key generated
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0){
                break;
            }
            //get a random index from the follows list which are in ArrayList<String> follows
            index = myRandom.nextInt(follows.size());
            //get the letter at the index gotten and call it next
            String next = follows.get(index);
            //add next(random letter from follows list) to the StringBuilder sb
            sb.append(next);
            
            //explained
            key = key.substring(1) + next;
        }
        //return sb as String
        return sb.toString();
    }
    
    //to print name
    public String toString(){
        return String.format("MarkovModel of order %d", myOrder);
	}
}
