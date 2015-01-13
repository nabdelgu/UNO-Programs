import java.util.ArrayList;
import java.util.Random;

public class Main {
    
    public static void main( String[] args){
        
/*       
 *****************************************************************************************************       
*                  Runntime of HashTable
*/           
            
         ArrayList<Integer> randomNumbers = new RandomGenerator().random(1000, 999);
         QuadraticProbingHashTable H = new QuadraticProbingHashTable(20_000_000);     
         RandomGenerator rn = new RandomGenerator();
        // runntime of hashtable          
         long startTimeHashtable = System.currentTimeMillis();   
        for(Integer n : randomNumbers) {   
            H.insert(new MyInteger(n));
        }           
         long endTimeHashtable   = System.currentTimeMillis();
         long totalTimeHashtable = endTimeHashtable - startTimeHashtable;
        System.out.println("Runtime of HashTable: " + totalTimeHashtable);   
        System.out.println("----------------------------------------------------------------------------------------"); 
       
/*       
 *****************************************************************************************************       
*                      Runntime of AvlTree
*/
         AvlTree a = new AvlTree();              
        long startTimeAvlTree = System.currentTimeMillis();   
        for(int n = 0; n < randomNumbers.size(); n++) {   
        a.insert(randomNumbers.get(n));   
        }           
         long endTimeAvlTree   = System.currentTimeMillis();
         long totalTimeAvlTree = endTimeAvlTree - startTimeAvlTree;
        System.out.println("Runtime of AvlTree: " + totalTimeAvlTree);   
                      
    
    }
        
}

