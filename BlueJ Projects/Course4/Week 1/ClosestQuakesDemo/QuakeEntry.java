
public class QuakeEntry implements Comparable<QuakeEntry>{
    //fields
    private Location myLocation;
    private String title;
    private double depth;
    private double magnitude;
    
    //constructor... gets all the neccessary quake information and assign them to the field variables
    public QuakeEntry(double lat, double lon, double mag, 
                      String t, double d) {
        myLocation = new Location(lat,lon);
        
        magnitude = mag;
        title = t;
        depth = d;
    }
    
    //these contructors(getters) are get the value of the variable outside this class... since the variables are
    //private, if we didnt write a getter method for them, they wont be accessible outside of this 
    //class and as such their values wont be known... NOTE-- these getter methods just return the values
    //to another class, the variables cant be edited from another class through these getter methods
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
    
    //compares my location to the inputted location... i think difference between the two locations
    @Override
    public int compareTo(QuakeEntry loc) {
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
    }
    
    //prints quake information by default
    public String toString(){
        return String.format("(%3.2f, %3.2f), mag = %3.2f, depth = %3.2f, title = %s", myLocation.getLatitude(),myLocation.getLongitude(),magnitude,depth,title);
    }
    
}