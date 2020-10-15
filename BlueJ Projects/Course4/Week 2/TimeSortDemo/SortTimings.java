import java.util.*;

public class SortTimings {
    Random random = new Random();
    
    //make a random string... just one random string to test something... @param size == size of 
    //random string you want to make
    private String makeString(int size){
        StringBuilder sb = new StringBuilder();
        String alph = "abcdefghijklmnopqrstuvwxyz";
        for(int k=0; k < size; k++){
            sb.append(alph.charAt(random.nextInt(alph.length())));
        }
        return sb.toString();
    }
    
    //make many random strings by putting the above method into a loop... then add all the strings 
    //to an ArrayList... @param wordSize = size of the strings you want to make... @param size = 
    //number of strings you want to make
    public ArrayList<String> makeRandomList(int wordSize, int size){
        ArrayList<String> list = new ArrayList<String>();
        for(int k=0; k < size; k++){
            list.add(makeString(wordSize));
        }
        return list;
    }
    
    //use compareTo() from comparator class in java to write a Bubble Sort code... for comparing
    // i.e sorting Strings @param = the ArrayList of Strings to be sorted
    public void bubbleSort(ArrayList<String> list){
        for(int k=0; k < list.size(); k++) {
            for(int j=0; j < list.size()-k-1; j++) {
                if (list.get(j).compareTo(list.get(j+1)) > 0) {
                    Collections.swap(list, j,j+1);
                }
            }
        }
    }
    
    //use compareTo() from comparator class in java to write a Selection Sort code... for comparing
    // i.e sorting Strings @param = the Array of Strings to be sorted
    public void selectSort(String[] list) {
        for(int k=0; k < list.length; k++){
            int mindex = k;
            for(int j=k+1; j < list.length; j++){
                if (list[j].compareTo(list[mindex]) < 0){
                    mindex = j;
                }
            }
            String temp = list[k];
            list[k] = list[mindex];
            list[mindex] = temp;
        }
    }
    
    //use compareTo() from comparator class in java to write a Selection Sort code... for comparing
    // i.e sorting Strings @param = the ArrayList of Strings to be sorted
    public void selectSort(ArrayList<String> list) {
        for(int k=0; k < list.size(); k++){
            int mindex = k;
            for(int j=k+1; j < list.size(); j++){
                if (list.get(j).compareTo(list.get(mindex)) < 0) {
                    mindex = j;
                }
            }
            Collections.swap(list, k, mindex);
        }
    }
    
    //check method also written using compareTo()
    public boolean isSorted(ArrayList<String> list) {
        for(int k=1; k < list.size(); k++){
            if (list.get(k).compareTo(list.get(k-1)) < 0) {
                return false;
            }
        }
        return true;
    }
    
    //to print out the time to complete Select Sort and the java Collections.sort() method to see 
    //which is faster
    public void timer(int start, int stop, int increment, int trials) {
        for(int k=start; k <= stop; k += increment) {
            ArrayList<String> list = makeRandomList(10,k);
            //for Select Sort
            double begin = System.nanoTime();
            for(int j=0; j < trials; j++) {
                ArrayList<String> copy = new ArrayList<String>(list);
                selectSort(copy);
                //if not sorted after using the method on it
                if (! isSorted(copy)) {
                    //print this to signify that this method wasnt able to sort the file
                    System.out.println("trouble on sorted select "+k);
                }
            }   
            double end = System.nanoTime();
            double stime = (end-begin)/1e9/trials;
            //for Collections.sort
            begin = System.nanoTime();
            for(int j=0; j < trials; j++) {
                ArrayList<String> copy = new ArrayList<String>(list);
                Collections.sort(copy);
                //if not sorted after using the method on it
                if (! isSorted(copy)) {
                    //print this to signify that this method wasnt able to sort the file
                    System.out.println("trouble on sorted tim "+k);
                }
            }   
            end = System.nanoTime();
            double ttime = (end-begin)/1e9/trials;
            System.out.printf("%d\t%3.2f\t%3.2f\n",k,stime,ttime);
        }
    }
    
    //test Select Sort
    public void runSelect(){
        String[] cats = {"tiger", "lion", "cheetah", "puma", "leopard"};
        selectSort(cats);
        for(String s : cats){
            System.out.println(s);
        }
    }
    
    //test timer() method
    public void runner(){
        timer(10000,100000,10000,2);
    }

}

