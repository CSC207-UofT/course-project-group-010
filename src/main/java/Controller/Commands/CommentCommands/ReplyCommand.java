package Controller.Commands.CommentCommands;

import Controller.Commands.Command;
import Controller.Commands.CommandExecutor;
import Exceptions.ArgumentException;
import Interface.IReadModifiable;
import UseCase.CommentManager.CommentManager;
import UseCase.UserManager;

import java.util.List;

public class ReplyCommand extends Command {

    /**
     * Initializes the command with minimum/maximum arguments
     */
    public ReplyCommand() {
        super(100, 2);
    }

    @Override
    public String help() {
        return "reply [commendID] [text] : replies to comment with text.";
    }

    /**
     * Replies to a comment with text.
     *
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkHelpArgsUserPageAuth(ce, arguments, "reply");
        IReadModifiable currentlyViewingPage = ce.getPageManager();
        UserManager user = ce.getUserManager();
        String id = arguments.get(0);
        String userName = user.getUser().getdisplayName();
        String text = this.buildComment(arguments);
        ((CommentManager) currentlyViewingPage).replyToComment(id, text.toString(), userName);
        return userName + " replied to comment " + id + "with text [" + text.toString() + "]";
    }

    private String buildComment(List<String> arguments) throws ArgumentException {
        checkArgumentsNum(arguments);
        arguments = arguments.subList(1, arguments.size());
        StringBuilder text = new StringBuilder();
        for (String s : arguments) {
            text.append(s);
            text.append(" ");
        }
        return text.toString().trim();
    }
}
