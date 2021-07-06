
/**
 * Class representing a customer.
 */
public class Customer extends Person {
    private int id;

    /**
     * Create new Customer object.
     *
     * @param id        customer id
     * @param name      customer first and last name
     * @param address   address of customer
     * @param phone     phone number
     * @param email     email address
     */
    public Customer(int id, String name, String address, String phone,
    String email) {
        super(name, address, phone, email);
        this.id = id;
    }

    /**
     * Get customer's id.
     * @return customer id number
     */
    public int getId() {
        return this.id;
    }

    /**
     * Return a String representing the customer's data.
     */
    @Override
    public String toString() {
        return "Id: " + this.getId() + "\n" + super.toString();
    }
}
