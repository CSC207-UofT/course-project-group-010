package Interface;


import java.util.HashMap;

public interface IGettable {
    /**
     * give up data to the course page for display
     */

    HashMap<String, Object> getData() throws IllegalArgumentException;
}
