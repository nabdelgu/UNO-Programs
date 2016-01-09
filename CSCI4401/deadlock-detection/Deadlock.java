import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author noah
 */
public class Deadlock {
    
    private static Rag r = new Rag();
    protected static PrintWriter pr = null;
    private static ArrayList<Rag.Node> nodeList = new ArrayList<>();
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        try {
            final String fileName = args[0];
            pr = new PrintWriter("./output.txt");
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = null;
            
            while ((line = br.readLine()) != null) {
                
                String tokens[] = line.split(" ");
                
                int process = Integer.parseInt(tokens[0]);
                String status = tokens[1];
                int resource = Integer.parseInt(tokens[2]);
                
                Rag.Node nProcess = r.getNode(process, nodeList, "Process");
                Rag.Node nResource = r.getNode(resource, nodeList, "Resource");
                
                if (status.equals("N")) {
                    
                    if (nProcess == null && nResource == null) {
                        Rag.Node processNode = new Rag.Node(process, true);
                        Rag.Node resourceNode = new Rag.Node(resource);
                        resourceNode.edgeValue = processNode;
                        nodeList.add(processNode);
                        nodeList.add(resourceNode);
                        //System.out.println("Process " + process + " needs resource " + resource + "- " + "Resource " + resource + " is allocated to process " + process + ".");
                        pr.println("Process " + process + " needs resource " + resource + "- " + "Resource " + resource + " is allocated to process " + process + ".");
                        r.didCauseCycle(resourceNode, resourceNode, pr);
                        r.didCauseCycle(processNode, processNode, pr);
                    }
                    if (nProcess == null && nResource != null) {
                        nodeList.remove(nResource);
                        
                        Rag.Node processNode = new Rag.Node(process, true);
                        if (nResource.edgeValue == null) {
                            nResource.edgeValue = processNode;
                            //System.out.println("Process " + process + " needs resource " + resource + "- " + "Resource " + resource + " is allocated to process " + process + ".");
                            pr.println("Process " + process + " needs resource " + resource + "- " + "Resource " + resource + " is allocated to process " + process + ".");
                        }
                        
                        if (nResource.edgeValue != null) {
                            processNode.edgeValue = nResource;
                            //System.out.println("Process " + process + " needs resource " + resource + "- " + "Process " + process + " must wait");
                            pr.println("Process " + process + " needs resource " + resource + "- " + "Process " + process + " must wait");
                        }
                        
                        nodeList.add(processNode);
                        nodeList.add(nResource);
                        r.didCauseCycle(nResource, nResource, pr);
                        r.didCauseCycle(processNode, processNode, pr);
                    }
                    if (nProcess != null && nResource == null) {                        
                        Rag.Node resourceNode = new Rag.Node(resource);
                        nodeList.remove(nProcess);
                        if (r.getIncomingEdge(nodeList, nProcess) == null) {
                            resourceNode.edgeValue = nProcess;
                            //System.out.println("Process " + process + " needs resource " + resource + "- " + "Process " + process + " must wait");
                            pr.println("Process " + process + " needs resource " + resource + "- " + "Process " + process + " must wait");
                        }
                        if (r.getIncomingEdge(nodeList, nProcess) != null) {
                            nProcess.edgeValue = nResource;
                            //System.out.println("Process " + process + " needs resource " + resource + "- " + "Process " + process + " must wait");
                            pr.println("Process " + process + " needs resource " + resource + "- " + "Process " + process + " must wait");
                        }
                        nodeList.add(resourceNode);
                        nodeList.add(nProcess);
                        
                        r.didCauseCycle(nProcess, nProcess, pr);
                        r.didCauseCycle(resourceNode, resourceNode, pr);
                    }
                    
                    if (nProcess != null && nResource != null) {
                        nodeList.remove(nProcess);
                        nodeList.remove(nResource);
                        
                        if (nResource.edgeValue == null) {
                            nResource.edgeValue = nProcess;
                            //System.out.println("Process " + process + " needs resource " + resource + "- " + "Resource " + resource + " is allocated to process " + process + ".");
                            pr.println("Process " + process + " needs resource " + resource + "- " + "Resource " + resource + " is allocated to process " + process + ".");
                        }
                        if (nResource.edgeValue != null) {
                            nProcess.edgeValue = nResource;
                            //System.out.println("Process " + process + " needs resource " + resource + "- " + "Process " + process + " must wait");
                            pr.println("Process " + process + " needs resource " + resource + "- " + "Process " + process + " must wait");
                        }
                        nodeList.add(nProcess);
                        nodeList.add(nResource);
                         r.didCauseCycle(nProcess, nProcess, pr);
                         r.didCauseCycle(nResource, nResource, pr);
                    }
                    
                } else if (status.equals("R")) {
                    
                    Rag.Node n = r.getIncomingEdge(nodeList, nResource);
                    nodeList.remove(nResource);
                    nodeList.remove(nProcess);
                    
                    if (n == null) {
                        nResource.edgeValue = null;
                        //System.out.println("Process " + process + " releases resource " + resource + "-" + " Resource " + resource + " is now free.");
                        pr.println("Process " + process + " releases resource " + resource + "-" + " Resource " + resource + " is now free.");
                    
                    } else if (n != null) {
                        nodeList.remove(n);
                        nResource.edgeValue = null;
                        n.edgeValue = null;
                        nResource.edgeValue = n;
                        //System.out.println("Process " + process + " releases resource " + resource + "-" + " Resource " + resource + " is allocated to process " + n.value);
                        pr.println("Process " + process + " releases resource " + resource + "-" + " Resource " + resource + " is allocated to process " + n.value);
                        nodeList.add(n);
                    }
                    nodeList.add(nResource);
                    nodeList.add(nProcess);
                    
                }
                
            }
            //System.out.println("EXECUTION COMPLETED: No deadlock encountered.");
            pr.println("EXECUTION COMPLETED: No deadlock encountered.");
            br.close();
            pr.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}

class Rag {

