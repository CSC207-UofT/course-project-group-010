package Controller.Commands.CommentCommands;

import Controller.AuthHelper;
import Controller.Commands.Command;
import Controller.Commands.CommandExecutor;
import Interface.IHasPermission;
import Interface.IReadModifiable;
import UseCase.CommentManager.CommentManager;

import java.util.List;

public class DisplaySubsetThreadCommand extends Command {
    /**
     * Initializes the command with minimum/maximum arguments
     *
     */
    public DisplaySubsetThreadCommand() {
        super(3, 2);
    }

    @Override
    public String help() {
        return "displaysubsetthread [startid] [depth] asc[optional]\n" +
                " gets a subset thread from [startid] of depth [depth], sorts by ascending upvotes if [asc]";
    }

    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkUserPageAuth(ce, arguments, "displaysubsetthread");
        IReadModifiable currentlyViewingPage = ce.getPageManager();
        String startid = arguments.get(0);
        int upToDepth = Integer.parseInt(arguments.get(1));
        boolean desc = arguments.size() <= 2 || !arguments.get(2).equalsIgnoreCase("asc");
        return ((CommentManager) currentlyViewingPage).displaySubsetThread(startid, desc, upToDepth);
    }
}