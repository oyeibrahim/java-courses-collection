
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipher {
    
    //count the number of ocurrences of each alphabet
    public int[] countLetters(String message) {
        //create alphabet
        String alph = "abcdefghijklmnopqrstuvwxyz";
        //create array for all the alphabets to hold there count
        int[] counts = new int[26];
        //scan each character of the message
        for(int k=0; k<message.length(); k++){
            //convert the character to lowercase so as to tally with alphabets listed above
            char ch = Character.toLowerCase(message.charAt(k));
            //get the index of the character in alphabets above
            int dex = alph.indexOf(ch);
            //if the character is in the alphabet
            if (dex != -1){
                //add 1 to the alphabet's place in the array counts
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    //return the INDEX with the largest ocurrence
    public int maxIndex(int[] vals){
        //this will hold the index with largest ocurrence
        int maxDex = 0;
        for(int k=0; k<vals.length; k++){
            //if the value of the index at k is greater than maxDex value
            if (vals[k] > vals[maxDex]){
                //then set maxDex to be equal to that index... this will make maxDex in the end 
                //hold the index with the highest value
                maxDex = k;
            }
        }
        //return maxDex
        return maxDex;
    }
    
    //use word frequency to break the cipher
    public String breakCaesarCipher(String input){
        
        //call method countLetters on the encrypted message to get the letter with highest frequency
        int[] freqs = countLetters(input);
        //this will get the index of the most frequent word
        int maxDex = maxIndex(freqs);
        //since we are assuming that E is the most frequent word, we find the distance of the most 
        //frequent word to E by subtracting the index of E in the alphabet if we were counting from 0
        //this will help get were E was shifted
        int dkey = maxDex - 4;
        //if ~~~maxDex~~~ is less than four that means the word is at the end of the alphabet and so  
        //we will need to wrap around the alphabets... this will prevent error of not finding the index
        if (maxDex < 4){
            //wrap around the alphabets
            dkey = 26 - (4-maxDex);
        }
        //call the oocaesarcipher class and assign to oocc
        //26-encrypted key is used to decrypt
        OOCaesarCipher oocc = new OOCaesarCipher(26 - dkey);
        
        return oocc.encrypt(input);
    }
    
    
    
    
    public void simpleTests(){
        //choose file
        FileResource fr = new FileResource();
        //gets the file as string so that we can use it as the parameter for OOCaesarCipher class 
        //because OOCaesarCipher class is looking for a string
        String input = fr.asString();
        //explanatory printing
        System.out.println("Original message is --");
        //print the original message
        System.out.println(input);
        //empty line print
        System.out.println();
        //OOCaesarCipher object initialisation ~~the constructor is initiated by the parameter key~~
        OOCaesarCipher oocc = new OOCaesarCipher(18);
        //pass in the message to the method encrypt in class OOCaesarCipher using the object created
        //assigning the variable encryptedMessage is to enable independent printing anytime later in
        //this code. if i didnt do that, i will have to print directly and if i want to print later, 
        //i will have to access the method again and pass it to print statement.
        String encryptedMessage = oocc.encrypt(input);
        //explanatory printing
        System.out.println("Encrypted message is --");
        //print the encrypted message
        System.out.println(encryptedMessage);
        //empty line print
        System.out.println();
        //pass in the message to the decrypt method from the object created... this will decrypt the 
        //message
        //String decryptedMessage = oocc.decrypt(input);
        //there was a bug in the above method i.e. using the object to decrypt so this is better
        //but since am calling the above method breakCaesarCipher it is using word frequency. so, in 
        //the case where word frequency wont work, writing a code for breaking the cipher will be 
        //the answer.
        String decryptedMessage = breakCaesarCipher(input);
        //explanatory printing
        System.out.println("Decrypted message is --");
        //print the decrypted message
        System.out.println(decryptedMessage);
    }
    
    
    
    
    public void testBreakCaesarCipher(){
        //choose file
        FileResource fr = new FileResource();
        //gets the file as string so that we can use it as the parameter for decrypt because 
        //decrypt is looking for a string
        String message = fr.asString();
        //just to be able to print
        String decrypted = breakCaesarCipher(message);
        System.out.println("decrypted message using word frequency is --");
        System.out.println(decrypted);
        System.out.println("encrypted message is --");
        System.out.println(message);
    }
    
    
}
