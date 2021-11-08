package UseCase;

import Entity.Course;
import Entity.InstructorUser;
import Entity.Rating;
import Entity.CommentGraph;
import UseCase.CoursePage.CoursePage;
import org.junit.Test;

import javax.xml.stream.events.Comment;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class CoursePageTest {
    @Test(timeout=100)
    public void testCoursePage(){
        Course sampleCourse = new Course("Sample Course", "CSC108");
        Rating sampleRating = new Rating();
        sampleRating.addScore("Computer Science", 1);
        sampleRating.addScore("Philosophy", 2);
        CommentGraph commentGraph = new CommentGraph(
                "Root comment 1", "Questions",
                "Sample User 1");
        InstructorUser profOne = new InstructorUser("Professor One", "1234");
        InstructorUser profTwo = new InstructorUser("Professor Two", "4567");

        List<InstructorUser> instructors = new ArrayList<>();

        instructors.add(profOne);
        instructors.add(profTwo);

        List<Integer> years = new ArrayList<>();
        years.add(2018);
        years.add(2019);
        years.add(2020);
        years.add(2021);

        CoursePage sampleCoursePage = new CoursePage(sampleCourse, sampleRating, instructors,
                years, commentGraph);
        System.out.println(sampleCoursePage);
    }
}
