package controller.commands.commandHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Gets user input for the necessary fields of course creation
 */
public class CourseCreationGetter {
    public List<List<String>> getUserInput() {
        Scanner in = new Scanner(System.in);
        ArrayList<String> course = new ArrayList<>();
        ArrayList<String> instructor = new ArrayList<>();
        // Get the other stuff
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
        return List.of(course, instructor);
    }
}
