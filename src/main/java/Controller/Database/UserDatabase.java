package Controller.Database;

import Interface.IDBSaveable;
import UseCase.UserManager;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * A user database. Will be more like a gateway class between an actual database(eg. SQL)
 * and this program.
 */
public class UserDatabase extends Database<UserManager> {

    public Map<String, String> getEntry(String id) {
        if (id.equals("12345")) {
            Map<String, String> newuser = new HashMap<>();
            newuser.put("type", "student");
            newuser.put("id","12345");
            newuser.put("name", "Kevin Hart");
            // change this to an otherdata map that stores other data
            newuser.put("other", "");
            return newuser;
        }
        return null;
    }
/*        if (userdata.containsKey(id)) {
            HashMap<String, List> newuser = new HashMap<String, List>();
            newuser.put("id", null);
            return newuser;
        }
    }*/

    public boolean setEntry(UserManager entry) {
//        userid = entry.getID();
//        if (userdata.containsKey(userid)) {
//            userdata.put("userid",entry.getdisplayName);
//            return true;
//        } else {
//            return false;
//        }
        return false;
    }
}
