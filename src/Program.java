import java.util.Scanner;

/**
 * Class Program is the main class of shop software.
 *
 * This software is used for managing products and customers of a business.
 * Customers can view and modify their contact information, change their
 * password and see all the products available in store.
 *
 * @version 1.0
 * @author Hanna Sepanmaa
 */
public class Program {

    /**
     * Starting point of the shop software system.
     *
     * Here we initiate the Scanner that takes user input, create
     * UserCredentials object for storing userdata (both customer data and
     * employee data), create new storage object for managing information
     * of the store's stock situation. Finally a logic object takes these
     * as parameters and starts the software.
     *
     * @param args command line parameters. Not used.
     */
    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);
        UserCredentials users = new UserCredentials();
        Storage storage = new Storage();
        Logic app = new Logic(input, users, storage);

        app.start();
    }
}
