package UseCase.CoursePage;

import Entity.CommentGraph;
import Entity.Course;
import Entity.InstructorUser;
import Entity.Rating;

import java.util.List;
import java.util.Optional;

public interface Builder {

    void setCourse(Course course);

    void setRatings(List<Rating> ratings);

    void setInstructor(String instructor);

}
