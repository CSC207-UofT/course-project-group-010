package Constants;

import Controller.Commands.*;
import Exceptions.CommandNotFoundException;

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
        command_dict.put("newuser", new NewUserCommand());
        command_dict.put("saveall", new SaveAllCommand());
        command_dict.put("logout", new LogoutCommand());
    }

    public Command get(String key) throws CommandNotFoundException {
        if(!command_dict.containsKey(key)) {
            throw new CommandNotFoundException();
        }
        return command_dict.get(key);
    }
}
