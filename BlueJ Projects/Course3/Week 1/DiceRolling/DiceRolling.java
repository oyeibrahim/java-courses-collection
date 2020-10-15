
/**
 * Simulate rolling two six-sided die, keep statistics
 * 
 * @author Duke Software Team
 * @version 1.0
 */
import java.util.Random;

public class DiceRolling
{
    //array way of counting dice
    public void simulate(int rolls){
        //read random method named rand
        Random rand = new Random();
        //create an array called counts to hold the number of possible outcomes in dice roll, i.e.
        //2s to 12s
        int [] counts = new int [13];
    

        for(int k=0; k < rolls; k++){
            //random for first dice
            int d1 = rand.nextInt(6) + 1;
            //random for second dice
            int d2 = rand.nextInt(6) + 1;
            //a confirmation print to show the code is actually counting occurrences
            System.out.println("roll is " + d1 + "+" + d2 + "=" + (d1+d2));
            //add whatever is the outcome to the right place in the array, e.g. 2s to 2, 12s to 12
            counts[d1+d2] += 1;
        }
        
        for (int k=2; k <=12; k++) {
            //loop over the array elements to print it one by one... i think this is how to print
            //array elements, looping...
            System.out.println(k + "'s=\t" + counts[k] + "\t" + 100.0 * counts[k]/rolls);
        }
        
    }

    //initial... this was created before the one on top
    public void simpleSimulate(int rolls){
        //read random method named rand
        Random rand = new Random();
        //will be used to count the number of 2s and 12s that occur in the dice roll
        int twos = 0;
        int twelves = 0;

        for(int k=0; k < rolls; k++){
            //random for first dice
            int d1 = rand.nextInt(6) + 1;
            //random for second dice
            int d2 = rand.nextInt(6) + 1;
            //check if the roll is a 2 and add it to the 2s count
            if (d1 + d2 == 2){
                twos += 1;
            }
            //check if its 12 and add it
            else if (d1 + d2 == 12){
                twelves += 1;    
            }
        }
        //print the number of 2s and 12s occurence and their percentage
        System.out.println("2's=\t" + twos + "\t" + 100.0 * twos/rolls);
        System.out.println("12's=\t"+twelves+"\t"+100.0*twelves/rolls);
    }
    
    
    
}
