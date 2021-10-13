package Constants;

import Controller.Commands.Command;
import Controller.Commands.LoginCommand;

import java.util.Hashtable;

/**
 * Stores strings that will call different command objects.
 */
public class CommandConstants {
    public final Hashtable<String, Command> command_dict =
            new Hashtable<String, Command>();

    public CommandConstants () {
        command_dict.put("login", new LoginCommand());
    }

    public Command get(String key) {
        return command_dict.get(key);
    }
}
