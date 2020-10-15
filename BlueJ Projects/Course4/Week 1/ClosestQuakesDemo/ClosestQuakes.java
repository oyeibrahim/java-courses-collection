
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes
{  
   //returns closest quakes
   //@param quakeData = Quake entry
   //@param current = city
   //@param howMany = number of closest quake we want to see
   public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, 
                                            Location current, int howMany){
        //creates a new copy ArrayList from the @param quakeData because we are going to delete some 
        //files in it as we use it in this method and we will not want to delete files in the original
        //copy or it will be lost forever... so creating a new copy will ensure we can modify and use 
        //that new copy anyway we like, even deleting files in it
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        //result holder
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        //if the quakeData is less than the number of nearest quake that we want to check, then just 
        //return the whole quakeDate... e.g if we want to check 10 and the data contains 5, just 
        //return the 5 since its not up to 10... NOTE-- the program will return error if the number 
        //want is greater than the total number of data in quakeData, hence the importance of writing
        //this to check that and prevent error
        if(quakeData.size() < howMany){
            System.out.println("WHOOPS!!!... you wanted to print " + howMany + 
                               " number(s) of nearest quake(s), but the file you passed in contains "
                               + copy.size() + " number(s) of quake data. So the total data will "
                               + "be returned inshaAllah, which is " + copy.size() + ":");
            System.out.println();
            ret = copy;
            return ret;
        }
        //you can use else if(quakeData.size() >= howMany) or use just else{} so that it will print
        //for if the number we want is the same as the size of the data... if we didnt pay attention
        //to that i.e. when they are the same, and we just put e.g. 
        //else if(quakeData.size() > howMany), it will not print record anything if they are equal
        else /*if(quakeData.size() >= howMany)*/{
            //gets the number of quakes we want and input in @param howMany... Full explanation below 
            //this loop
            for(int j=0; j < howMany; j++) {
                //NOTE--- this loop gets the nearest... and it gets just ONE quake which is the nearest
                //... to get the number of nearest quakes as much as we want and input in @param 
                //howMany,we must use the ABOVE loop for(int j=0; j < howMany; j++) that will get the 
                //number we want, if we didnt put the above loop, this loop will just get ONE quake 
                //and the method will return just one quake even if we want and input maybe 10 in the 
                //@param howMany
                
                //used to hold the current nearest quake
                int minIndex = 0;
                //iterate over the quake entries
                for(int k=1; k < copy.size(); k++){
                    //get current quake in iteration
                    QuakeEntry quake = copy.get(k);
                    //get its location
                    Location loc = quake.getLocation();
                    //if the distance of the currentquake to @param current(the city we are checking 
                    //for) is less than the distance of the quake at index of variable minIndex 
                    //(((which is 0 at first and is the index number of the largest so far after any 
                    //iteration)))
                    if (loc.distanceTo(current) < 
                        copy.get(minIndex).getLocation().distanceTo(current)){
                        //then set minIndex to be equal to the index of the currentquake
                        minIndex = k;   
                    }
                }
                //get the quake at the index minIndex (which is the index of the closest quake) 
                //and add it to ArrayList ret
                ret.add(copy.get(minIndex));
                //remove the added quake... so that when we iterate again, we will get another 
                //closest quake... if we didnt remove, all the quakes that will be returned even if 
                //we want 20 closest quakes will be the same closest quake, because this method 
                //is just to get the closest quake and it will continue returning closest quake. so 
                //we have to remove the current closest to get another closest
                copy.remove(minIndex);
            }
        }
        return ret;
   }
   
   /*
    * use the above method to get the closest earthquake to whichever location you want to check for
    */
   public void findClosestQuakes(){
       EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //changes the data to a QuakeEntry using the method read() from the class EarthQuakeParser 
        //called above
        ArrayList<QuakeEntry> list  = parser.read(source);
        //print total number of earthquake read from file
        System.out.println("read data for " + list.size());
        System.out.println();
        //pass in the longitude and latitude of the location we wish to check for to the 
        //constructor of the class Location 
        Location jakarta  = new Location(-6.211, 106.845);
        //use the above method to get the closest earthquake to the longitude and latitude  
        //entered above
        //@param = Quake entry
        //@param = city
        //@param = number of closest quake we want to see
        //variable close now contains the closest quake to location
        ArrayList<QuakeEntry> close = getClosest(list, jakarta, 10);
        //print the result
        for(int k=0; k < close.size(); k++){
            //get each entry in variable close one by one
            QuakeEntry entry = close.get(k);
            //finds the distance of each closest quake so we can print... we did not know the distance
            //before, we just get those close quakes
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000, entry);
        }
        System.out.println();
        //prints total number of close quakes found
        System.out.println("number of nearest quakes found: " + close.size());
   }
   
}
