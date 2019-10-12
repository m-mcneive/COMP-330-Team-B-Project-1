import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import junit.framework.TestCase;

public class WeightedGraphTest3 extends TestCase {
	private WeightedGraph test;
	// I had to change the array graph in WeightedGraph.java to public to get this to work
		public void testAddConnection() {
			Node a = new Node("Bobby", "firstname");
		    Node b = new Node("Charlotte", "firstname");
		    Node c = new Node("Chocolate", "flavor");
	        Node d = new Node("Candles","topping");
		    Node e = new Node("Vanilla", "flavor");
		    Node f = new Node("Sprinkles", "topping");
		    Node g = new Node("Strawberry", "flavor");
	        Node h = new Node("Frosting", "topping");
	        Node i = new Node("Melonie", "firstname");
	       
	        Node[] simpleList = new Node[9];
	        simpleList[0]= a;
	        simpleList[1]= b;
	        simpleList[2]= c;
	        simpleList[3]= d;
	        simpleList[4]= e;
	        simpleList[5]= f;
	        simpleList[6]= g;
	        simpleList[7]= h;
	        simpleList[8]= i;
	        
	        WeightedGraph test = new WeightedGraph(simpleList);
	        //because of the way it is built, buildGraph must work if any of the following methods are to work //
			
			test.addConnection(a,c,false,true);
			assertEquals(true,test.graph[4][1].equals("0"));
			assertEquals(true,test.graph[1][4].equals("0"));
			
			test.addConnection(b,h,true,true);
			assertEquals(true,test.graph[2][8].equals("2"));
			assertEquals(true,test.graph[8][2].equals("2"));
			
		}
		
		public void testCheckforConnections(){
			Node a = new Node("Bobby", "firstname");
		    Node b = new Node("Charl", "firstname");
		    Node c = new Node("Choco", "flavor");
	        Node d = new Node("Candles","topping");
		    Node e = new Node("Vanill", "flavor");
		    Node f = new Node("Sprink", "topping");
		    Node g = new Node("Strawb", "flavor");
	        Node h = new Node("Frost", "topping");
	        Node i = new Node("Meloni", "firstname");
	       
	        Node[] simpleList = new Node[9];
	        simpleList[0]= a;
	        simpleList[1]= b;
	        simpleList[2]= c;
	        simpleList[3]= d;
	        simpleList[4]= e;
	        simpleList[5]= f;
	        simpleList[6]= g;
	        simpleList[7]= h;
	        simpleList[8]= i;
	        
	        WeightedGraph test = new WeightedGraph(simpleList);
	        test.setItemNum(3);
	        test.addConnection(a,c,false,true); //Bobby hates chocolate
	        test.addConnection(i, g, true, true); // Melonie likes strawberry
	        //Charlotte must like chocolate and Bobby likes vanilla
	        
	        test.addConnection(a,d,true,true);//Bobby having birthday
	        test.addConnection(i,f,true,true); // Melonie loves sprinkles
	        //Charlotte must like frosting
	        
	        test.checkForConnections();
	        test.printGraph();
	        System.out.println();
	        System.out.println(test.graph[0][1]);
	        System.out.println(test.graph[0][2]);
	        System.out.println(test.graph[0][3]);
	        System.out.println(test.graph[0][4]);
	        System.out.println(test.graph[0][5]);
	        System.out.println(test.graph[0][6]);
	        System.out.println(test.graph[0][7]);
	        System.out.println(test.graph[0][8]);
	        System.out.println(test.graph[0][9]);
	        
	        
	        System.out.println(test.graph[3][0]);
	        System.out.println(test.graph[4][0]);
	        
	        //vertical starts from 0, horizontal starts from l - the titles on the vertical line aren't part of the array
	        assertEquals(true,test.graph[0][5].equals("2"));// Charlotte and Chocolate coordinates should be 2 (initially 1)
	        assertEquals(true,test.graph[4][1].equals("2")); // opposite coordinates
	        assertEquals(true,test.graph[4][9].equals("2")); // Charlotte and Frosting coordinates should be 2 (initially 1)
	        assertEquals(true,test.graph[8][5].equals("2")); //opposite coordinates
		}
		
