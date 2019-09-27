import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class AnotherRunner {

    public static void main(String[] args) {
        // Below are the objectives I wanted to achieve.
        // This code is a "beta version" of this final program where the user will be able to manually enter any number
        // of categories and items within categories. This will allow for more than 3 categories and 5 items within each.s
        //____________________________________________________________
        // System greeting + asks user to input number of categories
        // User enters number
        // System creates categories
        // LOOP for # of categories
        //      System asks user to name the category
        //      User enters Name; it is kept within a string variable categoryName
        //      System asks user to enter each item within the category
        //      List of Nodes created based on user inputs
        // PLEASE NOTE: I have not yet accounted for user error.(i.e. System: How many categories?| User: W| System: ERROR)

        Scanner scan = new Scanner(System.in);
        String numOfCat = "0";
        String numOfItems = "0";
        String catOrItemNum;
        String catName;
        String itemName;
        String name1, name2, userInput;
        Node a = null,b=null;
        Boolean relationship=null, correctInput, n1input = false, n2input = false, differentTypes;
        int num1;
        int num2;

        ArrayList<Node> arrayListOfItems =  new ArrayList<Node>();

        System.out.println("Hello! Enter the number of categories.");
        numOfCat = scan.next();

        if(!isNumber(numOfCat)){ // Used to check is the user has input a number or not.
            do{
                System.out.println("I'm sorry, but that doesn't seem to be a number. \n" +
                        "Please enter the NUMBER of categories.");
                numOfCat = scan.next();
            }while(!isNumber(numOfCat));
        }

        System.out.println("Great! Now enter the number of items within each category.");
        numOfItems = scan.next();

        if(!isNumber(numOfItems)){ // Used to check is the user has input a number or not.
            do{
                System.out.println("I'm sorry, but that doesn't seem to be a number. \n" +
                        "Please enter the NUMBER of items.");
                numOfItems = scan.next();
            }while(!isNumber(numOfItems));
        }

        for(int i = 0; i < Integer.parseInt(numOfCat); i++){
            //Sorry about this guys. Perfectionism tends to get the better of me.
            if(i == 0){
                //first
                catOrItemNum = "st";
            }else if( i == 1){
                //second
                catOrItemNum = "nd";
            }else if( i == 2){
                //third
                catOrItemNum = "rd";
            }else{
                //th from now on
                catOrItemNum = "th";
            }
            num1 = i+1;
            System.out.println("Please enter the name of the "+ num1 + catOrItemNum +" category");
            catName = scan.next();

            if(!isNumber(numOfItems)){ // Used to check is the user has input a number or not.
                do{
                    System.out.println("I'm sorry, but that doesn't seem to be a number. \n" +
                            "Please enter the NUMBER of items.");
                    numOfItems = scan.next();
                }while(!isNumber(numOfItems));
            }

            for(int j = 0; j < Integer.parseInt(numOfItems); j++) {
                if (j == 0) {
                    //first
                    catOrItemNum = "st";
                } else if (j == 1) {
                    //second
                    catOrItemNum = "nd";
                } else if (j == 2) {
                    //third
                    catOrItemNum = "rd";
                } else {
                    //th from now on
                    catOrItemNum = "th";
                }
                num2 = j+1;
                System.out.println("Please enter the name of the " + num2 + catOrItemNum + " item in this category");
                itemName = scan.next();
                Node placeholder = new Node(itemName, catName);
                arrayListOfItems.add(placeholder);
            }
        }

        Node[] list =  new Node[arrayListOfItems.size()];
        list = arrayListOfItems.toArray(list);
        WeightedGraph wg = new WeightedGraph(list);
        wg.setCatNum(Integer.parseInt(numOfCat));
        wg.setItemNum(Integer.parseInt(numOfItems));
        wg.printGraph();


        // Now that's done, we need to find a way to input the clues and have them transfer to the weights of the graph.
        // Option 1:
        //          User inputs the name of one item, then the name of another item and sets the connection to true of false
        // Option 2:
        //          User can input all three at once separated by commas

        // Below is implementation of option 1
        // Still need to implement a loop so that this keeps going until all clues are input or all connections are made.

        System.out.println("Great! Now that is done, time for us to input the clues you have!");
        System.out.print("We will have you input these by first typing the name of one item, then the other, and " +
                "finally, setting their relationship to either true or false.\n");

        //______This is where the main "Clue Input" loop will begin and take place.
        // Not writing now so that program can run.
        // Will update with updated code from WeightedGraph so that it loops until all connections are made.
        do {
            do {
                n1input=false;
                n2input = false;
                System.out.print("Please input the name of the first item in the relationship. \n");
                name1 = scan.next();
                while (!n1input) {
                    for (int k = 0; k < list.length; k++) {
                        if (list[k].getName().equalsIgnoreCase(name1)) {
                            a = list[k];
                            n1input = true;
                        }
                    }
                    if (!n1input) {
                        System.out.println("Sorry, that is not a valid input. Please try again. What is the FIRST item.");
                        name1 = scan.next();
                    }
                }
                System.out.print("Please input the name of the second item in the relationship. \n");
                name2 = scan.next();
                while (!n2input) {
                    for (int k = 0; k < list.length; k++) {
                        if (list[k].getName().equalsIgnoreCase(name2)) {
                            b = list[k];
                            n2input = true;
                        }
                    }
                    if (!n2input) {
                        System.out.println("Sorry, that is not a valid input. Please try again. What is the SECOND item");
                        name2 = scan.next();
                    }
                }
                if(a.getType().equalsIgnoreCase(b.getType())){
                    System.out.println("I'm sorry but "+ a.getName() +" and "+ b.getName()+ " are of the same type." +
                            "\nThis means that cannot hold a connection to one another. Let's try this again!");
                    differentTypes = false;
                }else{
                    differentTypes = true;
                }
            }while(!differentTypes);

            System.out.println("Are " + name1 + " and " + name2 + " related? (Please input \"yes\", \"y\", \"true\"," +
                    " or \"t\" for true. Please input \"no\", \"n\", \"false\", or \"f\" for false.)");
            do {
                userInput = scan.next();
                if (userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("no")
                        || userInput.equalsIgnoreCase("false") || userInput.equalsIgnoreCase("f")) {
                    relationship = false;
                    correctInput = true;
                } else if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")
                        || userInput.equalsIgnoreCase("true") || userInput.equalsIgnoreCase("t")) {
                    relationship = true;
                    correctInput = true;
                } else {
                    correctInput = false;
                    System.out.println("Sorry, " + userInput + " is not a valid input.");
                    System.out.println("Are " + name1 + " and " + name2 + " related? (Please input \"yes\", \"y\", " +
                            "\"true\", or \"t\" for true. Please input \"no\", \"n\", \"false\", or \"f\" for false.)");
                }
            } while (!correctInput);

            // Time to take those two nodes and set their relationship.

            wg.addConnection(a, b, relationship, true);
            System.out.println("Connection has been made. Please view the changes below");
            wg.printGraph();
            System.out.println(wg.checkForCompletion());
            if(!wg.checkForCompletion()) {
                System.out.println("Time to add the next connection.");
                a = null;
                b = null;
            }
            //______End of Loop
        }while(!wg.checkForCompletion());
        System.out.println("Looks like all of the connections have been found! You can find these below.");
        wg.printCompleteGraph();
    }

    public static boolean isNumber(String strNum) {
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}
