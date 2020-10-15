/**
 * Find the highest (hottest) temperature in any number of files of CSV weather data chosen by the user.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar) {
        //If largestSoFar is nothing
        if (largestSoFar == null) {
            largestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentTemp > largestTemp) {
                //If so update largestSoFar to currentRow
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }
    //FUNCTION
    //filenameWithColdestTemp = something.getName();     TO GET FILENAME
    //FUNCTION FOR TEST
    public CSVRecord hottestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        //The largestSoFar is the answer
        return largestSoFar;
    }

    
    public void testHottestInDay () {
        FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("TimeEST"));
    }
    //END
    
    //ANOTHER TEST
    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            // use method to compare two records
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        //The largestSoFar is the answer
        return largestSoFar;
    }

    
    public void testHottestInManyDays () {
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("DateUTC"));
    }
    //END
    //
    //ANOTHER FUNCTION
    public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
        //If smallestSoFar is nothing
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature < smallestSoFar’s
            if (currentTemp < smallestTemp || currentTemp != -9999) {
                //If so update smallestSoFar to currentRow
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }
    
    //FUNCTION TEST
    public CSVRecord coldestHourInFile(CSVParser parser) {
        //start with smallestSoFar as nothing
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        //The smallestSoFar is the answer
        return smallestSoFar;
    }
    
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") +
                   " at " + coldest.get("TimeEDT"));
    }
    //END
    
    //IN MANY
    public CSVRecord coldestInManyDays() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            
            // use method to get largest in file.
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }
    
    
    public void testFileWithColdestTemperature(){
        CSVRecord coldest = coldestInManyDays();
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") +
                   " at " + coldest.get("DateUTC"));
    }
    //END
    
    
    //HUMIDITY
    public CSVRecord getSmallestHumidityOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
        //If smallestSoFar is nothing
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        //Otherwise
        else {
            if (currentRow.get("Humidity") != "N/A" && smallestSoFar.get("Humidity") != "N/A"){
                double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("Humidity"));
                //Check if currentRow’s temperature < smallestSoFar’s
                if (currentTemp < smallestTemp) {
                    //If so update smallestSoFar to currentRow
                    smallestSoFar = currentRow;
                }
            }
        }
        return smallestSoFar;
    }
    
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        //start with smallestSoFar as nothing
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            smallestSoFar = getSmallestHumidityOfTwo(currentRow, smallestSoFar);
        }
        //The smallestSoFar is the answer
        return smallestSoFar;
    }
    
    
    
    public void testLowestHumidityInFile() {
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        
        System.out.println("Lowest Humidity was " + csv.get("Humidity") +
                   " at " + csv.get("DateUTC"));
    }
    //END
    
    
    //IN MANY
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            // use method to compare two records
            smallestSoFar = getSmallestHumidityOfTwo(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }
    
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") +
                   " at " + csv.get("DateUTC"));
    }
    
    //AVERAGE
    public void averageTemperatureInFile (FileResource fr) {
        StorageResource store = new StorageResource();
        double total = 0;
        double average = 0;
        //DirectoryResource dr = new DirectoryResource();
        for (CSVRecord rec : fr.getCSVParser()) {
            store.add(rec.get("Humidity"));
            double temp = Double.parseDouble(rec.get("Humidity"));
            total += temp;
            
        }
        //store.size()
        average = total/store.size();
        System.out.println(average);
        /*double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
        double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));*/
        //return average;
    }
    
    
    public void averageTemperatureInFileMany (FileResource fr) {
        StorageResource store = new StorageResource();
        double total = 0;
        double average = 0;
        //DirectoryResource dr = new DirectoryResource();
        for (CSVRecord rec : fr.getCSVParser()) {
            double temp = Double.parseDouble(rec.get("Humidity"));
            if (temp <= 24){
                store.add(rec.get("Humidity"));
                
                total += temp;
           }
        }
        //store.size()
        
       average = total/store.size();
       System.out.println(average);
        //return average;
    }
    
    //.get("Humidity") CSVParser parser
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        //CSVParser parser = fr.getCSVParser();
        averageTemperatureInFile(fr);
        //averageTemperatureInFileMany(fr);
        //CSVRecord csv = averageTemperatureInFile(parser);
        //CSVRecord largest = averageTemperatureInFile(fr.getCSVParser());
        //System.out.println("Average temperature for selected was " + csv);
    }
    
        public static double averageTemperatureWithHighHumidityInFile(CSVParser parser, double level) {

        double averageTemp;
        double sum = 0;
        int counter = 0;

        for (CSVRecord record : parser) {

            double humidity = Double.parseDouble(record.get("Humidity"));

            if (humidity >= level) {
                sum += Double.parseDouble(record.get("TemperatureF"));
                counter++;
            }

        }

        averageTemp = sum / counter;

        return averageTemp;

    }
    
    
    
        public static double averageTemperatureInFile(CSVParser parser) {

        double averageTemp = 0.0;
        double sum = 0;
        int counter = 0;
        for (CSVRecord record : parser) {


            double recordTemp = Double.parseDouble(record.get("TemperatureF"));

            if (recordTemp == -9999) {
                continue;
            } else {
                sum += Double.parseDouble(record.get("TemperatureF"));
                counter++;
            }


        }

        averageTemp = sum / counter;

        return averageTemp;
    }
    
    
    
    public void testAverageWithHigh() {
        FileResource fr = new FileResource();
        //CSVRecord nw = fr.getCSVParser();
        //double largest = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        double largest = averageTemperatureInFile(fr.getCSVParser());
        System.out.println(largest);
    }
}
