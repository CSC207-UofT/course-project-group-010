package UseCase.CoursePage;

import Entity.CommentGraph;
import Entity.Course;
import Entity.InstructorUser;
import Entity.Rating;

public interface Builder {

    void setCourse(Course course);

    void setRating(Rating rating);


    void addInstructors(InstructorUser instructor);

    void setYear(int year);

    void setCommentGraph(CommentGraph commentGraph);
}
