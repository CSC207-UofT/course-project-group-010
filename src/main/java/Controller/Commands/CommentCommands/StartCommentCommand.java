package controller.commands.commentcommands;

import controller.commands.Command;
import controller.commands.CommandExecutor;
import usecase.courseManager.CourseManager;

import java.util.List;
import java.util.Scanner;

/**
 * Starts a comment. This command is not currently in use, and will probably never be used.
 */
// TODO delete this sometime.
public class StartCommentCommand extends Command {
    /**
     * Initializes the command with minimum/maximum arguments
     */
    public StartCommentCommand() {
        super(0, 0);
    }

    @Override
    public String help() {
        return "startcomment to start comment section";
    }

    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkHelpArgsUserPageAuth(ce, arguments, "getcomments");

        // then pageManager will be instance of CourseManager
        CourseManager cm = ((CourseManager) ce.getPageManager());
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your comment text: ");
        String text = in.nextLine();
        // cm.startComment(text, ce.getUserManager().getUser());
        return "started a comment thread";
    }
}
