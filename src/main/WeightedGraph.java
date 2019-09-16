import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WeightedGraph {

  //Number of unique nodes
  private int length;
  //This will be the final graph we use to solve the problem
  private Node[][] graph;
  //This is the list of nodes as they are inputted, need to be sorted
  private Node[] nodesOriginal;
  //This is the list of nodes (in Node.name format) once seperated by type
  private String[] nodesUpdated;

  public WeightedGraph(Node[] nodesOriginal) {
    this.nodesOriginal = nodesOriginal;
    length = 5;
    graph = new Node[length][length];
    buildGraph();
  }

  public void buildGraph() {
    //List before sorting
    for (int x = 0; x < length; x ++) {
      System.out.println(nodesOriginal[x].getName());
    }
    nodesUpdated = seperateNodes();
    //List after sorting
    for (int x = 0; x < length; x ++) {
      System.out.println(nodesUpdated[x]);
    }
  }


  public String[] seperateNodes() {
    //HashMap to store the types of each Node (first name, item, etc)
    //Used to seperate the items in graph by type
    //The ArrayList will store all of the values at each type
    HashMap<String, ArrayList<String>> types = new HashMap<String, ArrayList<String>>();
    for (Node n : nodesOriginal) {
      //HashMap of 4 empty arraylists, key of each has is the type
      types.put(n.getType(), new ArrayList<String>());
    }

    //Populating each ArrayList in the hashmap seperated by type
    for (Node n : nodesOriginal) {
      types.get(n.getType()).add(n.getName());
    }

    //Adding elements from HashMap ArrayLists to a new Array. Will now be in order
    String[] arr = new String[length];
    int i = 0;
    //Iterates through Map and adds elements to arr, seperated by type
    for (Map.Entry<String, ArrayList<String>> entry : types.entrySet()) {
		    for (String s : entry.getValue()) {
          arr[i] = s;
          i++;
        }
		}
    return arr;
  }
}
