
/**
 * Write a description of class DistanceComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
//sort for distance using compare() method in the interface class Comparator
public class DistanceComparator implements Comparator<QuakeEntry> {
    Location fromWhere;
    
    public DistanceComparator(Location where) {
        fromWhere = where;
    }
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        double dist1 = q1.getLocation().distanceTo(fromWhere);
        double dist2 = q2. getLocation().distanceTo(fromWhere);
        return Double.compare(dist1, dist2);
    }
    
}
 