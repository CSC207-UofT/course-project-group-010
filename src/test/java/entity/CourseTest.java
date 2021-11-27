package entity;

import org.junit.Test;


import static org.junit.Assert.assertEquals;


public class CourseTest {

    @Test(timeout = 100)
    public void testtoString() {
        Course d = new Course("Software Design", "CSC207");
        d.setDescription("An introduction to software design and development concepts, methods, and tools using a statically-typed object-oriented programming language such as Java.");
        assertEquals(d.toString(), "Software Design" + "\n" + "CSC207" + "\n" + "An introduction to software design and development concepts, methods, and tools using a statically-typed object-oriented programming language such as Java.");
    }

    @Test(timeout = 100)
    public void testInit() {
        Course c = new Course("bad course", "MAT237");
        assertEquals(c.getName(), "bad course");
        assertEquals(c.getCode(), "MAT237");
        assertEquals(c.getDescription(), "There is currently no description available for this course");
    }

    @Test(timeout = 100)
    public void testDescriptionGetSet() {
        Course c = new Course("good course", "MAT137");
        c.setDescription("Rip Alfonso");
        assertEquals(c.getDescription(), "Rip Alfonso");
    }

}
