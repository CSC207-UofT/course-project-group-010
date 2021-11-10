package UseCase.CoursePage;

import Entity.CommentGraph;
import Entity.Course;
import Entity.InstructorUser;
import Entity.Rating;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class CoursePageBuilder implements Builder {

    private int year;
    private List<Integer> years;
    private Course course; // course object.
    private List<Rating> ratings; // All ratings for this course.
    private String instructor; // Selected Instructor to view this course.
    private List<String> instructors; //list of instructors teaching the course
    private HashMap<Integer, List<String>> instructorMap; //Hashmap of {year -> instructors} who taught this course at that year.


    @Override
    public void setYear(int year) {
        this.year = year;

    }

    @Override
    public void setYears(List<Integer> years) {
        this.years = years;

    }

    @Override
    public void setCourse(Course course){
        this.course = course;
    }

    @Override
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    @Override
    public void setInstructors(List<String> instructors){
        this.instructors = instructors;
    }

    @Override
    public void setInstructorMap(HashMap<Integer, List<String>> instructorMap){
        this.instructorMap = instructorMap;
    }




    @Override
    public void filterInstructor(String instructor){
        //Collect the ratings for all the years this instructor has taught this course.
        //Check If this instructor_filter has taught this course at any year
        List<Rating> newRating = new ArrayList<>();
        for(Rating r: this.ratings){
            if (r.instructor == instructor){
                newRating.add(r);
            }
        }
        this.setRatings(newRating);
        this.setInstructor(instructor);
        //need to add steps for filtering commentGraphs as well

        }


    @Override
    public void filterYear(int filter_year){
        List<Integer> years = this.years;

        if (years.contains(filter_year)){


            List<Rating> newRating = new ArrayList<>(); //New List to contain ratings at filter_year.

            //Collect all ratings left on this course at filter_year.

            for(Rating r: this.ratings){
                if (r.year == filter_year){
                    newRating.add(r);
                }
            }

            //Collect all instructors who taught this course at specified year.

            this.instructors = this.instructorsMap.get(filter_year); //List of instructors who have taught this course at filter_year.
            this.setRatings(newRating); //List of all ratings for this course provided at filter_year.
            this.setYear(filter_year); //Set CoursePage's Viewing_Year to filter_year.

            //need to add steps for filtering commentGraphs as well


        }

        else
        {
            // do not change the year
        }
    }


    public CoursePage getResult(){
        return new CoursePage(course, ratings, instructor);

    }



}
