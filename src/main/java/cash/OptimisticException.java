package cash;

/**
 * @author ArvikV
 * @version 1.0
 * @since 22.11.2021
 */
public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}
