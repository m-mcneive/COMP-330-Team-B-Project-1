import java.util.Scanner;
import java.util.ArrayList;

public class FinalRunner {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int sizes[] = Greeting(scan); // Start the Program and get the number of Categories and Items per Category
		Node[] list = nodeListCreator(sizes[0], sizes[1], scan);// Continues the program and creates a list of 
																// nodes based on category and item names
		WeightedGraph wg = new WeightedGraph(list); // Creates a Graph with the nodes created above
        wg.setCatNum(sizes[1]);
        wg.setItemNum(sizes[0]);
        wg.printGraph();
		
		clueLoop(list, wg, scan); // Here the user is continuously prompted to input clues
								  // User may ask for help at any point here for a list of options
								  // User may reset the graph
								  // User may type "done" to signify the completion of list of clues
								  // at which point the program will output partially completed graph
								  // along with all possible connections
	}
	
	public static int[] Greeting(Scanner scan) {
		String numOfCat="0";
		String numOfItem="0";
		int num[] = new int [2];
		
		System.out.println("Hello! Enter the number of categories.");
        numOfCat = scan.next();

        if(!isNumber(numOfCat)){
            do{
                System.out.println("I'm sorry, but that doesn't seem to be a number. \n" +
                        "Please enter the NUMBER of categories.");
                numOfCat = scan.next();
            }while(!isNumber(numOfCat));
        }

        System.out.println("Great! Now enter the number of items within each category.");
        numOfItem = scan.next();

        if(!isNumber(numOfItem)){
            do{
                System.out.println("I'm sorry, but that doesn't seem to be a number. \n" +
                        "Please enter the NUMBER of items.");
                numOfItem = scan.next();
            }while(!isNumber(numOfItem));
        }
        num[0] = Integer.parseInt(numOfItem);
        num[1] = Integer.parseInt(numOfCat);
        return num;
	}
	
	public static Node[] nodeListCreator(int numOfItem, int numOfCat, Scanner scan) {
		String ordinalIndicator, catName, itemName;
		int placeCat, placeItem, appearance = 0;
		boolean correctInput;
		ArrayList<Node> arrayListOfItems =  new ArrayList<Node>();
		
		for(int i = 0; i < numOfCat; i++){ 
            if(i == 0){
                ordinalIndicator = "st";
            }else if( i == 1){
                ordinalIndicator = "nd";
            }else if( i == 2){
                ordinalIndicator = "rd";
            }else{
                ordinalIndicator = "th";
            }
            placeCat = i+1;
            System.out.println("Please enter the name of the "+ placeCat + ordinalIndicator +" category");
            catName = scan.next();
            correctInput = false;
            do{ // this loop is used to make sure the category name the user inputs is not already used by another category.
            	appearance = 0;
            	for(int x = 0; x < arrayListOfItems.size(); x+= numOfCat ) {
	                if(catName.equalsIgnoreCase(arrayListOfItems.get(x).getType())) {
	                	appearance++;
	                }
            	}
            	if (appearance > 0) {
            		System.out.println("I'm sorry but it seems like the name " + catName + " is already in use by anothe category");
            		System.out.println("Please enter the name of the " + placeCat + ordinalIndicator + " item in this category");
                    catName = scan.next();
            	}else {
            		correctInput = true;
            	}
            }while(!correctInput);

            for(int j = 0; j < numOfItem; j++) {
            	if(j == 0){
                    ordinalIndicator = "st";
                }else if( j == 1){
                    ordinalIndicator = "nd";
                }else if( j == 2){
                    ordinalIndicator = "rd";
                }else{
                    ordinalIndicator = "th";
                }
                placeItem = j+1;
                System.out.println("Please enter the name of the " + placeItem + ordinalIndicator + " item in this category");
                itemName = scan.next();
                correctInput = false;
                do{ // this loop is used to make sure the itemName the user inputs is not already used by another Item.
                	appearance = 0;
                	for(int x = 0; x < arrayListOfItems.size(); x++ ) {
		                if(itemName.equalsIgnoreCase(arrayListOfItems.get(x).getName())) {
		                	appearance++;
		                }
                	}
                	if (appearance > 0) {
                		System.out.println("I'm sorry but it seems like the name " + itemName + " is already in use by anothe object");
                		System.out.println("Please enter the name of the " + placeItem + ordinalIndicator + " item in this category");
                        itemName = scan.next();
                	}else {
                		correctInput = true;
                	}
                }while(!correctInput);
                Node placeholder = new Node(itemName, catName);
                arrayListOfItems.add(placeholder);
            }
        }

        Node[] list =  new Node[arrayListOfItems.size()];
        list = arrayListOfItems.toArray(list);
        System.out.println(list.length);
        return list;
	}
	
	public static void clueLoop(Node[] list, WeightedGraph wg, Scanner scan) {
		Boolean firstInput, secondInput, askedOption, differentTypes = false, relationship = null, correctInput, noMoreClues = true;
		String firstItemName = null, secondItemName = null, userInput;
		Node placeholderA = null,placeholderB = null;
		int placeInProgram = 0; // Used to determine the point at which the user asked for help
		
		System.out.println("Great! Now that is done, time for us to input the clues you have!");
        System.out.print("We will have you input these by first typing the name of one item, then the other, and " +
                "finally, setting their relationship to either true or false.\n"
                + "(Type \"help\" for a help, \"reset\" to reset the graph, or \"done\" "
                + "if you have input all of your clues)\n");
        do {// complete check
            do {// types check
                do {//asked option check
                	do {//correct input check
                		askedOption = false;
                        firstInput = false;
                        correctInput = false;
		                System.out.print("Input the name of the first item in the relationship.\n");
		                firstItemName = scan.next();
		                
		                if(firstItemName.equalsIgnoreCase("help")) {
		                	HelpCheck(placeInProgram, wg, list);
		                	askedOption = true;
		                	correctInput = true;
		                }else if (firstItemName.equalsIgnoreCase("reset")) {
		                	wg = ResetCheck( wg, list);
		                	askedOption = true;
		                	correctInput = true;
		                	System.out.println("All graph connection values have been reset.\n");
		                	wg.printGraph();
		                }else if (firstItemName.equalsIgnoreCase("done")) {
		                	FinishedGraph(wg,noMoreClues);
		                	System.exit(0);
		                }else {
		                    for (int k = 0; k < list.length; k++) {
		                        if (list[k].getName().equalsIgnoreCase(firstItemName)) {
		                            placeholderA = list[k];
		                            firstInput = true;
		                        }
		                    }
			            	if (firstInput) {
			            		do {
			            			do {
				            			askedOption = false;
				                        secondInput = false;
						                System.out.print("Input the name of the second item in the relationship. \n");
						                secondItemName = scan.next();
						                if(secondItemName.equalsIgnoreCase("help")) {
						                	HelpCheck(placeInProgram, wg, list);
						                	askedOption = true;
						                	correctInput = true;
						                }else if (secondItemName.equalsIgnoreCase("reset")) {
						                	wg = ResetCheck(wg, list);
						                	askedOption = true;
						                	correctInput = true;
						                	System.out.println("All graph connection values have been reset.\n");
						                	wg.printGraph();
						                }else if (secondItemName.equalsIgnoreCase("done")) {
						                	FinishedGraph(wg,noMoreClues);
						                	System.exit(0);
						                }else {
						                    for (int k = 0; k < list.length; k++) {
						                        if (list[k].getName().equalsIgnoreCase(secondItemName)) {
						                            placeholderB = list[k];
						                            secondInput = true;
						                        }
						                    }
						                    if(secondInput) {
							                	correctInput = true;
						                    }else {
						                    	System.out.println("Sorry, that is not a valid input. Please try again.\n");
							                	correctInput = false;
						                    }
						                }
			            			}while(!correctInput);
			            		}while(askedOption);
			                }else {
			                	System.out.println("Sorry, that is not a valid input. Please try again.\n");
			                	correctInput = false;
			                }
		                }
                	}while(!correctInput);
                }while(askedOption);
	                
	                
                if(placeholderA.getType().equalsIgnoreCase(placeholderB.getType())){
                    System.out.println("I'm sorry but "+ placeholderA.getName() +" and "+ placeholderB.getName()+ " are of the "
                    		+ "same type. \nThis means that cannot hold a connection to one another. Let's try this again!");
                    differentTypes = false;
                }else{
                    differentTypes = true;
                }
                
                
                
            }while(!differentTypes);
            placeInProgram = 1;
            do {
            	do {
            		System.out.println("Are " + firstItemName + " and " + secondItemName + " related?");
            		correctInput = false;
            		askedOption = false;
	                userInput = scan.next();
	                // Checks to see if the user need help at place "1" of the loop
	                if(userInput.equalsIgnoreCase("help")) {
	                	HelpCheck(placeInProgram, wg, list);
	                	askedOption = true;
	                }else {
		                if (userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("no")
		                        || userInput.equalsIgnoreCase("false") || userInput.equalsIgnoreCase("f")) {
		                    relationship = false;
		                    correctInput = true;
		                } else if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")
		                        || userInput.equalsIgnoreCase("true") || userInput.equalsIgnoreCase("t")) {
		                    relationship = true;
		                    correctInput = true;
		                }else {
		                    correctInput = false;
		                    System.out.println("Sorry, " + userInput + " is not a valid input.");
		                }
	                }
            	}while(askedOption);
            } while (!correctInput);
            wg.addConnection(placeholderA, placeholderB, relationship, true);
            System.out.println("Connection has been made. Please view the changes below");
            wg.printGraph();
            System.out.println(wg.checkForCompletion());
            if(!wg.checkForCompletion()) {
                System.out.println("Time to add the next connection.");
                placeholderA = null;
                placeholderB = null;
            }
        }while(!wg.checkForCompletion());
        noMoreClues = false;
        FinishedGraph(wg,noMoreClues);
	}
	
	public static void FinishedGraph(WeightedGraph wg, boolean noMoreClues) {
		if(!noMoreClues){
			System.out.println("Looks like all of the connections have been found! You can find these below.");
	        wg.printCompleteGraph();
		}else{
			System.out.println("Looks like we were'nt able to find all of the connections. You can see all the possibilities below.");
	        wg.printIncompleteGraph();
		}
	}
	
	public static boolean HelpCheck(int placeInProgram, WeightedGraph wg, Node[] list) {
		if(placeInProgram == 0) {
			System.out.println("\n____HELP MENU____\n"
					+ "At this point you should input any of the names of the items you had listed.\n"
					+ "Make sure the two items you input are not in the same category."
					+ "You're options are listed below");
			for (int k = 0; k < list.length; k += wg.getItemNum() ) {
				for (int c = k; c < k + wg.getItemNum(); c++) {
                    System.out.print(list[c].getName() + "   ");
                }
                System.out.println();
            }
		}else if(placeInProgram == 1) {
			System.out.println("\n____HELP MENU____\n");
			System.out.println("At this point you should input either a y, yes, true, or t if these two items are connected"
					+ "\n or input a n, no, false, or f if they are not.\n You will be able to input \"done\" or \"reset\""
					+ "after this.");
		}
		return true;
	}
	
	public static WeightedGraph ResetCheck(WeightedGraph wg, Node[] list) {
		WeightedGraph resetwg = new WeightedGraph(list);
		return resetwg; // resets the graph back to original
	}
	
	public static boolean isNumber(String strNum) { // Used to check if the input from the user was a number
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
	
}
