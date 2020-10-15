//THIS CLASS IS A COLLECTION OF STRINGS AND USED TO STORE AND OPERATE ON A COLLECTION OF STRINGS,,, 
//like a String is a collection of Char
public class WordGram {
    //fields
    //to store words in pattern one word per slot
    private String[] myWords;
    //will make it possible to be able to use WordGrams as a key with a HashMap
    private int myHash;
    
    //constructor
    //copies the -@param size- number of words from -@param source- starting at the position -@param start- 
    //into a new WordGram. e.g WordGram(t, 2, 4) and t = "I am a very very good boy"  ---it will return---- 
    //"a very very good"
    public WordGram(String[] source, int start, int size) {
        //make the size of the Array be @param size which is the number of words you want to store or operate on
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }
    
    //get and return the word at the index passed in
    public String wordAt(int index) {
        //checks if the index doesnt exist i.e its less than zero or more than the total length of the text
        if (index < 0 || index >= myWords.length) {
            //if so, it throws an exception
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }
    
    //return the length of the WordGram
    public int length(){
        //it may be simple here, just returning the lenght of Array myWord... but to the class user, it is 
        //priceless as it returns the length of the WordGram
        return myWords.length;
    }
    
    //prints a WordGram out, showing all the words in the WordGram on one line separated by spaces
    //REAL USE--- when you write this type of code on the WordGram- System.out.println(WordGram);
    //it will call this method and use it to print out the WordGram
    public String toString(){
        //holder
        String ret = "";
        
        for(int k=0; k < myWords.length; k++) {
            ret += myWords[k] + " ";
        }
        //remove the space in front of the last word
        return ret.trim();
    }
    
    //check if the two item being compared have the same content... that means thier lenght must be equal, then 
    //their content too must be equal... so we first check for length equality then content equality
    public boolean equals(Object o) {
        //makes or check the @param to make sure it is of type WordGram as comparison is between two WordGram
        //this (WordGram) o; cast WordGram on the object to check if its WordGram... other is just a name given
        //to the parameter, it may be given another name
        WordGram other = (WordGram) o;
        // compare me to other...   "this" means the current WordGram i.e this WordGram... "other" is the name 
        //given above to the other WordGram we are comparing this WordGram with... instead of this, WE MAY USE
        //myWords.length()
        //if this WordGram and the other WordGram are not equal in length... LENGTH CHECK
        if(this.length() != other.length()){
            //if so, return false
        	return false;
        }
        
        //CONTENT CHECK
        for(int k=0; k < myWords.length; k++) {
            //if the current word in myWords at the current loop is not equal to the current word in other at the
            //current loop
        	if(!myWords[k].equals(other.wordAt(k))) {
        	    //if so return false
        		return false;
        	}
        }
        
        //if the code make it here, it means bothe the length and content are equal,,, so return true
        return true;
    }
    
    //return a new WordGram the same size as the original, consisting of each word shifted down one index 
    //(for example the word in slot 1 would move to slot 0, the word in slot 2 would move to slot 1, etc.) and 
    //-@param word- added to the end of the new WordGram. Be sure to test this method. For example, if a WordGram 
    //of size 4 is “this” “is” “a” “test”, and shiftAdd is called with the argument “yes”, then the method would 
    //return a new WordGram ”is” “a” “test” “yes”. This method should not alter the WordGram on which it is called
    //meaning we create a copy of the original WordGram and then operate on the copy
    //@param word == word to be added
    public WordGram shiftAdd(String word) { 
        //WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        //COPY... create new Array with same lenght as this WordGram myWords Array... can use myWords.length() too
        String[] newWords = new String[this.length()];
        for (int i=0; i<newWords.length-1; i++) {
            //this is the working code... it puts the word at a location in myWords into a location less than 
            //it by 1 in newWords... remaining to add the @param
        	newWords[i] = this.wordAt(i+1);
        }
        //add the @param to the last slut in the Array newWords
        newWords[newWords.length-1] = word;
        //return it
        WordGram out = new WordGram(newWords, 0, newWords.length);
        return out;
    }
    
    
    public int hashCode() {
        return toString().hashCode();
    }
}