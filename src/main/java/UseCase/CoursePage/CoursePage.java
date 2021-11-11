package UseCase.CoursePage;

import Entity.Course;
import Entity.InstructorUser;
import Entity.Rating;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

// TODO look at Commands.CheckoutCommand. The constructor for this is way to complex. Not going to work.
// also the constructor forces any class that constructs this to access entity classes, which is bad.
public class CoursePage {
    private Course course; // course object
    private Rating rating; // rating object
    private Optional<InstructorUser> defaultInstructor; // optional default instructor
    private Optional<Integer> defaultYear; // optional default year
    private List<InstructorUser> instructors; //list of instructors teaching the course
    private List<Integer> years; // list of years the course was taught

    public CoursePage(Course course, Rating rating, List<InstructorUser> instructors, List<Integer> years) {
        // reverse sort the list of years so that current year is in front
        Collections.reverse(years);
        this.course = course;
        this.rating = rating;
        this.instructors = instructors;
        this.years = years;

        // for now the default instructor will be the first one found in the list, later we will sort the list of
        // instructors by overriding the CompareTo method and comparing their names.

        // if there is at least one instructor
        if (instructors.size() > 0) {
            this.defaultInstructor = Optional.ofNullable(instructors.get(0));
        }

        // if there are no instructors
        else {
            this.defaultInstructor = Optional.empty();
        }

        if (years.size() > 0) {
            this.defaultYear = Optional.ofNullable(years.get(0));
        } else {
            this.defaultYear = Optional.empty();
        }
    }

    // get information from course Page
    public Course getCourse() {
        return this.course;
    }

    public Rating getRating() {
        return this.rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<InstructorUser> getInstructors() {
        return this.instructors;
    }

    public List<Integer> getYears() {
        return this.years;
    }

    public Optional<InstructorUser> getDefaultInstructor() {
        return this.defaultInstructor;
    }

    public void setDefaultInstructor(InstructorUser defaultInstructor) {
        this.defaultInstructor = Optional.ofNullable(defaultInstructor);
    }

    public Optional<Integer> getDefaultYear() {
        return this.defaultYear;
    }

    public void setDefaultYear(int defaultYear) {
        this.defaultYear = Optional.ofNullable(defaultYear);
    }
}
