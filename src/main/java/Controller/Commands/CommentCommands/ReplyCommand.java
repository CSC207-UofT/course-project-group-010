package Controller.Commands.CommentCommands;

import Controller.AuthHelper;
import Controller.Commands.Command;
import Controller.Commands.CommandExecutor;
import Interface.IHasPermission;
import Interface.IReadModifiable;
import UseCase.CommentManager.CommentManager;
import UseCase.UserManager;

import java.util.List;

public class ReplyCommand extends Command {

    /**
     * Initializes the command with minimum/maximum arguments
     *
     */
    public ReplyCommand() {
        super(100, 2);
    }

    @Override
    public String help() {
        return "reply [commendID] [text] : replies to comment with text.";
    }

    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkUserPageAuth(ce, arguments, "reply");
        IReadModifiable currentlyViewingPage = ce.getPageManager();
        UserManager user = ce.getUserManager();
        String id = arguments.get(0);
        String userName = user.getUser().getdisplayName();
        StringBuilder text = new StringBuilder();
        for (int i = 1; i < arguments.size(); i++) {
            text.append(arguments.get(i) + " ");
        }
        ((CommentManager) currentlyViewingPage).replyToComment(id, text.toString(), userName);
        return userName + " replied to comment " + id + "with text [" + text.toString() + "]";
    }
}
