
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

//sort by last word in Title and if equal, sort by Magnitude using compare() method in the 
//interface class Comparator
/////////EXPLAINED in TitleAndDepthComparator class
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        //get the last word.... method- get the substring starting from right after the last space
        String qe1LastWord = qe1.getInfo().substring(qe1.getInfo().lastIndexOf(" ")+1);
		String qe2LastWord = qe2.getInfo().substring(qe2.getInfo().lastIndexOf(" ")+1);
        if(qe1LastWord.compareTo(qe2LastWord)>0){
        	return 1;
        }
        else if (qe1LastWord.compareTo(qe2LastWord)<0){
        	return -1;
        }//explained in TitleAndDepthComparator class
        else{
        	return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
        }
    }
}
