
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import java.io.*;


public class WordsInFiles {
    //fields
    private HashMap<String,ArrayList<String>> map;
    
    //constructor
    public WordsInFiles(){
        //initialise the map
        map = new HashMap<String,ArrayList<String>>();
    }
    
    //add word as key and the ArrayList that was declared as value is to add the name of the files 
    //the word occur in
    private void addWordsFromFile(File f){
        //CHECK THE COMMENT IN METHOD buildWordFileMap BELLOW... you pass in the File to FileResource
        //so as to use it as FileResource... an advantage of File class is that you can get the 
        //filename which is impossible in FileResource
        FileResource fr = new FileResource(f);
        //for each word in the FileResource
        for (String w : fr.words()) {
            //get the filename... NOTE-- this ability is through the File class that was used as 
            //parameter to this method
            String fileName = f.getName();
            //create a new ArrayList that will be the Map value
            ArrayList<String> fileHolder = new ArrayList<String>();
            //check if the key in the Map has the word before
            if (map.containsKey(w)){
                //if so, assign the ArrayList fileHolder created above to the value of the Map
                fileHolder = map.get(w);
                //if the map value which is now the ArrayList fileHolder doesnt contain the 
                //filename before... filename is the name of the file that was assigned to String 
                //variable fileName above this current loop
                if(!fileHolder.contains(fileName)) {
                    //then add the filename to the ArrayList that is the value of the Map
                    fileHolder.add(fileName);
                }
            }//if the key in the Map doesnt have the word before
             else {
                 //then add the filename to the ArrayList that is the value of the Map
                 fileHolder.add(fileName);
                 //and then add both the word and the ArrayList to the Map as key and value
                 //respectively
                 map.put(w,fileHolder);
            }
        }

    }
    
    //method to select the files from DirectoryResource and use the File class to call the method
    //addWordsFromFile on them
    public void buildWordFileMap(){
        map.clear();
        //IMPORTANT-- this means a directory is being dealth with, so the directory and all the files
        //in it are concerned... So, to iterate over it or use it for anything, you must use the 
        //standard java FILE class to process it since you are not dealing with just a file in which
        //case the non standard FileResource will be used... an advantage of File class is that you
        //can get the filename which is impossible in FileResource
        DirectoryResource dr = new DirectoryResource();
        //this is how to use File class to process DirectoryResource... an advantage of File class 
        //is that you can get the filename which is impossible in FileResource
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
        
    }
    
    //returns the maximum number of files any word appears in
    public int maxNumber(){
        //this will hold the maximum number files appear in
        int max = 0;
        //iterate over the ArrayLists in the value part of the map
        for (ArrayList<String> fileNumber : map.values()){
            //if the size of the array which is the filenumber is more than the number holder max
            //created above
            if(fileNumber.size() > max){
                //then max will be equal to that number that is more than it
                max = fileNumber.size();
            }
        }
        //return max
        return max;
    }
    
    //returns an ArrayList of words that appear in number of files specified through the parameter
    //IMPORTANT to put whatever type of ArrayList the method is returning e.g <String>, <Integer>
    //so, it will be like this ArrayList<String>... when i didnt put it and just put ArrayList, i got
    //a strange notice even though it is compiling, it giving the notice despite compiling
    public ArrayList<String> wordsInNumFiles(int number){
       //will be used to hold the words
       ArrayList<String> words = new ArrayList<String>();
       //iterate over the String in the key part of the map
       for (String w : map.keySet()){
            //we can declare it outside here and add it to an int and the use the int name in the 
            //if statement e.g if(current == number)... we add .size() because ..remember.. what we
            //are acessing using map.get(w) is an ArrayList because the value part of the map is an 
            //ArrayList
            //int current = map.get(w).size();
            //we can do it like this also... so if the number of files equal to the number inputed 
            //through the parameter
            if(map.get(w).size() == number){
                //add the word w which is in the key of the map to the ArrayList words created 
                //above for holding the words
                words.add(w);
            }
        }
       return  words;
    }
    
    //prints the names of the files the word inputed through the parameter appears in
    public void printFilesIn(String word){
        ArrayList <String> fileName = new ArrayList<String>();
        //iterate over the String in the key part of the map
        for (String w : map.keySet()){
            if (w == word){
                 fileName = map.get(w);
            }
        }
        
        for (int k=0; k<fileName.size(); k++){
            System.out.println(fileName.get(k));
        }
        
    }
    
    //to output the class
    public void tester(){
        //loads the file into the HashMap
        buildWordFileMap();
        //since we will make use of the max number later, it is better to create a variable to hold
        //it
        int maxNumFile = maxNumber();
        System.out.println();
        //print the maximum number of files any word is in
        System.out.println("Max number of files a word appears in is : " + maxNumFile);
        System.out.println();
        //load method wordsInNumFiles into an ArrayList since it is retuning an ArrayList. this loading
        //into a new variable will allow for further processing than just printing directly in which 
        //case it will have to be recalled whenever you need it
        //NOTE--- putting maxNumFile as parameter will make this print just the words that occur in  
        //all the files... if i want to print otherwise e.g. name of words that appear in 2 files,
        //i will have to pass in 2 as parameter
        ArrayList<String> words = wordsInNumFiles(maxNumFile);
        //print files that most frequent word occur in
        System.out.println("Total of " + words.size() + " words are in maximum number of files"
                                    + " which is " + maxNumFile + " files : ");
        System.out.println();
        //iterate over ArrayList words
        for (String w : words){
            //print word
            System.out.println("This word -" + w + "- occurs in these files : ");
            //print number of file it occur in
            printFilesIn(w);
        }
        
        System.out.println();
        //System.out.println("complete map :");
        System.out.println();
        //to print out the complete map i.e. its keys and values
        /*for (String s : map.keySet()) {
            //assign the ArrayList part of the map to a value
            ArrayList<String> value = map.get(s);
            //print current word
            System.out.println("word  -" + s + "-  occurs in  :----  ");
            System.out.println();
            //iteration
            for (int k=0; k<value.size(); k++){
                //print files that the current word occur in
                System.out.println(value.get(k));
            }
            System.out.println();
        }*/
        
    }
    
    //a output way without creating an istance on the Blue J workbench
    public static void main(String[] args) { 
		WordsInFiles wif = new WordsInFiles(); 
		wif.tester(); 
	} 

    
    
    // another iteration method for ArrayList that i saw online and can be useful--
    //for (String file : ArrayList) {}-- this process entries in the ArrayList one by one
    
    
    //TO USE THIS import ---   java.util.Map.Entry; ---
    // another iteration method for HashMap that i saw online and can be useful--
    //for (Entry<String, List<String>> entry : Map.entrySet()) {}-- this will make the key and values
    //of the Map acessible and we can process them using e.g--
    //if (entry.getValue().size() == number) {}      and for key     result.add(entry.getKey());
    //what we need in the above is __entry.getValue()__ and __entry.getKey()__   
    //the rest are just example
    
    
}
