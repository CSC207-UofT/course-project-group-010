package Exceptions;

public class NotInDatabaseException extends Exception {
    public NotInDatabaseException(String message) {
        super(message);
    }

    public NotInDatabaseException() {
        super("Not in Database.");
    }
}
