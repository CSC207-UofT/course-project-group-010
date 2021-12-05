package controller.commands.commentcommands;

import controller.commands.Command;
import controller.commands.CommandExecutor;
import exceptions.ArgumentException;
import interfaces.IReadModifiable;
import usecase.CommentPresenter;
import usecase.UserManager;

import java.util.List;
import java.util.Scanner;

public class ReplyCommand extends Command {

    /**
     * Initializes the command with minimum/maximum arguments
     */
    public ReplyCommand() {
        super(1, 0);
    }

    @Override
    public String help() {
        return "Replies to a comment. Format: reply [commendID]. \nYou will be prompted for text.";
    }

    /**
     * Replies to a comment with text.
     *
     * @param ce command executor
     * @param arguments user arguments(comment id[optional])
     * @return the return string
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkAll(ce, arguments, "reply");
        Scanner in = new Scanner(System.in);
        IReadModifiable currentlyViewingPage = ce.getPageManager();
        UserManager user = ce.getUserManager();
        String userName = user.getUser().getDisplayName();
        System.out.println("Type your comment:");
        String text = in.nextLine();
        if (text.equalsIgnoreCase("")) {
            throw new ArgumentException("Please write some text. Try again.");
        }
        CommentPresenter cp = ((CommentPresenter) currentlyViewingPage);
        if (arguments.size() == 1) {
            cp.replyToComment(arguments.get(0), text, userName);
        } else {
            cp.replyToComment(text, userName);
        }

        return userName + " replied to comment with text [" + text + "]";
    }
}
