import java.util.Scanner;

/**
 * Logic for handling customer and employee views.
 */
public class Logic {
    private Scanner scanner;
    private int userType = -1;
    private UserCredentials users;
    private Login login;
    private Storage storage;

    /**
     * Create new Logic object.
     *
     * @param scanner   used for getting user input
     * @param users     managing user information
     * @param storage   managing products in store
     */
    public Logic(Scanner scanner, UserCredentials users, Storage storage) {
        this.scanner = scanner;
        this.users = users;
        this.login = new Login(this.users, this.scanner);
        this.storage = storage;
    }

    /**
     * UserType tells which userType is logged in.
     *
     * @param value     -1 = not logged in, 0 = employee, 1 = customer
     */
    public void setUserType(int value) {
        this.userType = value;
    }

    /**
     * Get which userType is logged in.
     *
     * @return  current userType logged in
     */
    public int getUserType() {
        return userType;
    }

    /**
     * The app logic starts here.
     */
    public void start() {
        login.printSystemStart();
        String logOrCreate = "";
        do {
            logOrCreate = login.start();
            if(logOrCreate.equals("log")) {
                setUserType(login.userLogin());
                chooseView();
            } else if(logOrCreate.equals("create")) {
                login.createAccount();
                //setUserType(login.userLogin());
                //chooseView();
            } else if(logOrCreate.equals("quit")) {
                System.out.println("System is closing. Bye now!");
                break;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        } while( !logOrCreate.equals("log") || !logOrCreate.equals("create")
        || !logOrCreate.equals("quit"));
    }

    /**
     * Choose which view is shown depending on which userType is logged in.
     */
    public void chooseView() {
        switch(getUserType()) {
        case 0: System.out.println("Continue to employee view");
                EmployeeView empView =
                new EmployeeView(scanner, users, storage);
                empView.start();
                break;
        case 1: System.out.println("Continue to customer view");
                CustomerView custView =
                new CustomerView(scanner, users, storage, login.getLastLoggedEmail());
                custView.start();
                break;
        default: System.out.println("No user identified");
                break;
        }
    }

}
