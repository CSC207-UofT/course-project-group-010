package interfaces;

/**
 * Any class that can be saved to a database, that needs to give up an id of some sort.
 */
public interface IDBSaveable {

    /**
     * gets some sort of id to save to the database. IDs should be unique
     * @return the ID of user
     */
    String getID();
}

