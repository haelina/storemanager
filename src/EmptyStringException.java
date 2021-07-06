
/**
 * Custom exception for situations where user gives empty String.
 */
public class EmptyStringException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Detailed message for EmptyStringException
     */
    private String message;

    /**
     * Constructor for new EmptyStringException with custom message.
     * @param message  gives proper details of exception
     */
    public EmptyStringException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
