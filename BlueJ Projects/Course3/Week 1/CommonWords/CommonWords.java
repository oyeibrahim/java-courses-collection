
/**
 * Count common words in Shakespeare's works
 * 
 * @author Duke Software Team
 * @version 1.0
 */
import edu.duke.*;

public class CommonWords
{
    //read the list of common words already provided in a folder.
    public String[] getCommon(){
        FileResource resource = new FileResource("data/common.txt");
        //string to hold the common words, we know the common words are 20 in the file thats why the
        //array was initialised to 20
        String[] common = new String[20];
        //this is used to create a loop so as to put in the words one by one. its increamented...
        int index = 0;
        //read the file WORD by WORD
        for(String s : resource.words()){
            //common array at index is equal to the word at index, so if the array is at index 0, the word
            //at index 0 will be added and the index will increament to read others
            common[index] = s;
            //index increamentation
            index += 1;
            //no need to set the loop exit since the array and the words already has limit of 20
            //so it will stop after that
        }
        //return the common array now full of all the common words
        return common;
    }
    
    //finds if the word is in the common word. So all the common words will be iterated one at a time
    //and will be used to search the words and if its there, it wil return the number of the common word
    public int indexOf(String[] list, String word) {
        for (int k=0; k<list.length; k++) {
            //if common word equals to word
            if (list[k].equals(word)) {
                   //return the common word number. this will be from 1 to 20,,, remember that our list
                   //of common words is 20
                   return k;
               }
           }
        //else return -1
        return -1;
    }
    
    //main code that counts the number of occurences
    public void countWords(FileResource resource, String[] common, int[] counts){
        //read the file (literature or and other file we want to count common word occurrences there)
        //WORD by WORD
        for(String word : resource.words()){
            //change to lowercase
            word = word.toLowerCase();
            //call method indexOf() created above to check if the word is in common word
            int index = indexOf(common,word);
            //if its not -1 i.e. its not there since we returned -1 if its not there
            if (index != -1) {
                //add 1 to the corresponding index in counts since we are returning the common word number
                //in method indexOf(), that will imply that the addition will take place at the index where
                //the word found lies in counts
                counts[index] += 1;
            }
        }
    }
    
    //just like a test method as all the working method as been created this is just like making use of them
    //and printing
    void countShakespeare(){
        //this will be used to read the files using loop. the names here are the files name
        //String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt",
        //                    "likeit.txt", "macbeth.txt", "romeo.txt"};
        //test
        String[] plays = {"small.txt"};
        //read the common words in through the method getCommon()
        String[] common = getCommon();
        //creat new array counts to hold the count of each common word and read in the size through
        //common words that was read in above
        int[] counts = new int[common.length];
        for(int k=0; k < plays.length; k++){
            //use plays array above to call and iterate through all files
            FileResource resource = new FileResource("data/" + plays[k]);
            //call method countWords() to do the counting
            countWords(resource,common,counts);
            //just a test to show or print which file has been completed
            System.out.println("done with " + plays[k]);
        }
        
        //printing... since its array, loop is good for printing all elements
        for(int k=0; k < common.length; k++){
            //print the common words (through the array common) and corresponding count on the same line
            System.out.println(common[k] + "\t" + counts[k]);
        }
    }
}
