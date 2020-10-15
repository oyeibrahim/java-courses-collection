
/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinMagFilter implements Filter
{
    //field
    private double magMin;
    //to print the name of this class when used so we will know what filter we use
    String name = "MinMagFilter";
    
    //constructor
    //interface FILTER becomes a variable for holding this class call
    public MinMagFilter(double min) {
        //collects inputted value
        magMin = min;
    }
    
    //Filter method satisfies is implemented here
    //returns true if its QuakeEntry parameter qe has a magnitude greater than or equal to magMin
    //SINCE its boolean return type, it will return true or false for whatever statement you want to
    //return,,, here it returns true or false for the statement -- qe.getMagnitude() >= magMin
    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin; 
    } 
    
    //return name
    public String getName(){
        return name;
    }
}
