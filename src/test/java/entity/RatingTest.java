package entity;

import exceptions.CommandNotAuthorizedException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class RatingTest {

    StudentUser sampleStudent;
    Rating rating;

    @Before
    public void init() {
        sampleStudent = new StudentUser("Sam", "4000");
        rating = new Rating(sampleStudent, 10.0);
    }

    @Test
    public void getScoreOk() throws CommandNotAuthorizedException {
        double desiredScore = 10.0;
        assertEquals(desiredScore, rating.getScore(), 0.001);
    }

    @Test
    public void getPoST() {
        StudentUser a = new StudentUser("Kevin", "kev123");
        a.setProgramDetail("DATA SCIENCE");
        Rating r = new Rating(a, 10.0);
        assertEquals("DATA SCIENCE", r.getRaterProgramOfStudy());
    }

    @Test
    public void testToString() {
        assertEquals("10.0", rating.toString());
    }
}
