
/**
 * Base class Person which includes basic data of a person.
 */
public class Person {
    private String name;
    private String address;
    private String phone;
    private String email;

    /**
     * Create new Person object.
     *
     * @param name      person's first and last name
     * @param address   postal address
     * @param phone     phone number
     * @param email     email address
     */
    public Person(String name, String address, String phone, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Get Person's name.
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get Person's address.
     * @return address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Get Person's phone number
     * @return phone number
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Setting new address for person.
     * @param address new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Setting new phone number for person.
     * @param phone new phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Setting new email address for person.
     * @param email new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get Person's email address.
     * @return email address
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Return a String representing the Person's data.
     */
    @Override
    public String toString() {
        return "Name: " + this.getName() + "\nAddress: " + this.getAddress() +
        "\nPhone: " + this.getPhone() + "\nEmail: " + this.getEmail();
    }

}
