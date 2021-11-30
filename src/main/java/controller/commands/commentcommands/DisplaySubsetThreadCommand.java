package controller.commands.commentcommands;

import controller.commands.Command;
import controller.commands.CommandExecutor;
import controller.CommentPresenter;
import interfaces.IReadModifiable;

import java.util.List;

public class DisplaySubsetThreadCommand extends Command {
    /**
     * Initializes the command with minimum/maximum arguments
     */
    public DisplaySubsetThreadCommand() {
        super(3, 2);
    }

    @Override
    public String help() {
        return "displaysubsetthread [startid] [depth] asc[optional]\n" +
                " gets a subset thread from [startid] of depth [depth], sorts by ascending upvotes if [asc]";
    }

    /**
     * Displays a subset of a thread, considering a starting id and a depth.
     * Can sort by ascending or descending upvotes.
     * THIS IS QUITE DEPRECATED
     *
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkAll(ce, arguments, "displaysubsetthread");
        IReadModifiable currentlyViewingPage = ce.getPageManager();
        String startid = arguments.get(0);
        int upToDepth = Integer.parseInt(arguments.get(1));
        boolean desc = arguments.size() <= 2 || !arguments.get(2).equalsIgnoreCase("asc");
        return ((CommentPresenter) currentlyViewingPage).getCommentManager().displaySubsetThread(startid, desc, upToDepth);
    }
}
