
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class CodonCount {
    //fields
    private HashMap<String,Integer> map;
    
    //constructor
    public CodonCount(){
        //initialise the map
        map = new HashMap<String,Integer>();
    }
    
    //this is used to add stuff into the map to prevent writing this code in every method that i 
    //will add something to the map
    public void hashAdd(String w){
        //you must not clear map here or it will clear the map with every iteration and all it will 
        //return will be the las iteration which will be one
        //if the map doesnt contain the dna before, add it and add one to its value
        if (!map.containsKey(w)){
    		map.put(w,1);
    	}//if the map contain the dna before, add one to its value
    	 else {
    		map.put(w,map.get(w)+1);
    	}
    }
    
    public void buildCodonMap(int start, String dnaCodon){
        //clear the map before using
        map.clear();
        
        //to test
        //int start = 0;
        //String dnaCodon = "CGTTCAAGTTCAA";
        
        //pass the string as StringBuilder... just to make for better modification
        StringBuilder dna = new StringBuilder(dnaCodon);
        //used to count the 3 alphabets... if start is one then start count at one and if count is 
        //any other figure, start count at that figure. thats why we say count = start... this will
        //not make us have to create count for every possible start value e.g. count=0, count1=1
        //count2 = 2   and so on.
        int count = start;
        //for start position 1
        //if start position passed in is 0
        if(start == 0){
            //as long as count is less than the lenght of the dna -3... this means its only 3 
            //alphabets that can be counted, so minus 3 means the remaining figure isnt valid for dna 
            //if i didnt pt that, there will be an out of bound exception
            while(count < dnaCodon.length()-3){
                //test printing of all the dna found
                //System.out.println(dna.substring(count, count+3));
                //assign each dna to String variable w for easy processing for Hashmap
                String w = dna.substring(count, count+3);
                //call method hashAdd to add the dna
                hashAdd(w);
                //start the count at next iteration after the three dna alphabets printed for this 
                //iteration
                count += 3;
            }
        }
        //for start position 2
        if(start == 1){
            while(count < dnaCodon.length()-3){
                //System.out.println(dna.substring(count1, count1+3));
                String w = dna.substring(count, count+3);
                hashAdd(w);
                count += 3;
            }
        }
        //for start position 3
        if(start == 2){
            while(count < dnaCodon.length()-3){
                //System.out.println(dna.substring(count2, count2+3));
                String w = dna.substring(count, count+3);
                hashAdd(w);
                count += 3;
            }
        }
        //test
        //print the dna and their value
        /*for(String w : map.keySet()){
            //assign value to int variable value for easy printing
			int value = map.get(w);
			System.out.println(w+"\t"+value);
		}*/
    }
    
    //gets the dna with the largest value
    public String getMostCommonCodon(){
        //initialise value to the posiible lowest
        int val = 0;
        //to hold the dna with highest value
        String largestDna = "";
        //loop through each word in the map
        for(String w : map.keySet()){
            //if val is less than the value of the current word
            if(val < map.get(w)){
                //val is equal to that value
                val = map.get(w);
                //largestDna is equal to that word... NOTE__-- i musnt use the other method of 
                //adding to a string - largestDna = largestDna+w - this will add every word it 
                //encounters that are bigger than val before it gets to highest value word. so 
                //where there are dnas with maybe value 1 before the highest occuring dna, it will
                //add everything to the highest occuring. but using this method - largestDna = w -
                //it will add just the word with highest value.
                largestDna = w;
            }
            
		}
		//test
        //System.out.println(val);
        //System.out.println(largestDna);
        return largestDna;
    }
    
    //get the value of most common codon. same thing as the above, just that it is made to return 
    //int value of most common codon
    public int getMostCommonCodonValue(){
        //initialise value to the posiible lowest
        int val = 0;
        //to hold the dna with highest value
        String largestDna = "";
        //loop through each word in the map
        for(String w : map.keySet()){
            //if val is less than the value of the current word
            if(val < map.get(w)){
                //val is equal to that value
                val = map.get(w);
                //largestDna is equal to that word... NOTE__-- i musnt use the other method of 
                //adding to a string - largestDna = largestDna+w - this will add every word it 
                //encounters that are bigger than val before it gets to highest value word. so 
                //where there are dnas with maybe value 1 before the highest occuring dna, it will
                //add everything to the highest occuring. but using this method - largestDna = w -
                //it will add just the word with highest value.
                largestDna = w;
            }
            
		}
		//test
        //System.out.println(val);
        //System.out.println(largestDna);
        return val;
    }
    
    
    //print dna in map if their value is between parameter start and end
    public void printCodonCounts(int start, int end){
        //print the dna and their value
        for(String w : map.keySet()){
             if(map.get(w) >= start && map.get(w) <= end){
                //assign value to int variable value for easy printing
    			int value = map.get(w);
    			System.out.println(w+"\t"+value);
             }
		}
    }
    
    //class output
    public void tester(){
        //choose a file
        FileResource fr = new FileResource();
        //get the file as a string cause we need to pass it in to method as string
        String dn = fr.asString();
        //change to uppercase
        String dna = dn.toUpperCase();
        //used as parameter to method printCodonCounts... we declare it like this for printing and
        //adding to parameter at the same time without having to declare different variable for each
        //task or having to write seperately
        int start = 1;
        int end = 5;
        //we loop since we want to print all the frames from 0 to 1 to 2 at the same time using 
        //method buildCodonMap
        for(int k=0; k < 3; k++){
            //call method buildCodonMap and pass in k which is an iteration and changes after every
            //iteration. then the file we chose that as been gotten as string
            buildCodonMap(k, dna);
            //call method getMostCommonCodon at current iteration and pass it to String com 
            //for easy printing
            String com = getMostCommonCodon();
            //call method getMostCommonCodonValue at current iteration and pass it to int cou 
            //for easy printing
            int cou = getMostCommonCodonValue();
            //pass the size of the map at current iteration to int s for easy printing
            int s = map.size();
            
            System.out.println("Reading frame starting with " + k + " results in "
                                           + s + " unique codons");
            System.out.println("and most common codon is " + com + " with count " + cou);
            
            System.out.println("Counts of codons between " + start + " and " 
                                           + end + " inclusive are:");
            
            printCodonCounts(start, end);
            
            System.out.println();
        }
        
    }
    
}
