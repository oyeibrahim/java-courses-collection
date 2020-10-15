
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;

public class MarkovZero {
    //fields
    private String myText;
	private Random myRandom;
	
	//constructor
	public MarkovZero() {
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
	
	//generates and returns random text that is @param numChars long
	//@param numChars is the number of alphabets plus space it is will generate randomly... if you 
	//put 50, it means if you count everything in the output text including space and everything, it
	//will be 50
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int k=0; k < numChars; k++){
		    //get random index from training text
			int index = myRandom.nextInt(myText.length());
			//add the character at that random index to sb
			sb.append(myText.charAt(index));
		}
		//return sb as String
		return sb.toString();
	}
}
