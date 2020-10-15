import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    //slices string... starts from whichSlice and loops or jumps through totalSlices
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder(message);
        String startPosition = "";
        for(int k= whichSlice; k< sb.length(); k += totalSlices){
            startPosition = startPosition + sb.charAt(k);
        }
        return startPosition;
    }

    //find the key for each alphabet in the message then return the key combination e.g DICE in 
    //number in an Array... klength here is the length of the key
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        String[] groups = new String[klength]; 
        for (int k = 0; k < klength; k++) {
            //passes the sliced message into groups[]... so groups[] is now holding the different 
            //sliced strings i.e message. Since we know the key is of lenght 4, we want to cut the 
            //message so e.g "the boy is good"... for key 1= t--g, 2= hbio, 3= eoso, 4= --g
            //then we get the key for each.so... key 1 may be= 2, 2 may be= 16, 3= 7, 4= 24...
            //so groups is now holding four different aspects of the message that when added together
            //forms the complete message
            groups[k] = sliceString(encrypted, k, klength); 
        } 
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int k = 0; k < klength; k++) {
            //gets the key for each slice in groups[] and puts them in key[]
            key[k] = cc.getKey(groups[k]); 
        }  

        return key;
    }
    
    //uses all methods above to decrypt message and this will be like the head method in this class
    //as it will be the one to call for decryption
    public void breakVigenere () { 
        FileResource fr = new FileResource("athens_keyflute.txt");
        String str = fr.asString();
        //call method tryKeyLength
        int[] key = tryKeyLength(str, 5, 'e');
        //create new VigenereCipher instance and pass in the key to the constructor
        VigenereCipher vc = new VigenereCipher(key);
        //call decrypt method from VigenereCipher class
        String result = vc.decrypt(str); 
        System.out.println(result);
    }
    
}
