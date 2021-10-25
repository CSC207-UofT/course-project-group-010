package Controller.Commands;

import Exceptions.ArgumentException;
import Exceptions.CommandNotAuthorizedException;

import java.util.List;

/**
 * A command that is passed from the CLI to the commandExecutor
 * This is the base class for all commands in the program
 *
 * The idea for this class was adapted from JShell, at
 * https://github.com/CSC207-UofT/Java-Shell/blob/master/src/main/java/commands/Command.java
 */
public abstract class Command {
    protected final int maxArguments;
    protected final int minArguments;

    /**
     * Initializes the command with minimum/maximum arguments
     * @param maxArguments
     * @param minArguments
     */
    public Command(int maxArguments, int minArguments) {
        this.maxArguments = maxArguments;
        this.minArguments = minArguments;
    }

    /**
     * Executes the command, can throw many different exceptions
     *
     * @param arguments
     * @return the string output that the user should see.
     * @throws Exception
     */
    abstract public String run(CommandExecutor ce, List<String> arguments) throws Exception;

    /**
     * returns the help string for a command. May not be implemented yet.
     * @return
     */
    public String help() {
        return "Help for this command is not available at this time";
    }

    /**
     * Checks that the number of arguments is correct for this command, and throws and
     * exception otherwise.
     */
    protected void checkArgumentsNum(List<String> arguments) throws ArgumentException {
        if (arguments.size() > maxArguments || arguments.size() < minArguments) {
            throw new ArgumentException();
        }
    }

    protected void checkUserExists(CommandExecutor ce) throws CommandNotAuthorizedException {
        if (ce.getUserManager() == null) {
            throw new CommandNotAuthorizedException("Not logged in.");
        }
    }
}
