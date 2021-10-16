package Controller.Database;

import Interface.IDBSaveable;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class Database<T extends IDBSaveable> {
    HashMap<String, List> userdata = new HashMap<String,List>();

    /**
     * Looks for an entry in the database the has the id [id]
     * Then initializes a new T object and returns it.
     * @param id
     * @return
     */
    // TODO update data type of arguments if necessary, I'm thinking it should be an id or something
    // because then it'll be like name: "kevin", "id": 1234 for example
    abstract public T getEntry(String id);

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
