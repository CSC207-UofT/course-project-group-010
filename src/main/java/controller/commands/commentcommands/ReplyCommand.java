package controller.commands.commentcommands;

import controller.commands.Command;
import controller.commands.CommandExecutor;
import controller.CommentPresenter;
import exceptions.ArgumentException;
import interfaces.IReadModifiable;
import usecase.UserManager;

import java.util.List;
import java.util.Scanner;

public class ReplyCommand extends Command {

    /**
     * Initializes the command with minimum/maximum arguments
     */
    public ReplyCommand() {
        super(1, 1);
    }

    @Override
    public String help() {
        return "Replies to a comment. Format: reply [commendID]. \nYou will be prompted for text.";
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
        Scanner in = new Scanner(System.in);
        IReadModifiable currentlyViewingPage = ce.getPageManager();
        UserManager user = ce.getUserManager();
        String id = arguments.get(0);
        String userName = user.getUser().getDisplayName();
        System.out.println("Type your comment:");
        String text = in.nextLine();
        if (text.equalsIgnoreCase("")) {
            throw new ArgumentException("Please write some text. Try again.");
        }
        ((CommentPresenter) currentlyViewingPage).replyToComment(id, text, userName);
        return userName + " replied to comment " + id + "with text [" + text.toString() + "]";
    }

    /**
     * Old buildComment method, deprecated.
     * @param arguments
     * @return
     * @throws ArgumentException
     */
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
