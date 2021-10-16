package Interface;

import java.util.Map;

public interface IDBSaveable {
    /**
     * save the information that the user entries into course database
     */
    Map<String, Object> giveDataToDatabase();

    /**
     * gets some sort of id. IDs should be unique
     * @return
     */
    String getID();
}

