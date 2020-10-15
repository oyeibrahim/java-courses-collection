
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


import java.util.ArrayList;
import java.util.Random;

public class MarkovOne {
    //fields
    private String myText;
	private Random myRandom;
	
	//constructor
	public MarkovOne() {
	    //initialize my Random
		myRandom = new Random();
	}
	
	//METHODS
	
	//help generate the same random text every time... useful for debugging
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	//initialize training text
	public void setTraining(String s){
	    //add the text passed in to myText... so myText will be used as training text
		myText = s.trim();
	}
	
	
	
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
	
	
	
	//generates and returns random text that is @param numChars long
	//@param numChars is the number of alphabets plus space it is will generate randomly... if you 
	//put 50, it means if you count everything in the output text including space and everything, it
	//will be 50
	/////////MarkovOne gets a word and gets its follows, so it produces two letters as output
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
		//get a letter at random from the training text
		int index = myRandom.nextInt(myText.length()-1);
		//get the letter next to the randomly gotten letter and call it key... this are just fancy, 
		//u may just pick any letter instead of generating randomly
		String key = myText.substring(index, index+1);
		//add that key to the StringBuilder sb
		sb.append(key);
		//loop through in @param numChars time, since numChars is the number of characters we want
		//to generate
		//numChars-1 used because the key is already one letter and if we dont say this, the loop
		//will get another follows for the key and try to add it to the generated text and in which
		//case, there will be an error as the limit for the generated text which is set by numChars
		//will be exceeded... so we do it because the last word is just one and the key is one, so 
		//we just add the key,,, because this loop is used to get follows and if we get follows 
		//again for the key at the last word, they key and follows will make two letter and the last 
		//word is looking for just one letter.... last word == last letter, just use last word for
		//better understanding
		for(int k=0; k < numChars-1; k++){
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
			
			//set the value of key for the next loop... so, key will be this (the value) entering
			//into the next loop
			//to avoid key being the value it was before entering the current loop which can cause
			//repetition, set key to the immediate letter after the letter it was when it entered 
			//the current loop
			//so key.substring(1) is the next letter after the key and adding next means key now
			//has two letters... in the next loop, the last letter will be selected and the follow
			//for that letter will be gotten
			key = key.substring(1) + next;
		}
		//return sb as String
		return sb.toString();
	}
}
