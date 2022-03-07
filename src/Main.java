import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main class for CreditCardCo project
 */
public class Main {

    /**
     * Main method.
     * @param args      The command line arguments.
     */
    public static void main(String[] args) {
        SearchTree<String> creditCards = new SearchTree<>();
        addData(creditCards,"CustData.csv");

        // creditCards.printInOrder();
        System.out.println(creditCards.generateInOrderString());
        // System.out.println("Total size: " + creditCards.getSize());
    }

    /**
     * Method for adding data from a CSV file to a StringTree.
     * @param tree      The StringTree data will be added to.
     * @param file      The CSV file to containing data.
     */
    public static void addData(SearchTree<String> tree, String file) {
        try {
            Scanner scan = new Scanner(new File(file));
            scan.nextLine();
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] data = line.split(",");
                tree.add(data[0]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, \"" + file + "\" could not be found.");
        }

    }

}
