package UseCase.CoursePage;

import Entity.CommentGraph;
import Entity.Course;
import Entity.InstructorUser;
import Entity.Rating;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
