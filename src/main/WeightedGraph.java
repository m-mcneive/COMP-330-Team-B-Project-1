import java.util.*;

public class WeightedGraph {

  //Number of unique nodes
  private int length;
  //This will be the final graph we use to solve the problem
  public String[][] graph;
  //This is the list of nodes as they are inputted, need to be sorted
  private Node[] nodesOriginal;
  //This is the list of nodes once seperated by type
  private Node[] nodesUpdated;

  //Number of categories
  private int catNum;
  //Number of items in a given category
  private int itemNum;

  public WeightedGraph(Node[] nodesOriginal) {
    this.nodesOriginal = nodesOriginal;
    length = nodesOriginal.length;
    graph = new String[length][length + 1];
    buildGraph();
  }

  // DG - added this for the user to be able to enter any number of categories and items within them
  public void setCatNum(int num){this.catNum = num;}
  public void setItemNum(int num){this.itemNum = num;}
  public int getItemNum(){return this.itemNum;}
  public int getCatNum(){return this.catNum;}

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
  int idx1 = -1;
  //index of n2
  int idx2 = -1;

  //Looks through nodesUpdated to find the indecies of n1 and n2
  for (int i = 0; i < length; i ++) {
    if (nodesUpdated[i].getName().equals(n1.getName())) {
      idx1 = i;
    } else if (nodesUpdated[i].getName().equals(n2.getName())) {
      idx2 = i;
    }
  }
  //A true value for bool means that there is a positive connection between n1 and n2
  //a false bool means a negative one
  if (graph[idx1][idx2 + 1].equals("1")) {
    if (bool) {

      graph[idx1][idx2 + 1] = "2";
      graph[idx2][idx1 + 1] = "2";
    } else {

      graph[idx1][idx2 + 1] = "0";
      graph[idx2][idx1 + 1] = "0";
    }
  } else {
    connect = false;
  }


  //This parameter is used so that the method connectRows() does not create an infinite loop
  //of calling this method

  if (connect){
    if (bool) {
      connectPositiveRows(idx1, idx2);
      connectLikeOnes(idx1, idx2);
    }
    if(!bool) {
      connectNegativeRows(idx1, idx2);
    }
  }
  setNonDefaultZeros();
  checkForConnections();
}




/*
* Iterates through all of graph looking for connections of '2'. If found, this means
* that the rest of connections of the same type to the node will be 0. This method
* finds which group the '2' connection is in and sets the rest of them to 0.
*/

