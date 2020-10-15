
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        //split into words, so it is read word by word
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        //holder
        StringBuilder sb = new StringBuilder();
        //get random word from myText
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        //call the random word gotten: key
        String key = myText[index];
        //add key to the StringBuilder
        sb.append(key);
        //add space in front of the key... unlike character prediction where space is considered a
        //character and can e generated at random, space isnt considered a word and so needs to be
        //added like this to prevent generating words without space
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            //test
            //System.out.print("Key \"" + key + "\" ");
            //get the word that follows key and add it to ArrayList follows... calling the getfollows
            //method below
            ArrayList<String> follows = getFollows(key);
            //test
            //System.out.println("is followed by: " + follows);
            //if no word follows the key stop the code
            if (follows.size() == 0) {
                break;
            }
            //get the index of a word at random from follows
            index = myRandom.nextInt(follows.size());
            //get the word at the index gotten at random
            String next = follows.get(index);
            //add the word to the StringBuilder
            sb.append(next);
            //add space in front of it
            sb.append(" ");
            //key is now equal to the follow word... this is to prevent key from having the same 
            //value it has in the current iteration in the next iteration, in which case the code
            //will generate repetition
            key = next;
        }
        //trim() is to remove the space in front of the last word... because the last word will have
        //space in front of it as we added space in front of all words
        return sb.toString().trim();
    }
    
    //helper for getfollows method bellow
    //return the index of the word we are searching for in the text
    private int indexOf(String[] words, String target, int start){
       //start at the parameter start, loop through the text i.e @param words
       for (int k=start; k < words.length; k++){
           //if the word at the current loop is equal to the word we are searching for i.e.
           //@param target
           if (words[k].equals(target)){
               //return the index of the word
               return k;
           }
       }
       //return -1 if not found... NOTE -1 is generally used for unfound index and java understands 
       //that
       return -1;
    }
    
    //get the word that follow the word we are checking for... @param key = word we are checking for
    //this will add all the follows for the word we are searching for its follows into an ArrayList
    //searching for each occurence of the word in the text and adding the follows
    private ArrayList<String> getFollows(String key) {
        //holder
        ArrayList<String> follows = new ArrayList<String>();
        //where to start in the training text
        int pos = 0;
        while (pos < myText.length){
           //call helper method indexOf() above... search the index of the key in the text
           //@param pos = where to start the search in the text
           int start = indexOf(myText, key, pos);
           //if the index is not found i.e -1, stop the code
           if (start == -1){
               break;
           }
           //if the index found plus the lenght of the key (this will equal the lenght of the word
           //from the start position) is equal or greater than text, in which case there cant be any
           //follows for it, stop the code
           //THE PLACE I COMMENTED out is preventing the code from producing from the seed the 
           //random text that the assignment say it will produce
           if (start /*+ key.length()*/ >= myText.length-1){
               break;
           }
           //get the next word after the key and call it next... start is the index of the key and 
           //+1 is adding 1 to the index of the key, so if the index of the key is 5, +1 will make 
           //it 6 so the word at ---index 6--- will be gotten which is the next word after the key
           String next = myText[start + 1];
           //add next to the ArrayList
           follows.add(next);
           //pos is the start plus lenght of key which is the position where the key stops... this 
           //is to ensure that we begin search from the next point in the next loop
           //THE PLACE I COMMENTED out is preventing the code from producing from the seed the 
           //random text that the assignment say it will produce
           pos = start+1/*key.length()*/;
        }
        //return the ArrayList
        return follows;
    }
    
    //to test method indexOf above
    public void testIndexOf(){
        String s = "this is just a test yes this is a simple test";
        //split into words, so it is read word by word
		String[] words = s.split("\\s+");
		//test
		System.out.print(indexOf(words, "this", 0) + "\n");
		System.out.print(indexOf(words, "this", 3) + "\n");
		System.out.print(indexOf(words, "frog", 0) + "\n");
		System.out.print(indexOf(words, "frog", 5) + "\n");
		System.out.print(indexOf(words, "simple", 2) + "\n");
		System.out.print(indexOf(words, "test", 5) + "\n");
    }
}
