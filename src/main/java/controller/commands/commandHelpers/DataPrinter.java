package controller.commands.commandHelpers;

import constants.CommandConstants;

import java.util.Map;

/**
 * The data printer helps display data from the getData() method that all IGettable objects must implement.
 * IT DECIDES HOW TO PROCESS THE DATA FROM IGETTABLE. THIS MAINTAINS THE SINGLE RESPONSIBILITY PRINCIPLE
 * eg. Courses, comment threads implement the interface.
 */
public class DataPrinter {
    // Data from getData() is a hashmap
    // The data string to put if all data is mapped to one key

    /**
     * Converts the data to a string representation.
     * If input map contains the allDataString, assumes that all data is mapped to that key.
     * @param data data to be processed
     * @return processed data as a string representation
     */
    public String printData(Map<String, Object> data) {
        if (data.containsKey(CommandConstants.allDataString)) {
            return data.get(CommandConstants.allDataString).toString();
        } else {
            return processDict(data);
        }
    }

    /**
     * Processes a dictionary of strings to objects, converting it to a string representation
     * @param data dictionary of datat to be represented
     * @return the string representation of the data
     */
    private String processDict(Map<String, Object> data) {
        StringBuilder returnString = new StringBuilder();
        for (String o : data.keySet()) {
            try {
                returnString.append(o).append(" : ").append(data.get(o).toString()).append("\n");
            } catch (Exception e) {
                returnString.append(o).append(" : ").append("n/a").append("\n");
            }
        }
        return returnString.toString();
    }

}
