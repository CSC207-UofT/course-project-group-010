package controller.commands.commandHelpers;

import java.util.HashMap;
import java.util.Map;

public class InstructorUserBuilder implements UserBuilder {

    @Override
    public Map<String, String> processOtherData(String argument) {
        Map<String, String> otherData = new HashMap<>();
        otherData.put("position", argument);
        return otherData;
    }

    @Override
    public String getOtherDataPromptString() {
        return "Enter your position:";
    }
}
