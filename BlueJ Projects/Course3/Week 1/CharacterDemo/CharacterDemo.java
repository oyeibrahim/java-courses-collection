
/**
 * Illustrate Character methods
 * 
 * @author Duke Software Team 
 * @version (a version number or a date)
 */
public class CharacterDemo {
    public void digitTest() {
        String test = "ABCabc0123456789';#!";
        for(int k=0; k < test.length(); k++){
            char ch = test.charAt(k);
            if (Character.isDigit(ch)){
               System.out.println(ch+" is a digit"); 
            }
            if (Character.isAlphabetic(ch)){
                System.out.println(ch+" is alphabetic");
            }
            if (ch == '#'){
                System.out.println(ch +" #hashtag");
            }
        }
    }
    
    public void conversionTest(){
        String test = "ABCDEFabcdef123!#";
        for(int k=0; k < test.length(); k++){
            char ch = test.charAt(k);
            char uch = Character.toUpperCase(ch);
            char lch = Character.toLowerCase(ch);
            System.out.println(ch+" "+uch+" "+lch);
        }
    }
    
    public boolean isVowel(char ch){
        String test = "AEIOUaeiou";
        //not inside loop because if so, it will do and print the command for every line just like
        //if i use "contain". am just looking for the presence of just one character
        int ns = test.indexOf(ch);
        if (ns != -1){
            //u can print but this print cant come bellow the return or it wont compile
            //saying its an unreachable statement
            //System.out.println("Owambe");
            //u can print "true out just like i did in the method call
            return (true);
        }else{
            //System.out.println("Try Again");
            //u can use it like the one above or like this one
            return false;
        }
        
    }

    public StringBuilder replaceVowels(String phrase, char ch){
        StringBuilder nw = new StringBuilder(phrase);
        for(int k=0; k < nw.length(); k++){
            char sh = nw.charAt(k);
            //call the method "isVowel" if true, this loop will run, else, it will not 
            //run for the line
            if (isVowel(sh)){
                nw.setCharAt(k, ch);
            }
            
        }
        return nw;
    }
    
    public StringBuilder emphasize(String phrase, char ch){
        //this will convert everitin to lowercase before processing but there is a better way
        //that will process the uppercase situation efficiently
        //convert to lower case
        //String np = phrase.toLowerCase();
        //create string builder holding the phrase
        StringBuilder nw = new StringBuilder(phrase);
        char bc = '*';
        char bb = '+';
        for(int k=0; k < nw.length(); k+=2){
            char sh = nw.charAt(k);
            if (sh == Character.toUpperCase(ch) || sh == Character.toLowerCase(ch)){
                nw.setCharAt(k, bc);
            }
        }
        
        for(int k=1; k < nw.length(); k+=2){
            char sh = nw.charAt(k);
            if (sh == Character.toUpperCase(ch) || sh == Character.toLowerCase(ch)){
                nw.setCharAt(k, bb);
            }
        }
        //return is here because we want to return the result of the two loops
        return nw;
    }
    // if (sh == Character.toUpperCase(ch) || sh == Character.toLowerCase(ch))
    //or--- if (sh == ch) ---- but wont handle different cases
    //possible to use this----- nw.setCharAt(k, '*'); instead of declaring another variable
    //and using this----- nw.setCharAt(k, bc);
    
    public void methodsTest(){
        boolean result1 = isVowel('a');
        System.out.println(result1);
        StringBuilder result2 = replaceVowels("Hello World", '*');
        System.out.println(result2);
        StringBuilder result3 = emphasize("dna ctgaaactga", 'a');
        System.out.println(result3);
        StringBuilder result4 = emphasize("Mary Bella Abracadabra", 'a');
        System.out.println(result4);
    }
    
    
    public StringBuilder onlineEmphasize(StringBuilder phrase, char ch) {
        for (int i = 0; i < phrase.length(); i++) {
            char str = phrase.charAt(i);
            if (str == ch) {
                if (i%2 == 0) phrase.setCharAt(i, '*');
                else phrase.setCharAt(i, '+');
            }
        }
        return(phrase);
    }
    
    public void testonlineEmphasize() {
            StringBuilder phrase = new StringBuilder("Mary Bella Abracadabra");
            StringBuilder result = onlineEmphasize(phrase, 'a');
            System.out.println(result);
    }
    
    
    
}
