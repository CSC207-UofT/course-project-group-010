package controller.commands.coursecommands;

import UseCase.CourseManager;
import UseCase.CoursePage;
import UseCase.CoursePageBuilder;
import UseCase.Director;
import controller.commands.Command;
import controller.commands.CommandExecutor;
import controller.databasegetter.CourseDatabaseGetter;

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
    public String help() {
        return "creates a course. Enter createcourse then follow the prompts given.";
    }

    /**
     * Prompts the user to create a new course.
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkHelp(arguments);
        checkArgumentsNum(arguments);
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
        while (!input.equalsIgnoreCase("end")) {
            System.out.println("Add instructor?[type end to end]");
            input = in.nextLine();
            if (!input.equalsIgnoreCase("end")) {
                instructor.add(input);
            }
        }
        // Using the builder to build things
        d.constructCoursePage(cpb, course, instructor);
        CoursePage cp = cpb.getResult();
        CourseManager cm = new CourseManager(cp);

        // Save to db and return
        CourseDatabaseGetter.getInstance().addEntry(cm);
        return "Successfully created " + course.get(0).toString() + " with " + instructor.size() + " instructors.\n" +
                "Make sure to run saveall to save your progress!";
    }
}
