
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     // constructor
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
     
     //print out the log entries
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         //iterate over the lines
         for (String line : fr.lines()){
             //use WebLogParser.parseEntry to get each line as a log
             LogEntry log = WebLogParser.parseEntry(line);
             //add to records
             records.add(log);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     //return number of unique ip
     public int countUniqueIPs(){
         //holder
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         //iterate over each entry in records
         for(LogEntry le : records){
             //get the IP address
             String IP = le.getIpAddress();
             //if the IP address is not in uniqueIPs
             if(!uniqueIPs.contains(IP)){
                //add the IP address to uniqueIPs
                uniqueIPs.add(IP); 
             }
         }
         //return the size of uniqueIPs
         return uniqueIPs.size();
     }
     
     //print ip that has status code greater than the parameter
     public void printAllHigherThanNum(int num){
         //holder
         ArrayList<LogEntry> entries = new ArrayList<LogEntry>();
         //iterate over each entry in records
         for(LogEntry le : records){
             int StatusCode = le.getStatusCode();
             if(StatusCode > num){
                 entries.add(le);
             }
         }
         
         for (int k=0; k<entries.size(); k++){
             System.out.println(entries.get(k));
         }
     }
     
     //print number of unique ip on that day
     public ArrayList<String> uniqueIPVisitsOnDay(String day){
         //holder
         ArrayList<String> uniqueIPsGivenDay = new ArrayList<String>();
         //iterate over each entry in records
         for(LogEntry le : records){
             String IP = le.getIpAddress();
             //just to access the getAccessTime
             String givenDayHolder = le.getAccessTime().toString();
             //extract the day from getAccessTime
             String givenDay = givenDayHolder.substring(4, 10);
             //here, using == will not work
             if(givenDay.equals(day)){
                //to get just unique ip and prevent recording an ip more than once
                if(!uniqueIPsGivenDay.contains(IP)){
                    //add the day to uniqueIPsGivenDay
                    uniqueIPsGivenDay.add(IP);
                }
             }
         }
         //return uniqueIPsGivenDay
         return uniqueIPsGivenDay;
     }
     
     //return number of unique ip that has status code between low and high inclusive
     public int countUniqueIPsInRange(int low, int high){
         //holder
         ArrayList<String> statusCode = new ArrayList<String>();
         //iterate over each entry in records
         for(LogEntry le : records){
             String IP = le.getIpAddress();
             int StatusCode = le.getStatusCode();
             if(StatusCode >= low && StatusCode <= high){
                //to get just unique ip and prevent recording an ip more than once
                if(!statusCode.contains(IP)){
                    statusCode.add(IP);
                }
             }
         }
         //return uniqueIPsGivenDay
         return statusCode.size();
     }
     
     //ANOTHER LESSON
     //RETURN NUMBER OF TIMES EACH IP VISITED THE SITE
     public HashMap<String,Integer> countVisitsPerIP(){
         //holder
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         //iterate
         for(LogEntry le : records){
             //get ip address
             String ip = le.getIpAddress();
             //if ip is not in counts
             if(! counts.containsKey(ip)){
                 //add the ip as key and 1 as value
                 counts.put(ip,1);
             }//if its already there, increase the value by 1
             else{
                 counts.put(ip,counts.get(ip)+1);
             }
         }
         return counts;
     }
     
     //number of highest visit
     public int mostNumberVisitsByIP(HashMap <String,Integer> counts){
         int holder = 0;
         for (Integer v : counts.values()) {
             if(v > holder){
                 holder = v;
             }
         } 
         return holder;
     }
     
     //returns ips with highest visits even if they are more than one
     public ArrayList<String> iPsMostVisits(HashMap <String,Integer> counts){
         //use the above method to get the most higest visit number
         int higestVisit = mostNumberVisitsByIP(counts);
         //holder
         ArrayList<String> mostVisitIPs = new ArrayList<String>();
         for (String s : counts.keySet()) {
             if(counts.get(s) == higestVisit){
                 mostVisitIPs.add(s);
             }
         }
         return mostVisitIPs;
     }
     
     //maps day to ips that visited on that day
     public HashMap<String, ArrayList<String>> iPsForDays(){
         HashMap<String, ArrayList<String>> counts  = new HashMap<String, ArrayList<String>>();
         for(LogEntry le : records){
             String IP = le.getIpAddress();
             //just to access the getAccessTime
             String givenDayHolder = le.getAccessTime().toString();
             //extract the day from getAccessTime
             String givenDay = givenDayHolder.substring(4, 10);
             //test
             //System.out.println(givenDay);
             //dont put this before the loop or there will be problem
             ArrayList<String> IPs = new ArrayList<String>();
             if(!counts.containsKey(givenDay)){
                 IPs.add(IP);
                 counts.put(givenDay, IPs);
             }
             else{
                 IPs = counts.get(givenDay);
                 IPs.add(IP);
              }
         }
         return counts;
     }
     
     //uses the above method to return the day with highest visit... the above method will be called 
     //in the tester class
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> counts){
         String day = "";
         int check = 0;
         for (String s : counts.keySet()) {
              if(counts.get(s).size() > check){
                  check = counts.get(s).size();
                  day = s;
              }
         } 
         return day;
     }
     
     //returns the ips that has the highest number of visits on the inputted day
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> counts, String day){
         //pass in the ArrayList value to an ArrayList
         ArrayList<String> list = counts.get(day);
         //we are using HashMap because its the best here and it will make us be able to call the 
         //method that will print ips with highest number on it, that is because the method we are 
         //to call require a HashMap <String,Integer> as parameter
         HashMap <String,Integer> countIP = new HashMap <String,Integer>();
         //iterate over ips in list
         for (String s : list) {
             //if countIP doesnt contain the ip before
             if(!countIP.containsKey(s)){
                 //put the ip and put 1 in its value
                 countIP.put(s, 1);
             }//if it contains it before
             else{
                 //put the ip and add one to its value
                 countIP.put(s, countIP.get(s) + 1);
              }
         }
         //call method iPsMostVisits to print the ips with most visit passing in countIP as parameter
         ArrayList<String> ipMostAccess = iPsMostVisits(countIP);
         //return ipMostAccess
         return ipMostAccess;
     }
     
}
