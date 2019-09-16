import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WeightedGraph {

  //Number of unique nodes
  private int length;
  //This will be the final graph we use to solve the problem
  private String[][] graph;
  //This is the list of nodes as they are inputted, need to be sorted
  private Node[] nodesOriginal;
  //This is the list of nodes once seperated by type
  private Node[] nodesUpdated;

  public WeightedGraph(Node[] nodesOriginal) {
    this.nodesOriginal = nodesOriginal;
    length = 8;
    graph = new String[length][length + 1];
    buildGraph();
  }


  public void buildGraph() {
    //List before sorting
    /*for (int x = 0; x < length; x ++) {
      System.out.println(nodesOriginal[x].getName());
    }
  */
    nodesUpdated = seperateNodes();
    //List after sorting
    for (int x = 0; x < length; x ++) {
      System.out.println(nodesUpdated[x].getName());
    }
    System.out.println();

    //Takes sorted list and creates the 2D array of the graph
    for (int i = 0; i < length; i ++) {
      graph[i][0] = nodesUpdated[i].getName();
      for (int j = 1; j < length + 1; j++) {
        graph[i][j] = "1";
      }
    }
    graph[0][0] = nodesUpdated[0].getName();

    //prints graph
    for (int i = 0; i < length; i ++) {
      for (int j = 0; j < length + 1; j ++) {
        System.out.print(graph[i][j] + "\t");
      }
      System.out.println();
    }

  }


  /*
  * This method is used to build the graph before problem is solved. Uses the
  * list of unsorted nodes (nodesOriginal) and hashes them into 4 ArrayLists
  * seperated by type. The lists are then recombined into a new array ordered
  * by type. The order of the elements themselves is not relevant, it is only
  * imoprtant that they be seperated by type.
  */
  private Node[] seperateNodes() {
    //HashMap to store the types of each Node (first name, item, etc)
    //Used to seperate the items in graph by type
    //The ArrayList will store all of the values at each type
    HashMap<String, ArrayList<Node>> types = new HashMap<String, ArrayList<Node>>();

    for (Node n : nodesOriginal) {
      //HashMap of 4 empty arraylists, key of each has is the type
      types.put(n.getType(), new ArrayList<Node>());
    }

    //Populating each ArrayList in the hashmap
    for (Node n : nodesOriginal) {
      types.get(n.getType()).add(n);
    }

    //Adding elements from HashMap ArrayLists to a new Array. Will now be in order
    //Return value
    Node[] arr = new Node[length];
    int i = 0;
    //Iterates through Map and adds elements to arr, seperated by type
    for (Map.Entry<String, ArrayList<Node>> entry : types.entrySet()) {
		    for (Node s : entry.getValue()) {
          arr[i] = s;
          i++;
        }
		}
    return arr;
  }
}
