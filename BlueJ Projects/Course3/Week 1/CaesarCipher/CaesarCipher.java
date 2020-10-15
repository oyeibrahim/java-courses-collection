import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //to handle the lowercase situation
        String alphabet2 = "abcdefghijklmnopqrstuvwxyz";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        //shifted for lowercase
        String shiftedAlphabet2 = alphabet2.substring(key) + alphabet2.substring(0,key);
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
    
    public void testCaesar() {
        int key = 15;
        //for direct input i.e not selecting file
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        //for file selection
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        //System.out.println("key is " + key + "\n" + encrypted);
        //26-key will return the original message for every key
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
        //System.out.println("Decrypted message--- " + decrypted);
    }
    
    
    //processes 1st figure with key1, 2nd with 2, 3rd with 1 and so on
    public String encryptTwoKeys(String input, int key1, int key2) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //to handle the lowercase situation
        String alphabet2 = "abcdefghijklmnopqrstuvwxyz";
        //Compute the shifted alphabet key1
        String shiftedAlphabetKey1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        //shifted for lowercase key1
        String shiftedAlphabetKey12 = alphabet2.substring(key1) + alphabet2.substring(0,key1);
        //Compute the shifted alphabet key2
        String shiftedAlphabetKey2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        //shifted for lowercase key2
        String shiftedAlphabetKey22 = alphabet2.substring(key2) + alphabet2.substring(0,key2);
        //Count from 0 to < length of encrypted, (call it i)
        //count for the 1st, 3rd, 5th and so on
        for(int i = 0; i < encrypted.length(); i+=2) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
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
    
    public void testEncryptTwoKeys() {
        int key1 = 14;
        int key2 = 24;
        //for direct input i.e not selecting file
        String message = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        //for file selection
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println(encrypted);
        System.out.println();
        //System.out.println("key is " + key + "\n" + encrypted);
        //26-key will return the original message for every key
        String decrypted = encryptTwoKeys(encrypted, 26-key1, 26-key2);
        System.out.println(decrypted);
        //System.out.println("Decrypted message--- " + decrypted);
    }
    
    
    
    
    
    
    
    
    public String onlineEncrypt(String input, int key) {
        int n = input.length();
        String original = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted = original.substring(key, 26);
        shifted = shifted + original.substring(0, key);
        String newinput = new String();
        for (int i = 0; i < n; i++) {
            int position = original.indexOf(input.substring(i, i + 1));
            //if its small letter and cant be found
            if (position == -1) {
                //den convert the original alphabets to small letter den search again
                position = original.toLowerCase().indexOf(input.substring(i, i + 1));
                //if its now found as small letter, add it to newinput converting shifted too to lowercase
                if (position > -1) newinput = newinput + shifted.substring(position, position+1).toLowerCase();
                //else if its any other figure like space and ! just add it
                else newinput = newinput + input.substring(i, i + 1);
            }
            //else if its capital letter that can be found just add it.
            //note dat d if statement above deal with small letter and special figures
            else if (position > -1) newinput = newinput + shifted.substring(position, position+1);
        }
        return(newinput);
    }
    
    public void testonlineEncrypt() {
            FileResource fr = new FileResource();
            String message = fr.asString();
            //String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
            String result = onlineEncrypt(message, 15);
            System.out.println("key is "+ 15 + "\n" + result);
    }
    
    
}

