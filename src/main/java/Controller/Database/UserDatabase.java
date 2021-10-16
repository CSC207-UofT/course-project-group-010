package Controller.Database;

import Interface.IDBSaveable;

import java.awt.*;
import java.util.HashMap;

public class UserDatabase extends Database {

    public HashMap<String, List> getEntry(String id) {
        if (userdata.containsKey(id)) {
            HashMap<String, List> newuser = new HashMap<String, List>();
            newuser.put("id", null);
            return newuser;
        }
    }

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
