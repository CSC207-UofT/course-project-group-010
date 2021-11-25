package Interface;

import entity.Course;

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

    HashMap<String, Object> getData();

    HashMap<Integer, List<Course>> getCourses();

    void setOtherData(Map<String, String> otherData);

    int getReviewCount();

    void incrementReviewCount();

    void setDisplayName(String s);

    void setreviewCount(int count);

    void setCourses(HashMap<Integer, List<Course>> c);


}
