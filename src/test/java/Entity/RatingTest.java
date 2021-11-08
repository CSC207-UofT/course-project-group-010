package Entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class RatingTest{
    @Test(timeout=100)
    public void testgetRating() {
        Rating r = new Rating(new Course("bad course", "MAT237"));
        Integer a = 5;
        assertEquals(r.getRating(),a);
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
