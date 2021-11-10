package UseCase.CoursePage;

import Entity.CommentGraph;
import Entity.Course;
import Entity.InstructorUser;
import Entity.Rating;

import java.util.List;
import java.util.Optional;

public interface Builder {

    void setYear(int year);

    void setYears(List<Integer> years);

    void setCourse(Course course);

    void setRating(Rating rating);

    void setInstructors(List<InstructorUser> instructors);

    void setCommentGraph(CommentGraph commentGraph);
}
