package controller.commands.commentcommands;

import controller.commands.Command;
import controller.commands.CommandExecutor;
import interfaces.IReadModifiable;
import usecase.CommentPresenter;

import java.util.List;

public class VoteCommand extends Command {

    /**
     * Initializes the command with minimum/maximum arguments
     */
    public VoteCommand() {
        super(2, 1);
    }

    /**
     * Upvotes/downvotes a comment.
     *
     * @param ce command executor
     * @param arguments user arguments(comment id and up/down)
     * @return return string
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkAll(ce, arguments, "vote");
        IReadModifiable currentlyViewingPage = ce.getPageManager();
        boolean up = arguments.size() == 2 ? arguments.get(1).equalsIgnoreCase("up") : arguments.get(0).equalsIgnoreCase("up");
        CommentPresenter cp = (CommentPresenter) currentlyViewingPage;
        if (arguments.size() == 1) {
            cp.vote(up);
        } else {
            cp.vote(arguments.get(0), up);
        }
        return "voted on comment with up=" + up;
    }

    @Override
    public String help() {
        return "vote [commentID] [up/down] : up/downvotes comment with id";
    }
}
