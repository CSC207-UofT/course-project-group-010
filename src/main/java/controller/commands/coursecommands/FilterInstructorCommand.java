//package controller.commands.coursecommands;
//
//import controller.commands.Command;
//import controller.commands.CommandExecutor;
//import usecase.CourseManager;
//
//import java.util.List;
//import java.util.Scanner;
//
//// TODO delete.
///**
// * Not in use, as the filter by instructor option was removed.
// */
//public class FilterInstructorCommand extends Command {
//    /**
//     * Initializes the command with minimum/maximum arguments
//     *
//     */
//    public FilterInstructorCommand() {
//        super(0, 0);
//    }
//
//    @Override
//    public String help() {
//        return "filter by instructor. Type [filter] to start";
//    }
//
//    @Override
//    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
//         checkAll(ce, arguments, "filter");
//         Scanner in = new Scanner(System.in);
//         // if it's authorized, then getPageManager should be an instance of CourseManager
//        CourseManager cm = ((CourseManager) ce.getPageManager());
//
//        System.out.println("what instructor do you want to filter by");
//        System.out.println("Available instructors: " + cm.getCoursePage().getInstructors().toString());
//        String instructor = in.nextLine();
//
//        // CoursePage a = cm.filterInstructor(instructor);
//        return "FILTERING IS DEPRECATED JUST DELETE THIS CLASS";
//    }
//}
