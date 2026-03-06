/**
 * Custom exception used for command and data validation errors.
 */
public class ChotuException extends RuntimeException {
    /**
     * Creates an exception without a message.
     */
    public ChotuException() {
        super();
    }

    /**
     * Creates an exception with the given message.
     *
     * @param message error message
     */
    public ChotuException(String message) {
        super(message);
    }
}
