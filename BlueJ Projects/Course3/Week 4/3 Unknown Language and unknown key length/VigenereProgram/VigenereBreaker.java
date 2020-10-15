import java.util.*;
import java.io.*;
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
    
    
    //-----UNKNOWN KEY LENGTH-----
    
    //read the words in dictionary and add them to HashSet for usage
    public HashSet<String> readDictionary(FileResource fr){
        //make a new HashSet
        HashSet<String> words = new HashSet<String>();
        
        for (String line : fr.lines()) {
            //this method prevents declaring another variable to hold the result --like i did--
            //line = line.toLowerCase();
            String lines = line.toLowerCase();
            words.add(lines);
        }
        
        return words;
    }
    
    //splits the message into individual word and look if the words are real, i.e are in the 
    //dictionary... returns the number of real words found
    public int countWords(String message, HashSet<String> dictionary){
        int result = 0;
        for (String word: message.split("\\W")) { 
            word = word.toLowerCase(); 
            if (dictionary.contains(word)){
                result += 1;
            }
        } 

        return result;
    }
    
    //decrypt message trying all possible keylength... and then for each keylenght, check how many
    //real words it has... and return the one with the highest number of real words.. in the assignment
    //we are to try keylength from 1 to 100, if not, we can have tryed more than that... but as 
    //the keylenght increases, the time to process increases
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int[] key_list = new int[100]; 
        int[] wordcount = new int[100]; 
         
        for (int k = 1; k <= 100; k++){
            //to make the array start from 1 and not from 0... we are tying to begin from 1 since 
            //the default is to start from 0 and we did not want that... so we are assigning 0 to 1
            ///---ANOTHER OPINION---we are just assigning the elements in the array to be 1 to 100
            //THIS IS THE CORRECT OPINION SINCE WE CANT CHANGE INDEX VALUE
            key_list[k-1] = k; 
        }
        
        //iterate... to try all possible keys
        for (int k = 0; k < 100; k++) { 
            //call tryKeyLength to try keys from 1 to 100
            int[] key = tryKeyLength(encrypted, key_list[k], 'e'); 
            //pass the key gotten in this current iteration to VigenereCipher class constructor
            VigenereCipher vc = new VigenereCipher(key);
            //use decrypt method to decrypt using the key in this current iteration
            String result = vc.decrypt(encrypted); 
            //usearray above to hold the number of real words in each key i.e. each iteration
            wordcount[k] = countWords(result, dictionary); 
        } 
         
        // figure out which key length has the largest word count. 
        int largest = 0; 
        int index = 0; 
        for (int k = 0; k < 100; k++) { 
            if (wordcount[k] > largest) { 
                largest = wordcount[k]; 
                index = k; 
            } 
        } 
         
        System.out.println("The largest count is "+largest); 
        
        //THE ABOVE is just like used to get the key that is correct AND THE below uses that correct
        //key to decrypt
        //THIS IS LIKE REPETITION but its good so as to just print the decrypted easily
        //the real key will be at index because index has the highest number of real words
        int truekey = key_list[index]; 
        //using the real key to get the keylenght... NOTE key[] now contains the keys
        int[] key = tryKeyLength(encrypted, truekey, 'e'); 
        System.out.println("The keys are "+"\t"); 
        for (int k = 0; k < key.length; k++) { 
            //print the keys
            System.out.println(key[k]); 
        } 
        //print the length of the key which is the length of key[]
        System.out.println("The key length is "+key.length);
        //call VigenereCipher to decrypt
        VigenereCipher vc = new VigenereCipher(key); 
        return vc.decrypt(encrypted); 

    }
    
    //head method for unknown keylenght
    public void breakVigenere2() { 
        FileResource fr = new FileResource("athens_keyflute.txt"); 
        String str = fr.asString(); 
        FileResource dic = new FileResource("dictionaries/English");
        //call method readDictionary
        HashSet<String> dictionary = readDictionary(dic);
        //to test, the message is too much and if i dont test like this, making the breakForLanguage
        //return void and commenting out the place it returns the message and calling it here like 
        //this, i will not be able to see the key
        //---I GOT A SOLUTION--- setting the output window to unlimited buffering makes it display
        //all result without cutting out anything
        //breakForLanguage(str, dictionary);
        String result = breakForLanguage(str, dictionary); 
        System.out.println(result); 
    } 

    
    //-----UNKNOWN KEY LENGTH AND UNKNOWN LANGUAGE-----
    
    //check the letter that occurs most in the dictionary... this will mean that the letter is the 
    //most common since the dictionary contains all words in the language
    public String mostCommonCharIn(HashSet<String> dictionary){
        //hold the each letter and its value
        HashMap<String, Integer> count = new HashMap<String, Integer>(); 
        String all = "abcdefghijklmnopqrstuvwxyz"; 
        for (int k = 0; k < 26; k++) { 
            //put all the alphabets in variable all into the key of HashMap count and put 0 in 
            //each letters value
            count.put(all.substring(k,k+1),0); 
        } 
         
        //iterte over the words in the dictionary
        for (String word: dictionary) { 
            word = word.toLowerCase();
            //split the word into letters... REMEMBER we want to look for the most common letter 
            //and word is made up of letters, so we split the word into letter
            //it then puts each letter gotten into this Array
            String[] letters = word.split("");
            //iterate over each letter in HashMap count key
            for (String letter: count.keySet()) {
                //iterate over the letters in the Array letters
                for (String le: letters) {
                    //if the the letter in the array is equal to the any letter in the HashMap
                    if (le.equals(letter)){
                        //put the letter and add one to its value... we put the letter again because
                        //we cant insert just the value, HashMap require inserting the key and the
                        //value
                        count.put(letter, count.get(letter)+1);
                    }
                } 
            } 
        } 

        //to get the key with highest value and return the key
        int max = 0; 
        String result=null; 
         
        for (String letter: count.keySet()) { 
            if (max < count.get(letter)) { 
                max = count.get(letter); 
                result = letter; 
            } 
        } 
         
        return result; 

        
    }
    
    //uses method mostCommonCharIn above to get the most common letter and pass in that letter instead
    //of the 'e' we were using before
    //decrypt message trying all possible keylength... and then for each keylenght, check how many
    //real words it has... and return the one with the highest number of real words.. in the assignment
    //we are to try keylength from 1 to 100, if not, we can have tried more than that
    public String breakForLanguage2(String encrypted, HashSet<String> dictionary) { 
        // find out the most common char. using mostCommonCharIn
        char common = mostCommonCharIn(dictionary).charAt(0); 
        System.out.println("The most common char is "+common); 
         
        // breakForLanguage 
         
        int[] key_list = new int[100]; 
        int[] wordcount = new int[100]; 
         
        for (int k = 1; k <= 100; k++){
            //we are just assigning the elements in the array to be 1 to 100
            key_list[k-1] = k;
        } 
         
        for (int k = 0; k < 100; k++) {
            //try key from 1 to 100
            int[] key = tryKeyLength(encrypted, key_list[k], common);
            //pass in the current key to the class
            VigenereCipher vc = new VigenereCipher(key);
            //use the current key to decrypt
            String result = vc.decrypt(encrypted);
            //count the number of real words the current key provides
            wordcount[k] = countWords(result, dictionary); 
        } 
         
        // figure out which key length has the most real word count. 
        int largest = 0; 
        int index = 0; 
        for (int k = 0; k < 100; k++) { 
            if (wordcount[k] > largest) { 
                largest = wordcount[k]; 
                index = k; 
            } 
        } 
         
         
        System.out.println("The largest count is (real words) "+largest);
        
        //THE ABOVE is just like used to get the key that is correct AND THE below uses that correct
        //key to decrypt
        //THIS IS LIKE REPETITION but its good so as to just print the decrypted easily
        //the real key will be at index because index has the highest number of real words
        
        //assigns the real key to truekey
        int truekey = key_list[index];
        //using the real key to get the keylenght... NOTE key[] now contains the keys
        int[] key = tryKeyLength(encrypted, truekey, common);
        //to print the key
        System.out.println("The keys are "+"\t"); 
        for (int k = 0; k < key.length; k++) { 
            System.out.println(key[k]); 
        } 
        System.out.println("The key length is "+key.length);
        //pass in the key to the class
        VigenereCipher vc = new VigenereCipher(key);
        //decrypt using the key and print
        System.out.println(vc.decrypt(encrypted));
        //decrypt using the key and return
        return vc.decrypt(encrypted); 
    } 

    //breaks the encryption for each language, and see which gives the best results... the HashMap 
    //in its parameter is mapping each language(key) to its dictionary(value)
    public HashMap<String, String> breakForAllLanguages(String encrypted, 
    HashMap<String, HashSet<String>> languages){
        HashMap<String, String> result = new HashMap<String, String>(); 
        for (String lan: languages.keySet()) {
            //prints the language its currently iterating over
            System.out.println("Currently breaking into "+lan);
            //decrypts using the current language in the iteration and stores the decrypted message 
            //in lanstring
            String lanstring = breakForLanguage2(encrypted, languages.get(lan));
            //put each language and its decrypted message in the HashMap result
            result.put(lan, lanstring); 
        } 
        return result; 

    }
    
    //head method for unknown keylenght and unknown language
    public void breakVigenere3() { 
        ///////////////////FileResource fr = new FileResource("athens_keyflute.txt"); 
        FileResource fr = new FileResource();
        String str = fr.asString(); 
         
        // initialize language HashMap. this will store name of language and its dictionary
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        
        //to select all dictionaries
        DirectoryResource dr = new DirectoryResource();
        //iterate over the selected dictionary
        for (File f: dr.selectedFiles()) {
            //get each dictionary as string
            FileResource fr2 = new FileResource(f.toString());
            //holder, will be put inside the hashmap
            HashSet<String> result = new HashSet<String>();
            //read each line in fr2 (because all words in the dictionary are on DIFFERENT LINES)
            for (String line: fr2.lines()) { 
                line = line.toLowerCase();
                //add each line to HashSet result
                result.add(line); 
            }
            //puts the name of the file as well as its dictionary
            languages.put(f.getName(), result);
            //prints when it finished reading the current iteration
            System.out.println("Finished reading "+f.getName()); 
             
        } 
         
         
        //call break for all languages 
        HashMap<String, String> decrypted = breakForAllLanguages(str, languages);
        System.out.println(decrypted.get("English")); 
        //System.out.println(decrypted); 
    } 

    
}
