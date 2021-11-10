package UseCase.CoursePage;

import Entity.Course;
import Entity.Instructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CoursePage {
    private Course course; // course object
<<<<<<< Updated upstream
    private Rating rating; // rating object
    private Optional<Instructor> instructor; // optional default instructor
    private Optional<Integer> year; // optional default year
    private List<Instructor> instructors; //list of instructors teaching the course
    private List<Integer> years; // list of years the course was taught

    public CoursePage (Course course, Rating rating, List<Instructor> instructors, List<Integer> years){
=======
    private List<Rating> ratings; // List of ratings under this course; rating object
    private String instructor; // optional default instructor, WAS // Optional<InstructorUser>
    private List<String> instructors; //list of instructors teaching the course
    private CommentGraph commentGraph;

    public CoursePage(List<Integer> years, Course course, List<Rating> ratings,
                      List<String> instructors, CommentGraph commentGraph){
>>>>>>> Stashed changes
        // reverse sort the list of years so that current year is in front
        Collections.reverse(years);
        this.course = course;
<<<<<<< Updated upstream
        this.rating = rating;
        this.instructors = instructors;
        this.years = years;
=======
        this.ratings = ratings;
        this.instructors = instructors;
        //this.years = years;
        //this.commentGraph = commentGraph;
>>>>>>> Stashed changes

        // for now the default instructor will be the first one found in the list, later we will sort the list of
        // instructors by overriding the CompareTo method and comparing their names.

        // if there is at least one instructor
        if (instructors.size() > 0)
        {
            this.instructor = instructors.get(0);
        }

        // if there are no instructors
        else
        {
            this.instructor = "No Instructors";
        }

        if (years.size() > 0)
        {
            this.year = Optional.ofNullable(years.get(0));
        }

        else
        {
            this.year = Optional.empty();
        }
    }

    // get information from course Page
    public Course getCourse(){
        return this.course;
    }

    public List<Rating> getRatings(){
        return this.ratings;
    }

<<<<<<< Updated upstream
    public List<Instructor> getInstructors(){
=======
    public List<String> getInstructors(){
>>>>>>> Stashed changes
        return this.instructors;
    }

    public List<Integer> getYears(){
        return this.years;
    }

<<<<<<< Updated upstream
    public Optional<Instructor> getInstructor(){
=======
    public String getInstructor(){
>>>>>>> Stashed changes
        return this.instructor;
    }

    public int getYear(){
        return this.year;
    }

<<<<<<< Updated upstream
    public void setInstructor(Instructor instructor){
        this.instructor = Optional.ofNullable(instructor);
=======
    public void setInstructor(String instructor){
        this.instructor = instructor;
>>>>>>> Stashed changes
    }

    public void setYear(int year){
        this.year = year;
    }

<<<<<<< Updated upstream
=======
    public void setRatings(List<Rating> ratings){
        this.ratings = ratings;
    }
>>>>>>> Stashed changes

}
