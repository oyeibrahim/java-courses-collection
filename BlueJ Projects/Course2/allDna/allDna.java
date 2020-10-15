
/**
 * Write a description of allDna here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;


public class allDna {
    public int findStopIndex(String dna, int index){
        int stop1 = dna.indexOf("TGA", index);
        if (stop1 == -1 || (stop1-index) % 3 != 0){
        stop1 = dna.length();
        }
        int stop2 = dna.indexOf("TAA", index);
        if (stop2 == -1 || (stop2-index) % 3 != 0){
        stop2 = dna.length();
        }
        int stop3 = dna.indexOf("TAG", index);
        if (stop3 == -1 || (stop3-index) % 3 != 0){
        stop3 = dna.length();
        }
      return Math.min(stop1, Math.min(stop2,stop3));
   }

   
    public StorageResource printAll(String dna) {
        //FileResource dna = new FileResource(f);
        //String source = page.asString();
        StorageResource store = new StorageResource();
        int start = 0;
        while (true){
            int tag = dna.indexOf("ATG",start);
            if (tag == -1) {
                break;
            }
            int end = findStopIndex(dna, tag+3);
            if (end != dna.length()){
                //System.out.println(
                store.add(dna.substring(tag,end+3));
                start = end+3;
            }else{
                start = start+3;
            }
        }
        return store;
    }
    
    
    public StorageResource findCTG(String result) {
		StorageResource store = new StorageResource();
		int start = 0;
		while (true) {
		    //result here is a method parameter
			int index = result.indexOf("CTG", start);
			if (index == -1) {
				break;
			}
			int end = index+3;
    		store.add(result.substring(index,end));
    		start = end;
			}
			return store;
		}
		
    
    
    
    
    public void myTesting() {
        DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			//return the file as a string
			String s = fr.asString();
			//storage result here because the method we are calling is storage resource
            StorageResource result = printAll(s);
            System.out.println("size = "+ result.size());
            //print line in between to distinguish between results
            System.out.println();
            System.out.println();
            //storage resource here asexplained earlier. it is also applicable 4 every oda method type
            StorageResource res = findCTG(s);
            System.out.println("size = "+ res.size());
            System.out.println();
            System.out.println();
            for (String gene: result.data()){
                StorageResource sr = new StorageResource();
                if (gene.length()>60){
                    //a method of printing result
                    /*sr.add(gene);
                    System.out.println(sr.size());*/
                    //another method
                    System.out.println(gene.length()+ "\t" + gene);
                }
            }
        }
    }
    
    public void printGenes(StorageResource sr){
        for (String gene: sr.data()){
            if (gene.length()>60){
                System.out.println(gene.length()+ "\t" + gene);
            }
        }
    }
    
    /*public void realTesting() {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			String s = fr.asString();
			System.out.println("read " + s.length() + " characters");
			//String result = printAll(s);
			//System.out.println("found " + result);
		}
	}*/

}
