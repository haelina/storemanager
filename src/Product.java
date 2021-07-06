
/**
 * Class Product represents a singe product sold in store.
 */
public class Product {
    private int id;
    private String name;
    private String description;
    private int amount;

    /**
     * Create a new Product with id, name, description and amount.
     *
     * @param id            unique id
     * @param name          unique name
     * @param description   short description of product
     * @param amount        amount of products in storage
     */
    public Product(int id, String name, String description, int amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    /**
     * Get the name of product.
     * @return  product's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return a String representing product data.
     */
    @Override
    public String toString() {
        return "id: " + this.id + ", name: " + this.name +
        " description: " + this.description + " amount: " + this.amount;
    }
}
