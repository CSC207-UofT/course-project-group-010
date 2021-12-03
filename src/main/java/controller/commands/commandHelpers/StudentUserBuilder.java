package controller.commands.commandHelpers;

import constants.ProgramConstants;

import java.util.HashMap;
import java.util.Map;

public class StudentUserBuilder implements UserBuilder{

    @Override
    public Map<String, String> processOtherData(String argProgram) {
        argProgram = argProgram.toUpperCase();
        argProgram = new ProgramConstants().contains(argProgram) ? argProgram : "N/A";
        Map<String, String> otherData = new HashMap<>();
        otherData.put("programDetail", argProgram);
        return otherData;
    }

    @Override
    public String getOtherDataPromptString() {
        return "Program Detail: " + "\n" + "Choose from one of following options: \n" + new ProgramConstants() + "\n PRESS ENTER TO SKIP";
    }
}
