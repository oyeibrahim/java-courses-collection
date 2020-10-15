
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.io.File;
import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;
import edu.duke.*;

public class WordLengths {
    
    public void countWordLengths(FileResource resource, int[] counts){
        //without parameters use this to replace them
        //FileResource resource = new FileResource();
        //int [] counts = new int[12];
        //just for testing
        //int [] counts2 = new int[6];
        
        for (String word : resource.words()){
            //create new stringbuilder holding the words for successful iteration as string cant
            //be iterated very well
            StringBuilder words = new StringBuilder(word);
            //this is for easiness, it is used in the third if statement !Character.isLetter(nw)
            int nw = words.charAt(words.length()-1);
            //tests
            //int nw = words.length();
            //System.out.println(nw);
            //test
            //for(int i=0; i<1; i++){
                //for every word, check each of their letters
                //char curr = words.charAt(i);
                //if its alphabet
                //if (Character.isAlphabetic(curr)){
                //CAN USE THIS OR USE Character.isAlphabetic
                //Character.isLetter
                //check if no symbol at the beginning and at the end, then use them in the loop
                //i.e. those that have no symbol
                if(Character.isLetter(words.charAt(0)) && 
                    Character.isLetter(words.charAt(words.length()-1))){
                    //this loop contains words with no symbol at the beginning and at the end
                    for(int k=0; k<counts.length; k++){
                        //if the lenght of the word is the value of k
                       if (words.length() == k) {
                           //add one to the counter at the index of the value of k
                           counts[k] += 1;
                       }
                       //then loop again and continue adding to the right lenght value
                       //till there are no words again
                    }
                    
                    //if the lenght of the word is more than the array size or whatever
                    //size we want to choose as the maximum
                    if (words.length() > 30){
                        //add the number of those words to the maximum value we choose
                        counts[30] += 1;
                    }
                }
                
                
                //if the word begin with letter but end with symbol
                //this cant be within the above loop because the above loop only contains words
                //free of symbols and so this loop wont grab any word
                //this loop should be independent and grab its own words... imade the mistake
                //so take care
                if(Character.isLetter(words.charAt(0)) && 
                    !Character.isLetter(nw)){
                      //this loop has words with symbol at the end
                      //create new stringbuilder for successful iteration as string cant
                      //be iterated very well
                      //this stringbuilder contains the word that have symbols at the end with
                      //the symbols haven been removed from them through this method
                      //words.substring(0, words.length()-1) which start with the first letter
                      //and end with the letter before the end letter and we can call to remove
                      //it without putting and exclusive statement as to what to remove because
                      //THIS LOOP CONTAINS JUST WORDS THAT HAVE SYMBOLSAT THE END.
                      StringBuilder newWords = new StringBuilder
                        (words.substring(0, words.length()-1));
                      //testing
                      //System.out.println(newWords);
                              
                      for(int i=0; i<counts.length; i++){
                             //if the lenght of the word is the value of i
                             if (newWords.length() == i) {
                                 //add one to the counter at the index of the value of i
                                 counts[i] += 1;
                             }
                      }
                }
                
                //}
           //}
           //tests
            //System.out.println(words + "  --  " + words.length());
            //System.out.println(words);
            //System.out.println(words.length());
        }
        //printing array elements through loop
        for(int k=1; k < counts.length; k++){
            //prnts number through k and then print the value in each number through array
            //in front of the number
            System.out.println(k + " --  " + counts[k]);
        }
    }
    
    
    
    public int indexOfMax(int[] values){
        int maxIndex = 0;
        for(int k=0; k < values.length; k++){
            if(maxIndex < values[k]){
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    
    
    
    //test to print a method returning array
    /*public void newtest(){
        int [] ans = countWordLengths();
        for(int k=1; k < ans.length; k++){
            System.out.println(ans[k]);
        }
    }*/
    
    //just an independent test with word lenght
    public void test(){
        String mn = "gmn";
        //int nw = 3;
        int[] nw = new int[6];
        //if(mn.length() == nw){
            //System.out.println("yes now");
        //}
        for(int k=0; k < nw.length; k++){
            //using == nw[k] is another way
            if (mn.length() == k /*== nw[k]*/) {
                   nw[k] += 1;
            }else{
                   nw[5] += 1;
                }
        }
        for(int k=1; k < nw.length; k++){
            System.out.println(nw[k]);
        }
    }
    
    
    
    
    
    public void testCountWordLengths(){
        FileResource resource = new FileResource();
        int [] counts = new int[31];
        //since the method is printing, just use this
        countWordLengths(resource, counts);
        //since we have called the method countWordLengths on counts above, values in counts has 
        //been updated and we can use counts with the values in it after the method call above
        //thats why we can call indexOfMax on it below, if we call it above the countWordLengths
        //method call, it wount respond as their will be no values in the array counts and it
        //will just print 0 this will also be the case if you did not call the method
        //countWordLengths at all, as the counts will still be 0 and not updated, the method
        //indexOfMax will just print 0.
        //use
        System.out.println("most common word length--");
        System.out.println(indexOfMax(counts));
        //or use
        /*int ans = indexOfMax(counts);
        System.out.println(ans);*/
        
        
        
        
        
        //cant use this since the method is not returning else it will just print rubbish
        /*for(int k=0; k < counts.length; k++){
            countWordLengths(resource, counts);
            System.out.println(k + "--" + counts[k]);
        }
        for(int k=0; k < counts.length; k++){
            System.out.println(k + "--" + counts[k]);
        }*/
        
        
    }
    
    
    
}
