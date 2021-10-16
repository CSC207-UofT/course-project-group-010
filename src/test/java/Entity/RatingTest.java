package Entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RatingTest {

    @Test(timeout=100)
    public void testInit() {
        Rating r = new Rating();
        r.processRating(50);
        r.processRating(100);
        assertEquals(r.getRating(), 75);
    }
}
