package Exceptions;

/**
 * ArgumentException: Occurs when an invalid number of arguments were given for a command.
 */
public class ArgumentException extends Exception {
    public ArgumentException(String message) {
        super(message);
    }

    public ArgumentException() {
        super("Invalid number of Arguments");
    }
}
