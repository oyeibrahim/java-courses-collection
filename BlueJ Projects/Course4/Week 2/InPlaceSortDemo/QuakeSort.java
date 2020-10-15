import java.util.*;

public class QuakeSort {
    /*
     * This class sort by re-arranging the elements in the file instead of creating another file to 
     * hold the sorted element... this prevents erasing elements in the original file and also prevents
     * having to create another file to hold the sorted elements
     */
    //return index of the smallest in the filter we want
    //@param quakes = file to process
    //@param from = which index to start looking for the smallest quake
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        //pass from to minIdx
        int minIdx = from;
        //iterate over the file starting from the index that @param from says to start
        for (int i = from +1; i < quakes.size(); i++) {
            //we use minIdx which is now equal to @param from as index to get the index of the quake
            //we are to start from
            //if the current quake in iteration is smaller than the quake at minIdx(the quake we 
            //started from)
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                //then index of that quake equal to minIdx
                minIdx = i;
            }
            //the iteration will ensure we get the smallest quake... Explained in project
            //SelectionSortDemo in week two under class QuakeSort
        }
        //return minIdx(index of smallest quake)
        return minIdx;
    }
    
    //uses the method above to get the sort the file passed in
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
        //count from 0 to < in.size()
        //iterate
        for(int i = 0; i < in.size(); i++) {
            /* find the index of the smallest quake*/
            //call method getSmallestMagnitude() above to get the index of the sallest quake...
            //NOTE-- @param i ... this is the index the method above will start looking for the 
            //smallest quake... this is because whereever the i loop above gets to is  where we have 
            //sorted to and we will not want the method to look into that place because it is already
            //sorted and also if it were to look into that place, it will get the smallest quake from
            //that place because quakes in that place will always be smaller than the current quake 
            //and above because we are sorting according to size(i.e. small ones first) and so that
            //place will already contain the smallest quakes
            int minIdx = getSmallestMagnitude(in, i);
            /* swap the ith quake with the minIdxth quake */
            //get the quake at index of current iteration
            QuakeEntry qi = in.get(i);
            //get the quake at index returned by method getSmallestMagnitude()... this will be the 
            //smallest quake from where we start from
            QuakeEntry qmin = in.get(minIdx);
            //change the element at the current iteration into the smallest element gotten through
            //the method above i.e assigned to qmin
            in.set(i, qmin);
            //change the element at the index returned by above method(samllest quake index) which 
            //is now empty since we have moved it to the index of the current iteration to the 
            //element at the index of the current iteration
            in.set(minIdx, qi);
            //////////////////////
            //what happened above... suppose we have arranged to 5 (that will be the current iteration)
            // 1, 2, 3, 4, 10, 7, 9, 8, 5, 6
            //This--- in.set(i, qmin);-- will move 5 (which will be the index returned by the method
            //above to where 10 is now
            //so---   1, 2, 3, 4, --5--, 7, 9, 8, --Empty--, 6
            //This--- in.set(minIdx, qi);-- will then move 10 to where 5 was, that is now empty since
            //5 was moved
            //so---   1, 2, 3, 4, --5--, 7, 9, 8, --10--, 6
            //////////////////////
        }
    }
    
    
    /////////////////////////////-------------/ HEAD /-----------------////////////////////////////////
    /* tester method to use in BlueJ */
    public void testSort(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/earthquakeDataSampleSix2.atom";
        String source = "data/earthquakeDataSampleSix1.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        //call method sortByMagnitude() above to sort the ArrayList elements
        //we can call it like this because the method is not returning a new ArrayList, it is only 
        //re-arranging elements of the current ArrayList
        //
        //sortByMagnitude(list);
        
        ////////////
        
        //test for sortByLargestDepth()
        //sortByLargestDepth(list);
        
        ////////////
        
        //test for sortByMagnitudeWithBubbleSort()
        //sortByMagnitudeWithBubbleSort(list);
        //explanatory printing only for small file like "data/earthquakeDataSampleSix2.atom" bcos the 
        //printing will be too much for a big file
        /*
        System.out.println();
        System.out.println("EarthQuakes in sorted order :");
        System.out.println();
        */
        
        ////////////
        
        //test for sortByMagnitudeWithBubbleSortWithCheck()
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        
        ////////////
        
        //test for sortByMagnitudeWithCheck()
        sortByMagnitudeWithCheck(list);
        
        ////////////
        
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
    }
    
    
    //Depth sort largest to smallest
    //returns an integer representing the index position of the QuakeEntry with the largest depth
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from){
        //pass from to minIdx
        int maxIdx = from;
        //iterate over the file starting from the index that @param from says to start
        for (int i = from +1; i < quakes.size(); i++) {
            //we use minIdx which is now equal to @param from as index to get the index of the quake
            //we are to start from
            //if the current quake in iteration is greater than the quake at minIdx(the quake we 
            //started from)
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                //then index of that quake equal to minIdx
                maxIdx = i;
            }
            //the iteration will ensure we get the smallest quake... Explained in project
            //SelectionSortDemo in week two under class QuakeSort
        }
        //return minIdx(index of smallest quake)
        return maxIdx;
    }
    
    //uses the method above to get the sort the file passed in
    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
        //count from 0 to < in.size()
        //iterate
        for(int i = 0; i < in.size(); i++) {
            /* find the index of the largest quake*/
            int maxIdx = getLargestDepth(in, i);
            /* swap the ith quake with the minIdxth quake */
            //get the quake at index of current iteration
            QuakeEntry qi = in.get(i);
            //get the quake at index returned by method getLargestDepth()... this will be the 
            //largest quake from where we start from
            QuakeEntry qmax = in.get(maxIdx);
            //change the element at the current iteration into the largest element gotten through
            //the method above i.e assigned to qmax
            in.set(i, qmax);
            //change the element at the index returned by above method(largest quake index) which 
            //is now empty since we have moved it to the index of the current iteration to the 
            //element at the index of the current iteration
            in.set(maxIdx, qi);
        }
    }//////////////////////tested in method testSort() above
    
    
    /*
     * Bubble Sort
     */
    //@param int numSorted = represents the number of times this method has already been called on this
    //ArrayList and thus also represents the number of the elements that are guaranteed to already be 
    //where they belong when the ArrayList is sorted by magnitude
    //Bubble sort sorts the file passing through the whole file different times with first pass 
    //guaranteeing that the last element is in order, the 2nd guaranttee that the 2nd to last element 
    //is in order 3rd, 3rd to last is in order. So it will put all in order after going through the 
    //file in the fileSize - 1 times, putting the second to the first in place, in which case the first
    //to will have been in the right place automatically, so no need to go through the file for the 
    //first element again
    /////SO bubble sort sorts from last to first i.e putting the last elements in place before putting
    //the first elements in place.
    //SO @param already sorted and so it will guide us to where we stop iteration
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted ){
        //its starting fron 1 bcos we dont need to check for first element (REMEMBER!)....
        //i< quakeData.size()-numSorted bcos numSorted will be increasing with
        //each complete iteration and so to get the numSorted for each iteration we say the 
        //size - numSorted... this will give us the number of files that we will iterate over
        //and will guide to where to stop iteraion
        for (int i=1; i< quakeData.size()-numSorted; i++) {
            //if magnitude of quake at index i-1 > that at index i.... for the first iteration it 
            //looks like this:-
            //if magnitude of quake at index 0(i-1) > that at index 1
            //REMEMBER that i will be changing every iteration
            if (quakeData.get(i-1).getMagnitude() > quakeData.get(i).getMagnitude()) {
                //if its greater, get quake at i-1(the bigger quake)
                QuakeEntry qireduceone = quakeData.get(i-1);
                //get quake at i (the less quake)
                QuakeEntry qi = quakeData.get(i);
                //set quake at i-1 to be quake at i... i.e. set the bigger in front of smaller, where
                //smaller was before
                quakeData.set(i-1,qi);
                ///set quake at i to be quake at i-1... i.e. set the smaller behind bigger, where
                //bigger was before
                quakeData.set(i,qireduceone);
            }
        }
    }
    
    //If the ArrayList in has N elements in it, this method should call onePassBubbleSort N – 1 
    //times to sort the elements in ArrayList in
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        //iterates in fileSize-1 times
        for (int i=0; i< in.size()-1; i++) {
            onePassBubbleSort(in, i);
            
            //explanatory printing only for small file like "data/earthquakeDataSampleSix2.atom" bcos the 
            //printing will be too much for a big file
            /*
            System.out.println();
            System.out.println("Printing Quakes after pass " + i + " :");
            System.out.println();
            for (QuakeEntry qe: in) {
                System.out.println(qe);
            }
            */
        }
    }
    
    //CHECK IF ANY of the sorts have complted before the whole iteration is complete so we can stop 
    //the iteration at point of complete sorting even if the iteration is not complete, this will 
    //save time
    
    //method returns true if the earthquakes are in sorted order by magnitude from smallest to 
    //largest. Otherwise this methods returns false
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        //we use i = 1 because we want to access i-1 and i-1 = 0 so its like we are starting from 0
        //we use quakes.size()-1 bcos we dont need to check for the last... if all the rest are in 
        //order, then the last will be in order
        for (int i=1; i< quakes.size()-1; i++){
            if (quakes.get(i-1).getMagnitude() > quakes.get(i).getMagnitude()) {
                return false;
            }
        }
        return true;
    }
    
    //like method sortByMagnitudeWithBubbleSort() above but this time, it is checking so as to stop
    //earlier than iteration if the sorting has been completed earlier than iteration
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        //iterates in fileSize-1 times
        for (int i=0; i< in.size()-1; i++) {
            //call method checkInSortedOrder() to check before every iteration if sort has already 
            //been completed
            if(checkInSortedOrder(in)) {
                //if so, print this to tell that the iteration is been stoped
				System.out.println(i + " passes were needed to sort the elements.");
				//if so, break i.e. stop the iteration
				break;
			}
            onePassBubbleSort(in, i);
            
            //explanatory printing only for small file like "data/earthquakeDataSampleSix2.atom" bcos the 
            //printing will be too much for a big file
            /*
            System.out.println();
            System.out.println("Printing Quakes after pass " + i + " :");
            System.out.println();
            for (QuakeEntry qe: in) {
                System.out.println(qe);
            }
            */
        }
    }
    
    
    
    //////////////////
    //we cant use method checkInSortedOrder() that we used to check for Bubble sort because in the loop 
    //of bubble sort check, we use i< quakes.size()-1 (( quakes.size()-1 )) and size-1 is not used in
    //selection sortso that method cant be used for checking selection sort like we want to in methods 
    //below
    /////////////////
    
    //new checkInSortedOrder
    public boolean checkInSortedOrderForSelectionSort(ArrayList<QuakeEntry> quakes){
        //we use i = 1 because we want to access i-1 and i-1 = 0 so its like we are starting from 0
        //we use quakes.size()-1 bcos we dont need to check for the last... if all the rest are in 
        //order, then the last will be in order
        for (int i=1; i< quakes.size(); i++){
            if (quakes.get(i-1).getMagnitude() > quakes.get(i).getMagnitude()) {
                return false;
            }
        }
        return true;
    }
    
    //its like method sortByMagnitude() above but this time, it is checking like the method above
    //using method checkInSortedOrderForSelectionSort() above
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        
        for(int i = 0; i < in.size(); i++) {
            //call method checkInSortedOrder() to check before every iteration if sort has already 
            //been completed
            if(checkInSortedOrderForSelectionSort(in)) {
                //if so, print this to tell that the iteration is been stoped
				System.out.println( i + " passes were needed to sort the elements.");
				//if so, break i.e. stop the iteration
				break;
			}
            int minIdx = getSmallestMagnitude(in, i);
            /* swap the ith quake with the minIdxth quake */
            QuakeEntry qi = in.get(i);
            
            QuakeEntry qmin = in.get(minIdx);
            
            in.set(i, qmin);
            
            in.set(minIdx, qi);
            
        }
    }
    
}
