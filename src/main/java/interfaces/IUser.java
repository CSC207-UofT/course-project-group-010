package interfaces;

import java.util.HashMap;
import java.util.Map;

/**
 * A user of the program
 */
public interface IUser {

    /**
     * @return string representation of this User
     */
    String getID();

    String getDisplayName();

    Map<String, String> getOtherData();

    HashMap<String, Object> getData();

}
