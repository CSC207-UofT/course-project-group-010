package Interface;

import entity.CommentGraph;
import entity.Course;
import entity.Rating;

import java.util.List;

public interface Builder {


    public void setCourse(Course course);

    public void setInstructors(List<String> instructors);

    public void setRatings(List<Rating> ratings);

    public void setCommentGraphs(List<CommentGraph> cg);

    public void reset();


//    public void setRelativeScore(float programRelativeScore);
//    // We can combine basic configuration and filter configuration into two methods
//    void setBasicConfiguration(HashMap<String, Object> basicConfiguration);
//    // If we allow the builder to filter, we can add filter configuration
//    void setFilterConfiguration(HashMap<String, Object> filterConfiguration);

}
