
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    //@param markov -- the markov model you want to call, it has variable type IMarkovModel because it
    //implements the interface class IMarkovModel
    //@param text -- training text
    //@param size -- number of letters the generated text will have
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setRandom(seed);
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    //head method... calls te above method after getting all required parameters then insert them
    //to make the method generate random text
    public void runMarkov() {
        /*
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        */
        //test... to use, delete the enter and probably use wordlenght 60
        String st = "The boy is a very good boy, he plays really well and studies hard, he does not think of school as a place to study rather he studies hard on his own and he later got much knowledge that he was so much respected and he got on top of the world";
        
        int size = 200;
        
        int seed = 42;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
        
        System.out.println();
        System.out.println();
        System.out.println();
        
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        System.out.println();
        System.out.println();
        System.out.println();
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        System.out.println();
        System.out.println();
        System.out.println();
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);
        
    }
    
    //for printing.... already explained
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
    //test EfficientMarkovModel
    public void testHashMap() {
		String st = "yes-this-is-a-thin-pretty-pink-thistle";
		int seed = 42;
		int size = 50;
		EfficientMarkovModel emm = new EfficientMarkovModel(2);
        runModel(emm, st, size, seed);
	}
	
	
	//to compare time for MarkovModel and EfficientMarkovModel
	public void compareMethods() {
        FileResource fr = new FileResource("data/hawthorne.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int seed = 42;
		int size = 1000;
		
		//FOR MarkovModel
		
		//get the time the code begin running
		double startTime = System.nanoTime();
		//create new object using order 2
        MarkovModel mTwo = new MarkovModel(2);
        //run it
        runModel(mTwo, st, size, seed);
        //get the time the code running is completed
        double endTime = System.nanoTime();
        //print the time between thestart and completion of the code
        System.out.println("The running time of MarkovModel is " + (endTime-startTime)/ 1000000000.0 + " seconds");
        
        
        //FOR EfficientMarkovModel
        
        //get the time the code begin running
        startTime = System.nanoTime();
        //create new object using order 2
		EfficientMarkovModel emm = new EfficientMarkovModel(2);
		//run it
        runModel(emm, st, size, seed);
        //get the time the code running is completed
        endTime = System.nanoTime();
        //print the time between thestart and completion of the code
        System.out.println("The running time of EfficientMarkovModel is " + (endTime-startTime)/ 1000000000.0 + " seconds");
	}
	
	//for quiz
	public void testQuiz() {
		FileResource fr = new FileResource("data/romeo.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int seed = 615;
		int size = 1000;
		//  comment out printHashMapInfo()
		EfficientMarkovModel emm = new EfficientMarkovModel(5);
		runModel(emm, st, size, seed);
	}
}
