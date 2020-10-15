
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import java.util.Map.Entry;

public class GladLibMap {
    
    //fields
    //HashMap here will have its key contain the words that are in angular bracket that we will change 
    //in the story and the values contain the words we change change the key to in the story
	private HashMap <String, ArrayList<String>> map;
	//used to track the words used so that it wont be repeated
	private List<String> seenWordList;
	private List<String> seenLabelList;
	private Random myRandom;
	//for URLResource
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	//for FileResource
	private static String dataSourceDirectory = "data";
	
	//constructor for Fileresource
	public GladLibMap(){
	    //initialise te field variable map
	    map = new HashMap<String, ArrayList<String>>();
	    //call method initializeFromSource passing in the field dataSourceDirectory which has the 
	    //String "data" this will make the method initializeFromSource use the method readIt to read
	    //the files in this category as a FileResource as the name after concatenation will be 
	    //"data/adjective.txt"...
		initializeFromSource(dataSourceDirectory);
		//used to initialise thee random field random field
		myRandom = new Random();
	}
	
	//constructor... doing what the above GladLib() is doing only that you have to pass in data 
	//for its parameter at runtime
	//NOTE...-- this will be useful if you are calling this class fron another class as you will 
	//be able to pass in data for the constructor here but if its just the above GladLib(), the 
	//constructor will initialise by itself and you cant pass any data to it. so in the above 
	//GladLib() you cant create objects with different working way like one processing URLResource 
	//and the other processing FileResource but here, you can create objects with different working 
	//pattern depending on the data you pass to its constructor
	//SO THE TWO CONSTRUCTORS WERE CREATED SO THAT ONE WILL INITIALISE THE CONSTRUCTOR BY ITSELF 
	//MAKING ITS OBJECTS FIXED AND THE OTHER WILL ASK FOR DATA MAKING ITS OBJECTS WORK ACCORDING 
	//TO WHAT YOU PASS TO THE CONSTRUCTOR
	public GladLibMap(String source){
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	//to read in a file for the field variable Hashmap
	private ArrayList<String> readIt(String source){
	    //ArrayList
		ArrayList<String> list = new ArrayList<String>();
		//to check if its URLResource file
		if (source.startsWith("http")) {
		    //if so, create a new URLResource and pass in the URL i.e. source
			URLResource resource = new URLResource(source);
			//process line by line. because words in the file occupy different lines
			for(String line : resource.lines()){
			    //add each line to the ArrayList i.e save it there
				list.add(line);
			}
		}//if its not a URLResource, then it will be a FileResource.
		else {
		    //so, create a new FileResource
			FileResource resource = new FileResource(source);
			//process the file line by line. because words in the file occupy different lines
			for(String line : resource.lines()){
			    //add each line to the ArrayList i.e save it there
				list.add(line);
			}
		}
		//return the ArrayList that now contain each line in the file i.e each word in the file
		return list;
	}
	
	//make use of the readIt method to get the data in the files line by line
	private void initializeFromSource(String source) {
	    //this Array is used just to get the name of the word in angular bracket to be replaced and
	    //to get the filename... this is possible because both of them have a unique name e.g 
	    //verb.txt
	    String[] labels = {"country", "noun", "animal", 
						   "adjective", "name", "color", 
 						   "timeframe", "verb", "fruit"};
 		//iterate over all te Strings in the above Array adding them to the map one by one
 		for (String label :labels) {
 		    //pass in the parameter to method readIt so that files that words to be used as replacement
 		    //can be gotten from the file and passed into the map
 		    //the parameter passed to method readIt will all become a String when finally processed
 		    ArrayList<String> wordList = readIt(source + "/" + label + ".txt");
 		    //pass in the current word and its corresponding value which is an ArrayList of words 
 		    //gotten from the file
 		    map.put(label, wordList);
 		}
 		//initialise other field variables
 		seenWordList = new ArrayList<>();
 		seenLabelList = new ArrayList<>();
 		myRandom = new Random();
	}
	
	//to get random word from what is passed in
	private String randomFrom(ArrayList<String> source){
	    //This random is from the random declared in the field... you must decalre random before 
	    //using it
	    //get random word from value which is ArrayList... this is achieved here by passin in the 
	    //size to the variable index since random can ONLY BE USED FOR numbers. so whichever random
	    //number passed into index will be within the range of indexes in the ArrayList. so...
		int index = myRandom.nextInt(source.size());
		//get the word at the index or number returned by random and return THE WORD. thats why this 
		//method is returning string and not numbers since we are returning the word at that index and
		//not the index
		return source.get(index);
	}
	
	//this uses method randomFrom to get the random word from each file from the field variables
	//that are to be used for replacement of words in the story by passing them as parameter to 
	//the method randomFrom in turn i.e. one by one
	private String getSubstitute(String label) {
		//used to get a random number between 5 to 50... though its generating numbers more than 
		//those btw 50 and 50... so saying btw 50 and 50 is just for clarity
		//so the field myRandom which will get the random between the two numbers
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		//pass in the ArrayList as label here means words in angle brackets in the story which are 
		//stored as key in the map, so calling map.get(label) will return the value which is an 
		//Arraylist
		return randomFrom(map.get(label));
	}
	
	//to get words with angular brackets in the story i.e. words that are to be replaced... pass in 
	//each word or the word as String... THIS method iterates over the story in its entirety getting
	//words that are to be replaced and replacing them
	private String processWord(String w){
	    //get the index of first angular bracket
		int first = w.indexOf("<");
		//get the index of second angular bracket after first angular bracket
		int last = w.indexOf(">",first);
		//if the fist and second angular bracket are --NOT-- in the word
		if (first == -1 || last == -1){
		    //return the word. i.e dont make any changes since we are not doing anything to words
		    //without angular bracket
			return w;
		}
		//if the fist and second angular bracket are in the word
		//prefix is equal to maybe space before the first angular bracket... in any case, this will 
		//remove the angular bracket since its index is in the variable first
		String prefix = w.substring(0,first);
		//this will remove the second angular bracket since its index is in the variable last... 
		String suffix = w.substring(last+1);
		//so we will have just the word e.g if the original word is <verb>, after the above two 
		//variables have been used on it, it will become verb... we created this two so as to use
		//them to return the replace word
		
		//This also do the same thing the above two did i.e remove angular bracket... this one then
		//pass the new word into the variable label... we are not using the above two because we will
		//use them to return the replaced words thats why they were created
		String label  = w.substring(first+1,last);
		//used to hold the replaced word
		String sub = getSubstitute(label);
		//THIS here just did some things on the changing process so as to track how many words were
		//changed and other things
		//Another method using do while loop for the while loop below it
		/*do { 
			sub = getSubstitute(label);
		} while (seenWordList.contains(sub)); 
		seenWordList.add(sub);*/
		//THIS HERE is used to avoid using a word twice
		//while the field variable ArrayList seenWordList contains the replaced word
		while(seenWordList.contains(sub)){
		    //then re run the random on the word so that it will get another word
		    sub = getSubstitute(label);
		}
		//if it gets another word, add that word to seenWordList so that when it processes for another
		//word to be replaced it wont used the word again as seenWordList will have contained the 
		//word now
		seenWordList.add(sub);
		
		//THIS IS used to add the word to be replaced without the angular bracket (which the variable
		//label is holding) to the field variable ArrayList seenLabelList
		//if the map has the label i.e. word to be replaced and the word is not in the ArrayList
		//seenLabelList before
		if (map.containsKey(label) && !seenLabelList.contains(label)) {
		    //add the word (label) to the ArrayList seenLabelList
			seenLabelList.add(label); 
		}
		
		//the prefix(which remove the first angular bracket) and the replaced word and then the 
		//suffixw(hich remove the second angular bracket)... so the replaced word is returned 
		//without angular bracket
		return prefix+sub+suffix; 
	} 

	
	//MAIN METHOD
	//this reads the story pass method processWord to operate on the story and change words that are 
	//to be changed, then return the modified story
	private String fromTemplate(String source){
	    //to contain the modified story
		String story = "";
		//if the passed in file is URLResource
		if (source.startsWith("http")) {
		    //create a new URLResource and pass in the URL i.e. source
			URLResource resource = new URLResource(source);
			//process word by word
			for(String word : resource.words()){
			    //pass method processWord to each word to replace those that that to be replaced
			    //and add the new story to the String variable story created above
				story = story + processWord(word) + " ";
			}
		}//if its not a URLResource, then it will be a FileResource.
		else {
		    //create a new FileResource and pass in the file i.e. source
			FileResource resource = new FileResource(source);
			//process word by word
			for(String word : resource.words()){
			    //pass method processWord to each word to replace those that that to be replaced
			    //and add the new story to the String variable story created above
				story = story + processWord(word) + " ";
			}
		}
		//return the new story
		return story;
	}
	
	//used to print out the story... this is written specially to tell the width we want the story
	//to be in lenght when printed
	//its parameter are the Story as a string and the line width
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		//.split cuts the string into different chunks at areas where we can find \\s+ and iterates
		for(String w : s.split("\\s+")){
		    //if lenght of each chunk is more than the lenght specified in the parameter
			if (charsWritten + w.length() > lineWidth){
			    //print empty line
				System.out.println();
				charsWritten = 0;
			}
			//print space after word
			System.out.print(w+" ");
			//increament int variable charsWritten to be charsWritten + lenght of the word + 1
			charsWritten += w.length() + 1;
		}
	}
	
