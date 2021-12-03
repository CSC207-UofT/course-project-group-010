package controller.commands.coursecommands;

import controller.commands.Command;
import controller.commands.CommandExecutor;
import controller.commands.commandHelpers.CourseCreationGetter;
import controller.databasegetter.CourseDatabaseGetter;
import exceptions.CommandNotAuthorizedException;
import usecase.CourseManager;
import usecase.coursePage.CoursePage;
import usecase.coursePage.CoursePageBuilder;
import usecase.coursePage.Director;

import java.io.IOException;
import java.util.List;

public class CreateCourseCommand extends Command {
    /**
     * Initializes the command with minimum/maximum arguments
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
        checkAll(ce, arguments, "createcourse");

        // TODO consider implementing the userinputgetter thing and changing this up yeah but idk this is weird so...
        List<List<String>> userInput = new CourseCreationGetter().getUserInput();
        List<String> course = userInput.get(0);
        List<String> instructor = userInput.get(1);
        return constructCourse(course, instructor);

    }

    @Override
    protected void checkAll(CommandExecutor ce, List<String> arguments, String method) throws Exception {
        checkHelp(arguments);
        checkArgumentsNum(arguments);
    }

    private String constructCourse(List<String> course, List<String> instructor) throws IOException, ClassNotFoundException, CommandNotAuthorizedException {
        // Create the builder
        CoursePageBuilder cpb = new CoursePageBuilder();
        Director d = new Director();

        // Using the builder to build things
        d.constructCoursePage(cpb, course, instructor);
        CoursePage cp = cpb.getResult();
        CourseManager cm = new CourseManager(cp);

        // Save to db and return
        CourseDatabaseGetter.getInstance().addEntry(cm);
        return "Successfully created " + course.get(0) + " with " + instructor.size() + " instructors.\n" +
                "Make sure to run saveall to save your progress!";
    }
}
