
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    //fields
    //current here is the distance we want to compare the quake with... e.g i want to know the 
    //distance btw a quake that happen in china to osogbo... so osogbo's location will be curent here
    private Location current;
    //maxDist here is like the distance in meters .e.g. i want the maximum dis tance btw something to
    //be 50m
    private double maxDist;
    //to print the name of this class when used so we will know what filter we use
    String name = "DistanceFilter";
    
    //constructor
    //gets the values and assign them
    public DistanceFilter(Location cur, double max){
        current = cur;
        maxDist = max;
    }
    
    //returns true if a QuakeEntry's distance from the given location is less than the specified 
    //maximum distance. Otherwise return false
    public boolean satisfies(QuakeEntry qe){
        //you use distanceTo() to get the distance from one location to the other
        if(qe.getLocation().distanceTo(current) < maxDist){
            return true;
        }else{
            return false;
        }
        //ANOTHER method
        //return qe.getLocation() < maxDist;
    }
    
    //return name
    public String getName(){
        return name;
    }
}
