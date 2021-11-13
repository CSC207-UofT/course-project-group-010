package Controller.Commands.CourseCommands;

import Controller.Commands.Command;
import Controller.Commands.CommandExecutor;

import java.util.List;
import java.util.Scanner;

public class CreateCourseCommand extends Command {
    /**
     * Initializes the command with minimum/maximum arguments
     *
     */
    public CreateCourseCommand() {
        super(0, 0);
    }

    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Title: ");
        String title = in.nextLine();
        System.out.println("Code: ");
        String code = in.nextLine();
        return "you called the course " + title + "(code: " + code + ")";
    }
}
