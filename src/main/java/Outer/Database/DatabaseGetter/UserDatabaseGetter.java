package Outer.Database.DatabaseGetter;

import Constants.FileConstants;
import Exceptions.NotInDatabaseException;
import Outer.Database.Database;
import UseCase.CourseManager.CourseManager;
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
    public boolean containsKey(String key) {
        return this.userDict.containsKey(key);
    }

    public void saveAll () throws IOException {
        db.saveToFile(new FileConstants().USER_FILE, this.userDict);
    }

    public static UserDatabaseGetter getInstance() throws IOException, ClassNotFoundException {
        if (instance == null) {
            instance = new UserDatabaseGetter();
        }
        return instance;
    }
}
