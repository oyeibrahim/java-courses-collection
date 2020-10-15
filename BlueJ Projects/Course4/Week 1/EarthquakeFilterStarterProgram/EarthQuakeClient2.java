/**
 * Assignment
 * Implementing Filters
 * 
 * @version July 7, 2016
 */

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }
    
    //uses the satisfies method of the @param Filter f i.e the filter it checks for in its class...
    //it is of variable type Filter because it implements the filter class
    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        }
        return answer;
    }
    
    
    ////////////to make a method available to all class, add it to a interface class... e.g the 
    //getName() method... if we didnt put it in interface, the MatchAll will not be able to use it and
    //it will only be possible for getting acessed if we did not pass the instance of its class as a 
    ///////////variable of Filter
    
    
    //uses the above method to filter quake files
    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        //read as QuakeEntry
        ArrayList<QuakeEntry> list  = parser.read(source);
        //prints all entry
        /*
        System.out.println("all quakes read :");
         * for(QuakeEntry qe : list){
            System.out.println(qe);
        }
        System.out.println();
        */
        System.out.println("read data for : " +list.size()+ " quakes");
        System.out.println();
        //Filter f = new MinMagFilter(4.0);
        //using two filters
        //Filter f1 = new MagnitudeFilter(4.0, 5.0);
        //Filter f2 = new DepthFilter(-35000.0, -12000.0);
        
        Location Tokyo = new Location(35.42, 139.43);
        Filter f1 = new DistanceFilter(Tokyo, 10000000.0);
        Filter f2 = new PhraseFilter("end", "Japan");
        
        //pass the QuakeEntry list and filter f1 for first filter then pass the result of the first
        //filter as second parameter for second filter
        
        ArrayList<QuakeEntry> m  = filter(list, f1); 
        ArrayList<QuakeEntry> m7 = filter(m, f2);
        
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        }
        System.out.println();
        System.out.println("Found :--- " + m7.size() + " --- quakes that match that criteria");
        System.out.println();
        System.out.println("Filters used : " + f1.getName() + ", " + f2.getName());
    }
    
    //explained in week 1
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }
    
    //explained in week 1
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }
    }
    
    //to test many filters at once, calling the above method filter once... it uses the 
    //MatchAllFilter() class to do this
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        //read as QuakeEntry
        ArrayList<QuakeEntry> list  = parser.read(source);
        //prints all entry
        /*
        System.out.println("all quakes read :");
        for(QuakeEntry qe : list){
            System.out.println(qe);
        }
        System.out.println();
        */
        System.out.println("read data for : " +list.size()+ " quakes");
        System.out.println();
        /////////////////////////////////////////////////////////
        //we are not passing this instantiation into a Filter variable because we want to call methods
        //in it that are independent of the interface it implements... if we pass it into Filter, there
        //and call methods from it that are independent of the interface class i.e are not declared in 
        //the interface class like method satisfies() that is universal as it is declared in the 
        //interface class, it wont compile....  BUT still even though we did not pass it into Filter,
        //it is still of variable Filter thats why we can pass the instantiated object as second 
        //variable in the method filter i.e filter(list, maf);  and that se cond method is looking for 
        //a Filter variable type i.e filter(ArrayList<QuakeEntry> quakeData, Filter f)... so even if 
        //we did not pass the object of a class that implements an interface class as the interface 
        //class variable type, it is still of its variable type and can be used as its variable 
        //////////////////////type.
        MatchAllFilter maf = new MatchAllFilter();
        //////////////////////////////////////////////////////////
        //this is why we did not pass the MatchAllFilter class instantiation as Filter variable type...
        //because this method addFilter() that we are calling is independent of the interface and so if
        //we had pass the class object as Filter variable type and then call this method, it wont 
        //compile... 
        //we are calling other classes that implements the interface class and passing there object as
        //parameter to method addFilter() in class MatchAllFilter so it can filter with all of them
        //////////////////////together
        
        maf.addFilter(new MagnitudeFilter(0.0, 2.0));
        maf.addFilter(new DepthFilter( -100000.0, -10000.0));
        maf.addFilter(new PhraseFilter("any", "a"));
        
        //maf.addFilter(new MagnitudeFilter(1.0, 4.0));
        //maf.addFilter(new DepthFilter( -180000.0, -30000.0));
        //maf.addFilter(new PhraseFilter("any", "o"));
        
        //maf.addFilter(new MagnitudeFilter(0.0, 5.0));
        //Location BillundDenmark = new Location(55.7308, 9.1153);
        //maf.addFilter(new DistanceFilter(BillundDenmark, 3000000.0));
        //maf.addFilter(new PhraseFilter("any", "e"));
        
        //the maf as @param here is the maf we called the class instantiation those maf.addFilter()
        //are just to call the method addFilter in class MatchAllFilter and add filters into it
        ArrayList<QuakeEntry> m7  = filter(list, maf); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
        System.out.println();
        System.out.println("Found :--- " + m7.size() + " --- quakes that match that criteria");
        System.out.println();
        System.out.println("Filters used are : " + maf.getName());
    }
    
    //like above... to test other filters, not that i cant use the above to test other filters but 
    //just that the assignment say to create this
    //HAS been explained above
    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        //read as QuakeEntry
        ArrayList<QuakeEntry> list  = parser.read(source);
        //prints all entry
        /*
        System.out.println("all quakes read :");
         * for(QuakeEntry qe : list){
            System.out.println(qe);
        }
        System.out.println();
        */
        System.out.println("read data for : " +list.size()+ " quakes");
        System.out.println();
        
        MatchAllFilter maf = new MatchAllFilter();
        //for location in distance filter
        Location Tulsa = new Location(36.1314, -95.9372);
        maf.addFilter(new MagnitudeFilter(0.0, 3.0));
        maf.addFilter(new DistanceFilter(Tulsa, 10000000.0));
        maf.addFilter(new PhraseFilter("any", "Ca"));
        
        ArrayList<QuakeEntry> m7  = filter(list, maf); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        }
        System.out.println();
        System.out.println("Found :--- " + m7.size() + " --- quakes that match that criteria");
        System.out.println();
        System.out.println("Filters used are : " + maf.getName());
    }

}