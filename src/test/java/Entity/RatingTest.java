package Entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class RatingTest {
    @Test(timeout=100)
    public void testGetRating() {
        Rating r = new Rating((float) 8.0/10, "CSC207", "Paul Gries", 2021);
        float expectedScore = 0.8F;
        assertEquals(r.getScore(), expectedScore, 0.01);
    }
}


//package Entity;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class RatingTest {
//
//    @Test(timeout=100)
//    public void testInit() {
//        Rating r = new Rating(new Course("CSC207", "write code"));
//        r.processRating(50, new StudentUser("thelegend27", "noah@gmail.com", "CS"));
//        r.processRating(100,new StudentUser("Ben", "benten@gmail.com", "English"));
//        assertEquals(java.util.Optional.ofNullable(r.getRating()), 75);
//    }
//}
