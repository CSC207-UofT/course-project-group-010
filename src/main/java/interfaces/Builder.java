package interfaces;

import entity.CommentGraph;
import entity.Course;
import entity.Rating;

import java.util.HashMap;
import java.util.List;

public interface Builder {

    // TODO builder has 1 class that uses it, do we really need builder --> cpb --> director to build a single course page??
    // void buildRatings(List<List<String>> ratings);

    // todo never used, only implemented
    // void setCommentGraph(CommentGraph cg);

    void buildCourse(List<String> course);

    // void buildCommentGraph(List<String> typeName, HashMap<String, List<String>> initialComments);

    void setCourse(Course course);

    void setInstructors(List<String> instructors);

    // todo never used, only implemented
    // void setRatings(List<Rating> ratings);

    void reset();


//    public void setRelativeScore(float programRelativeScore);
//    // We can combine basic configuration and filter configuration into two methods
//    void setBasicConfiguration(HashMap<String, Object> basicConfiguration);
//    // If we allow the builder to filter, we can add filter configuration
//    void setFilterConfiguration(HashMap<String, Object> filterConfiguration);

}
