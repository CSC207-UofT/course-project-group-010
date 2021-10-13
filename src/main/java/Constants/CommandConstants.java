package Constants;

import Controller.Commands.Command;
import Controller.Commands.CommandExceptions.CommandNotFoundException;
import Controller.Commands.LoginCommand;
import Controller.Commands.PrintCommand;

import java.util.Hashtable;

/**
 * Stores strings that will call different command objects.
 */
public class CommandConstants {
    public final Hashtable<String, Command> command_dict =
            new Hashtable<String, Command>();

    public CommandConstants () {
        command_dict.put("login", new LoginCommand());
        command_dict.put("print", new PrintCommand());
    }

    public Command get(String key) throws CommandNotFoundException {
        if(!command_dict.containsKey(key)) {
            throw new CommandNotFoundException();
        }
        return command_dict.get(key);
    }
}
