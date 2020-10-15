
/**
 * Write a description of interface Filter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Filter
{
    public  boolean satisfies(QuakeEntry qe);
    
    //to get the name of classes that implement this interface when printing
    public String getName();
}
/*
 * NOTE-- interface becomes a variable for holding the call on whatever class that implements it... So it
 * acts like a variable throughout all the class
 * ANOTHER is that filter acts through all the classes
 */