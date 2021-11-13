package Controller.Commands.CourseCommands;

import Controller.Commands.Command;
import Controller.Commands.CommandExecutor;
import UseCase.CoursePage.CoursePageBuilder;
import UseCase.CoursePage.Director;

import java.util.ArrayList;
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

        // Create the shit
        CoursePageBuilder cpb = new CoursePageBuilder();
        Director d = new Director();

        ArrayList<String> course = new ArrayList<>();
        ArrayList<String> instructor = new ArrayList<>();
        // Get the other 23%@$#$%^@
        System.out.println("Title: ");
        course.add(in.nextLine());
        System.out.println("Code: ");
        course.add(in.nextLine());
        System.out.println("Description: ");
        course.add(in.nextLine());
        String input = "";
        while (!input.equalsIgnoreCase("n")) {
            System.out.println("Add instructor?[type end to end]");
            String a = in.nextLine();
            if (!a.equalsIgnoreCase("n")) {
                instructor.add(a);
            }
        }
        d.constructCoursePage(cpb, course, instructor);
        return "course is " + course.toString() + " instructor is " + instructor.toString();
    }
}
