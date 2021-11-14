package Controller.Commands.CommentCommands;

import Controller.Commands.Command;
import Controller.Commands.CommandExecutor;
import UseCase.CourseManager.CourseManager;

import java.util.List;

public class GetCommentsCommand extends Command {
    /**
     * Initializes the command with minimum/maximum arguments
     */
    public GetCommentsCommand() {
        super(0, 0);
    }

    @Override
    public String help() {
        return "format: getcomments";
    }

    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkHelpArgsUserPageAuth(ce, arguments, "getcomments");

        // then pageManager will be instance of CourseManager
        ce.setPageManager(((CourseManager) ce.getPageManager()).getOnlyComment());
        return "viewing comments";
    }
    // TODO implement this once coursePage is updated.
}
