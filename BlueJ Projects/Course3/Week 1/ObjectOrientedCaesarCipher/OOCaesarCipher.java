
/**
 * Write a description of OOCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class OOCaesarCipher {
    //fields ~~will always be with the class~~
    private String alphabet;
    private String alphabet2;
    private String shiftedAlphabet;
    private String shiftedAlphabet2;
    private int mainKey;
    
    //constructor ~~just to initiate the class and set how it will work in tha 
    //particular object~~
    //NOTE-- must be the same name as the class
    public OOCaesarCipher(int key){
        //Write down the alphabet
        //NOTE __ you are not to write the variable type here again e.g. String alphabet = "..."; 
        //just write the variable name e.g. alphabet = "..."; if u write the variable type, the 
        //constructor wont initialise and there will be a null pointer exception where the use of 
        //the code under constructor is employed, since the code under constructor hasnt been executed
        //THIS IS VERY IMPORTANT, IT GAVE ME A THOUGHT TIME
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //to handle the lowercase situation
        alphabet2 = "abcdefghijklmnopqrstuvwxyz";
        
        //Compute the shifted alphabet
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        //shifted for lowercase
        shiftedAlphabet2 = alphabet2.substring(key) + alphabet2.substring(0,key);
        
        //used to write the decrypt method in this class
        mainKey = key;
    }
    
    //METHODS
    
    //encrypt method
    public String encrypt(String input) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //if the currentcharacter is lowercase (lowercase situation handling)
            if(Character.isLowerCase(currChar)){
                //Find the index of currChar in the alphabet (call it idx)
                int idx = alphabet2.indexOf(currChar);
                //If currChar is in the alphabet
                if(idx != -1){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabet2.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
            }
            //uppercase situation handling
            if(Character.isUpperCase(currChar)){
                //Find the index of currChar in the alphabet (call it idx)
                int idx = alphabet.indexOf(currChar);
                //If currChar is in the alphabet
                if(idx != -1){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabet.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        //dis is out of the two loops so it will return for both cases in a string
        return encrypted.toString();
    }
    
    public String decrypt(String input){
        OOCaesarCipher oocc = new OOCaesarCipher(26 - mainKey);
        return oocc.encrypt(input);
    }
    
    
    
} 

    
    
    
    
    