		public void testCheckForCompletion() {
			Node a = new Node("Bobby", "firstname");
		    Node b = new Node("Charlotte", "firstname");
		    Node c = new Node("Chocolate", "flavor");
	        Node d = new Node("Candles","topping");
		    Node e = new Node("Vanilla", "flavor");
		    Node f = new Node("Sprinkles", "topping");
		    Node g = new Node("Strawberry", "flavor");
	        Node h = new Node("Frosting", "topping");
	        Node i = new Node("Melonie", "firstname");
	       
	        Node[] simpleList = new Node[9];
	        simpleList[0]= a;
	        simpleList[1]= b;
	        simpleList[2]= c;
	        simpleList[3]= d;
	        simpleList[4]= e;
	        simpleList[5]= f;
	        simpleList[6]= g;
	        simpleList[7]= h;
	        simpleList[8]= i;
	        
	        WeightedGraph test = new WeightedGraph(simpleList);
	        test.setCatNum(3);
	        test.setItemNum(3);
	        
	        test.addConnection(a,c,false,true); //Bobby hates chocolate
	        test.addConnection(i, g, true, true); // Melonie likes strawberry
	        //Charlotte must like chocolate and Bobby likes vanilla
	        
	        assertFalse(test.checkForCompletion());// graph is not finished
	        
	        test.addConnection(a,d,true,true);//Bobby having birthday
	        test.addConnection(i,f,true,true); // Melonie loves sprinkles
	        //Charlotte must like frosting
	        
	        test.checkForConnections();
	        assertTrue(test.checkForCompletion()); // graph is finished at this point
	        
		}
	
	public void testPrintIncompleteGraph() {
			Node a = new Node("Bobby", "firstname");
		    Node b = new Node("Charlotte", "firstname");
		    Node c = new Node("Chocolate", "flavor");
	        Node d = new Node("Candles","topping");
		    Node e = new Node("Vanilla", "flavor");
		    Node f = new Node("Sprinkles", "topping");
		    Node g = new Node("Strawberry", "flavor");
	        Node h = new Node("Frosting", "topping");
	        Node i = new Node("Melonie", "firstname");
	       
	        Node[] simpleList = new Node[9];
	        simpleList[0]= a;
	        simpleList[1]= b;
	        simpleList[2]= c;
	        simpleList[3]= d;
	        simpleList[4]= e;
	        simpleList[5]= f;
	        simpleList[6]= g;
	        simpleList[7]= h;
	        simpleList[8]= i;
	        
	        WeightedGraph test = new WeightedGraph(simpleList);
	        test.setCatNum(3);
	        test.setItemNum(3);
	        
	        test.addConnection(a,c,false,true);
	        test.addConnection(c,h,true,true);
	        test.printGraph();
	        test.printIncompleteGraph();
	        
	        assertEquals(true,test.graph[0][4].equals("0"));
	        assertEquals(true,test.graph[3][1].equals("0")); // Bobby hates chocolate indices
	        assertEquals(true,test.graph[0][9].equals("2"));
	        assertEquals(true,test.graph[8][1].equals("2")); //Chocolate and frosting tied
	        assertEquals(true,test.graph[4][8].equals("1"));
	        assertEquals(true,test.graph[7][5].equals("1")); //Sprinkles and Charlotte relationship
	        assertEquals(true,test.graph[2][6].equals("1"));
	        assertEquals(true,test.graph[5][3].equals("1")); //Melonie and Strawberries relationship should be neutral
	        assertEquals(false,test.graph[2][6].equals("0"));
	        assertEquals(false,test.graph[5][3].equals("0"));
	        assertEquals(false,test.graph[2][6].equals("2"));
	        assertEquals(false,test.graph[5][3].equals("2"));
		}

}
