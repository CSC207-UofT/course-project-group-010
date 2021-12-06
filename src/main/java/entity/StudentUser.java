package entity;

import constants.ProgramConstants;
import interfaces.IUser;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class StudentUser implements Serializable, IUser {

    private final String ID;
    private final String displayName;
    private final Map<String, String> otherData;

    /**
     * A StudentUser object. Constructor is overloaded so a StudentUserObject can be initialized with or without
     * otherData. The parameters are currently final as no changes need to be made; future developers may remove
     * the finality of the parameter if need be.
     *
     *
     * This is the StudentUser constructor.
     *
     * @param ID The uTor ID of this student.
     * @param displayName The display name of this student.
     * @param otherData A map containing information about this student; currently ID, displayName, and program details.
     *                  Open to extension to include more data.
     */
    public StudentUser(String displayName, String ID, Map<String, String> otherData) {
        this.displayName = displayName;
        this.ID = ID;
        this.otherData = otherData;
        fixOtherData();
    }


    /**
     * This is the StudentUser constructor that can initialize a StudentUser object without otherData as an input.
     * It initializes an empty HashMap instead.
     *
     * @param ID The uTor ID of this student.
     * @param displayName The display name of this student.
     */
    public StudentUser(String displayName, String ID) {
        this.displayName = displayName;
        this.ID = ID;
        this.otherData = new HashMap<>();
        fixOtherData();
    }

    /**
     * @return a string representation of this StudentUser.
     */
    @Override
    public String toString() {
        return this.getDisplayName() + "\n" + this.getID();
    }

    /**
     * @return the ID of this StudentUser.
     */
    @Override
    public String getID() {
        return this.ID;
    }

    /**
     * @return the DisplayName of this StudentUser.
     */
    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * @return the otherData of this StudentUser.
     */
    @Override
    public Map<String, String> getOtherData() {
        return otherData;
    }

    /**
     * @return the program that this StudentUser is enrolled in.
     */

    public String getProgramDetail() {

        return this.getOtherData().get("programDetail");
    }

    /**
     * Set the program that this StudentUser is enrolled in.
     */
    public void setProgramDetail(String s) {
        this.getOtherData().put("programDetail", s);
        fixOtherData();
    }

    /**
     * @return A hashmap of data containing all relevant information about this student.
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
     * Set this StudentUser's program to NO_PROGRAM if program detail is not provided.
     * Open to extension in the future if more data is not provided by the user.
     */
    private void fixOtherData() {
        ProgramConstants pc = new ProgramConstants();
        if (!this.otherData.containsKey("programDetail") || !pc.contains(this.otherData.get("programDetail"))) {
            setProgramDetail(ProgramConstants.NO_PROGRAM);
        }
    }
}



