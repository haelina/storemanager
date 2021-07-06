
/**
 * Class GiftCertificate for creating gift certificates.
 */
public class GiftCertificate extends Product implements Printable {
    private double value;
    private String code;

    /**
     * Create GiftCertificate object with random code.
     *
     * @param id            product id
     * @param name          product name
     * @param description   product description
     * @param amount        amount of certificates in stock
     * @param value         value of gift certificate
     */
    public GiftCertificate(int id, String name, String description,
    int amount, double value) {
        super(id, name, description, amount);
        this.value = value;
        this.code = generateCode(8);
    }

    /**
     * Generate random String with given amount of numbers
     * that are converted to String.
     *
     * @param amount    code has this many numbers
     * @return          String consisting of numbers
     */
    public String generateCode(int amount) {
        String nums = "";
        for(int i = 0; i < amount; i++) {
            int generated = (int) ((Math.random() * 9) + 1);
            nums += String.valueOf(generated);
        }
        return nums;
    }

    /**
     * Print the data of this gift voucher.
     */
    public void print() {
        System.out.println("Gift voucher data:");
        System.out.println("Value: " + this.value);
        System.out.println("Code: " + this.code);
    }
}
