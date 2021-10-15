package Outer;

import Controller.Commands.CommandExecutor;
import Controller.Commands.CommandRequest;

import java.util.Scanner;

public class ScreenIO {

    // this is about to just run on a while loop until you close the program

    /**
     * This is a CLI that prompts the user and prints the output.
     * Idea is basically stolen from Java-Shell, but this is academically integrit
     * I swear.
     * @param args
     */
    public static void main(String[] args) {
        CommandExecutor commandExecutor = CommandExecutor.getInstance();
        Scanner in = new Scanner(System.in);

        // TODO change the condition
        while(true) {
            System.out.print("> ");
            String commandLine = in.nextLine();
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
        //in.close();
    }
}
