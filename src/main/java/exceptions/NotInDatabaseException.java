package exceptions;

public class NotInDatabaseException extends Exception {
    public NotInDatabaseException(String message) {
        super(message);
    }
}
