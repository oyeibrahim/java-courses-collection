
/**
 * Write a description of ObjectOrientedCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class ObjectOrientedCaesarCipherTwo {
    //fields ~~will always be with the class~~
    private String alphabet;
    private String alphabet2;
    private String shiftedAlphabetKey1;
    private String shiftedAlphabetKey12;
    private String shiftedAlphabetKey2;
    private String shiftedAlphabetKey22;
    private int mainKey1;
    private int mainKey2;
    
    //constructor ~~just to initiate the class and set how it will work in tha 
    //particular object~~
    //NOTE-- must be the same name as the class
    public ObjectOrientedCaesarCipherTwo(int key1, int key2){
        //Write down the alphabet
        //NOTE __ you are not to write the variable type here again e.g. String alphabet = "..."; 
        //just write the variable name e.g. alphabet = "..."; if u write the variable type, the 
        //constructor wont initialise and there will be a null pointer exception where the use of 
        //the code under constructor is employed, since the code under constructor hasnt been executed
        //THIS IS VERY IMPORTANT, IT GAVE ME A THOUGHT TIME
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //to handle the lowercase situation
        alphabet2 = "abcdefghijklmnopqrstuvwxyz";
        //Compute the shifted alphabet key1
        shiftedAlphabetKey1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        //shifted for lowercase key1
        shiftedAlphabetKey12 = alphabet2.substring(key1) + alphabet2.substring(0,key1);
        //Compute the shifted alphabet key2
        shiftedAlphabetKey2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        //shifted for lowercase key2
        shiftedAlphabetKey22 = alphabet2.substring(key2) + alphabet2.substring(0,key2);
        
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    
    
    //METHODS
    
    //encrypt method
    public String encryptTwoKeys(String input) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        
        //Count from 0 to < length of encrypted, (call it i)
        //count for the 1st, 3rd, 5th and so on
        for(int i = 0; i < encrypted.length(); i+=2) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //if the currentcharacter is lowercase (lowercase situation handling)
            if(Character.isLowerCase(currChar)){
                //Find the index of currChar in the alphabet (call it idx)
                int idx = alphabet2.indexOf(currChar);
                //If currChar is in the alphabet
                if(idx != -1){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabetKey12.charAt(idx);
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
                    char newChar = shiftedAlphabetKey1.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
            }
            //Otherwise: do nothing
        }
        
        //count for the 2nd, 4th, 6th and so on
        for(int i = 1; i < encrypted.length(); i+=2) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //if the currentcharacter is lowercase (lowercase situation handling)
            if(Character.isLowerCase(currChar)){
                //Find the index of currChar in the alphabet (call it idx)
                int idx = alphabet2.indexOf(currChar);
                //If currChar is in the alphabet
                if(idx != -1){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabetKey22.charAt(idx);
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
                    char newChar = shiftedAlphabetKey2.charAt(idx);
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
    
    
    
    public String decryptTwoKeys(String input){
        ObjectOrientedCaesarCipherTwo oocctk = new ObjectOrientedCaesarCipherTwo
                                                (26 - mainKey1, 26 - mainKey2);
        return oocctk.encryptTwoKeys(input);
    }
    
}
