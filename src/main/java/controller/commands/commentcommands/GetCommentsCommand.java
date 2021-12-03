package controller.commands.commentcommands;

import controller.commands.Command;
import controller.commands.CommandExecutor;
import controller.commands.commandHelpers.CommentsPageGetter;

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
        return "Gets the comment section. This is deprecated, try using checkout -c instead.";
    }

    /**
     * Gets the comment section of the page that the user is currently viewing, if it is a course page.
     * This is integrated into checkout -c as well, currently.
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkAll(ce, arguments, "getcomments");

        // then pageManager will be instance of CourseManager
        CommentsPageGetter cg = new CommentsPageGetter();

        // all necessary checks should have been run by checkPageAuth
        cg.getPage(ce);
        return "viewing comments";
    }
}
