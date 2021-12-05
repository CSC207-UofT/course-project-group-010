package usecase.coursePage;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CoursePageBuilderTest {
    public CoursePage cp;

    @Before
    public void setup() {
        List<String> course = List.of("Math", "MAT137", "description");
        List<String> instructor = List.of("Alfonso");
        CoursePageBuilder cpb = new CoursePageBuilder();
        Director d = new Director();

        // Using the builder to build things
        d.constructCoursePage(cpb, course, instructor);
        cp = cpb.getResult();
    }

    @Test
    public void testBasic() {
        assertEquals("MAT137", cp.getCourse().getCode());
        assertEquals(1, cp.getInstructors().size());
        assertEquals(0, cp.getRatings().size());
        assertEquals(0, cp.getAverageScore(), 1);
        assertNotEquals(null, cp.getCommentGraph());
        assertNotEquals(null, cp.getThread());
    }

}