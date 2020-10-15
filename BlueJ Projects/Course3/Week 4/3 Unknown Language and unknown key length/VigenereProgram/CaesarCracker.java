import edu.duke.*;

public class CaesarCracker {
    char mostCommon;
    
    //default construtor if nothing is inputted to initialise the class
    public CaesarCracker() {
        mostCommon = 'e';
    }
    
    //this constructor will work with something inputted for initialisation
    public CaesarCracker(char c) {
        mostCommon = c;
    }
    
    //used to count the number of occurence of each alphabet
    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k=0; k < message.length(); k++){
            int dex = alph.indexOf(Character.toLowerCase(message.charAt(k)));
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    //returns the index with highest value i.e the alphabeth with higest number of occurence
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k=0; k < vals.length; k++){
            if (vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    //returns the key gotten using the above two methods
    public int getKey(String encrypted){
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int mostCommonPos = mostCommon - 'a';
        int dkey = maxDex - mostCommonPos;
        if (maxDex < mostCommonPos) {
            dkey = 26 - (mostCommonPos-maxDex);
        }
        return dkey;
    }
    
    //calls the CaesarCipher class and pass in the key gotten above as key to decrypt a message
    public String decrypt(String encrypted){
        int key = getKey(encrypted);
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(encrypted);
        
    }
   
}
