/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    //using it as a field makes it common to every method and this will avoid declaring it again for
    //every method i want to use it in
    LogAnalyzer la = new LogAnalyzer();

    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        //i have used it as a field so its common to every method so i dont need to declare again
        //LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }

    public void testUniqueIP(){
        la.readFile("weblog2_log");
        int uniqueIP = la.countUniqueIPs();
        System.out.println("Number of Unique IPs are " + uniqueIP + " IPs");
    }
    
    public void testPrintAllHigherThanNum(){
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay(){
        la.readFile("weblog2_log");
        ArrayList <String> ans = la.uniqueIPVisitsOnDay("Sep 24");
        System.out.println(ans.size());
        //print the entries
        /*for (int k=0; k<ans.size(); k++){
            System.out.println(ans.get(k));
        }*/
    }
    
    public void testCountUniqueIPsInRange(){
        la.readFile("weblog2_log");
        int ans = la.countUniqueIPsInRange(200, 299);
        System.out.println(ans);
    }
    
    public void testCountVisitsPerIP(){
        la.readFile("short-test_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
    }
    
    public void testMostNumberVisitsByIP(){
        la.readFile("weblog2_log");
        //call method to get each ip visit data
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        int mostVisit = la.mostNumberVisitsByIP(counts);
        System.out.println(mostVisit);
    }
    
    public void testIPsMostVisits(){
        la.readFile("weblog2_log");
        //call method to get each ip visit data
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        ArrayList<String> ans = la.iPsMostVisits(counts);
        System.out.println(ans);
    }
    
    public void testIPsForDays(){
        la.readFile("weblog1_log");
        HashMap<String, ArrayList<String>> ans = la.iPsForDays();
        System.out.println(ans);
    }
    
    public void testDayWithMostIPVisits(){
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> counts = la.iPsForDays();
        String ans = la.dayWithMostIPVisits(counts);
        System.out.println(ans);
    }
    
    public void testIPsWithMostVisitsOnDay(){
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> counts = la.iPsForDays();
        ArrayList ans = la.iPsWithMostVisitsOnDay(counts, "Sep 30");
        System.out.println(ans);
    }
}
