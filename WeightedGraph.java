import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.*;

public class WeightedGraph {

  //Number of unique nodes
  private int length;
  //This will be the final graph we use to solve the problem
  private String[][] graph;
  //This is the list of nodes as they are inputted, need to be sorted
  private Node[] nodesOriginal;
  //This is the list of nodes once seperated by type
  private Node[] nodesUpdated;
  // variables used in changes added by Alex begin here
  public Boolean relation = false;
  public String name1;
  public String name2;
  public String name3;
  public String name4;
  public String name5;
  
  private int n1 = -1, n2 = -1, n3 =-1, n4 = -1, n5 = -1; // only need 5 numbers since you are switching their positions for x,y
  private int counter; // Clue object sends info of how many nodes it has so that it can be used to retrieve them
  private LinkedList<String> clueElements; // holds all nodes involved in a single clue
  // end of variables changed
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
  * separated by type. The lists are then recombined into a new array ordered
  * by type. The order of the elements themselves is not relevant, it is only
  * important that they be separated by type.
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


//test
//test2


  public void printGraph() {
    //prints graph
    for (int i = 0; i < length; i ++) {
      for (int j = 0; j < length + 1; j ++) {
        System.out.print(graph[i][j] + "\t");
      }
      System.out.println();
    }
  }
  
  public void printClueGraph(ArrayList<Clue> clues) {
	  clueElements = new LinkedList<String>(); // holds all nodes involved in a single clue
	  ListIterator<Clue> clueList = clues.listIterator();
	  while (clueList.hasNext()) {
		  Clue c = clueList.next();
		  relation = c.getRelationship();
		  this.counter = c.getCounter();
		  for(int retrieval = 1; retrieval <= counter; retrieval++) { // retrieve nodes from each clue
			  clueElements.add(c.getName(retrieval));
			//  clueElements.add(c.getType(retrieval)); // at this point clueElements would have an alternating order of name and type
		  }                                          // ie: redundant since Matt's Graph is already sorted
		  
		  //take clueElements and find coordinates to change to either 0 or 2
		  ListIterator<String> clueElementIterator = clueElements.listIterator();
		  for(int choice = counter; choice == 0; choice--) {
			  switch(choice) {
				  case 1:
					  name1 = clueElementIterator.next();
					  break;
				  case 2:
					  name2 = clueElementIterator.next();
					  break;
				  case 3:
					  name3 = clueElementIterator.next();
					  break;
				  case 4:
					  name4 = clueElementIterator.next();
					  break;
				  case 5:
					  name5 = clueElementIterator.next();
					  break;
				  default:
					  System.out.println("clueElementIterator error- do not use numbers...");
					  break;
					  
		      }//switch
		   }// for
	  
		  for(int coord = counter; coord == 0; coord--) {
			  int starter = 0;
			  switch(coord) {
			  case 1:
				  while (n1 == -1) {
					  if(graph[starter][0].equals(name1)) {
						  n1 = starter;
						  starter = 0;
					  }
					  else
						  starter++;
				  }
				  
				  break;
			  case 2:
				  while (n2 == -1) {
					  if(graph[starter][0].equals(name2)) {
						  n2 = starter;
						  starter = 0;
					  }
					  else
						  starter++;
				  }
				  break;
			  case 3:
				  while (n3 == -1) {
					  if(graph[starter][0].equals(name3)) {
						  n3 = starter;
						  starter = 0;
					  }
					  else
						  starter++;
				  }
				  break;
			  case 4:
				  while (n4 == -1) {
					  if(graph[starter][0].equals(name4)) {
						  n4 = starter;
						  starter = 0;
					  }
					  else
						  starter++;
				  }
				  break;
			  case 5:
				  while (n5 == -1) {
					  if(graph[starter][0].equals(name5)) {
						  n5 = starter;
						  starter = 0;
					  }
					  else
						  starter++;
				  }
				  break;
			  default:
				  System.out.println("coordinate finding error - do not use numbers...");
				  break;
				  
	          }//switch
		 }// coordinate finding for loop
		  
		  // at this point we have all the names and all of the coordinate numbers tied to each name
		  
	  
	     }// while loop
  } // void printClueGraph
} // weighted graph
