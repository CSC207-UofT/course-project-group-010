package controller.commands.commandHelpers;

import exceptions.ArgumentException;

import java.util.Map;

/**
 * Prompts the user to enter text, and builds the user based on the information.
 * Read our writeup for information on this design decision
 */
public interface UserBuilder {

    /**
     * Processes user input for display name
     * @param argDisplayName user input
     * @return processed name
     */
    default String processDisplayName(String argDisplayName) {
        return argDisplayName;
    }

    /**
     * Checks if user input id matches our format for ids.
     * This is an example of the single responsibility principle. The regex processing is isolated to this class.
     * @param argID id inputted by the user
     * @return processed id
     * @throws ArgumentException if the format is incorrect
     */
    default String processID(String argID) throws ArgumentException {
        argID = argID.replace(" ", "");
        argID = argID.toLowerCase();
        boolean isSame = argID.matches("^[a-z]+[0-9]*$");
        if (!isSame) {
            throw new ArgumentException("Invalid. IDs consist of 1 or more letters, followed by 0 or more numbers.");
        }
        return argID;
    }

    /**
     * Processes otherData map for the user, based on their input
     * @param argument user input
     * @return otherData map
     */
    Map<String, String> processOtherData(String argument);

    /**
     * @return the prompt string to print to the user to collect otherData argument
     */
    String getOtherDataPromptString();
}
