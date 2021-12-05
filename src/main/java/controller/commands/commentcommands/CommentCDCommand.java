package controller.commands.commentcommands;

import controller.commands.Command;
import controller.commands.CommandExecutor;
import usecase.CommentPresenter;

import java.util.List;

/**
 * Command to navigate the comment section like a filesystem.
 * Works similar to the cd command on windows/linux
 */
public class CommentCDCommand extends Command {

    /**
     * Initializes the command with minimum/maximum arguments
     */
    public CommentCDCommand() {
        super(1, 1);
    }

    /**
     * Navigates the comment section, if the user is currently viewing one
     * eg. cd id1/id2/id3 will traverse to id1 > id2 > id3. cd .. will go backwards.
     *
     * @param ce        command executor
     * @param arguments user arguments
     * @return return string
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkAll(ce, arguments, "commentcd");

        // User is authorized to perform the command, then the currentlyViewingPage is a commentPresenter
        CommentPresenter cvp = (CommentPresenter) ce.getPageManager();

        // pass control to the CommentPresenter
        cvp.cdCommand(arguments.get(0));
        return "Now checking out " + cvp.getFullPath();
    }

    @Override
    public String help() {
        return "Navigates comments, like a directory. CD stands for Change DComment.\n" +
                "Usage: cd [path]. Path is a string of ids, separated by a /. You can use .. to go back.\n" +
                "eg. cd id1/id2, or cd ../..";
    }
}
