
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter>  filters;
    //to print the name of each instance loaded into ArrayList<Filter> filters (i.e. filter used) above
    private String name = "";
    
    public MatchAllFilter(){
        filters= new ArrayList<Filter>();
    }
    
    //adds the @param Filter to its private ArrayList
    //like we do when creating a new instance of the classes that implements Filter, i.e we pass their
    //instance into variable Filter so @param Filter f here means f is an instance of a class that 
    //implements Filter... so the method hereis adding instances of classes that implements filter
    //into the ArrayList... This method will add just one though, but if we iterate, it will add as
    //many as we want
    public void addFilter(Filter f){
        filters.add(f);
    }
    
    //returns true if the QuakeEntry satisfies all the filter conditions(from all the classes that 
    //implement interface Filter) that we want it to satisfy, otherwise it returns false
    public boolean satisfies(QuakeEntry qe){
        //for each instances(Filter implementing class) in the ArrayList<Filter> filters
        for(Filter f : filters){
            //if the quake entry from the @param qe does not satisfy the current instance(object of
            //Filter interface implementing class) in iteration, return false
            //tis will do for one quake as the @param is just one quake but iteration will make us do 
            //it for as many quake as we want
            if(!f.satisfies(qe)){
                return false;
            }
        }
        //else (if it satisfies it) return true
        return true;
    }
    
    //return name
    public String getName(){
        for(Filter f : filters){
            name = name + f.getName() + ", ";
        }
        return name;
    }
}
