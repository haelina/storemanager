import java.util.Scanner;

/**
 * Class handling user login and account creation.
 */
public class Login {
    private UserCredentials users;
    private Scanner scanner;
    private String lastLoggedEmail;

    /**
     * Create the Login object.
     *
     * @param users     UserCredentials object used in login
     * @param scanner   Scanner obj used for user input
     */
    public Login(UserCredentials users, Scanner scanner) {
        this.users = users;
        this.scanner = scanner;
        lastLoggedEmail = "";
    }

    /**
     * Print starting message when system starts.
     */
    public void printSystemStart() {
        System.out.println("----------------------------------");
        System.out.println("|       System starting...       |");
        System.out.println("----------------------------------\n");
        System.out.println("Please log in or create an account first.");
        System.out.println("----------------------------------------");
    }

    /**
     * Start the login screen and choose login or create account.
     *
     * @return  "log", "create" or "null"
     */
    public String start() {
        System.out.println();
        System.out.println("What do you want to do?");
        System.out.println("--------");
        System.out.println("log = log in");
        System.out.println("create = create new customer account");
        System.out.println("quit = exit the system");
        System.out.println("--------");
        String logOrCreate = this.scanner.nextLine();
        if (logOrCreate.toLowerCase().equals("log")) {
            return "log";
        } else if (logOrCreate.toLowerCase().equals("create")) {
            return "create";
        } else if (logOrCreate.toLowerCase().equals("quit")) {
            return "quit";
        }else {
            return "null";
        }
    }

    /**
     * Login to system and recognize user type (customer or employee).
     *
     * Admin credentials for testing: ( email = admin, password = 123 ).
     * Customer credentials for testing ( email = hta@google.com,
     * password = kissa ).
     *
     * @return  -1 = no login, 0 = employee, 1 = customer
     */
    public int userLogin() {
        int userType = -1;
        while(true) {
            System.out.println("----------------");
            System.out.println("|    Log in:   |");
            System.out.println("----------------");
            System.out.println("Admin demo: email = admin, password = 123");
            System.out.print("Customer demo: email = hta@google.com, ");
            System.out.println("password = cat");
            System.out.print("Type your email here: ");
            String email = scanner.nextLine();
            if (!users.validCustomerEmail(email) &&
            !users.validEmployeeEmail(email)) {
                System.out.println("Email is not valid, try again.");
                continue;
            }
            System.out.print("Type your password here: ");
            String pass = scanner.nextLine();
            if(users.isValidCustomer(email, pass)) {
                System.out.println("Login successfull.");
                setLastLoggedEmail(email);
                userType = 1;
                break;
            } else if (users.isValidEmployee(email, pass)) {
                System.out.println("Login successfull.");
                userType = 0;
                break;
            }
        }
        return userType;
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
     * Create new customer account.
     */
    public void createAccount() {
        try {
            String email = getUserOption("Type your email here: ");
            if(this.users.validCustomerEmail(email)) {
                System.out.println("This email already has an account.");
            } else {
                String name = getUserOption("Type your name here: ");
                String address = getUserOption("Type your address here: ");
                String phone = getUserOption("Type your phonenumber here: ");
                String pass = getUserOption("Type your password here: ");
                users.addCustomer(new Customer(this.users.getCustomerAmount() + 1,
                name, address, phone, email), pass);
                System.out.println();
                System.out.println("New account created successfully");
            }
        } catch (EmptyStringException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Return the email address of latest customer or employee logged in.
     * @return  email address of latest logged customer
     */
    public String getLastLoggedEmail() {
        return this.lastLoggedEmail;
    }

    /**
     * Setting email address of latest logged in customer or employee.
     *
     * @param email email address of customer or employee
     */
    public void setLastLoggedEmail(String email) {
        this.lastLoggedEmail = email;
    }
}
