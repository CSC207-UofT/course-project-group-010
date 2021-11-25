package outer.database;

import Interface.IDBSaveable;

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
public class Database<T extends IDBSaveable & Serializable> {
    // TODO TODO TODO DatabaseGetter currently instantiates database, this is VERRRY BAD, fix.
    // consider making db.LoadDatabase like a new class like DatabaseLoader idk the TA didn't even notice this.
    // TODO make create user/course commands that make new objects and then save them to the db or something
    // TODO make the database load on startup and save before the program closes.

    public void saveToFile(String filePath, Map<String, T> objects) throws IOException {
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
     * @param filePath
     * @return the map, or an empty map otherwise.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Map<String, T> loadDatabase(String filePath) throws IOException, ClassNotFoundException {
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
