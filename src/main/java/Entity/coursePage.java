package Entity;

import java.util.ArrayList;
import java.util.List;

/** Junhyuk Park : This class can be in use case classes layer??
 *  Implementation looks awkward here.
 */
public class coursePage {
    Course course;
    List<Rating> ratings;
    InstructorUser instructor;
    int year;
    int filterYear;
    InstructorUser filterInstructor;
    Review mainComment;

    public coursePage(Course course, List<Rating> ratings, InstructorUser instructor, int year,
               int filterYear, InstructorUser filterInstructor) {
        this.course = course;
        this.ratings = ratings;
        this.instructor = instructor;
        this.year = year;
        this.filterYear = filterYear;
        this.filterInstructor = filterInstructor;
    }


    @Override
    public String toString() {
        String result = this.course.getName() + "\n" + "Reviews :\n";
        for(Rating i : this.ratings) {
            result += i.toString() + "\n";
        }
        return result;
    }

    Course getCourse() {
        return this.course;
    }

    void setCourse(Course course) {
        this.course = course;
    }

    String getInfo() {
        // TODO implement
        return "";
    }

    void setInfo() {
        // TODO implement
    }

    double getRating() {
        // TODO implement
        return 0.0;
    }

    void setRatings() {
        // TODO implement
    }

    void setMainComment(Review review) {
        // TODO implement
        this.mainComment = review;
    }

    Review getMainComment() {
        // TODO implement;
        return this.mainComment;
    }

    public boolean addRating(Rating rating) {
        if(rating.getCourse().equals(this.getCourse())) {
            this.ratings.add(rating);
            return true;
        }
        return false;
    }
}
