package UseCase.CoursePage;

import Entity.CommentGraph;
import Entity.Course;
import Entity.Rating;
import Entity.StudentUser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

public class CoursePageBuilderTest {

    @Test
    public void getResult() {
        CoursePageBuilder builder = new CoursePageBuilder();

        Course calculus1 = new Course("Calculus with Proofs", "MAT137");

        List<String> sampleInstructors = Arrays.asList("Alfonso Gracia-Saz", "Joel Kamnitzer");

        StudentUser sampleStudent = new StudentUser("Asuna", "500");
        Rating sampleRating = new Rating(sampleStudent, 0.9F, sampleInstructors.get(0));
        List<Rating> ratingsList = List.of(sampleRating);

        CommentGraph sampleCommentGraph = new CommentGraph("Questions", "Wilson",
                sampleInstructors.get(0));
        List<CommentGraph> commentGraphList = List.of(sampleCommentGraph);

        builder.setCourse(calculus1);
        builder.setInstructors(sampleInstructors);
        builder.setRatings(ratingsList);
        builder.setCommentGraphs(commentGraphList);

        CoursePage coursePage = builder.getResult();
        assertEquals(coursePage.getCourse(), calculus1);
        assertEquals(coursePage.getCommentGraphs(), commentGraphList);
        assertEquals(coursePage.getRatings(), ratingsList);
        assertEquals(coursePage.getInstructors(), sampleInstructors);
    }
}