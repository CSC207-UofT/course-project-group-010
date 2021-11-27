package controller.commands.commentcommands;

import controller.commands.Command;
import controller.commands.CommandExecutor;
import controller.CommentPresenter;
import interfaces.IReadModifiable;

import java.util.List;

public class VoteCommand extends Command {

    /**
     * Initializes the command with minimum/maximum arguments
     */
    public VoteCommand() {
        super(2, 2);
    }

    @Override
    public String help() {
        return "vote [commentID] [up/down] : up/downvotes comment with id";
    }

    /**
     * Upvotes/downvotes a comment.
     *
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkAll(ce, arguments, "vote");
        IReadModifiable currentlyViewingPage = ce.getPageManager();
        boolean up = arguments.get(1).equalsIgnoreCase("up");
        ((CommentPresenter) currentlyViewingPage).vote(arguments.get(0), up);
        return "voted on comment " + arguments.get(0) + "with up=" + up;
    }
}
