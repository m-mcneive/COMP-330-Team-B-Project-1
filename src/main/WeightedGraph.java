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
    length = nodesOriginal.length;
    graph = new String[length][length + 1];
    buildGraph();
  }


  public void buildGraph() {

    nodesUpdated = seperateNodes();

    //Takes sorted list and creates the 2D array of the graph
    for (int i = 0; i < length; i ++) {

      //This adds the names of each value to the first index in each row
      graph[i][0] = nodesUpdated[i].getName();

      for (int j = 1; j < length + 1; j++) {
        //Adds a 1 for every edge, signifying that we are unsure if there is a valid connection
        graph[i][j] = "1";
      }
    }
    setDefaultZeros();
  }


  /*
  * This method is used to help build the graph before problem is solved. Uses the
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
      //HashMap of 4 empty arraylists, key of each hash is the type
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


/*
* Sets the default zeros for the graph. The default zeros are for connections between the
* same type   i.e. all of the first names will have a connecrtion of 0 between themselves
*/
private void setDefaultZeros() {

  for (int i = 0; i < length; i ++) {
    for (int j = i; j < length; j ++) {
      if (nodesUpdated[i].getType().equals(nodesUpdated[j].getType())) {
        graph[i][j + 1] = "0";
        graph[j][i + 1] = "0";
      }
    }
  }
}


/*
* n1 and n2 are the 2 nodes that we are connecting
* bool designates whether a connection is positive (2) or negative (0)
*
* Finds the indicies of the 2 nodes and sets them to either 2 or 0 in graph
*/

public void addConnection(Node n1, Node n2, boolean bool, boolean connect) {
  //index of n1
  int connectionIdx1 = -1;
  //index of n2
  int connectionIdx2 = -1;

  System.out.println(n2.getName());
  //Looks through nodesUpdated to find the indecies of n1 and n2
  for (int i = 0; i < length; i ++) {
    if (nodesUpdated[i].getName().equals(n1.getName())) {
      connectionIdx1 = i;
      System.out.println(connectionIdx1 + " idx1");
    } else if (nodesUpdated[i].getName().equals(n2.getName())) {
      connectionIdx2 = i;
      System.out.println(connectionIdx2 + " idx2");
    }
  }
  //A true value for bool means that there is a positive connection between n1 and n2
  //a false bool means a negative one
  if (bool) {
    graph[connectionIdx1][connectionIdx2 + 1] = "2";
    graph[connectionIdx2][connectionIdx1 + 1] = "2";
  } else {
    graph[connectionIdx1][connectionIdx2 + 1] = "0";
    graph[connectionIdx2][connectionIdx1 + 1] = "0";
  }

  if (connect){
    connectRows(n1, n2);
  }
  setNonDefaultZeros();


}


/*
* Iterates through all of graph looking for connections of '2'. If found, this means
* that the rest of connections of the same type to the node will be 0. This method
* finds which group the '2' connection is in and sets the rest of them to 0.
*/

private void setNonDefaultZeros() {
  //Number of elements per type
  int numPerType = length / 4; //3

  //Nested for loops iterate through the whole graph looking for any '2s'
  for (int i = 0; i < length; i++){
    for (int j = 1; j < length + 1; j ++) {
      if (graph[i][j].equals("2")) {

        //finds which type group the 2 was found in (e.g. first names would be group 1,
        //items would be group 2, etc.)
        //Value for setting zeros in a row
        int groupRow = (j-1) / numPerType + 1;

        //Iterates through that group only, setting everything but the 2 to a zero
        //Sets the value in a row only
        for (int x = (groupRow - 1) * numPerType + 1; x < (groupRow) * numPerType + 1; x++) {
          if (!graph[i][x].equals("2"))
            graph[i][x] = "0";
          }

        //finds which type group the 2 was found in (e.g. first names would be group 1,
        //items would be group 2, etc.)
        //Value for setting zeros in a column
        int groupCol = i/numPerType + 1;

        //Iterates through that group only, setting everything but the 2 to a zero
        //Sets the value in a column only
        for (int y = (groupCol - 1) * numPerType; y < groupCol * numPerType; y++) {
          if (!graph[y][j].equals("2"))
            graph[y][j] = "0";
          }
        }
      }
    }
  }


/*
* Method to connect two nodes fully. Once a positive connection has been made
* between two nodes, each one should be connect to all of the other connections
* of each other. For ecample, if Pat lost his comb and we later find out that the
* person who lost his comb was the teacher, then teacher should also connect to Pat
* and vice versa
*/

private void connectRows(Node n1, Node n2) {

  //Indecies of the 2 nodes to be connected
  int n1Idx = -1;
  int n2Idx = -1;
  //Looks through nodesUpdated to find the indecies of n1 and n2
  for (int i = 0; i < length; i ++) {
    if (nodesUpdated[i].getName().equals(n1.getName())) {
      n1Idx = i;
    } else if (nodesUpdated[i].getName().equals(n2.getName())) {
      n2Idx = i;
    }
  }

//Looks through the first node to find any other positive connections
  for (int i = 0; i < length; i++) {
    if (graph[n1Idx][i + 1].equals("2") && n2Idx != i) {
      //If founnd, connects the newly found node to the other one
      addConnection(n2, nodesUpdated[i], true, false);
    }
  }
  //Looks through the second node to find any other positive connections
  for (int i = 0; i < length; i++) {
    if (graph[n2Idx][i + 1].equals("2") && n1Idx != i) {
      //If founnd, connects the newly found node to the other one
      addConnection(n1, nodesUpdated[i], true, false);
    }
  }
}





/*
* prints the graph
*/

  public void printGraph() {
    System.out.print("\t");
    for (int x = -1; x < length; x ++) {
      if (x >= 0) {
        System.out.print(nodesUpdated[x].getName() + "\t");
      }
    }
    System.out.println();
    for (int i = 0; i < length; i ++) {
      for (int j = 0; j < length + 1; j ++) {
        System.out.print(graph[i][j] + "\t");
      }
      System.out.println();
    }
  }
}
