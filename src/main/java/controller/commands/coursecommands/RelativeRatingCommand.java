//package controller.commands.coursecommands;
//
//import constants.ProgramConstants;
//import controller.commands.Command;
//import controller.commands.CommandExecutor;
//import controller.databasegetter.UserDatabaseGetter;
//import entity.StudentUser;
//import exceptions.ArgumentException;
//import exceptions.CommandNotAuthorizedException;
//import usecase.CourseManager;
//import usecase.UserManager;
//
//import java.util.List;
//import java.util.Scanner;
//
///**
// * Return the RelativeRating for this Course.
// *
// * The RelativeRating is the average rating score across all ratings left by students across the specified program input.
// *
// * Example;
// *
// * checkout CSC207
// * getRR Accounting
// * "On average, students from Accounting gave this course a score of 5.9/10."
// *
// *
// */
//
//public class RelativeRatingCommand extends Command {
//
//    public RelativeRatingCommand() { super(1, 1);
//    }
//
//    /**
//     * Returns the RelativeRating for this course provided with the [program name] input.
//     * Format is getRR [program name].
//     *
//     * @param ce A CommandExecutor object required to run this command.
//     * @param arguments A list containing the string representation of the arguments provided.
//     * @return String
//     * @throws Exception
//     *
//     *
//     */
//    // TODO what is the format of relativeratingcommand? How do you use it(should be in the help string)
//    // TODO relative rating doesn't display right on courses anyways
//    // TODO what does relativeratingcommand even do??
//    // TODO probably just roll it back and delete
//    // List<String> arguments: [getRelativeRating, Computer Science]
//    @Override
//    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
//        // CheckAll auto-runs checkhelp, etc.
//        // checkHelp(arguments);
//        // super.checkArgumentsNum(arguments);
//        Scanner in = new Scanner(System.in);
//
//        checkAll(ce, arguments, "rate");
//        // checkUserExists(ce);
//
//        //Ask user to select the program which they want the relative rating for
//        ProgramConstants pc = new ProgramConstants();
//        System.out.println("Relative Ratings across Programs: " + "\n" + "Note : Choose from one of following options: \n" + pc);
//        //Get Users input.
//        String argProgramDetail = in.nextLine().toUpperCase();
//        if (!pc.contains(argProgramDetail)) {
//            throw new ArgumentException("Unable to provide Relative Rating. Make sure you are viewing a course, or have selected one of the " +
//                    "provided programs.");
//        }
//        //Get all the ratings that were left by students of argProgramDetail.
//        // pageManager will be an instance of CourseManager because it is authorized.
//        float relativeRating = ((CourseManager) ce.getPageManager()).getRelativeRating(arguments.get(0));
//        String courseCode =  ((CourseManager) ce.getPageManager()).getID();
//        String string_RR =  (String.valueOf(relativeRating));
//
//        //If there are no ratings left by students of argProgramDetail, i.e = 0
//        if (relativeRating == 0) {
//            return "There are currently no ratings by students from " + argProgramDetail + ".";
//        }
//
//        else {
//            return "Students from " + argProgramDetail + "on average rated " + courseCode + " " + string_RR + "."; }
//    }
//
//    @Override
//    public String help() {
//        return "Rates a course, must be viewing a course to use. Format \"rate [1-10]\"";
//    }
//
//
//
//
//}
