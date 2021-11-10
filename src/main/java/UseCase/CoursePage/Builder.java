package UseCase.CoursePage;

import Entity.CommentGraph;
import Entity.Course;
import Entity.InstructorUser;
import Entity.Rating;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface Builder {

    void setYears(List<Integer> years);

    void setYear(int year);

    void setCourse(Course course);

    void setRatings(List<Rating> ratings);

    void setInstructor(String instructor);

    void setInstructors(List<String> instructors);

    void setInstructorMap(HashMap<Integer, List<String>> instructorMap);

    void filterInstructor(String instructor);

    void filterYear(int year);

}
