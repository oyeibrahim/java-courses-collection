import java.util.*;

public class QuakeSort {
    //gets the quake with the lowest value in the filter we are checking for
    public QuakeEntry getSmallestMagnitude(ArrayList<QuakeEntry> quakes) {
        //get the first quake in the file passed in and assign it to min
        QuakeEntry min = quakes.get(0);
        //iterate over all the quakes
        for(QuakeEntry q: quakes) {
            //check for magnitude
            //if the magnitude of the current quake in iteration if less than the magnitude of the 
            //first quake which is min
            if (q.getMagnitude() < min.getMagnitude()) {
                //then min equals the current quake
                min = q;
            }
            //NOTE-- dont think it will just return any quake less than the first quake oo... 
            //REMEMBER that this is an itertion and min will be assigned to all current quake that 
            //is lower than it so min is not constant, it will always change to a lesser quake until
            //the iteration is complete and in which case, it will be the lowest quake
        }
        //return min 
        return min;
    }
    
    public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in) {
        //out starts as empty ArrayList
        //holder
        ArrayList<QuakeEntry> out = new ArrayList<QuakeEntry>();
        //As long as in is not empty
        //to ensure the iteration stops when ArrayList in is empty
        while(!in.isEmpty()) {
            //Find smallest element in in (minElement)... call the method above to get smallest quake
            QuakeEntry minElement = getSmallestMagnitude(in); 
            //Remove the smallest quake (minElement) from in
            in.remove(minElement);                            
            //Add the smallest quake (minElement) to out
            out.add(minElement);
        }
        //out is the answer
        return out;
    }
    /* tester method to use in BlueJ */
    public void testSort(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        //call method sortByMagnitude() on list
        list = sortByMagnitude(list);
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
    }

}
