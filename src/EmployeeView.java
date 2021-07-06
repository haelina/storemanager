import java.util.Scanner;

/**
 * Admin view for company employees.
 *
 * Here employee can:
 * 1 ) Search for a customer
 * 2 ) Modify customer contact info
 * 3 ) Add new products to store
 * 4 ) See list of all customers
 * 5 ) View all products in store
 */
public class EmployeeView extends ViewBase {
    private Scanner scanner;
    private UserCredentials users;
    private Storage storage;

    /**
     * Create EmployeeView using Scanner, UserCredentials and Storage.
     *
     * @param scanner   scanner used for getting user input
     * @param users     userCredential for managing customer data
     * @param storage   storage for managing products in store
     */
    public EmployeeView(Scanner scanner, UserCredentials users,
    Storage storage) {
        this.scanner = scanner;
        this.users = users;
        this.storage = storage;
    }

    /**
     * The starting point of admin screen.
     */
    public void start() {
        boolean isLoggedIn = true;
        int option = 0;
        System.out.println();
        System.out.println("---------------------------");
        System.out.println("| Welcome to admin panel! |");
        System.out.println("---------------------------");
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
                System.out.print("Input must be from range 1-6, ");
                System.out.println("choose again.");
                continue;
            }
            if(option != 6) {
                taskDelegator(option);
            } else {
                System.out.println("You have logged out. Bye!");
                isLoggedIn = false;
            }
        }
    }

    /**
     * Print all the options available for admin user.
     */
    public void printOptions() {
        System.out.println();
        System.out.println("What do you want to do?");
        System.out.println("-----------------------");
        System.out.println("1 ) Search for a customer");
        System.out.println("2 ) Modify customer info");
        System.out.println("3 ) Add new product");
        System.out.println("4 ) See list of all customers");
        System.out.println("5 ) Print all products in storage");
        System.out.println("6 ) Log out");
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
     * Validate if the option admin chose was valid.
     *
     * @param option    int 1-6 from employee
     * @return          boolean value indicating option was valid or not
     */
    public boolean validateOption(int option) {
        if(option > 0 && option < 7) {
            return true;
        }
        return false;
    }

    /**
     * Get next operation according to employee's choice.
     *
     * @param option int 1-6 from employee
     */
    public void taskDelegator(int option) {
        switch(option) {
            case 1: searchForCustomer();
                    break;
            case 2: modifyCustomerData();
                    break;
            case 3: addProduct();
                    break;
            case 4: this.users.printAllCustomers();
                    break;
            case 5: System.out.println("Products in stock are:");
                    System.out.println("----------------------");
                    this.storage.printAllProducts();
                    break;
            default: System.out.println("No task selected");
                    break;
        }
    }

    /**
     * Function for searching Customer.
     *
     * @return  Customer object or null if customer was not found.
     */
    public Customer searchForCustomer() {
        String searchBy = "";
        try {
            searchBy = getUserOption("Give a customer's name or email: ");
        } catch (EmptyStringException e) {
            System.out.println(e.getMessage());
            return null;
        }
        Customer customer = this.users.searchForCustomer(searchBy);
        if(customer != null) {
            System.out.println("Found a customer:");
            System.out.println("-----------------");
            System.out.println(customer);
            return customer;
        }
        System.out.println("Customer not found.");
        return null;
    }

    /**
     * Modify customer info.
     *
     * Needs to search for customer before modifying the customer data.
     */
    public void modifyCustomerData() {
        Customer customer = searchForCustomer();
        String arr[] =
        {"postal address", "phone number", "email address"};
        if(customer != null) {
            System.out.println("Found customer: " + customer);
            System.out.println("Modifying customer now");
            try {
                String address = customer.getAddress();
                String phone = customer.getPhone();
                String email = customer.getEmail();
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
                this.users.modifyCustomer(customer, address,
                phone, email);
            } catch (EmptyStringException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Couldn't find customer.");
        }
    }

    /**
     * Add new product to the store.
     */
    public void addProduct() {
        try {
            String name = getUserOption("Give product name: ");
            String description = getUserOption("Give product description: ");
            int amount = Integer.parseInt(getUserOption("Give amount: "));
            Product product = new Product(this.storage.getStorageSize() + 1,
            name, description, amount);
            storage.addProduct(product);
        } catch (NumberFormatException e) {
            System.out.println("Error with given product amount: " +
            e.getMessage());
        } catch (EmptyStringException e) {
            System.out.println(e.getMessage());
        }
    }
}
