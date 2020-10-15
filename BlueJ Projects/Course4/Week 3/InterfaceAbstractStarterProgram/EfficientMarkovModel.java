
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.HashMap;

//helps to make use getfollows less by storing the result for each new string so it can be accessed
//later without running getfollows again... this will make the code faster.
public class EfficientMarkovModel extends AbstractMarkovModel {
    //field
    //for holding each new string and its follows
    private HashMap<String, ArrayList<String>> myMap;
    
    //constructor
	public EfficientMarkovModel(int n) {
		myOrder = n;
		//can use this instead of myOrder = n; dont know why though
		//super(n);
		myMap = new HashMap<String, ArrayList<String>>();
	}
	
	//override the setTraining in abstract class and also calls method buildMap() and 
	//printHashMapInfo() that are both written bellow... NOTE this is where to call both methods
	//if you call it in the runner class, it will not work properly and i think it will use the 
	//other getfollows method as this one will not have built
	@Override
	public void setTraining(String s){
	    myText = s.trim();
	    //can use this instead of myText = s.trim(); dont know why though
		//super.setTraining(s);
		buildMap();
		printHashMapInfo();
    }
	
	
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
            if(follows == null){
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
    
    //add each string and its follows to the HashMap
    private void buildMap() {
        //i < myText.length() - (myOrder-1)  to make sure that we stop at the last word that can 
        //have a follows and not at the last word in the text which will not have a follows
		for (int i = 0; i < myText.length() - (myOrder-1); i++) {
		    //get the string that we want to get its follows and call it current
			String current = myText.substring(i, i+myOrder);
			//this will hold the follows
			String follow ="";
			//if current is less than the text
			if(i+myOrder<myText.length()){
			    //then get the follows of current and add it to String follows
				follow = myText.substring(i+myOrder, i+myOrder+1);
            }
            
            //if the HashMap contains the key current(of the current iteration)
			if (myMap.containsKey(current)) {
			    //then just add the follows to its value
				myMap.get(current).add(follow);
			}//if the HashMap doesnt contain the key current(of the current iteration)
			else {
			    //holder
				ArrayList<String> list = new ArrayList<String>();
				//add the follows to the holder
				list.add(follow);
				//put current to key and the holder which is now holding the follows for current 
				//to the value
				myMap.put(current, list);
			}
		}
	}
    
	
	//to get the follows, just return the value of the HashMap
	//to override the getFollows in the abstract class
	@Override
	public ArrayList<String> getFollows(String key) {
		return myMap.get(key);
	}
    
	
	//to test that the hashmap contains the follows
	//Print the HashMap (all the keys and their corresponding values). Only do this if the HashMap 
	//is small.
	//Print the number of keys in the HashMap
    //Print the size of the largest value in the HashMap—that is, the size of the largest
    //ArrayList of characters
    //Print the keys that have the maximum size value.
	public void printHashMapInfo() {
		//print number of keys
		System.out.println("It has " +  myMap.size() + " keys in the HashMap");
		int maxSize = 0;
		for (String key : myMap.keySet()) {
		    //get key with maximum size value
			maxSize = Math.max(maxSize, myMap.get(key).size());
			//print the keys
			//System.out.printf("Key:\t[%s]\tvalues: ", key);
			//print the values
			//System.out.println(myMap.get(key));
		}
		//print size of the largest value
		System.out.println("The maximum number of keys following a key is " + maxSize);
		ArrayList<String> keys = new ArrayList<String>();
		for (String key : myMap.keySet()) {
			if(myMap.get(key).size() == maxSize){
				keys.add(key);
			}
		}
		//print the keys with the maximum value
		System.out.println("Keys that have the largest ArrayList are: " + keys);
	}
	
    
    //to print name
    @Override
    public String toString(){
        return String.format("Efficient MarkovModel of order %d", myOrder);
	}
}
