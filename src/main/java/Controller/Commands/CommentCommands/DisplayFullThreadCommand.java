package Controller.Commands.CommentCommands;

import Controller.Commands.Command;
import Controller.Commands.CommandExecutor;
import Interface.IReadModifiable;
import UseCase.CommentManager.CommentManager;

import java.util.List;

public class DisplayFullThreadCommand extends Command {

    /**
     * Initializes the command with minimum/maximum arguments
     */
    public DisplayFullThreadCommand() {
        super(0, 0);
    }

    @Override
    public String help() {
        return "displayfullthread: display full comment thread";
    }

    /**
     * Displays full comment thread, assuming that the user is viewing a comment.
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
