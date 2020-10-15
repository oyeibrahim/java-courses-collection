
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

//THE reason why it is slow is thatit is generating getfollows for each word so it does this maybe 
//millions of time... an efficient design is having it do getfollows once and storing it so that it
//will use the same getfollows for the rest and wont have to generate for each word, when we do that,
//it will be super fast
public class MarkovRunner {
    
    //this method runMarkovZero or One or Two or whatever Markov you want to run... it has no 
    //parameters. This method reads in a file the user chooses, 
    //creates a MarkovZero object, and then generates three sets of randomly generated text using 
    //the file read in to choose the random characters from.
    public void runMarkovZero() {
        //read in file
		FileResource fr = new FileResource();
		//get the file as String
		String st = fr.asString();
		//replaces each new line with space... so the words in the file read in will be on a single
		//line... its like replacing the enter key that makes new line while typing with a space
		//in which case, there wont be a new line again
		st = st.replace('\n', ' ');
		//call MarkovZero class
		MarkovZero markov = new MarkovZero();
		/*
		//call setRandom method from the Markov class to set the type of random text it will generate
		//every time you call it using the same parameter @param int... so, it will generate the same 
		//text everytime if you are using the same @param... this is useful for debugging
		markov.setRandom(101);
		*/
		//call setTraining method from the Markov class and use the file read in as training text
		markov.setTraining(st);
		//the k < 3 here produces three random texts
		for(int k=0; k < 3; k++){
		    //call getRandomText method from the Markov class and pass in the lenght of the text 
		    //you want it to generate i.e the total number of characters you want to be in the text
		    //it will generate
			String text = markov.getRandomText(500);
			//call method printOut below toprint out the text generated
			printOut(text);
		}
	}
	
	//MarkovOne
	public void runMarkovOne() {
        //read in file
		FileResource fr = new FileResource();
		//get the file as String
		String st = fr.asString();
		//replaces each new line with space... so the words in the file read in will be on a single
		//line... its like replacing the enter key that makes new line while typing with a space
		//in which case, there wont be a new line again
		st = st.replace('\n', ' ');
		
		//test... to use, delete the enter
        //String st = "The boy is a very good boy, he plays really well and studies hard, he does not 
        //think of school as a place to study rather he studies hard on his own and he later got much 
        //knowledge that he was so much respected and he got on top of the world";
		
		//call MarkovOne class
		MarkovOne markov = new MarkovOne();
		
		//call setRandom method from the Markov class to set the type of random text it will generate
		//every time you call it using the same parameter @param int... so, it will generate the same 
		//text everytime if you are using the same @param... this is useful for debugging
		//the @param is called seed
		markov.setRandom(42);
		
		//call setTraining method from the Markov class and use the file read in as training text
		markov.setTraining(st);
		//the k < 3 here produces three random texts
		for(int k=0; k < 3; k++){
		    //call getRandomText method from the Markov class and pass in the lenght of the text 
		    //you want it to generate i.e the total number of characters you want to be in the text
		    //it will generate
			String text = markov.getRandomText(500);
			//call method printOut below toprint out the text generated
			printOut(text);
		}
	}
	
	//MarkovFour
	public void runMarkovFour() {
        //read in file
		FileResource fr = new FileResource();
		//get the file as String
		String st = fr.asString();
		//replaces each new line with space... so the words in the file read in will be on a single
		//line... its like replacing the enter key that makes new line while typing with a space
		//in which case, there wont be a new line again
		st = st.replace('\n', ' ');
		
		//test... to use, delete the enter
        //String st = "The boy is a very good boy, he plays really well and studies hard, he does not 
        //think of school as a place to study rather he studies hard on his own and he later got much 
        //knowledge that he was so much respected and he got on top of the world";
		
		//call MarkovOne class
		MarkovFour markov = new MarkovFour();
		
		//call setRandom method from the Markov class to set the type of random text it will generate
		//every time you call it using the same parameter @param int... so, it will generate the same 
		//text everytime if you are using the same @param... this is useful for debugging
		//the @param is called seed
		markov.setRandom(25);
		
		//call setTraining method from the Markov class and use the file read in as training text
		markov.setTraining(st);
		//the k < 3 here produces three random texts
		for(int k=0; k < 3; k++){
		    //call getRandomText method from the Markov class and pass in the lenght of the text 
		    //you want it to generate i.e the total number of characters you want to be in the text
		    //it will generate
			String text = markov.getRandomText(60/*500*/);
			//call method printOut below toprint out the text generated
			printOut(text);
		}
	}
	
	//MarkovModel
	public void runMarkovModel() {
        //read in file
		FileResource fr = new FileResource();
		//get the file as String
		String st = fr.asString();
		//replaces each new line with space... so the words in the file read in will be on a single
		//line... its like replacing the enter key that makes new line while typing with a space
		//in which case, there wont be a new line again
		st = st.replace('\n', ' ');
		
		//test... to use, delete the enter
        //String st = "The boy is a very good boy, he plays really well and studies hard, he does not 
        //think of school as a place to study rather he studies hard on his own and he later got much 
        //knowledge that he was so much respected and he got on top of the world";
		
		//call MarkovOne class
		MarkovModel markov = new MarkovModel(6);
		
		//call setRandom method from the Markov class to set the type of random text it will generate
		//every time you call it using the same parameter @param int... so, it will generate the same 
		//text everytime if you are using the same @param... this is useful for debugging
		//the @param is called seed
		markov.setRandom(38);
		
		//call setTraining method from the Markov class and use the file read in as training text
		markov.setTraining(st);
		//the k < 3 here produces three random texts
		for(int k=0; k < 3; k++){
		    //call getRandomText method from the Markov class and pass in the lenght of the text 
		    //you want it to generate i.e the total number of characters you want to be in the text
		    //it will generate
			String text = markov.getRandomText(60/*500*/);
			//call method printOut below toprint out the text generated
			printOut(text);
		}
	}
	
	//for setting the printing format... @param is the text you want to print
	private void printOut(String s){
	    //put the text you want to print into an Array word by word... the split is used to cut out 
	    //spaces so as to add the text word by word
		String[] words = s.split("\\s+");
		int psize = 0;
		//print this above the text to be printed for distinction
		System.out.println("----------------------------------");
		//k < words.length the length of the Array
		for(int k=0; k < words.length; k++){
		    //print each word then put space in front of it
			System.out.print(words[k]+ " ");
			//to print 60 characters on each line
			//psize will be equal to the lenght of the total words printed on the current line
			psize += words[k].length() + 1;
			//if the lenght is greater than 60
			if (psize > 60) {
			    //if the lenght is greater than 60
			    //then print on a new line
				System.out.println();
				//reset psize to 0 again so that it will be used to count the next line
				psize = 0;
			}
		}
		//print this below the text to be printed for distinction
		System.out.println("\n----------------------------------");
	}
	
}
