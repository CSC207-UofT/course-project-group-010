package entity;

import constants.ProgramConstants;
import interfaces.IUser;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class StudentUser implements Serializable, IUser {

    private final String ID; //ID
    private final String displayName; //Username
    private final Map<String, String> otherData;

    /**
     * Initializes a new StudentUser
     * @param displayName name
     * @param ID id
     * @param otherData other data, eg. programDetail.
     */
    public StudentUser(String displayName, String ID, Map<String, String> otherData) {
        this.displayName = displayName;
        this.ID = ID;
        this.otherData = otherData;
        fixOtherData();
    }

    /**
     * Overloaded initializer, creates blank otherData
     * @param displayName name
     * @param ID id
     */
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

    /**
     * Gets data for presentation purposes
     * @return a map of the data
     */
    @Override
    public HashMap<String, Object> getData() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("ID", ID);
        result.put("displayName", displayName);
        result.put("programDetail", getProgramDetail());
        return result;
    }

    /**
     * Sets the programDetail to N/A if it is invalid.
     */
    private void fixOtherData() {
        ProgramConstants pc = new ProgramConstants();
        if (!this.otherData.containsKey("programDetail") || !pc.contains(this.otherData.get("programDetail"))) {
            setProgramDetail(ProgramConstants.NO_PROGRAM);
        }
    }
}



