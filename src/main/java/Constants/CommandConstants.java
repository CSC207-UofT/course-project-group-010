package Constants;

import Controller.Commands.*;
import Controller.Commands.CommandExceptions.CommandNotFoundException;

import java.util.Hashtable;

/**
 * Stores strings that will call different command objects.
 */
public class CommandConstants {
    public final Hashtable<String, Command> command_dict =
            new Hashtable<String, Command>();

    public CommandConstants () {
        command_dict.put("help", new HelpCommand());
        command_dict.put("login", new LoginCommand());
        command_dict.put("print", new PrintCommand());
        command_dict.put("checkout", new CheckoutCommand());
        command_dict.put("rate", new RateCommand());
    }

    public Command get(String key) throws CommandNotFoundException {
        if(!command_dict.containsKey(key)) {
            throw new CommandNotFoundException();
        }
        return command_dict.get(key);
    }
}
