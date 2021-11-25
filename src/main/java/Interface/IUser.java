package Interface;

import Entity.Course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IUser {

    /**
     * @return string representation of this User
     */

    String toString();

    String getID();

    String getdisplayName();

    Map<String, String> getOtherData();

    void setOtherData(Map<String, String> otherData);

    HashMap<String, Object> getData();

    HashMap<Integer, List<Course>> getCourses();

    void setCourses(HashMap<Integer, List<Course>> c);

    int getReviewCount();

    void incrementReviewCount();

    void setDisplayName(String s);

    void setreviewCount(int count);


}
