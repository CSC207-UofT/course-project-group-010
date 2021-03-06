package entity;

import exceptions.ArgumentException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class RatingTest {

    StudentUser sampleStudent;
    Rating rating;

    @Before
    public void init() throws ArgumentException {
        sampleStudent = new StudentUser("Sam", "4000");
        sampleStudent.setProgramDetail("DATA SCIENCE");
        rating = new Rating(sampleStudent, 10.0);
    }

    @Test
    public void getScoreOk() {
        double desiredScore = 10.0;
        assertEquals(desiredScore, rating.getScore(), 0.001);
    }

    @Test
    public void getPoST() {
        assertEquals("DATA SCIENCE", rating.getRaterProgramOfStudy());
    }

    @Test
    public void testToString() {
        assertEquals("10.0", rating.toString());
    }
}
