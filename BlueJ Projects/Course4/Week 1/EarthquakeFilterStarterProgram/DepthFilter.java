
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {
    //fields
    private double minDepth;
    private double maxDepth;
    //to print the name of this class when used so we will know what filter we use
    String name = "DepthFilter";
    
    //constructor
    //gets the values and assign them
    public DepthFilter(double min, double max){
        minDepth = min;
        maxDepth = max;
    }
    
    //returns true if a QuakeEntry's depth is between the minimum and maximum depths, or equal 
    //to one of them. Otherwise return false
    public boolean satisfies(QuakeEntry qe){
        if(qe.getDepth() >= minDepth && qe.getDepth() <= maxDepth){
            return true;
        }else{
            return false;
        }
        //ANOTHER method
        //return qe.getDepth() >= minDepth && qe.getDepth() <= maxDepth;
    }
    
    //return name
    public String getName(){
        return name;
    }
}
