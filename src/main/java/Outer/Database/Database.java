package Outer.Database;

import Interface.IDBSaveable;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A very inefficient database.
 * Has the ability to load all the data(as a map of [id, object])
 * And save data of the same format
 * @param <T>
 */
public class Database<T extends IDBSaveable & Serializable> {

    public void saveToFile(String filePath, Map<String, T> objects) throws IOException {

        File dbFile = new File(filePath);
        dbFile.createNewFile();
        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the Map
        output.writeObject(objects);
        output.close();
    }

    public Map<String, T> loadDatabase(String filePath) throws IOException, ClassNotFoundException {
        File dbFile = new File(filePath);
        dbFile.createNewFile();
        InputStream file = new FileInputStream(filePath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // serialize the Map
        Map<String, T> retMap = (Map<String, T>) input.readObject();
        input.close();
        return retMap == null ? new HashMap<String, T>(): retMap;
    }
}
