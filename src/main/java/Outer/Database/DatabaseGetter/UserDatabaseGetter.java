package Outer.Database.DatabaseGetter;

import Constants.FileConstants;
import Exceptions.NotInDatabaseException;
import Outer.Database.Database;
import UseCase.UserManager;

import java.io.IOException;
import java.util.Map;

/**
 * A user database. Will be more like a gateway class between an actual database(eg. SQL)
 * and this program.
 */
public class UserDatabaseGetter extends DatabaseGetter<UserManager> {

    public UserManager getEntry(String id) throws Exception {
        // TODO create student/prof constants
//        if (id.equals("12345")) {
//            return new UserManager("student", "Kevin", "12345",
//                    Map.ofEntries(Map.entry("programDetail", "Data Science Specialist")));
//        }
//        throw new NotInDatabaseException("User not found in Database.");

        Database<UserManager> db = new Database<>();
        Map<String, UserManager> UserDict = db.loadDatabase(new FileConstants().USER_FILE);
        try {
            return UserDict.get(id);
        } catch (Exception e) {
            throw new NotInDatabaseException("User not found in Database");
        }

    }

    public void setEntry(UserManager entry) throws IOException, ClassNotFoundException {
        Database<UserManager> db = new Database<>();
        Map<String, UserManager> UserDict = db.loadDatabase(new FileConstants().USER_FILE);
        UserDict.put(entry.getID(), entry);
        db.saveToFile(new FileConstants().USER_FILE, UserDict);
    }
}