    boolean addStart = true;
    public static Node start;
    ArrayList<Node> deadlockNodes = new ArrayList<>();

    public void setStartValue(Node n) {
        start = n;
    }

    public void didCauseCycle(Node node, Node start, PrintWriter pr) {
        deadlockNodes.add(node);
        if (node.edgeValue != null) {
            if (node.edgeValue.value == start.value && node.edgeValue.type.equals(start.type)) {
                printDeadlockNodes(deadlockNodes, pr);
                addStart = true;
                Deadlock.pr.close();
                System.exit(0);
            }
            if (node.edgeValue != null) {
                didCauseCycle(node.edgeValue, start, pr);
            }

        } else if (node.edgeValue == null) {
            deadlockNodes.clear();
        }

    }

    public void printDeadlockNodes(ArrayList<Node> deadlockNodes, PrintWriter pr) {
        ArrayList<Node> listProcesses = new ArrayList<>();
        ArrayList<Node> listResources = new ArrayList<>();

        for (Node n : deadlockNodes) {

            if (n.type.equals(Node.NodeType.PROCESS)) {
                listProcesses.add(n);

            }
            if (n.type.equals(Node.NodeType.RESOURCE)) {
                listResources.add(n);
            }
        }
        Collections.sort(listProcesses.toArray());
        Collections.sort(listResources.toArray());
        //System.out.print("DEADLOCK DETECTED: Processes ");
        pr.print("DEADLOCK DETECTED: Processes ");
        int counter = 1;
        for (Node n : listProcesses) {
            if (listProcesses.size() == counter) {
                //System.out.print(n.value);
                pr.print(n.value);
            } else if (listProcesses.size() != counter) {
                //System.out.print(n.value + ", ");
                pr.print(n.value + ", ");
            }       
            counter++;
        }
        counter = 1;
        //System.out.print(" and Resources ");
        pr.print(" and Resources ");
        for (Node n : listResources) {
            if (listResources.size() == counter) {
                //System.out.print(n.value);
                pr.print(n.value);
            } else if (listResources.size() != counter) {
                //System.out.print(n.value + ", ");
                pr.print(n.value + ", ");
            }
            counter++;
        }

        //System.out.println(" are found in the cycle.");
        pr.println(" are found in the cycle.");

    }

    public Node getIncomingEdge(ArrayList<Node> nodeList, Node resource) {
        for (Node n : nodeList) {

            if (java.util.Objects.equals(n.edgeValue, resource)) {
                return n;
            }
        }
        return null;
    }

    public Node getNode(int value, ArrayList<Rag.Node> nodeList, String type) {
        if (type.equals("Process")) {
            for (Node n : nodeList) {
                if (n.value == value && n.type.equals(Node.NodeType.PROCESS)) {
                    return n;
                }
            }
        } else if (type.equals("Resource")) {
            for (Node n : nodeList) {
                if (n.value == value && n.type.equals(Node.NodeType.RESOURCE)) {
                    return n;
                }
            }
        }
        return null;
    }

    public static class Node {

        public enum NodeType {

            RESOURCE, PROCESS
        }

        public NodeType type;
        public int value;
        public Node edgeValue;

        public Node(int value, boolean isProcess) {
            this.type = NodeType.PROCESS;
            this.value = value;
            this.edgeValue = null;
        }

        public Node(int value) {
            this.type = NodeType.RESOURCE;
            this.value = value;
            this.edgeValue = null;
        }

    }

}

