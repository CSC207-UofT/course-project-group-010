package controller.commands;

import controller.AuthHelper;
import controller.databasegetter.CourseDatabaseGetter;
import controller.databasegetter.UserDatabaseGetter;
import entity.StudentUser;
import exceptions.ArgumentException;
import exceptions.CommandNotAuthorizedException;
import usecase.CourseManager;
import usecase.UserManager;

import java.util.List;
import java.util.Scanner;

/**
 * Return the RelativeRating for this Course.
 *
 * The RelativeRating is the average rating score across all ratings left by students across the specified program input.
 *
 * Example;
 *
 * checkout CSC207
 * getRR Accounting
 * "On average, students from Accounting gave this course a score of 5.9/10."
 *
 *
 */

public class RelativeRatingCommand extends Command {

    public RelativeRatingCommand() { super(0, 0);
    }

    /**
     * Returns the RelativeRating for this course provided with the [program name] input.
     * Format is getRR [program name].
     *
     * @param ce A CommandExecutor object required to run this command.
     * @param arguments A list containing the string representation of the arguments provided.
     * @return String
     * @throws Exception
     *
     *
     */

    // List<String> arguments: [getRelativeRating, Computer Science]
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkViewingPageExists(ce);
        checkUserExists(ce);
        checkArgumentsNum(arguments);
        Scanner in = new Scanner(System.in);

        //Ask user to select the program which they want the relative rating for
        System.out.println("Relative Ratings across Program: " + "Choose from one of following options: ACCOUNTING, ACTUARIAL SCIENCE, ANTHROPOLOGY, APPLIED MATHEMATICS, APPLIED STATISTICS,COMPUTER SCIENCE, DATA SCIENCE.");
        //Get Users input.
        String argProgramDetail = in.nextLine().toUpperCase();
        //Get all the ratings that were left by students of argProgramDetail.
        if (ce.getPageManager() instanceof CourseManager) {
            //Calculate relativeRating via courseManager
            float relativeRating = ((CourseManager) ce.getPageManager()).getRelativeRating(arguments.get(0));
            //Create string object of course and rr to display.
            String courseCode =  ((CourseManager) ce.getPageManager()).getID();
            String string_RR =  (String.valueOf(relativeRating));

            //If there are no ratings left by students of argProgramDetail, i.e = 0
            if (relativeRating == 0) {
                return "There are currently no ratings by students from " + argProgramDetail + ".";
            }

            else {
            return "Students from " + argProgramDetail + "on average rated " + courseCode + " " + string_RR + "."; }
        }

        return "Unable to provide Relative Rating. Make sure you are viewing a course, or have selected one of the " +
                "provided programs.";
    }

    @Override
    public String help() {
        return "View the average rating across all ratings from students of [program name].";
    }




}
