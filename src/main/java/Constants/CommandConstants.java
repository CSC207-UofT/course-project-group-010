package Constants;

import Controller.Commands.*;
import Controller.Commands.CommentCommands.*;
import Controller.Commands.CourseCommands.CreateCourseCommand;
import Controller.Commands.CourseCommands.FilterInstructorCommand;
import Exceptions.CommandNotFoundException;

import java.util.Hashtable;

/**
 * Stores strings that will call different command objects.
 */
public class CommandConstants {
    public final Hashtable<String, Command> command_dict = new Hashtable<>();

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
        command_dict.put("filter", new FilterInstructorCommand());
        command_dict.put("getcomments", new GetCommentsCommand());
        //command_dict.put("startcomment", new StartCommentCommand());
    }

    public Command get(String key) throws CommandNotFoundException {
        if (!command_dict.containsKey(key)) {
            throw new CommandNotFoundException();
        }
        return command_dict.get(key);
    }
}
