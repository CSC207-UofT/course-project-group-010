package Outer;

import Constants.FileConstants;
import Controller.Commands.CommandExecutor;
import Controller.Commands.CommandRequest;
import Outer.Database.Database;
import Outer.Database.DatabaseGetter.CourseDatabaseGetter;
import Outer.Database.DatabaseGetter.UserDatabaseGetter;
import UseCase.UserManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ScreenIO {

    // this is about to just run on a while loop until you close the program

    /**
     * This is a CLI that prompts the user and prints the output.
     * Idea is basically stolen from Java-Shell, but this is academically integrit
     * I swear
     * because it literally just takes input in a loop.
     * @param args
     */
    public static void main(String[] args) throws Exception {
        CommandExecutor commandExecutor = CommandExecutor.getInstance();
        Scanner in = new Scanner(System.in);
        String commandLine = "";
        // TODO consider a better way to implement then this possible
        System.out.println("type \"end\" to end program.");
        while(!commandLine.equals("end")) {
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

    /**
     * Bad sample data loading function
     * @throws Exception
     */
    public static void loadSampleData() throws Exception {
        UserManager um = new UserManager("student", "Kevin Wang", "12345",
                Map.ofEntries(Map.entry("programDetail", "Data Science Specialist")));
        UserManager um2 = new UserManager("instructor", "Asif Zaman", "237", null);
        Database<UserManager> db = new Database<>();
        Map<String, UserManager> map = new HashMap<>();
        map.put(um.getID(), um);
        map.put(um2.getID(), um2);
        db.saveToFile(new FileConstants().USER_FILE, map);
        // When courses come out we can load some sample course data too.
    }
}
