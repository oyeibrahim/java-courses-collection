
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    //@param size = number of words to generate
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 
    
    //@param seed = random seed
    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 
    
    //test MarkovWordOne
    public void runMarkov() {
        
        FileResource fr = new FileResource(); 
        String st = fr.asString();
        //replaces all new lines (enter) with space
        st = st.replace('\n', ' ');
        
        int size = 120;
        
        int seed = 175;
        
        //test
        //String st = "this is just a test yes this is a simple test";
        
        //create new MarkovWordOne instance
        MarkovWordOne markovWord = new MarkovWordOne();
        
        //for runModel(IMarkovModel markov, String text, int size) whithout seed
        //runModel(markovWord, st, size); 
        //for runModel(IMarkovModel markov, String text, int size, int seed) with seed
        runModel(markovWord, st, size, seed);
    } 
    
    //test MarkovWordTwo
    public void runMarkovTwo() {
        
        FileResource fr = new FileResource(); 
        String st = fr.asString();
        //replaces all new lines (enter) with space
        st = st.replace('\n', ' ');
        
        int size = 120;
        
        int seed = 549;
        
        //test
        //String st = "this is just a test yes this is a simple test";
        
        //create new MarkovWordTwo instance
        MarkovWordTwo markovWord = new MarkovWordTwo();
        
        //for runModel(IMarkovModel markov, String text, int size) whithout seed
        //runModel(markovWord, st, size); 
        //for runModel(IMarkovModel markov, String text, int size, int seed) with seed
        runModel(markovWord, st, size, seed);
    }
    
    //set printing preference
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

}
