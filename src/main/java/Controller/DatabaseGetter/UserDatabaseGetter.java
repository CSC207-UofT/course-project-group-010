package Controller.databasegetter;

import constants.FileConstants;
import exceptions.CommandNotAuthorizedException;
import exceptions.NotInDatabaseException;
import Outer.database.Database;
import UseCase.UserManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A user database. Will be more like a gateway class between an actual database(eg. SQL)
 * and this program.
 */
public class UserDatabaseGetter extends DatabaseGetter<UserManager> {

    private static UserDatabaseGetter instance = null;
    private final Database<UserManager> db;
    private final Map<String, UserManager> userDict;

    private UserDatabaseGetter() throws IOException, ClassNotFoundException {
        Map<String, UserManager> userDict1;
        this.db = new Database<>();
        userDict1 = this.db.loadDatabase(new FileConstants().USER_FILE);
        if (userDict1 == null) {
            userDict1 = new HashMap<>();
        }
        this.userDict = userDict1;
    }

    public static UserDatabaseGetter getInstance() throws IOException, ClassNotFoundException {
        if (instance == null) {
            instance = new UserDatabaseGetter();
        }
        return instance;
    }

    public UserManager getEntry(String id) throws Exception {
        // TODO create student/prof constants
//        if (id.equals("12345")) {
//            return new UserManager("student", "Kevin", "12345",
//                    Map.ofEntries(Map.entry("programDetail", "Data Science Specialist")));
//        }
//        throw new NotInDatabaseException("User not found in Database.");
        // If an entry is modified, it will reflect in this database. Therefore, we will just save all objects away
        // before the program ends or something ??
        try {
            return this.userDict.get(id);
        } catch (Exception e) {
            throw new NotInDatabaseException("User not found in Database");
        }

    }

    @Override
    public void setEntry(UserManager entry) {
        this.userDict.put(entry.getID(), entry);
    }

    @Override
    public void addEntry(UserManager entry) throws CommandNotAuthorizedException {
        if (!this.userDict.containsKey(entry.getID())) {
            this.userDict.put(entry.getID(), entry);
        }
        else {
            throw new CommandNotAuthorizedException("user with inputted id is already in the database");
        }
    }

    @Override
    public boolean containsKey(String key) {
        return this.userDict.containsKey(key);
    }

    public void saveAll() throws IOException {
        db.saveToFile(new FileConstants().USER_FILE, this.userDict);
    }

    /**
     * Represents the user database as a string, showing all available ids and corresponding display names.
     * @return
     */
    @Override
    public String toString() {
        StringBuilder retStr = new StringBuilder();
        for (String key : this.userDict.keySet()) {
            // TODO make it easier to access users basic info from userManager
            // this is dependent on the abstract user class, I guess, so that's alright?
            retStr.append(key + ": ");
            UserManager um = userDict.get(key);
            retStr.append(um.getUser().getdisplayName() + "\n");
        }
        return retStr.toString().strip();
    }
}
