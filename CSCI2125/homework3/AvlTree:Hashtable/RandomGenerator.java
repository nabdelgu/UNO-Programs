import java.util.ArrayList;

    /**
     * Gives a N amount of random numbers
     * Random numbers are in a specified range    
     * @param N
     * @param maxRange
     * @return ArrayList
     */

    public class RandomGenerator{


    public ArrayList<Integer> random(int N, int maxRange){
       
        ArrayList<Integer> i = new ArrayList<Integer>();
        RandomGenerator rn = new RandomGenerator();
        while(N != 0){
            
           // int answer = rn.nextInt(maxRange) + 1; 
            i.add(N);
            N--;     
        }
        return i;
    }


}
