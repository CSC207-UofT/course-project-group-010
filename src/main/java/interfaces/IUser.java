package interfaces;

import entity.Course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IUser {

    /**
     * @return string representation of this User
     */

    String getID();

    String getDisplayName();

    Map<String, String> getOtherData();

    // TODO NOT IN USE DELETE
    void setOtherData(Map<String, String> otherData);

    HashMap<String, Object> getData();

    // TODO GETCOURSES AND SET COURSES ARE NOT USED. DELETE AND DELETE OVERRIDEN METHODS
    HashMap<Integer, List<Course>> getCourses();

    void setCourses(HashMap<Integer, List<Course>> c);

    // TODO only used in tests, delete
    int getReviewCount();

    // TODO the next 3 are never used. Delete.
    void incrementReviewCount();

    void setDisplayName(String s);

    void setReviewCount(int count);


}
