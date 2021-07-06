import java.util.Scanner;

/**
 * Customer view for customers that are logged in.
 *
 * Here customer can:
 * 1 ) See customer data
 * 2 ) Modify customer contact information
 * 3 ) Change password
 * 4 ) See list of products
 */
public class CustomerView extends ViewBase {
    private Scanner scanner;
    private UserCredentials users;
    private Storage storage;
    private Customer loggedCustomer;

    /**
     * Create new CustomerView object and store info of customer that
     * is currently logged in.
     *
     * @param scanner   used for getting user input
     * @param users     used for managing customer data
     * @param storage   used for managing products
     * @param email     email used for identifying logged customer
     */
    public CustomerView(Scanner scanner, UserCredentials users,
    Storage storage, String email) {
        this.scanner = scanner;
        this.users = users;
        this.storage = storage;
        this.loggedCustomer = users.searchCustomerByEmail(email);
    }

    /**
     * Starting point of customer view after logging in.
     */
    public void start() {
        boolean isLoggedIn = true;
        int option = 0;
        System.out.println();
        System.out.println("------------------------------");
        System.out.println("| Welcome to customer panel! |");
        System.out.println("------------------------------");
        while(isLoggedIn) {
            option = 0;
            printOptions();
            try {
                String userInput = getUserOption("Choose option: ");
                option = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Given option was not a number: " +
                e.getMessage());
            } catch (EmptyStringException e) {
                System.out.println(e.getMessage());
            }
            if(!validateOption(option)) {
                System.out.print("Input must be from range 1-5, ");
                System.out.println("choose again.");
                continue;
            }
            if(option != 5) {
                taskDelegator(option);
            } else {
                System.out.println("You have logged out. Bye!");
                isLoggedIn = false;
            }
        }
    }

    /**
     * Validate if the option customer chose was valid.
     *
     * @param option    int 1-5 from customer
     * @return          boolean value indicating option was valid or not
     */
    public boolean validateOption(int option) {
        if(option > 0 && option < 6) {
            return true;
        }
        return false;
    }

    /**
     * Get input from user.
     *
     * @param guide guidance String regarding what kind of input is required
     * @return      input from user
     * @throws EmptyStringException in case the input is empty or null
     */
    public String getUserOption(String guide)
        throws EmptyStringException {
        System.out.print(guide);
        String input = scanner.nextLine();
        if(input.length() == 0 || input == null) {
            throw new EmptyStringException("Error: Value was not given.");
        }
        return input;
    }

    /**
     * Proceed to next operation according to customer's choice.
     *
     * @param option int 1-4 from customer
     */
    public void taskDelegator(int option) {
        switch(option) {
            case 1: System.out.println("Your customer details are:");
                    System.out.println("--------------------------");
                    printCustomerDetails();
                    break;
            case 2: System.out.println("Modifying customer data now:");
                    System.out.println("----------------------------");
                    modifyCustomerContactInfo();
                    break;
            case 3: System.out.println("Products currently in stock:");
                    System.out.println("----------------------------");
                    this.storage.printAllProducts();
                    break;
            case 4: System.out.println("Changing password now:");
                    System.out.println("----------------------");
                    changePassword();
                    break;
            default: System.out.println("No task selected");
                    break;
        }
    }

    /**
     * Function for changing customer's password.
     */
    public void changePassword() {
        try {
            String pass = getUserOption("Give new password: ");
            this.users.changePassWord(this.loggedCustomer, pass);
        } catch (EmptyStringException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Print customer data of currently logged in customer.
     */
    public void printCustomerDetails() {
        String email = this.loggedCustomer.getEmail();
        System.out.println(this.users.searchCustomerByEmail(email));
    }

    /**
     * Function for modification of customer's contact information.
     */
    public void modifyCustomerContactInfo() {
        String arr[] =
        {"postal address", "phone number", "email address"};
        try {
            String address = this.loggedCustomer.getAddress();
            String phone = this.loggedCustomer.getPhone();
            String email = this.loggedCustomer.getEmail();
            for (int i = 0; i < arr.length; i++) {
                System.out.print("Do you want to modify your ");
                System.out.println("" + arr[i] + "?");
                System.out.print("y / n : ");
                String opt = scanner.nextLine();
                if(opt.toLowerCase().equals("y")) {
                    if(i == 0) {
                        address = getUserOption("Give new " + arr[i] + ": ");
                    } else if (i == 1) {
                        phone = getUserOption("Give new " + arr[i] + ": ");
                    } else if (i == 2) {
                        email = getUserOption("Give new " + arr[i] + ": ");
                    }
                }
            }
            this.users.modifyCustomer(this.loggedCustomer, address,
            phone, email);
        } catch (EmptyStringException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Printing list of options that are available for the customer.
     */
    public void printOptions() {
        System.out.println();
        System.out.println("What do you want to do?");
        System.out.println("-----------------------");
        System.out.println("1 ) Check your customer data");
        System.out.println("2 ) Modify your contact information");
        System.out.println("3 ) See list of products");
        System.out.println("4 ) Change password");
        System.out.println("5 ) Log out");
    }
}
