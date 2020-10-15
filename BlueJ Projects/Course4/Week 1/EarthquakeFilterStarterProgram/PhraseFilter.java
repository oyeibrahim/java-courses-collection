
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    String where;
    String phrase;
    //to print the name of this class when used so we will know what filter we use
    String name = "PhraseFilter";
    
    public PhraseFilter(String whr, String phr){
        where = whr;
        phrase = phr;
    }
    
    //returns true if String phrase is in the quake info... String where is to say if the phrase is 
    //the start, end or anywhere in the info
    public boolean satisfies(QuakeEntry qe){
        //get the current quake info
        String info = qe.getInfo();
        //check for start, end and any and add accordingly
        if( where == "start"){
            if(info.startsWith(phrase)){
                return true;
            }
        }
        else if(where == "end"){
            if(info.endsWith(phrase)){
                return true;
            }
        }
        else if(where == "any"){
            if(info.contains(phrase)){
                return true;
            }
        }
        return false;
    }
    
    //return name
    public String getName(){
        return name;
    }
}
