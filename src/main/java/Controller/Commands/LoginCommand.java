package Controller.Commands;

import java.util.List;

/**
 * Login command.
 * Current format is login[utorID]
 */
public class LoginCommand extends Command{
    /**
     * The format for a login command is login [utorID] for now
     * so it will only take 1 argument
     */
    public LoginCommand() {
        super(1, 1);
    }

    @Override
    public String help() {
        String s = "Format: login [utorid]";
        return s;
    }

    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkArgumentsNum(arguments);
        String id = arguments.get(0);
        // TODO check if ce already has a user, throw otherwise
        // TODO check utorid in database, get data and initialize the usermanager if needed
        return "Couldn't login";
    }
}
