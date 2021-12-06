package controller.commands.commandHelpers;

import java.util.HashMap;
import java.util.Map;

public class InstructorUserBuilder implements UserBuilder {

    /**
     * Processes otherData map for the instructor, based on user input argument
     * @param argument input argument
     * @return processed otherData dictionary
     */
    @Override
    public Map<String, String> processOtherData(String argument) {
        Map<String, String> otherData = new HashMap<>();
        otherData.put("position", argument);
        return otherData;
    }

    /**
     * @return The string to print to prompt the user to enter otherData information
     */
    @Override
    public String getOtherDataPromptString() {
        return "Enter your position:";
    }
}
