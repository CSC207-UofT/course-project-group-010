package Controller.Commands.CommentCommands;

import Controller.Commands.Command;
import Controller.Commands.CommandExecutor;
import Exceptions.ArgumentException;
import Interface.IReadModifiable;
import UseCase.CommentManager.CommentManager;

import java.util.List;

public class GetPathCommand extends Command {
    /**
     * Initializes the command with minimum/maximum arguments
     */
    public GetPathCommand() {
        super(2, 2);
    }

    @Override
    public String help() {
        return "getpath [startid] [endid]: displays path of comments from startid to endid";
    }

    /**
     * Gets and prints the path from one comment to another. Only prints comments that must be traversed
     * to reach the other comment.
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkHelpArgsUserPageAuth(ce, arguments, "getpath");
        IReadModifiable currentlyViewingPage = ce.getPageManager();
        try {
            return ((CommentManager) currentlyViewingPage).getPath(arguments.get(0), arguments.get(1));
        } catch (Exception e) {
            throw new ArgumentException("Please input valid ids");
        }

    }
}
