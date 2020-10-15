
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipherTwo {
    
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
    
    
    //this method returns string counting from 1, 3, 5 and then one counting from 2, 4, 6
    public String halfOfString(String message, int start){
        StringBuilder encrypted = new StringBuilder(message);
        //this one are just holders for the new string we will create
        String startPosition = "";
        String endPosition = "";
        //if the start position is 0... NOTE that here you must use == not = this disturbed me 
        //while writing this code so take care
        if (start == 0){
            //count for the 1st, 3rd, 5th and so on
            for(int i = start; i < encrypted.length(); i+=2) {
                //this prints the new string fron index 0 then after the second alphabet
                startPosition = startPosition + encrypted.charAt(i);
                //adding the + in front like this prints a reverse string
                //startPosition = encrypted.charAt(i) + startPosition;
            }
            //return the new string
            return startPosition;
        }
        
        //if the start position is 1... NOTE that here you must use == not = this disturbed me 
        //while writing this code so take care
        if (start == 1){
            //count for the 2nd, 4th, 6th and so on
            for(int i = start; i < encrypted.length(); i+=2) {
                //this prints the new string fron index 1 then after the third alphabet
                endPosition = endPosition + encrypted.charAt(i);
                //adding the + in front like this prints a reverse string
                //endPosition = encrypted.charAt(i) + endPosition;
            }
            //return the new string
            return endPosition;
        }
        //this one is just because it say to add a return statement here, if not, there is no
        //need for it... and its not working... i have tested it by adding stuffs inside and it 
        //didnt return it
        return "";
    }
    
    
     //to decrypt message encrypted using two keys
    public String breakCaesarCipherTwoKeys(String input){
        
        
        //call halfString for position 1 and 2 and assign them to different variables
        String encryptedAtOne = halfOfString(input, 0);
        String encryptedAtTwo = halfOfString(input, 1);
        
        //just to test that the two variables are holding the right halfs
        //System.out.println(encryptedAtOne);
        //System.out.println(encryptedAtTwo);
        
        //FOR FIRST HALF
        //call method countLetters on the encrypted message to get the letter with highest frequency
        int[] freqsAtOne = countLetters(encryptedAtOne);
        //this will get the index of the most frequent word
        int maxDexAtOne = maxIndex(freqsAtOne);
        //since we are assuming that E is the most frequent word, we find the distance of the most 
        //frequent word to E by subtracting the index of E in the alphabet if we were counting from 0
        //this will help get were E was shifted
        int dkeyAtOne = maxDexAtOne - 4;
        //if ~~~maxDex~~~ is less than four that means the word is at the end of the alphabet and so  
        //we will need to wrap around the alphabets... this will prevent error of not finding the index
        if (maxDexAtOne < 4){
            //wrap around the alphabets
            dkeyAtOne = 26 - (4-maxDexAtOne);
        }
        
        
        //FOR SECOND HALF
        //call method countLetters on the encrypted message to get the letter with highest frequency
        int[] freqsAtTwo = countLetters(encryptedAtTwo);
        //this will get the index of the most frequent word
        int maxDexAtTwo = maxIndex(freqsAtTwo);
        //since we are assuming that E is the most frequent word, we find the distance of the most 
        //frequent word to E by subtracting the index of E in the alphabet if we were counting from 0
        //this will help get were E was shifted
        int dkeyAtTwo = maxDexAtTwo - 4;
        //if ~~~maxDex~~~ is less than four that means the word is at the end of the alphabet and so  
        //we will need to wrap around the alphabets... this will prevent error of not finding the index
        if (maxDexAtTwo < 4){
            //wrap around the alphabets
            dkeyAtTwo = 26 - (4-maxDexAtTwo);
        }
        
        //call the caesarcipher class and assign to oocctk
        ObjectOrientedCaesarCipherTwo oocctk = new ObjectOrientedCaesarCipherTwo
                                                (26 - dkeyAtOne, 26 - dkeyAtTwo);
        
        return oocctk.encryptTwoKeys(input);
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
        ObjectOrientedCaesarCipherTwo oocctk = new ObjectOrientedCaesarCipherTwo(17,3);
        //pass in the message to the method encrypt in class OOCaesarCipher using the object created
        //assigning the variable encryptedMessage is to enable independent printing anytime later in
        //this code. if i didnt do that, i will have to print directly and if i want to print later, 
        //i will have to access the method again and pass it to print statement.
        String encryptedMessage = oocctk.encryptTwoKeys(input);
        //explanatory printing
        System.out.println("Encrypted message is --");
        //print the encrypted message
        System.out.println(encryptedMessage);
        //empty line print
        System.out.println();
        //pass in the message to the decrypt method from the object created... this will decrypt the 
        //message
        //String decryptedMessage = oocctk.decryptTwoKeys(input);
        //there was a bug in the above method i.e. using the object to decrypt so this is better
        //but since am calling the above method breakCaesarCipher it is using word frequency. so, in 
        //the case where word frequency wont work, writing a code for breaking the cipher will be 
        //the answer.
        String decryptedMessage = breakCaesarCipherTwoKeys(input);
        //explanatory printing
        System.out.println("Decrypted message is --");
        //print the decrypted message
        System.out.println(decryptedMessage);
    }
}
