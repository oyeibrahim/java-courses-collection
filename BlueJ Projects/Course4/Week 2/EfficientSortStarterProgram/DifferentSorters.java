
/**
 * Write a description of class DifferentSorters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
///////////////////////////
//i think the Collections.sort() is for both compare() and compareTo()... it only knows which to call
//depending on the parameters passed in,, in case of compareTo() you pass in one parameter to 
//Collections.sort() and in case of compare(), you pass in two parameters to Collections.sort()
///////////////////////////
public class DifferentSorters {
    //sort using compareTo() in QuakeEntry class which implements comparable and sorts with location
    public void sortWithCompareTo() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list);
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
        System.out.println();
        //to print out the quake in index 10(i.e position 11) like the assignment say... for testing
        int quakeNumber = 10;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }    
    
    //uses MagnitudeComparator class that implements Comparator... the class uses comparator to sort
    //for magnitude
    public void sortByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        //@param list = file you want to sort... @param new MagnitudeComparator()= instance of the
        //class you wrote to sort for this filter you want to sort for
        Collections.sort(list, new MagnitudeComparator());
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }
    
    //uses DistanceComparator class that implements Comparator... the class uses comparator to sort
    //for distance from a place
    public void sortByDistance() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        // Location is Durham, NC
        Location where = new Location(35.9886, -78.9072);
        //@param list = file you want to sort... @param new MagnitudeComparator()= instance of the
        //class you wrote to sort for this filter you want to sort for...the @param inside the 
        //instance is the location you are checking how far from it the quakes are
        Collections.sort(list, new DistanceComparator(where));
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }
    
    //uses TitleAndDepthComparator class to sort for title and if the title are the same, sort for 
    //depth
    public void sortByTitleAndDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        Collections.sort(list, new TitleAndDepthComparator());
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
        System.out.println();
        //to print out the quake in index 10(i.e position 11) like the assignment say... for testing
        int quakeNumber = 10;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }
    
    //uses TitleLastAndMagnitudeComparator class to sort by last word in Title and if they are 
    //the same, sort by Magnitude
    public void sortByLastWordInTitleThenByMagnitude(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        Collections.sort(list, new TitleLastAndMagnitudeComparator());
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
        System.out.println();
        //to print out the quake in index 10(i.e position 11) like the assignment say... for testing
        int quakeNumber = 10;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }
}
