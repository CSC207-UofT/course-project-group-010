package Controller.Database;

import Interface.IDBSaveable;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * A Gateway to a database, like an SQL database.
 * Will be responsible for returning an object of type T from the database,
 * and saving an object of type T to the database.
 * Currently, most things will just be hard-coded as we don't actually have a database.
 * @param <T>
 */
public abstract class Database<T extends IDBSaveable> {

    /**
     * Looks for an entry in the database the has the id [id]
     * Returns a map of the data it found, returns null if no data was found.
     * @param id
     * @return
     */
    abstract public T getEntry(String id) throws Exception;

    /**
     * sets an entry in the database, returning True if something was updated.
     * Checks getID() method of entry and updates existing entry if one with that id exists
     * in the database already.
     * @param entry
     * @return
     */
    abstract public boolean setEntry(T entry);

    /*
    Quick explanation of databases:
    Every entry in IDBSaveable will have a unique ID, hopefully. They have to implement a method that allows
    them to return that unique ID.

    So the Database class can look for an entry with that ID. It will then initialize a T object with the stuff
    in the database I guess, and return a T object.

    The setEntry will look if a T with the id matching that of [entry] already exists, and modify that entry if so.
    Otherwise it will make a new entry.
     */


}
