package outer.database;

import interfaces.IDBSaveable;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * A very inefficient database.
 * Has the ability to load all the data(as a map of [id, object])
 * And save data of the same format
 *
 * @param <T>
 */
@SuppressWarnings("ALL")
public class Database<T extends IDBSaveable & Serializable> {

    /**
     * Static method that "loads" the database(returns a map that represents the database)
     *
     * @param filePath the path of file
     * @param <T> type of object to be saved
     * @return map of ids to T objects
     * @throws IOException            if input/output is invalid
     * @throws ClassNotFoundException if the class is not found
     */
    public static <T extends IDBSaveable & Serializable> Map<String, T> loadDB(String filePath) throws IOException, ClassNotFoundException {
        Database<T> db = new Database<>();
        Map<String, T> retDict = db.loadFromFile(filePath);
        return retDict == null ? new HashMap<>() : retDict;
    }

    /**
     * Static method that saves the db contents to a file.
     *
     * @param filePath the path of file
     * @param objects map of ids to T objects
     * @param <T> Type of object to be saved
     * @throws IOException if input/output is invalid
     */
    public static <T extends IDBSaveable & Serializable> void saveToFile(String filePath, Map<String, T> objects) throws IOException {
        // Create new file if it doesn't exist
        File dbFile = new File(filePath);
        dbFile.createNewFile();

        // Serialization
        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the Map
        output.writeObject(objects);
        output.close();
    }

    /**
     * Loads a map of DBSaveable object ids to objects.
     *
     * @param filePath the path of file
     * @return the map, or an empty map otherwise.
     * @throws IOException            if input/output is invalid
     * @throws ClassNotFoundException if the class is not found
     */
    public Map<String, T> loadFromFile(String filePath) throws IOException, ClassNotFoundException {
        File dbFile = new File(filePath);
        // Create new DB file if it doesn't exist.
        dbFile.createNewFile();

        if (dbFile.length() == 0) {
            return new HashMap<>();
        }

        // Deserialization
        InputStream file = new FileInputStream(filePath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // serialize the Map
        Map<String, T> retMap = (Map<String, T>) input.readObject();
        input.close();
        return retMap == null ? new HashMap<>() : retMap;
    }
}
