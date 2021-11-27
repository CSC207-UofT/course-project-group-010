package controller.commands;

import controller.AuthHelper;
import exceptions.ArgumentException;
import exceptions.CommandHelpException;
import exceptions.CommandNotAuthorizedException;
import interfaces.IReadModifiable;
import usecase.UserManager;

import java.util.List;

/**
 * A command that is passed from the CLI to the commandExecutor
 * This is the base class for all commands in the program
 * <p>
 * The idea for this class was adapted from JShell, at
 * https://github.com/CSC207-UofT/Java-Shell/blob/master/src/main/java/commands/Command.java
 */
public abstract class Command {
    protected final int maxArguments;
    protected final int minArguments;

    /**
     * Initializes the command with minimum/maximum arguments
     *
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
     *
     * @return
     */
    public String help() {
        return "Help for this command is not available at this time";
    }

    protected void checkHelp(List<String> arguments) throws CommandHelpException {
        if (arguments.size() > 0 && arguments.get(0).equalsIgnoreCase("-h")) {
            throw new CommandHelpException(this.help());
        }
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

    protected void checkViewingPageExists(CommandExecutor ce) throws ArgumentException {
        if (ce.getPageManager() == null) {
            throw new ArgumentException("Not viewing any pages.");
        }
    }

    protected void checkHelpArgsUserPageAuth(CommandExecutor ce, List<String> arguments, String method) throws Exception {
        checkHelp(arguments);
        checkArgumentsNum(arguments);
        checkUserExists(ce);
        checkViewingPageExists(ce);
        IReadModifiable currentlyViewingPage = ce.getPageManager();
        UserManager user = ce.getUserManager();
        new AuthHelper().checkAuth(currentlyViewingPage, user, method);
    }
}
