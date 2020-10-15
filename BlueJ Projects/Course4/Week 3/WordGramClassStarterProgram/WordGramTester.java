import java.util.*;

public class WordGramTester {
    //test WordGram generally... WordGram is like a collection of String like String is a collection of Char
	public void testWordGram(){
		String source = "this is a test this is a test this is a test of words";
		//split word by word
		String[] words = source.split("\\s+");
		//number of words to be generated
		int size = 4;
		for(int index = 0; index <= words.length - size; index += 1) {
		    //pass in the source text, index to start the generation in the source text and the number of words 
		    //to be generated
			WordGram wg = new WordGram(words,index,size);
			System.out.println(index+"\t"+wg.length()+"\t"+wg);
		}
	}
	
	//test the equals() method of wordgram
	public void testWordGramEquals(){
		String source = "this is a test this is a test this is a test of words";
		String[] words = source.split("\\s+");
		//ArrayList of wordgram since WordGram is now a class... it will hold the collection of Strings
		ArrayList<WordGram> list = new ArrayList<WordGram>();
		int size = 4;
		for(int index = 0; index <= words.length - size; index += 1) {
			WordGram wg = new WordGram(words,index,size);
			//add the Strings generated to the ArrayList
			list.add(wg);
		}
		//check if any other WordGram (collection of Strings) in the ArrayList is the same with the first WordGram
		//in the ArrayList
		WordGram first = list.get(0);
		System.out.println("checking "+first);
		for(int k=0; k < list.size(); k++){
		    //cant be used as it checks if the two items are of the same object i.e they are in the same memory
		    //location... and that is not what we want to check for, we only want to check if they have the same 
		    //content
			//if (first == list.get(k)) {
			//calls the equals() method in WordGram
			if (first.equals(list.get(k))) {
				System.out.println("matched at "+k+" "+list.get(k));
			}
		}
	}
	
	//test wordAt()
	public void testWordAt(){
		String source = "this is a test this is a test this is a test of words";
		//split word by word
		String[] words = source.split("\\s+");
		//number of words to be generated
		int size = 14;
		int index = 0;
		WordGram wg = new WordGram(words,index,size);
		System.out.println(wg.wordAt(4));
	}
	
	//test length()
	public void testLength(){
		String source = "this is a test this is a test this is a test of words";
		//split word by word
		String[] words = source.split("\\s+");
		//number of words to be generated
		int size = 14;
		int index = 0;
		WordGram wg = new WordGram(words,index,size);
		System.out.println(wg.length());
	}
	
	//test toString()
	public void testToString(){
		String source = "this is a test this is a test this is a test of words";
		//split word by word
		String[] words = source.split("\\s+");
		//number of words to be generated
		int size = 14;
		int index = 0;
		WordGram wg = new WordGram(words,index,size);
		System.out.println(wg);
	}
}
