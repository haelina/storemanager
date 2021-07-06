import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class used for managing Products in the store.
 */
public class Storage {
    private List<Product> products;

    /**
     * Init new Storage object and create ArrayList for Product objects.
     */
    public Storage() {
        this.products = new ArrayList<Product>();
    }

    /**
     * Add new Product to storage.
     *
     * @param product   object that is added to storage
     * @return      new Product if it is added or null if product not added
     */
    public Product addProduct(Product product) {
        if(isUniqueProduct(product.getName())) {
            products.add(product);
            System.out.println("New product added: ");
            System.out.println("------------------");
            System.out.println(product);
            return product;
        }
        return null;
    }

    /**
     * Check that object with same name is not already in storage.
     *
     * @param name  check if this name exists in storage
     * @return      boolean value
     */
    public boolean isUniqueProduct(String name) {
        Iterator<Product> itr = this.products.iterator();
        while(itr.hasNext()) {
            if(itr.next().getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return amount of unique products in storage.
     *
     * @return  amount of products
     */
    public int getStorageSize() {
        return this.products.size();
    }

    /**
     * Print all the products in storage.
     */
    public void printAllProducts() {
        if(products.isEmpty()) {
            System.out.println("There are currently no products in storage.");
        } else {
            products.forEach(product -> System.out.println(product));
        }
    }
}
