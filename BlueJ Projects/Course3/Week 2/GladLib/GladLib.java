import edu.duke.*;
import java.util.*;

public class GladLib {
    //fields
	private ArrayList<String> adjectiveList;
	private ArrayList<String> nounList;
	private ArrayList<String> colorList;
	private ArrayList<String> countryList;
	private ArrayList<String> nameList;
	private ArrayList<String> animalList;
	private ArrayList<String> timeList;
	private ArrayList<String> verbList;
	private ArrayList<String> fruitList;
	//used to track the words used so that it wont be repeated
	private ArrayList<String> trackList;
	private Random myRandom;
	//for URLResource
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	//for FileResource
	private static String dataSourceDirectory = "data";
	
	//constructor for Fileresource
	public GladLib(){
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
	public GladLib(String source){
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	//to read in a file for the field variables
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
	    //the filename is ..... .txt .e.g. adjective.txt and the String parameter that will be added
	    //will be the filepath i.e the folder that contains the file... the files are in the folder 
	    //"data"... so if we pass in "data" as parameter, the filename and path will be complete
	    //as it will be concatenated to become one e.g.   "data/adjective.txt"...
	    //the names like adjectiveList are from the field variables and they are ArrayList because
	    //method readIt is returning ArrayList. so the returned value will be saved in them...
		adjectiveList= readIt(source+"/adjective.txt");	
		nounList = readIt(source+"/noun.txt");
		colorList = readIt(source+"/color.txt");
		countryList = readIt(source+"/country.txt");
		nameList = readIt(source+"/name.txt");		
		animalList = readIt(source+"/animal.txt");
		timeList = readIt(source+"/timeframe.txt");
		verbList = readIt(source+"/verb.txt");
		fruitList = readIt(source+"/fruit.txt");
		trackList = new ArrayList<String>();
	}
	
	//to get random word from what is passed in
	private String randomFrom(ArrayList<String> source){
	    //get random word from the parameter passed in
		int index = myRandom.nextInt(source.size());
		//return the random word
		return source.get(index);
	}
	
	//this uses method randomFrom to get the random word from each file from the field variables
	//that are to be used for replacement of wors in the story by passing them as parameter to 
	//the method randomFrom in turn i.e. one by one
	private String getSubstitute(String label) {
	    //the parameter String label here are the words in angular bracket in the original story 
	    //that are to be replaced accordingly... e.g. <country> , <verb> , <name>
	    //so if its <country>, replace it with a randome word from the ArrayList field countryList
	    //thisis achieved by passing the ArrayList field as parameter to the method randomFrom
	    //which will return a random word from the ArrayList
		if (label.equals("country")) {
			return randomFrom(countryList);
		}
		//the above is done for all others
		if (label.equals("color")){
			return randomFrom(colorList);
		}
		if (label.equals("noun")){
			return randomFrom(nounList);
		}
		if (label.equals("name")){
			return randomFrom(nameList);
		}
		if (label.equals("adjective")){
			return randomFrom(adjectiveList);
		}
		if (label.equals("animal")){
			return randomFrom(animalList);
		}
		if (label.equals("timeframe")){
			return randomFrom(timeList);
		}
		if (label.equals("verb")){
			return randomFrom(verbList);
		}
		if (label.equals("fruit")){
			return randomFrom(fruitList);
		}
		//this one is not from the ArrayList field its just trying to get a random number between 
		//5 to 50... so the field myRandom which will get the random between the two numbers
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		//if the label is not any of the above, return the String **UNKNOWN**
		return "**UNKNOWN**";
	}
	
	//to get words with angular brackets i.e. words that are to be replaced... pass in each word or
	//the word as String
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
		//this will remove the second angular bracket since its index is in the variable last
		String suffix = w.substring(last+1);
		//call method getSubstitute to replace the word in the angular brackets with a random word 
		//the field variables that are to be used for word replacement
		String sub = getSubstitute(w.substring(first+1,last));
		while(trackList.contains(sub)){
		    sub = getSubstitute(w.substring(first+1,last));
		}
		trackList.add(sub);
		//return the word before the first angular bracket, the replaced word, then the word after 
		//the angular bracket
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
	
	//used to print out the story... tis is written specially to tell the width we want the story
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
	
	
	//to print the output
	public void makeStory(){
	    trackList.clear();
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
		System.out.println("\nReplaced words: " + trackList.size());
		System.out.println();
		System.out.println();
		//to print number of replaced words... read in the data
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
        System.out.println(pivot);
	}
	


}
