
import java.util.Scanner;
/**
 * @author noah
 */
public class FindShortestRoadPath {
    
        /**
         * Main method
         * @param args 
         */
        public static void main(String[] args){
            Scanner scanner = new Scanner(System.in);

             //Take a file name from the command line
             System.out.println("Enter a file name to read in");
             String fileInput = scanner.nextLine();
             final Graph g = Graph.readFromFile(fileInput);
             //Take a source vertex from the command line
             System.out.println("Enter a start vertex");
             String vertexStart = scanner.nextLine();
             int vs = Integer.parseInt(vertexStart);
             final Graph.Vertex start = g.map.get(vs);
             //Take a destination vertex from the command line
             System.out.println("Enter a end vertex");
             String vertexEnd = scanner.nextLine();
             int ve = Integer.parseInt(vertexEnd);
             final Graph.Vertex end = g.map.get(ve);
             //Take in a outputfile name from the commmand line
             System.out.println("Enter the name of an outputfile");
             String fileOutput = scanner.nextLine();
             final String outFileName = fileOutput;

             //Compute the minimum distance and minimum path
             Graph.DijkstraReturn dijkstraReturn = Graph.dijkstra(g, start, end);
             //Write the minimum distance and path to a file            
             //write the path and shortest distance to an output file
             Graph.writeToFile(outFileName, dijkstraReturn.newVert, start, end, dijkstraReturn.minDistance);
        }// end if main method
}// end of class FindShortestRoadPath
