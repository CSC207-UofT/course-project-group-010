package Interface;

public interface IDBSaveable {
    /**
     * save the information that the user entries into course database
     */
    String giveData();

    /**
     * gets some sort of id. IDs should be unique
     * @return
     */
    String getID();
}

