import edu.duke.*;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int theKey;
    
    public CaesarCipher(int key) {
        theKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) +
                            alphabet.substring(0,key);
        alphabet = alphabet + alphabet.toLowerCase();
        shiftedAlphabet = shiftedAlphabet + shiftedAlphabet.toLowerCase();
    }
    
    //encrypt letters one at a time... in encrypt, the from will be alphabet and the to will be 
    //shiftedAlphabet and vice versa... it is a TEMPLATE implemented by methods below
    private char transformLetter(char c, String from, String to) {
        int idx = from.indexOf(c);
        if (idx != -1) {
            return to.charAt(idx);
        }
        return c;
    }
    
    //implements the method transformLetter above to encrypt just ONE character
    public char encryptLetter(char c) {
        return transformLetter(c, alphabet, shiftedAlphabet);
    }
    
    //implements the method transformLetter above to decrypt just ONE character
    public char decryptLetter(char c) {
        return transformLetter(c, shiftedAlphabet, alphabet);
    }
    
    //implements the method transformLetter to encrypt a whole message... this is also a 
    //TEMPLATE implemented by methods below
    private String transform(String input, String from, String to){
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            c = transformLetter(c, from, to);
            sb.setCharAt(i, c);
        }
        return sb.toString();
    }
    
    //implements the method transform above to encrypt a whole message
    public String encrypt(String input) {
        return transform(input, alphabet, shiftedAlphabet);
    }
    
    //implements the method transform above to decrypt a whole message
    public String decrypt(String input) {
        return transform(input, shiftedAlphabet, alphabet);
    }
    
    //prints key by default
    public String toString() {
        return "" + theKey;
    }
    
}
