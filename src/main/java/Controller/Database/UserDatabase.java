package Controller.Database;

import Interface.IDBSaveable;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class UserDatabase extends Database {

    public Map<String, String> getEntry(String id) {
        if (id.equals("12345"){
            return ("12345","Kevin_Hart")

        }
    }
/*        if (userdata.containsKey(id)) {
            HashMap<String, List> newuser = new HashMap<String, List>();
            newuser.put("id", null);
            return newuser;
        }
    }*/

    public boolean setEntry(T entry) {
        userid = entry.getID();
        if (userdata.containsKey(userid)) {
            userdata.put("userid",entry.getdisplayName);
            return true;
        } else {
            return false;
        }
    }
}
