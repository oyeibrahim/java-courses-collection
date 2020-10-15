
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class tester {
    
    public void testCaesarCipher(){
        int key = 12;
        CaesarCipher cc = new CaesarCipher(key);
        //for letter
        char letter = 'j';
        char ans = cc.encryptLetter(letter);
        char ans2 = cc.decryptLetter(ans);
        System.out.println("One letter encryption");
        System.out.println("Original: " + letter);
        System.out.println("Encrypted: " + ans);
        System.out.println("Decrypted: " + ans2);
        System.out.println();
        //for message
        FileResource fr = new FileResource("titus-small.txt");
        String message = fr.asString();
        String ans3 = cc.encrypt(message);
        String ans4 = cc.decrypt(ans3);
        System.out.println("Message encryption");
        System.out.println("Original: ");
        System.out.println(message);
        System.out.println("Encrypted: ");
        System.out.println(ans3);
        System.out.println("Decrypted: ");
        System.out.println(ans4);
        System.out.println();
        System.out.println("Key: " + cc);
    }
    
    public void testCaesarCracker(){
        //for no argument... default to e
        CaesarCracker cCnA = new CaesarCracker();
        FileResource fr = new FileResource("titus-small_key5.txt");
        String message = fr.asString();
        String ans = cCnA.decrypt(message);
        System.out.println("Original: ");
        System.out.println(message);
        System.out.println("Decrypted: ");
        System.out.println(ans);
        System.out.println();
        //for passing in argument
        CaesarCracker cC = new CaesarCracker('a');
        FileResource fn = new FileResource("oslusiadas_key17.txt");
        String message2 = fn.asString();
        String ans2 = cC.decrypt(message2);
        System.out.println("Portuguese");
        System.out.println("Original: ");
        System.out.println(message2);
        System.out.println("Decrypted: ");
        System.out.println(ans2);
    }
    
    public void testVigenereCipher(){
        FileResource fr = new FileResource("titus-small.txt");
        String message = fr.asString();
        int[] key = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(key);
        String ans = vc.encrypt(message);
        String ans2 = vc.decrypt(ans);
        System.out.println("Original: ");
        System.out.println(message);
        System.out.println("Encrypted: ");
        System.out.println(ans);
        System.out.println("Decrypted: ");
        System.out.println(ans2);
        System.out.println();
        System.out.println("Key: " + vc);
    }
    
    public void testVigenereBreaker(){
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("athens_keyflute.txt");
        String message = fr.asString();
        //sliceString
        /*String message = "abcdefghijklm";
        String ans = vb.sliceString(message, 0, 3);
        System.out.println(ans);*/
        //tryKeyLength
        /*int [] ans = vb.tryKeyLength(message, 5, 'e');
        for (int k = 0; k < ans.length; k++){
            System.out.println(ans[k]);
        }*/
        //breakVigenere
        //vb.breakVigenere();
        //---unknown keylenght---
        //vb.breakVigenere2();
        //---unknown keylenght---unknown language----
        vb.breakVigenere3();
    }
    
}
