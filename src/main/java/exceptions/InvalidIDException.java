package exceptions;

public class InvalidIDException extends Exception
{
    public InvalidIDException(String message) {
        super(message);
    }
    public InvalidIDException() {
        super("Invalid ID, the specified ID does not exist.");
    }
}
