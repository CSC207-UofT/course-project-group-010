package controller.commands.commandHelpers;

import constants.ProgramConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * Helps build a studentUser by processing user input
 */
public class StudentUserBuilder implements UserBuilder{

    /**
     * Processes student's input for otherData
     * @param argProgram the user's input
     * @return the outputted otherData map
     */
    @Override
    public Map<String, String> processOtherData(String argProgram) {
        argProgram = argProgram.toUpperCase();
        argProgram = new ProgramConstants().contains(argProgram) ? argProgram : ProgramConstants.NO_PROGRAM;
        Map<String, String> otherData = new HashMap<>();
        otherData.put("programDetail", argProgram);
        return otherData;
    }

    /**
     * @return the prompt string to print to the user to ask for their program detail
     */
    @Override
    public String getOtherDataPromptString() {
        return "Program Detail: " + "\n" + "Choose from one of following options: \n" + new ProgramConstants() + "\n PRESS ENTER TO SKIP";
    }
}
