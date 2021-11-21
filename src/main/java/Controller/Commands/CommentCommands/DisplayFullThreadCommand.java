package Controller.Commands.CommentCommands;

import Controller.Commands.Command;
import Controller.Commands.CommandExecutor;
import Interface.IReadModifiable;
import UseCase.CommentManager.CommentManager;

import java.util.List;

/**
 * Displays the full comment thread. This is deprecated, you can use checkout -c instead.
 */
public class DisplayFullThreadCommand extends Command {

    /**
     * Initializes the command with minimum/maximum arguments
     */
    public DisplayFullThreadCommand() {
        super(0, 0);
    }

    @Override
    public String help() {
        return "displayfullthread: display full comment thread\n This is deprecated, try using checkout -c instead.";
    }

    /**
     * Displays full comment thread if the user is viewing a comment.
     *
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkHelpArgsUserPageAuth(ce, arguments, "displayfullthread");
        IReadModifiable currentlyViewingPage = ce.getPageManager();
        // If we're authorized to displayfullthread, the thing should be of type CommentManager.
        return ((CommentManager) currentlyViewingPage).displayEntireThread(true, -1);
    }
}
