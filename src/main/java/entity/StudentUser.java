package entity;

import interfaces.IUser;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentUser implements Serializable, IUser {

    public static int MAXIMUM_DISPLAY_LENGTH = 25;
    private final String ID; //ID
    private String displayName; //Username
    private Map<String, String> otherData;

    //Constructors
    public StudentUser(String displayName, String ID, Map<String, String> otherData) {
        this.displayName = displayName;
        this.ID = ID;
        this.otherData = otherData;
    }

    // TODO this is only in use in tests, consider deleting(but honestly I think it's alright, idk)
    public StudentUser(String displayName, String ID) {
        this.displayName = displayName;
        this.ID = ID;
        this.otherData = new HashMap<>();
        setProgramDetail("N/A");
    }

    @Override
    public String toString() {
        return this.getDisplayName() + "\n" + this.getID();
    }

    //Getters
    @Override
    public String getID() {
        return this.ID;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public Map<String, String> getOtherData() {
        return otherData;
    }

    //Setters

    public String getProgramDetail() {

        return this.getOtherData().get("programDetail");
    }

    public void setProgramDetail(String s) {
        this.getOtherData().put("programDetail", s);
    }

    @Override
    public HashMap<String, Object> getData() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("ID", ID);
        result.put("displayName", displayName);
        result.put("programDetail", getProgramDetail());
        return result;
    }
}



