package Controller.Commands.CommentCommands;

import Controller.Commands.Command;
import Controller.Commands.CommandExecutor;
import UseCase.CourseManager.CourseManager;

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
        cm.startComment(text, ce.getUserManager().getUser());
        return "started a comment thread";
    }
}
