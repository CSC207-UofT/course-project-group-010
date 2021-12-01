//package entity;
//
//import exceptions.CommandNotAuthorizedException;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//
//public class RatingTest {
//
//    StudentUser sampleStudent;
//    Rating rating;
//
//    @Before
//    public void init() {
//        sampleStudent = new StudentUser("Sam", "4000");
//        rating = new Rating(sampleStudent, 1.0F, "Paul Gries");
//    }
//
//    @Test
//    public void setScoreOk() throws CommandNotAuthorizedException {
//        float desiredScore = 0.5F;
//        rating.setScore(desiredScore);
//        assertEquals(desiredScore, rating.getScore(), 0.001);
//    }
//
//    @Test(expected = CommandNotAuthorizedException.class)
//    public void setScoreFail() throws CommandNotAuthorizedException {
//        float desiredScore = 100.0F;
//        rating.setScore(desiredScore);
//    }
//}
