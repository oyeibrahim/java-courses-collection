
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

//sort by Title and if the title is equal, sort by Depth using compare() method in the interface 
//class Comparator
public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        //how to use or override compare
        //THIS may show that compareTo() is inside compare()... cause we are using compare to when
        //overriding compare
        if(qe1.getInfo().compareTo(qe2.getInfo())>0){
        	return 1;
        }
        else if (qe1.getInfo().compareTo(qe2.getInfo())<0){
        	return -1;
        }//if the info is the same then compare with depth... no need to start writing 1, -1, 0 since
        //am not trying to say if 0 filter by something else... because the method on its own returns
        //1, -1, 0... only if i want to set a condition for anyone maybe 0, i will have to re-write
        //it to return 1, -1, then if 0 return another filter
        else{
        	return Double.compare(qe1.getDepth(), qe2.getDepth());
        }
    }
}
