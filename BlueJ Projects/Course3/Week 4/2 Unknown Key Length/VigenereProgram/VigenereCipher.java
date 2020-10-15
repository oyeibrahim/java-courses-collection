import edu.duke.*;
import java.util.*;

public class VigenereCipher {
    //ciphers here is an array of CaesarCipher class, i.e it contains CaesarCipherin each slot...
    //this is because vigenere uses many CaesarCipher to encrypt
    CaesarCipher[] ciphers;
    
    //constructor... requires the key
    public VigenereCipher(int[] key) {
        //initialise the field ciphers and passing in the the lenght of the key... NOTE vigenere 
        //uses key like DICE = 3824... so we create a CaesarCipher for each number making the 
        //lenght of the key our desired number of CaesarCipher objects
        ciphers = new CaesarCipher[key.length];
        //loops through each CaesarCipher object created
        for (int i = 0; i < key.length; i++) {
            ciphers[i] = new CaesarCipher(key[i]);
        }
    }
    
    //encryt... moving through the key and encrypting each word in the message with the right key
    public String encrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        //i think this changes the message to an array with each element being each character in 
        //the message
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            //calls method encryptLetter from CaesarCipher class
            answer.append(thisCipher.encryptLetter(c));
            //increament... to work with the key
            i++;
        }
        return answer.toString();
    }
    
    //decrypt
    public String decrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        //i think this changes the message to an array with each element being each character in 
        //the message
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            //calls method decryptLetter from CaesarCipher class
            answer.append(thisCipher.decryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    
    //returns the key
    public String toString() {
        return Arrays.toString(ciphers);
    }
    
}
