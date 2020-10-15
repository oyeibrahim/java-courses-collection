
public class QuakeEntry implements Comparable<QuakeEntry>{
    
    private Location myLocation;
    private String title;
    private double depth;
    private double magnitude;

    public QuakeEntry(double lat, double lon, double mag, 
                      String t, double d) {
        myLocation = new Location(lat,lon);
        
        magnitude = mag;
        title = t;
        depth = d;
    }
    
    public Location getLocation(){
        return myLocation;
    }
    
    public double getMagnitude(){
        return magnitude;
    }
    public String getInfo(){
        return title;
    }
    public double getDepth(){
        return depth;
    }
    
    //sort for location using compareTo() method in the interface class Comparable... notice how we 
    //have to Override the method... we Override(change the function of the method or what the 
    //method does to what we want it to do)... i think this is because we cant create different class
    //for sorting with different filters in the Comparable compareTo method so we have to Override it 
    //for whatever we want to do...Thats why compare() method in comparator class is better because 
    //using it, we can create different class for different filters, so we dont have to Override for 
    //different filters
    @Override
    public int compareTo(QuakeEntry loc) {
       
       
       
       ////////////////////////////////////////////////////////////////////////
       //sorts by magnitude
       //////you an use this type of code in which case the copareTo takes care of everything... but if
       //you use those ones below, you will have to re-write what the compareTo() already does like 
       //-tive for less, +tive for greater and 0 for equal... but re-writing may be useful sometimes
       //e.g if yo want to check for another filter if the one you are checking for is for example 
       //equal... like is done below
       //return Double.compare(magnitude, loc.getMagnitude());
       
       // Here is another way to sort by Magnitude
       //this makes those with the same magitude get sorted by depth... see below comment on each if 
       //statement
       //for if the loc magnitude is greater
       if (magnitude < loc.getMagnitude()){
           return -1;
          }
       //for if the loc magnitude is less
       if (magnitude > loc.getMagnitude()){
           return 1;
          }
       //for if the loc magnitude is equal (automatically since we have checked for greater and less)
       return Double.compare(depth,loc.getDepth());
       ////////so, if its equal, it instead check for depth
       
       
        
       ///////////////////////////////////////////////////////////////////////////
       //  Below here sorts by Location
        /*
        double difflat = myLocation.getLatitude() - loc.myLocation.getLatitude();
        if (Math.abs(difflat) < 0.001) {
            double diff = myLocation.getLongitude() - loc.myLocation.getLongitude();
            if (diff < 0) return -1;
            if (diff > 0) return 1;
            return 0;
        }
        if (difflat < 0) return -1;
        if (difflat > 0) return 1;
        
        
        // never reached
        return 0;
        */
    }
    
    public String toString(){
        return String.format("(%3.2f, %3.2f), mag = %3.2f, depth = %3.2f, title = %s", myLocation.getLatitude(),myLocation.getLongitude(),magnitude,depth,title);
    }
    
}