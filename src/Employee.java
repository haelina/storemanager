
/**
 * Class representing an employee.
 */
public class Employee extends Person {
    private int salary;
    private int id;

    /**
     * Create new employee.
     *
     * @param id        unique id
     * @param name      name of an employee
     * @param address   employee's address
     * @param phone     employee's phone number
     * @param email     email address
     * @param salary    salary
     */
    public Employee(int id, String name, String address, String phone, String email, int salary) {
        super(name, address, phone, email);
        this.salary = salary;
        this.id = id;
    }

    /**
     * Get id of an employee.
     * @return id of employee (int value)
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get salary.
     * @return  salary amount
     */
    public int getSalary() {
        return this.salary;
    }

    /**
     * Return a String representing the employee's data.
     */
    @Override
    public String toString() {
        return "Id: " + this.getId() + "\n" + super.toString() + "\nSalary: " + this.getSalary();
    }

}
