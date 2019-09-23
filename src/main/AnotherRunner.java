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

        Scanner scan = new Scanner(System.in);
        String numOfCat;
        String numOfItems;
        String catOrItemNum;
        String catName;
        String itemName;
        int num1;
        int num2;

        ArrayList<Node> arrayListOfItems =  new ArrayList<Node>();

        System.out.println("Hello! Enter the number of categories.");
        numOfCat = scan.next();

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

            System.out.println("Great! Now enter the number of items within this category.");
            numOfItems = scan.next();

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
        wg.printGraph();

        //Now that's done, we need to find a way to input the clues and have them transfer to the weights of the graph.

        System.out.println("Great! Now that is done, time for us to input the clues you have!");
        System.out.println("");

    }
}
