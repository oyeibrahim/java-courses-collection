
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    //return those with magnitude greater than @param magMin
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //iterate over @param quakeData
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;              
    }
    
    //return those that have a distance less than @param distMax from the location @param from
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, 
                                                       double distMax, Location from) {      
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            //get location of the current quake in the iteration and compare its distance to 
            //@param distMax
            if (qe.getLocation().distanceTo(from) < distMax) {
                //add if the distance is less than distMax
                answer.add(qe);
            }
        }
        return answer;
    }
    
    //gets the CSV out of the passed in file... i.e converts the file passed in to CSV
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
    
    //print those quake with magnitude greater than any number you want
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        //changes the data to a QuakeEntry using the method read() from the class EarthQuakeParser 
        //called above
        ArrayList<QuakeEntry> list = parser.read(source);
        //print total number of earthquake read from file
        System.out.println("read data for " + list.size() + " quakes");
        System.out.println();
        //print those quake with magnitude greater than the number inserted
        /*
        for (QuakeEntry qe : list) {
            if (qe.getMagnitude() > 5.0) {
                System.out.println(qe);
            }
        }
        */
       double num = 5.0;
        //uses method to do the above i.e print those quake with magnitude greater than the number 
        //inserted
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, num);
        for (QuakeEntry qe : listBig) {
           System.out.println(qe); 
        }
        System.out.println();
        System.out.println("Total number of quakes with mag greater than " +
                                    num + " are :- " + listBig.size());
    }
    
    //use method dumpCSV() to convert to CSV
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //changes the data to a QuakeEntry using the method read() from the class EarthQuakeParser 
        //called above
        ArrayList<QuakeEntry> list = parser.read(source);
        //call method dumpCSV() to convert the file to CSV and print.
        dumpCSV(list);
        //print the total number of quakes in the file
        System.out.println("# quakes read: " + list.size());
    }
    
    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //changes the data to a QuakeEntry using the method read() from the class EarthQuakeParser 
        //called above
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        System.out.println();
        
        //Durham, NC
        //Location city = new Location(35.988, -78.907);
        //Bridgeport, CA
        //pass in the longitude and latitude of the location we wish to check for to the constructor 
        //of the class Location
        Location city = new Location(38.17, -118.82);
        //uses method filterByDistanceFrom() created above to get those quakes that have a distance 
        //less than 1000*1000 from the location
        //every quake entry in variable close is of the distance we want since the method 
        //filterByDistanceFrom()
        //has poured the answer into it
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            //we can call distanceTo() on an object only after calling the class Location on that 
            //object because distanceTo() is a method in class Location... distanceTo() gets distance 
            //to the @param passed in in metres
            //we call distanceTo() here so has to be able to print the distance... because even though 
            //the method filterByDistanceFrom() has already gotten all the quakes with the distance, 
            //it wount print the distance and if the distance is not printed we can not confirm if the 
            //method we wrote is working correctly
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }
        System.out.println();
        System.out.println("Found : " + close.size() + " quakes that match that closeness criteria");
    }
    
    //return an ArrayList of type QuakeEntry of all the earthquakes from @param quakeData whose depth 
    //is between minDepth and maxDepth, exclusive
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, 
                                                double maxDepth){
        //holder
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //iterate over @param quakeData
        for (QuakeEntry qe : quakeData) {
            //if the depth is greater than @param minDepth and less than @param maxDepth
            if (qe.getDepth() > minDepth && qe.getDepth() < maxDepth) {
                answer.add(qe);
            }
        }
        return answer;              
    }
    
    //prints quakes whose depth are between two inputted values
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //changes the data to a QuakeEntry using the method read() from the class EarthQuakeParser 
        //called above
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        System.out.println();
        double minDepth = -10000.0;
        double maxDepth = -5000.0;
        System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);
        System.out.println();
        //calls filterByDepth() to ge the quakes whose depth are between two inputted values
        ArrayList<QuakeEntry> result = filterByDepth(list, minDepth, maxDepth);
        for(int k=0; k< result.size(); k++){
            System.out.println(result.get(k));
        }
        System.out.println();
        System.out.println("Found : " + result.size() + " quakes that match that depth criteria");
    }
    
    /*
     * In this assignment you will filter earthquakes by a phrase in the title given for the earthquake in
     * three ways, finding those earthquakes whose title starts with a phrase, ends with a phrase, or
     * just has a phrase somewhere in the title.
     */
    
    
    //return an ArrayList of type QuakeEntry of all the earthquakes from @param quakeData whose titles 
    //have the given @param phrase found at location @param where (“start” means the phrase must start 
    //the title, “end” means the phrase must end the title and “any” means the phrase is a substring 
    //anywhere in the title.)
    /*
     * @param quakeData is the quake entries
     * @param where indicates where to search inthe title and has one of three values: (“start”, ”end”, 
     * or “any”)
     * @param phrase indicates the phrase to search for in the title of the earthquake
     */
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, 
                                                        String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //iterate
        for(QuakeEntry qe : quakeData){
            //get the current quake info
            String info = qe.getInfo();
            //check for start, end and any and add accordingly
            if( where == "start"){
                if(info.startsWith(phrase)){
                    answer.add(qe);
                }
            }
            else if(where == "end"){
                if(info.endsWith(phrase)){
                    answer.add(qe);
                }
            }
            else if(where == "any"){
                if(info.contains(phrase)){
                    answer.add(qe);
                }
            }
            
        }
        
        return answer;              
    }
    
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //changes the data to a QuakeEntry using the method read() from the class EarthQuakeParser 
        //called above
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        System.out.println();
        String where = "start";
        String phrase = "Explosion";
        //calls filterByPhrase() to get the quakes whose info contains the phrase at the beginning,
        //end or anywhere as inputted
        ArrayList<QuakeEntry> result = filterByPhrase(list, where, phrase);
        for(int k=0; k< result.size(); k++){
            System.out.println(result.get(k));
        }
        System.out.println();
        System.out.println("Found : " + result.size() + " quakes that match " + phrase + 
                                            " at " + where);
    }
    
}
