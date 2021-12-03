package controller.commands.commentcommands;

import controller.commands.Command;
import controller.commands.CommandExecutor;
import exceptions.ArgumentException;
import interfaces.IReadModifiable;
import usecase.CommentPresenter;

import java.util.List;

// TODO kind of deprecated, too technical and not really useful
public class GetPathCommand extends Command {
    /**
     * Initializes the command with minimum/maximum arguments
     */
    public GetPathCommand() {
        super(2, 2);
    }

    @Override
    public String help() {
        return "getpath [startid] [endid]: displays path of comments from startid to endid\n It's basically a filtering command.";
    }

    /**
     * Gets and prints the path from one comment to another. Only prints comments that must be traversed
     * to reach the other comment.
     *
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkAll(ce, arguments, "getpath");
        IReadModifiable currentlyViewingPage = ce.getPageManager();
        try {
            return ((CommentPresenter) currentlyViewingPage).getCommentManager().getPath(arguments.get(0), arguments.get(1));
        } catch (Exception e) {
            throw new ArgumentException("Please input valid ids");
        }

    }
}
