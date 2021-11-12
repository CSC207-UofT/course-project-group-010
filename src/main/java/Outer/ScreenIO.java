package Outer;


import Constants.FileConstants;
import Constants.UserType;
import Controller.Commands.CommandExecutor;
import Controller.Commands.CommandRequest;
import Controller.DatabaseGetter.CourseDatabaseGetter;
import Controller.DatabaseGetter.UserDatabaseGetter;

import java.util.Scanner;

public class ScreenIO {

    // this is about to just run on a while loop until you close the program

    /**
     * This is a CLI that prompts the user and prints the output.
     * Idea is basically stolen from Java-Shell, but this is academically integrit
     * I swear
     * because it literally just takes input in a loop.
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        CommandExecutor commandExecutor = CommandExecutor.getInstance();
        Scanner in = new Scanner(System.in);
        String commandLine = "";
        // TODO consider a better way to implement then this possible
        System.out.println("type \"end\" to end program.");
        while (!commandLine.equals("end")) {
            System.out.print("$ ");
            commandLine = in.nextLine();
            try {
                CommandRequest request = new CommandRequest(commandLine);
                String output = commandExecutor.processRequest(request);
                if (!output.equals("")) {
                    System.out.println(output);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        CourseDatabaseGetter.getInstance().saveAll();
        UserDatabaseGetter.getInstance().saveAll();
        //in.close();
    }
}
