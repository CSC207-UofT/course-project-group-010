package entity;

import constants.ProgramConstants;
import exceptions.ArgumentException;
import interfaces.IUser;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentUser implements Serializable, IUser {

    private final String ID; //ID
    private String displayName; //Username
    private Map<String, String> otherData;

    //Constructors
    public StudentUser(String displayName, String ID, Map<String, String> otherData) {
        this.displayName = displayName;
        this.ID = ID;
        this.otherData = otherData;
        fixOtherData();
    }

    // TODO this is only in use in tests, consider deleting(but honestly I think it's alright, idk)
    public StudentUser(String displayName, String ID) {
        this.displayName = displayName;
        this.ID = ID;
        this.otherData = new HashMap<>();
        fixOtherData();
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
        fixOtherData();
    }

    @Override
    public HashMap<String, Object> getData() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("ID", ID);
        result.put("displayName", displayName);
        result.put("programDetail", getProgramDetail());
        return result;
    }

    private void fixOtherData() {
        ProgramConstants pc = new ProgramConstants();
        if (!this.otherData.containsKey("programDetail") || !pc.contains(this.otherData.get("programDetail"))) {
            setProgramDetail("N/A");
        }
    }
}



