
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    //fields
    //protected here means they can only be accessed by sub-classes
    protected String myText;
    protected Random myRandom;
    protected int myOrder;
    
    //COMMON to all method so we are writing the code here, all the subclasses will use it from here
    //check bellow for clarity
    //constructor... initialise Random variable myRandom
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    //COMMON to all method so we are writing the code here, all the subclasses will use it from here
    //check bellow for clarity
    //constructor... initialise Random variable myRandom and request for order type for the subclass
    //MarkovModel that can use any order inputted
    public AbstractMarkovModel(int n) {
        myRandom = new Random();
        myOrder = n;
    }
    
    //COMMON to all method so we are writing the code here, all the subclasses will use it from here
    //check bellow for clarity
    //help generate the same random text every time... useful for debugging
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    //from interface class IMarkovModel... initialise training text... WE DONT MAKE this abstract and
    //we already write the code in it here because all markov classes get their training text in 
    //the same way... so this will work for all markovs...check below
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    //from interface class IMarkovModel... NOTE how we made the interface class method abstract here
    //so it must be implemented by this class sub-classes, to there own different taste
    //WE MAKE THIS abstract and we did not write the code in it here because each markov class has 
    //different implementation of this method
    //@param numChar = lenght of the generated text
    abstract public String getRandomText(int numChars);
    
    
    
    //WE DONT MAKE this abstract and we already write the code in it here because all markov classes
    //use the same getFollows
    
    //find all the characters from the private variable myText that follow @param key and put 
    //all these characters into an ArrayList and then return this ArrayList
    protected ArrayList<String> getFollows(String key){
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
}