	//returns the total number of words in all the ArrayLists (values part) in the HashMap i.e. the
	//number of words that we can use to replace the words to be replaced
	public int totalWordsInMap() {
	    //used to hold the count
		int total = 0; 
		//for every entry in the HashMap map... iterate over each entry
		//to use this import ---   java.util.Map.Entry;
		for (Entry<String, ArrayList<String>> entry : map.entrySet()){
		    //total is equal to the size of the value which is an ArrayList
			total += entry.getValue().size(); 
		}
		//return total which is now holding the number of words that can be used to replace the words
		//to be replaced
		return total; 
	} 
	
	//returns the total number of words in the ArrayLists of the categories that were used for a
	//particular GladLib. If only noun, color, and adjective were the categories used in a
    //values part of the map, then only calculate the sum of all the words in those three ArrayLists
    //THIS is why we are using seenLabelList to check the number of labels that are to be replaced
    //in the story
	public int totalWordsConsidered() {
	    //used to hold the count
		int total = 0;
	    //for each word in seenLabelList
		for (String label : seenLabelList) {
		    //get its value in the map and get the size of its value... the size is the total number
		    //of words in it
		    //add the size to total
			total += map.get(label).size(); 
		} 
		//return total which is now holding the size of the value all the label used in the key
		return total; 
	} 

	
	
