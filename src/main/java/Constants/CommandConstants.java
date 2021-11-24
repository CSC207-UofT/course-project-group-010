package constants;

import Controller.commands.*;
import Controller.commands.commentcommands.*;
import Controller.commands.coursecommands.CreateCourseCommand;
import Controller.commands.debuggingcommands.ListCoursesCommand;
import Controller.commands.debuggingcommands.ListUsersCommand;
import exceptions.CommandNotFoundException;

import java.util.Hashtable;

/**
 * Stores strings that will call different command objects.
 */
public class CommandConstants {
    public final Hashtable<String, Command> command_dict = new Hashtable<>();
    // allDataString used in the checkoutCommand.
    public static final String allDataString = "all-data";

    public CommandConstants() {
        command_dict.put("help", new HelpCommand());
        command_dict.put("login", new LoginCommand());
        command_dict.put("print", new PrintCommand());
        command_dict.put("checkout", new CheckoutCommand());
        command_dict.put("rate", new RateCommand());
        command_dict.put("newuser", new NewUserCommand());
        command_dict.put("saveall", new SaveAllCommand());
        command_dict.put("logout", new LogoutCommand());
        command_dict.put("displayfullthread", new DisplayFullThreadCommand());
        command_dict.put("displaysubsetthread", new DisplaySubsetThreadCommand());
        command_dict.put("getpath", new GetPathCommand());
        command_dict.put("reply", new ReplyCommand());
        command_dict.put("vote", new VoteCommand());
        command_dict.put("createcourse", new CreateCourseCommand());
        // command_dict.put("filter", new FilterInstructorCommand());
        command_dict.put("getcomments", new GetCommentsCommand());
        //command_dict.put("startcomment", new StartCommentCommand());
        command_dict.put("listcourses", new ListCoursesCommand());
        command_dict.put("listusers", new ListUsersCommand());
        command_dict.put("cd", new CommentCDCommand());
    }

    public Command get(String key) throws CommandNotFoundException {
        if (!command_dict.containsKey(key)) {
            throw new CommandNotFoundException();
        }
        return command_dict.get(key);
    }
}
