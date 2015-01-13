
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import sun.security.provider.certpath.Vertex;

/**
 * @author noah
 * @date 11/27/14
 */


public class Graph{
    //ArrayList 
     public HashMap<Integer,Vertex> map;
    
     /**
      * 1 argument constructor    
     **/
    public Graph(){
        this.map = new HashMap<>();
    }
    /*
    *Vertex inner class
    */
    public static class Vertex {   
        final public ArrayList<Edge> edges = new ArrayList<>(); // Adjacency unvisited
        final public int name;
        private Vertex(final int name){
            this.name = name;
        }
    }
    /**
     * Edge inner class
     */
    public static class Edge {   
        public final Vertex fromVertex;
        public final Vertex toVertex;
        public final int weight; 
        public Edge(final Vertex fromVertex, final Vertex toVertex, final int weight){
            this.fromVertex = fromVertex;
            this.toVertex = toVertex;
            this.weight = weight;
        }      
    }
    /**
     * DijkstraReruen inner class
     * Used in DijkstraReruen method to return a ArrayList and a minimum distance
    **/
    public static class DijkstraReturn{
        ArrayList<Vertex> newVert = new ArrayList<Vertex>();
        int minDistance;
        
        public DijkstraReturn(final ArrayList<Vertex> newVert, int minDistance){
            this.newVert = newVert;
            this.minDistance = minDistance;           
        }
    }
    
    /**
     * Prints the shortest path to the destination
     * @param shortest path ArrayList
     * @return name of the vertex
     */
    public static String printPath(ArrayList<Vertex> path){
        String s = "";
        for (Vertex v : path){
            s += v.name + " --> ";
        }
        return s;
    }
    
    /**
     * Computes the shortest path and the minimum distance
     * @param graph which contains a HashMap
     * @param source vertex
     * @param target vertex
     * @return DijkstraReturn object
     */
    public static DijkstraReturn dijkstra(final Graph graph, final Vertex source, final Vertex target){
        
        final ArrayList<Vertex> unvisited = new ArrayList<>();
        final HashMap<Vertex,Integer> distanceMap = new HashMap<>();
        final HashMap<Vertex,Vertex> previousMap = new HashMap<>();
        
        unvisited.add(source);
        distanceMap.put(source,0);
        
        //Peform the algorithm.
        while(!unvisited.isEmpty()){
            final Vertex v =  minValueArrayList(unvisited, distanceMap);
            //System.out.println("Visiting: " + v.name);
            unvisited.remove(v);
            for(final Edge e : v.edges){
                final Vertex newVertex = e.toVertex;
                if (distanceMap.get(newVertex) == null){
                    unvisited.add(newVertex);
                }
                final int altDistance = distanceMap.get(v) + e.weight;
                if(distanceMap.get(newVertex) == null || altDistance < distanceMap.get(newVertex)){
                    
                    distanceMap.put(newVertex, altDistance);
                    previousMap.put(newVertex, v);
                }
            }
        }
        
        //No path was found.
        if (distanceMap.get(target) == null){
            throw new IllegalArgumentException("No path found.");
        }
        
        //Trace back from the target to find the path.
        final ArrayList<Vertex> shortestPathList = new ArrayList<>();
        Vertex currentVertex = target;
        while(previousMap.get(currentVertex) != null){
            shortestPathList.add(previousMap.get(currentVertex));
            currentVertex = previousMap.get(currentVertex);
        }
        
        Collections.reverse(shortestPathList);
        
        //Return
        DijkstraReturn dijkstraReturn = new DijkstraReturn(shortestPathList,distanceMap.get(target));
        return dijkstraReturn;
    }

    /**
     * Finds the minimum vertex in the ArrayList
     * @param myArrayList contains vertices
     * @param HashMap containing the vertex distances
     * @return the minimum vertex
     */
    public static Vertex minValueArrayList(ArrayList<Vertex> myArrayList, HashMap<Vertex,Integer> distances){
         Vertex minVertex = null;
         int minDistance = -1;
         for(final Vertex v : myArrayList){
             if(minDistance == -1 || distances.get(v) < minDistance){
                 minDistance = distances.get(v);
                 minVertex = v;
             }
         }        
         return minVertex;   
     }
   
   /**
     * Read from a file and pass the Vert into an array unvisited   
     * @param fileName
     * @return ArrayList of vertices
     */
    
    public static Graph readFromFile(String fileName){
            final Graph graph = new Graph();
            
          try{
              FileInputStream inputStream = new FileInputStream(fileName);
              DataInputStream dataInputStream = new DataInputStream(inputStream);
              BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
              Scanner input = new Scanner(inputStream);
              HashMap<Integer, Vertex> map = new HashMap<Integer, Vertex>();

              while(input.hasNextLine()){

                  String line = input.nextLine();	
                    String[] tokens = line.split(" ");
                    if(tokens[0].equals("a")){
                        //Read the line.
                        int fromVertexName = Integer.parseInt(tokens[1]);
                        int toVertexName = Integer.parseInt(tokens[2]);
                        int ajacencyVertexWeight = Integer.parseInt(tokens[3]);
                        //Find our vertices.
                        Vertex fromVertex = graph.map.get(fromVertexName);
                        if (fromVertex == null){
                            fromVertex = new Vertex(fromVertexName);
                            graph.map.put(fromVertexName,fromVertex);
                        }
                        Vertex toVertex = graph.map.get(toVertexName);
                        if (toVertex == null){
                            toVertex = new Vertex(toVertexName);
                            graph.map.put(toVertexName,toVertex);
                        }
                        //Construct the edge.
                        final Edge edge = new Edge(fromVertex, toVertex, ajacencyVertexWeight);
                        fromVertex.edges.add(edge);                    
                    }


              }// end of while loop
              dataInputStream.close();

          }
          catch(FileNotFoundException e){
              System.out.println(e.getMessage());
          }//end of catch block

          catch(IOException e){

              System.out.println(e.getMessage());

          }//end of catch block
          
           return graph;
           
        }// end of method readFromFile

    /**
     * Write the minimum distance and path to a file
     * @param fileName
     * @param ArrayList with the shortest path 
     * @param source Vertex
     * @param destination Vertex
     * @param minDistance to destination
     */
    public static void writeToFile(String fileName, ArrayList<Vertex> path, Vertex source, Vertex destination, int minDistance){

        try {            
             PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));                   
             System.out.print(source);
             System.out.print(destination.name);
             System.out.print(minDistance);
             String cost = "from source: " + source.name + " to destination: " + destination.name + " the Min distance is " + minDistance;
             int start = source.name;
             int finish = destination.name;
          
             out.println(cost);  
             out.println("Minimum path: ");
             for(Vertex v : path){
                 int name = v.name;
                 out.println(name);           
             }
             out.println(finish);

             out.close();                  
            }// end of try block

            catch(FileNotFoundException e){
                System.out.println(e.getMessage());
            }// end of catch block

            catch(IOException e){
                System.out.println(e.getMessage());
            }// end of catch block
        }//end of method writeToFile

}//end of class FindShortetRoadPath











