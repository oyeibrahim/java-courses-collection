
/**
 * Write a description of interface IMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public interface IMarkovModel {
    //initialize training text
    public void setTraining(String text);
    
    //generates and returns random text that is @param numChars long
	//@param numChars is the number of alphabets plus space it is will generate randomly... if you 
	//put 50, it means if you count everything in the output text including space and everything, it
	//will be 50
    public String getRandomText(int numChars);
    
    //help generate the same random text every time... useful for debugging
    public void setRandom(int seed);
}
