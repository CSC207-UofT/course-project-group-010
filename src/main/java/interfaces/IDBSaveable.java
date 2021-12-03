package interfaces;


public interface IDBSaveable {

    /**
     * gets some sort of id to save to the database. IDs should be unique
     * @return the ID of user
     */
    String getID();
}

