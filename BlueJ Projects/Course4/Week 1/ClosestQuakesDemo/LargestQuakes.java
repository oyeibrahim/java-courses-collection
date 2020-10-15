
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class LargestQuakes {
    
    //reads the passed in data and convert it to QuakeEntry then prints the content and the size of 
    //the QuakeEntry created
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //changes the data to a QuakeEntry using the method read() from the class EarthQuakeParser 
        //called above
        ArrayList<QuakeEntry> list  = parser.read(source);
        /*
        for(QuakeEntry qe : list){
            System.out.println(qe);
        }
        */
        //System.out.println();
        //print total number of earthquake read from file
        System.out.println("read data for " + list.size());
        System.out.println();
        /*
         * test indexOfLargest
         */
        /*
        int highestMagIndex = indexOfLargest(list);
        System.out.println("index with highest magnitude is : " + highestMagIndex);
        System.out.println();
        System.out.println("its info is : " + list.get(highestMagIndex));
        System.out.println();
        System.out.println("its magnitude is : " + list.get(highestMagIndex).getMagnitude());
        */
       
       /*
        * test getLargest
        */
        int howMany = 5;
        ArrayList<QuakeEntry> result = getLargest(list, howMany);
        System.out.println("the " + howMany + " quakes with largest magnitude are : ");
        for(QuakeEntry qe : result){
            System.out.println(qe);
        }
        System.out.println();
        System.out.println("The number is : " + result.size());
    }
    
    /*
     * returns an integer representing the index location in data of the earthquake with the 
     * largest magnitude
     */
    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        //to hold the index
        int holder = 0;
        //to check the highest magnitude
        double check = 0.0;
        for(int k=0; k < data.size(); k++){
            QuakeEntry qe = data.get(k);
            //get the magnitude of the current quake in iteration
            double mag = qe.getMagnitude();
            if(mag > check){
                check = mag;
                //k will be the index, so we are assigning k to holder
                holder = k;
            }
        }
        return holder;
    }
    
    /*
     * returns an ArrayList of type QuakeEntry of the top howMany largest magnitude earthquakes
     * from quakeData
     * i.e returns arraylist of highest magnitude eathquakes... the number of highest magnitude
     * quakes we want is set by @param howMany
     */
    ///////this type of method has been explained in details in the class ClosestQuakes
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        //creates new copy of the original data... this is to prevent editing the original data,
        //since we are going to edit the data here so we will only edit the copy
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        //result holder
        ArrayList<QuakeEntry> res = new ArrayList<QuakeEntry>();
        //to check for if the number you want to print is greater than the data you provided so that
        //it will act... if not, it will return an error
        if(quakeData.size() < howMany){
            System.out.println("WHOOPS!!!... you wanted to print " + howMany + 
                               " number(s) of HighMag quake(s), but the file you passed in contains "
                               + copy.size() + " number(s) of quake data. So the total data will "
                               + "be returned inshaAllah, which is " + copy.size() + ":");
            System.out.println();
            //res is the whole copy
            res = copy;
            return res;
        }//if its freater than or equal to
        else /*if(quakeData.size() >= howMany)*/{
            //this will ensure we get the number of quakes that we want and inputted through @param
            //howMany... the code inside it just returns one quakedata, it is this one that ensure
            //we go over for as many time as we want and get the number of data we want
            for(int j=0; j < howMany; j++) {
                //holder
                int maxIndex = 0;
                //we use the above method to get the quake with highest magnitude
                maxIndex = indexOfLargest(copy);
                
                //add the quake at maxIndex(with highest magnitude) to res
                res.add(copy.get(maxIndex));
                //remove the current highest magnitude quake from copy after putting it in res...
                //this is to ensure we are not printing the same quake through all the iteration...
                //if not, it will return the same quake as much as we inputted through @param howMany
                copy.remove(maxIndex);
            }
            
        }
        //return the answer
        return res;
    }
    
}
