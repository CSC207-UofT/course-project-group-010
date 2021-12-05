package interfaces;

import java.util.HashMap;
import java.util.Map;

/**
 * A user of the program
 */
public interface IUser {

    /**
     * @return id of the user
     */
    String getID();

    String getDisplayName();

    Map<String, String> getOtherData();

    HashMap<String, Object> getData();

}
