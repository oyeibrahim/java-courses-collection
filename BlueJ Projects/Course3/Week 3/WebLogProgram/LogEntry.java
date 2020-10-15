
/**
 * Write a description of class LogRecord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import  java.util.*;
public class LogEntry{
    //fields for all the elements in the log
     private String ipAddress;
     private Date accessTime;
     private String request;
     private int statusCode;
     private int bytesReturned;
     //constructor... initialises the fields and pass in the elements
   public LogEntry(String ip, Date time, String req, int status, int bytes) {
       ipAddress = ip;
       accessTime = time;
       request = req;
       statusCode = status;
       bytesReturned = bytes;
       
   }
   
   //each of these methods return the elements in them
   public String getIpAddress() {
         return ipAddress;
    }
    public Date getAccessTime() {
         return accessTime;
   }   
   public String getRequest() {
         return request;
   }
   public int getStatusCode() {
         return statusCode;
   }
   public int getBytesReturned() {
         return bytesReturned;
   }
   
   //an automatic output method that joins all the elements and make a log
   public String toString() {
       return ipAddress + " " + accessTime + " " + request 
           + " " + statusCode + " " + bytesReturned;
    }
}