private void setNonDefaultZeros() {

  //Nested for loops iterate through the whole graph looking for any '2s'
  for (int i = 0; i < length; i++){
    for (int j = 1; j < length + 1; j ++) {
      if (graph[i][j].equals("2")) {

        //finds which type group the 2 was found in (e.g. first names would be group 1,
        //items would be group 2, etc.)
        //Value for setting zeros in a row
        int groupRow = (j-1) / itemNum + 1;

        //Iterates through that group only, setting everything but the 2 to a zero
        //Sets the value in a row only
        for (int x = (groupRow - 1) * itemNum + 1; x < (groupRow) * itemNum + 1; x++) {
          if (!graph[i][x].equals("2"))
            graph[i][x] = "0";
          }

        //finds which type group the 2 was found in (e.g. first names would be group 1,
        //items would be group 2, etc.)
        //Value for setting zeros in a column
        int groupCol = i/itemNum + 1;

        //Iterates through that group only, setting everything but the 2 to a zero
        //Sets the value in a column only
        for (int y = (groupCol - 1) * itemNum; y < groupCol * itemNum; y++) {
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

private void connectPositiveRows(int idx1, int idx2) {
//Looks through the first node to find any other positive connections
  for (int i = 0; i < length; i++) {
    if (graph[idx1][i + 1].equals("2") && idx2 != i) {
      //If founnd, connects the newly found node to the other one
      addConnection(nodesUpdated[idx2], nodesUpdated[i], true, false);
    }
  }
  //Looks through the second node to find any other positive connections
  for (int i = 0; i < length; i++) {
    if (graph[idx2][i + 1].equals("2") && idx1 != i) {
      //If founnd, connects the newly found node to the other one
      addConnection(nodesUpdated[idx1], nodesUpdated[i], true, false);
    }
  }
}

/*
* Similar to connectPositiveRows but used for when a connection of 0 is given for
* 2 values. If NodeA and NodeB are given a 0 connection, any 2 connection in NodeA
* should create a negative connection for NodeB and vice versa.
*/
private void connectNegativeRows(int idx1, int idx2) {
  for (int i = 0; i < length; i++) {
    if (graph[idx1][i + 1].equals("2")) {
      addConnection(nodesUpdated[idx2], nodesUpdated[i], false, false);
    }
  }
  for (int j = 0; j < length; j++) {
    if (graph[idx2][j + 1].equals("2")) {
      addConnection(nodesUpdated[idx1], nodesUpdated[j], false, false);
    }
  }
}






/*
* Once 2 nodes recieve a positive connection, not only should the 0s be changed.
* This method makes it so that each node will now only share "1" connections. If
* 2 nodes connect, the values that do not share a connection of 1 should both be
* set to 0. Method is only called after a positive connection is made.
*/
private void connectLikeOnes(int idx1, int idx2) {
  //Iterates through every column in a row
  for (int i = 0; i < length; i ++) {
    //If both values to not both equal 1 then they will need to be set to 0
      if (!(graph[idx1][i + 1].equals("1") && graph[idx2][i + 1].equals("1"))) {
        //However, if the values are the 2 connection then they should not change
        if (!(graph[idx1][i + 1].equals("2") || graph[idx2][i + 1].equals("2"))) {
          graph[idx1][i + 1] = "0";
          graph[idx2][i + 1] = "0";
          graph[i][idx1 + 1] = "0";
          graph[i][idx2 + 1] = "0";
        }
      }
    }
}






/*
* Method to look through the whole graph for possible connections that can be made.
* Essentially, it uses the process of elimination to make connections. If a node
* has only a single 1 connection for a value in a specific group and zeros for the
* rest, then the one remaining 1 connection should be a 2.
*/

public void checkForConnections() {
  //Current row
  int row = 0;
  //Current column
  int col = 0;
  //Tracks the number of 1s that appear in a group
  int num1s;
  //If a 1 is found, this will track it's index
  int idx;
  // DG - Adding variables to get the number of categories and items

  //Loop ends when row becomes the length to the graph, will not cause an out of
  //bounds error
  while (row < length) {
    num1s = 0;
    idx = 0;

    //Searches only 5 elements (one group) at a time
    // DG- here instead of 5 i replaces it with itemNum
    for (int i = col; i < col + itemNum; i++) {
      if (graph[row][i + 1].equals("1")) {
        //Increments num1s if a 1 is found
        num1s++;
        idx = i;
      }
    }

    //If only one 1 was found, connect row with the value at idx
    if (num1s == 1) {
      addConnection(nodesUpdated[row], nodesUpdated[idx], true, true);
    }

    //Resets indicies
    if (col == length - itemNum) {
      col = 0;
      row++;
    } else {
      col += itemNum;
    }
  }
}





/*
* Method to check if the graph is complete. Graph is complete when each row has
* exactly three 2 connections. Furthermore, there will be exactly 60 2 connections
*/

public boolean checkForCompletion() {
  //Tracks total number of 2s in the graph
  int num = 0;
  int currentNum = 0;
  for (int i = 0; i < length; i++) {
    //tracks number of 2s in the specific row
    currentNum = 0;
    for (int j = 0; j < length; j++) {
      //Returns false if a one is found at any point
      if (graph[i][j + 1].equals("1")) {
        return false;
        //If the value is a 2, increment both num and current num. If the value
        //is a 0 we do nothing
      } else if (graph[i][j + 1].equals("2")) {
        num++;
        currentNum++;
      }
    }
    //At the end of each row, if currentNum is not 3 then we return false
    if (currentNum != (catNum - 1)) {
      return false;
    }
  }
  //If the total number of 2s found is not 60 then we return false
  if (num != (currentNum*catNum*itemNum)) {
    return false;
  }
  return true;
}


/*
* Once the solution has been found, prints the final connections
*/

public void printCompleteGraph() {
  //First 5 will act as "key" nodes
  for (int i = 0; i < itemNum; i++) {
    System.out.print(nodesUpdated[i].getName() + " -> ");
    //In each row, any 2 connection will be printed
    for (int j = 0; j < length; j++) {
      if (graph[i][j + 1].equals("2")) {
        System.out.print(nodesUpdated[j].getName() + "   ");
      }
    }
    System.out.println();
  }
}



/*
* If the user finishes inputting clues and no solution can be found, prints the
* possible remaining connections
*/

public void printIncompleteGraph() {
  //Selects the first group to act as the "key" nodes
  for (int i = 0; i < itemNum; i++) {
    System.out.print(nodesUpdated[i].getName() + " -> ");
    String current = "";  //String that will be printed
    boolean isValid = false;  //True if a 2 connection is found, false otherwise

    //Loops through the rest of the row one group at a time
    for (int j = 0; j < length; j += itemNum) {

      int numZero = 0;  //Counts the number of zeros in each group
      for (int l = 0; l < itemNum; l++){

        //If a 2 is found then this is an actual connection
        if (graph[i][j + l + 1].equals("2")) {
          current = nodesUpdated[l + j].getName();
          isValid = true;

          //If a one is found then this connection is incomplete. Appends the name
          // and " or "
        } else if (graph[i][j + l + 1].equals("1")) {
          current += nodesUpdated[j + l].getName() + " or ";
          isValid = false;

          //Else increment numZero
        } else {
          numZero ++;
        }
      }

<<<<<<< HEAD
        //If numZero is 5 then the connection is of the same type and should
        //not be printed
        if (isValid && numZero != 5) {
          System.out.print(current + "  ");
          current = "";
          isValid = false;

          //If isValid is false, cuts off the last "or" before printing
        } else if (numZero != 5) {
          System.out.print(current.substring(0, current.length() - 3) + "  ");
=======
        if (isValid && num != itemNum) {
          System.out.print(current + "  ");
          current = "";
          isValid = false;
        } else if (num != itemNum) {
          System.out.print(current.substring(0, current.length()) + "  ");
>>>>>>> f9b91ac3c0e37868cfdcfd431bf5a7e885ed0172
          current = "";
        }
    }
    System.out.println();
  }
}



/*
* prints the graph
*/

  public void printGraph() {
    System.out.print("\t");
    //Prints the column names
    for (int x = 0; x < length; x++) {
      if (nodesUpdated[x].getName().length() > 6) {
        System.out.print(nodesUpdated[x].getName().substring(0,6) + "\t");
      } else {
        System.out.print(nodesUpdated[x].getName() + "\t");
      }
    }
    System.out.println();
    for (int i = 0; i < length; i ++) {
      for (int j = 0; j < length + 1; j ++) {
        if (graph[i][j].length() > 6) {
          System.out.print(graph[i][j].substring(0,6) + "\t");
        } else {
          System.out.print(graph[i][j] + "\t");
        }
      }
      System.out.println();
    }
  }
}
