import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SearchTree<String> creditCards = new SearchTree<>();
    }

    public void addData(SearchTree<String> tree, String file) {
        try {
            Scanner scan = new Scanner(new File(file));
            scan.nextLine();
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] data = line.split(",");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, \"" + file + "\" could not be found.");
        }

    }

}
