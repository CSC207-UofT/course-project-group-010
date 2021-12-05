package interfaces;


import java.util.HashMap;

/**
 * Any class that must produce a map of data for presentation purposes
 */
public interface IGettable {

    /**
     * give up data to the course page for display
     */
    HashMap<String, Object> getData() throws IllegalArgumentException;
}
