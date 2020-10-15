/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public void countryInfo(CSVParser parser, String Country) {
        for (CSVRecord record : parser) {
            String count = record.get("Country");
            if (count.contains(Country)) {
                System.out.println(record.get("Country") + ": " + record.get("Exports") +
                ": " + record.get("Value (dollars)"));
            }/*else {
               System.out.println("NOT FOUND");
            }*/
        }
    }
    //(count.contains(Country))
    //(count.indexOf(Country) != -1)exportItem
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String count = record.get("Exports");
            if (count.contains(exportItem1) && count.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public StorageResource numberOfExporters(CSVParser parser, String exportItem) {
        StorageResource store = new StorageResource();
        for (CSVRecord record : parser) {
            String count = record.get("Exports");
            if (count.contains(exportItem)) {
                store.add(record.get("Country"));
            }
        }
        return store;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String count = record.get("Value (dollars)");
            if (count.length() > amount.length()) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //listExporters(parser, "cocoa");
        //countryInfo(parser, "Nauru");
        //listExportersTwoProducts(parser, "cotton", "flowers");
        /*StorageResource res = numberOfExporters(parser, "cocoa");
        System.out.println("Number of Countries:--   " + res.size());*/
        bigExporters(parser, "$999,999,999,999");
    }
}
