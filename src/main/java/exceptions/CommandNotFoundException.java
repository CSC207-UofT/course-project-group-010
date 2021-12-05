package exceptions;

public class CommandNotFoundException extends Exception {
    public CommandNotFoundException() {
        super("Command not Found");
    }
}
