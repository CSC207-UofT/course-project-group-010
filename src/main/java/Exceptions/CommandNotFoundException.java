package Exceptions;

public class CommandNotFoundException extends Exception {
    public CommandNotFoundException(String message) {
        super(message);
    }

    public CommandNotFoundException() {
        super("Command not Found");
    }
}
