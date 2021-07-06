import java.util.Map;
import java.util.HashMap;

/**
 * Class for storing user data and passwords.
 *
 * UserCredentials includes two separate hashmaps for storing data of
 * customers or employees and their passwords.
 */
public class UserCredentials {
    private Map<Customer, String> customers;
    private Map<Employee, String> employees;

    /**
     * Initiate the hashmaps and add initial data to them.
     */
    public UserCredentials() {
        this.customers = new HashMap<Customer, String>();
        this.employees = new HashMap<Employee, String>();
        addInitialData();
    }

    /**
     * Add some data to hashmaps customers and employees.
     */
    public void addInitialData() {
        this.customers.put(new Customer(1, "nina johnson",
        "upstreet 3 36100 Tampere","040855555", "hta@google.com"), "cat");
        this.customers.put(new Customer(2, "oswald ericsson",
        "flowerbun 1 16100 Salo", "040851243", "jaakko@google.com"), "dog");
        this.employees.put(new Employee(1, "john cleveland",
        "harm street 3 16000 Salo", "04058344914", "admin", 3500), "123");
    }

    /**
     * Get the customers hashmap.
     * @return      map containing all the customers
     */
    public Map<Customer,String> getCustomers() {
        return this.customers;
    }

    /**
     * Function for adding a new customer to customers hashmap.
     *
     * @param user      details of new customer
     * @param password  password for customer's user account
     */
    public void addCustomer(Customer user, String password) {
        this.customers.put(user, password);
        System.out.println("New user added:");
        System.out.println("--------------");
        System.out.println(user);
        System.out.println("password: " + password);
    }

    /**
     * Check if the customer email match with the password given.
     *
     * @param email     customer's email address
     * @param password  customer's password
     * @return          boolean value
     */
    public boolean isValidCustomer(String email, String password) {
        for (Customer customer : customers.keySet()) {
            if (customer.getEmail().equals(email) &&
            customers.get(customer).equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the employee email match with the password given.
     *
     * @param email     employee's email address
     * @param password  employee's password
     * @return          boolean value
     */
    public boolean isValidEmployee(String email, String password) {
        for (Employee employee : employees.keySet()) {
            if (employee.getEmail().equals(email) &&
            employees.get(employee).equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the current amount of customers stored in the system.
     *
     * @return  amount of customers (int value)
     */
    public int getCustomerAmount() {
        return this.customers.size();
    }

    /**
     * Check if the email given is stored in customers hashmap.
     *
     * @param email email address that needs to be checked
     * @return      boolean value
     */
    public boolean validCustomerEmail(String email) {
        for (Customer customer : customers.keySet()) {
            if (customer.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the email given is stored in employees hashmap
     *
     * @param email email address that needs to be checked
     * @return      boolean value
     */
    public boolean validEmployeeEmail(String email) {
        for (Employee employee : employees.keySet()) {
            if (employee.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Search for a customer by name or email.
     *
     * If searchBy parameter includes @-symbol it is an email address.
     * By default the search is done with the customer's name.
     * Returns null if the customer is not found from system.
     *
     * @param searchBy  search for this email or name from customers
     * @return          Customer that matched the search parameter
     */
    public Customer searchForCustomer(String searchBy) {
        if(searchBy.contains("@")) {
            return searchCustomerByEmail(searchBy);
        } else {
            return searchCustomerByName(searchBy);
        }
    }

    /**
     * Search for a customer by name from customers.
     *
     * @param name  search for this name
     * @return      Customer object found or null if not found
     */
    public Customer searchCustomerByName(String name) {
        for(Customer i: customers.keySet()) {
            if(i.getName().toLowerCase().equals(name.toLowerCase())) {
                return i;
            }
        }
        return null;
    }

    /**
     * Search for a customer by email address from customers.
     *
     * @param email search for this email
     * @return      Customer object found or null if not found
     */
    public Customer searchCustomerByEmail(String email) {
        for(Customer i: customers.keySet()) {
            if(i.getEmail().toLowerCase().equals(email.toLowerCase())) {
                return i;
            }
        }
        return null;
    }

    /**
     * Modify customer's contact information.
     * @param x         Customer object that is modified
     * @param address   new postal address
     * @param phone     new phone number
     * @param email     new email number
     */
    public void modifyCustomer(Customer x, String address, String phone,
    String email) {
        x.setAddress(address);
        x.setPhone(phone);
        x.setEmail(email);
    }

    /**
     * Change customer's password.
     *
     * @param x     Customer whose password is changed
     * @param pass  new password for customer
     */
    public void changePassWord(Customer x, String pass) {
        this.customers.replace(x, pass);
    }

    /**
     * Print all the customers and their passwords in the system.
     */
    public void printAllCustomers() {
        System.out.println("All customers:");
        System.out.println("--------------");
        for(Customer i: customers.keySet()) {
            System.out.println("Customer: " + i +
            "\npassword: " + customers.get(i));
        }
    }

    /**
     * Print all the employees and their passwords in the system.
     */
    public void printAllEmployees() {
        System.out.println("All employees:");
        for(Employee i: employees.keySet()) {
            System.out.println("Employee: " + i +
            "\npassword: " + employees.get(i));
        }
    }
}
