
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter{
    //fields
    private double minMag;
    private double maxMag;
    //to print the name of this class when used so we will know what filter we use
    String name = "MagnitudeFilter";
    
    //constructor
    //gets the values and assign them
    public MagnitudeFilter(double min, double max){
        minMag = min;
        maxMag = max;
    }
    
    //returns true if a QuakeEntry's magnitude is between the minimum and maximum magnitudes, or equal 
    //to one of them. Otherwise return false
    public boolean satisfies(QuakeEntry qe){
        if(qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag){
            return true;
        }else{
            return false;
        }
        //ANOTHER method
        //return qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag;
    }
    
    //return name
    public String getName(){
        return name;
    }
}
