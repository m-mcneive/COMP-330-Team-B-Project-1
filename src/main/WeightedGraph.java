import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class WeightedGraph {
  private Node[][] graph;
  private Node[] nodes;

  public WeightedGraph(Node[] nodes) {
    this.nodes = nodes;
    buildGraph();
  }

  public void buildGraph() {
    graph = new Node[20][20];

    //HashMap to store the types of each Node (first name, item, etc)
    //Used to seperate the items in graph by type
    HashMap<String, Integer> types = new HashMap<String, Integer>();
    for (Node n : nodes) {
      types.put(n.getType(), 1);
    }

    //prints out all of the types in out list
    Iterator it = types.keySet().iterator();
    while (it.hasNext()) { System.out.println(it.next()); }
    
  }
}