	//to print the output
	public void makeStory(){
	    seenWordList.clear();
	    //this String will hold the name of the file we want to process so that the same file can 
	    //be passed to the story replacement code and the total number of replaced word code...
	    //by passing in just file(name of this string) for both. so we wont have to be writing 
	    //filename all over again for each code.
	    String file = "data/madtemplate2.txt";
	    //print a new line
	    System.out.println("\n");
	    //call method fromTemplate passing in the story and assigning it to String variable story
		String story = fromTemplate(file);
		//this method is used to print out the story
		printOut(story, 60);
		//empty line print
		System.out.println();
		System.out.println();
		//to print number of replaced words... read in the data
		System.out.println("\nReplaced words: " + seenWordList.size());
		System.out.println();
		System.out.println();
		//prints Total words in map that can be used to replace words that are to be replaced
		System.out.println("Total words in map that can be used to replace words to be replaced: " 
		                              + totalWordsInMap()); 
		System.out.println();
		System.out.println();
		//prints total of words in the values that were considered i.e that have their key present
		//as label (words to be replaced) in the story
		System.out.println("Total words considered i.e that have their key present as label: " 
		                              + totalWordsConsidered());
		//to print number of replaced words... read in the data using another method
		FileResource resource = new FileResource(file);
		//this will hold the number
		int pivot = 0;
		//iterate over all words in the file
		for (String item : resource.words()) {
    		//get the index of first angular bracket
    		int first = item.indexOf("<");
    		//get the index of second angular bracket after first angular bracket
    		int last = item.indexOf(">",first);
    		//if the fist and second angular bracket are in the word
    		if (first != -1 || last != -1){
    		    //we add one to the count holder
    			pivot += 1;
    		}
    		
        }
        //we print the number in the count holder
        System.out.println("\nReplaced words: " + pivot);
	}
	
	
	
}