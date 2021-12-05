package controller.databasegetter;

import constants.FileConstants;
import exceptions.CommandNotAuthorizedException;
import exceptions.NotInDatabaseException;
import outer.database.Database;
import usecase.UserManager;

import java.io.IOException;
import java.util.Map;

/**
 * A user database. Will be more like a gateway class between an actual database(eg. SQL)
 * and this program.
 */
public class UserDatabaseGetter extends DatabaseGetter<UserManager> {

    // TODO move this in with Database, make a mock sql thing that literally just calls getEntry and stuff
    // like FROM DATABASE GET ID --> UserDatabaseGetter.getinstance().getwhatever
    private static UserDatabaseGetter instance = null;
    // private final Database<UserManager> db;
    private final Map<String, UserManager> userDict;

    private UserDatabaseGetter() throws IOException, ClassNotFoundException {
        userDict = Database.loadDB(new FileConstants().USER_FILE);
    }

    public static UserDatabaseGetter getInstance() throws IOException, ClassNotFoundException {
        if (instance == null) {
            instance = new UserDatabaseGetter();
        }
        return instance;
    }

    /**
     * Gets a saved entry by id
     *
     * @param id id of the entry
     * @return the entry
     * @throws NotInDatabaseException if user is not found in Database
     */
    public UserManager getEntry(String id) throws NotInDatabaseException {
        try {
            return this.userDict.get(id);
        } catch (Exception e) {
            throw new NotInDatabaseException("User not found in Database");
        }

    }

    /**
     * Adds an entry to the database
     *
     * @param entry the entry to be added
     * @throws CommandNotAuthorizedException if the command is not authorized
     */
    @Override
    public void addEntry(UserManager entry) throws CommandNotAuthorizedException {
        if (!this.userDict.containsKey(entry.getID())) {
            this.userDict.put(entry.getID(), entry);
        } else {
            throw new CommandNotAuthorizedException("user with inputted id is already in the database");
        }
    }

    /**
     * checks if an id is in the database
     *
     * @param key the id
     * @return true if the id is in the database . false if the id is not in the database
     */
    @Override
    public boolean containsKey(String key) {
        return this.userDict.containsKey(key);
    }

    /**
     * Saves the database
     *
     * @throws IOException if input/output is invalid
     */
    public void saveAll() throws IOException {
        Database.saveToFile(new FileConstants().USER_FILE, this.userDict);
    }

    /**
     * Represents the user database as a string, showing all available ids and corresponding display names.
     *
     * @return a string representation of user database
     */
    @Override
    public String toString() {
        StringBuilder retStr = new StringBuilder();
        for (String key : this.userDict.keySet()) {
            retStr.append(key).append(": ");
            UserManager um = userDict.get(key);
            retStr.append(um.getUser().getDisplayName()).append("\n");
        }
        return retStr.toString().strip();
    }
}
