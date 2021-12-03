package controller.commands.commandHelpers;

import exceptions.ArgumentException;

import java.util.Map;

/**
 * Prompts the user to enter text, and builds the user based on the information.
 * Read our writeup for information on this design decision
 */
// TODO write this up
public interface UserBuilder {

    default String processDisplayName(String argDisplayName) {
        return argDisplayName;
    }

    default String processID(String argID) throws ArgumentException {
        argID = argID.replace(" ", "");
        argID = argID.toLowerCase();
        boolean isSame = argID.matches("^[a-z]+[0-9]*$");
        if (!isSame) {
            throw new ArgumentException("Invalid. IDs consist of 1 or more letters, followed by 0 or more numbers.");
        }
        return argID;
    }

    Map<String, String> processOtherData(String argument);

    String getOtherDataPromptString();
}
