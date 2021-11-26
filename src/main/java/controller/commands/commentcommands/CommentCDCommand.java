package controller.commands.commentcommands;

import controller.commands.Command;
import controller.commands.CommandExecutor;
import controller.CommentPresenter;

import java.util.List;

public class CommentCDCommand extends Command {
    /**
     * Initializes the command with minimum/maximum arguments
     *
     */
    public CommentCDCommand() {
        super(1, 1);
    }

    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkHelpArgsUserPageAuth(ce, arguments, "commentcd");
        // then the currentlyViewingPage is a commentPresenter

        CommentPresenter cvp = (CommentPresenter) ce.getPageManager();
        cvp.cdCommand(arguments.get(0));
        return "Now checking out " + cvp.getFullPath();
    }

    @Override
    public String help() {
        String helpStr = "Navigates comments, like a directory. CD stands for Change DComment.\n" +
                "Usage: cd [path]. Path is a string of ids, separated by a /. You can use .. to go back.\n" +
                "eg. cd id1/id2, or cd ../..";
        return helpStr;
    }
}
