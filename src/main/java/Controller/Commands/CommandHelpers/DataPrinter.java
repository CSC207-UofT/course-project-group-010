package Controller.commands.commandHelpers;

import constants.CommandConstants;

import java.util.Map;

public class DataPrinter {
    /**
     * The data printer helps display data from the getData() method that all IGettable objects must implement.
     * eg. Courses, comment threads implement the interface.
     */
    // Data from getData() is a hashmap
    // The data string to put if all data is mapped to one key

    /**
     * Converts the data to a string representation.
     * If input map contains the allDataString, assumes that all data is mapped to that key.
     * @param data
     * @return
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
     * @param data
     * @return
     */
    private String processDict(Map<String, Object> data) {
        String returnString = "";
        for (String o : data.keySet()) {
            try {
                returnString = returnString + o + " : " + data.get(o).toString() + "\n";
            } catch (Exception e) {
                returnString = returnString + o + " : " + "n/a" + "\n";
            }
        }
        return returnString;
    }

}
