
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import edu.duke.FileResource;

public class Tester {
    //to test the method getFollows
    public void testGetFollows(){
        //text to be used to test the method to be tested
        String text = "this is a test yes this is a test.";
        ///////////////
        //word or character to test for
        String wrc = "t";
        ///////////////
        MarkovOne mo = new MarkovOne();
        //set training text
        mo.setTraining(text);
        //call getFollows passing in the word we are to test for whch is in String variable wrc above
        ArrayList<String> res =mo.getFollows(wrc);
        
        //print the size of the result i.e number of follow words found
        System.out.println("Number of follow words found :-- " + res.size());
        System.out.println();
        for(String s : res){
            System.out.println(s);
        }
    }
    
    //to test the method getFollows on a file
    public void testGetFollowsWithFile(){
        //file to be used to test the method to be tested
        FileResource fr = new FileResource();
        //get the file as String
        String text = fr.asString();
        //replaces each new line with space... so the words in the file read in will be on a single
		//line... its like replacing the enter key that makes new line while typing with a space
		//in which case, there wont be a new line again
		text = text.replace('\n', ' ');
        ///////////////
        //word or character to test for
        String wrc = "t";
        ///////////////
        MarkovOne mo = new MarkovOne();
        //set training text
        mo.setTraining(text);
        //call getFollows passing in the word we are to test for which is in String variable wrc above
        ArrayList<String> res =mo.getFollows(wrc);
        
        //print the size of the result i.e number of follow words found
        System.out.println("Number of follow words found :-- " + res.size());
        /*
        System.out.println();
        for(String s : res){
            System.out.println(s);
        }
        */
    }
}
