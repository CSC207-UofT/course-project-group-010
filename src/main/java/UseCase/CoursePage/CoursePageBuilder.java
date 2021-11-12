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

    private Course course; // course object. Empty string if not assigned.
    private List<String> instructors; // List containing empty string if not assigned.
    private List<Rating> ratings = null; // List of all ratings left for this course across all instructors.
    private List<CommentGraph> commentGraphs = null; //List of all commentGraphs for this course across all instructors.



    @Override
    public void setCourse(Course course){
        this.course = course;
    }

    @Override
    public void setInstructors(List<String> instructors){
        this.instructors = instructors;
    }

    @Override
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }


    @Override
    public void setCommentGraphs(List<CommentGraph> cg){
        this.commentGraphs = cg;
    }

    @Override
    public void reset(){
        this.ratings= null;
        this.commentGraphs = null;
    }

    public CoursePage getResult() {
        CoursePage cp = new CoursePage(this.course, this.instructors);
        //If Director takes info regarding ratings/cg in the constructPage constructor, assign values in CoursePage.
        if (this.ratings != null) {
            cp.setRatings(this.ratings);
        }
        if (this.commentGraphs != null) {
            cp.setCommentGraphs(this.commentGraphs);
        }
        this.reset();
        return cp;
    }




// PHASE 2 BELOW


//    public void setRelativeScore(float programRelativeScore) {
//        this.programRelativeScore = programRelativeScore;
//    }

//  private float programRelativeScore;  // Phase 2



//    @Override
//    public void setInstructorMap(HashMap<Integer, List<String>> instructorMap){
//        this.instructorMap = instructorMap;
//    }
//    private int year;
//    private List<Integer> years;
//    private HashMap<Integer, List<String>> instructorMap; //Hashmap of {year -> instructors} who taught this course at that year.

//
//    @Override
//    public void setYear(int year) {
//        this.year = year;
//
//    }
//
//    @Override
//    public void setBasicConfiguration(HashMap<String, Object> basicConfiguration) {
//        this.basicConfiguration = basicConfiguration;
//    }
//
//    @Override
//    public void setFilterConfiguration(HashMap<String, Object> filterConfiguration) {
//        this.filterConfiguration = filterConfiguration;
//    }
//
//    @Override
//    public void setYears(List<Integer> years) {
//        this.years = years;
//
//    }

//    @Override
//    public void filterInstructor(String instructor){
//        //Collect the ratings for all the years this instructor has taught this course.
//        //Check If this instructor_filter has taught this course at any year
//        List<Rating> newRating = new ArrayList<>();
//        for(Rating r: this.ratings){
//            if (r.getInstructor() == instructor){
//                newRating.add(r);
//            }
//        }
//        this.setRatings(newRating);
//        this.setInstructor(instructor);
//        //need to add steps for filtering commentGraphs as well
//
//        }
//
//
//    @Override
//    public void filterYear(int filter_year){
//        List<Integer> years = this.years;
//
//        if (years.contains(filter_year)){
//
//
//            List<Rating> newRating = new ArrayList<>(); //New List to contain ratings at filter_year.
//
//            //Collect all ratings left on this course at filter_year.
//
//            for(Rating r: this.ratings){
//                if (r.getYear() == filter_year){
//                    newRating.add(r);
//                }
//            }
//
//            //Collect all instructors who taught this course at specified year.
//
//            this.instructors = this.instructorsMap.get(filter_year); //List of instructors who have taught this course at filter_year.
//            this.setRatings(newRating); //List of all ratings for this course provided at filter_year.
//            this.setYear(filter_year); //Set CoursePage's Viewing_Year to filter_year.
//
//            //need to add steps for filtering commentGraphs as well
//
//
//        }
//
//        else
//        {
//            // do not change the year
//        }
//    }

//    public void build() {
//        // We need to bring InstructorUser object to construct CoursePage or change how coursepage is constructed.
//        this.coursePage = new CoursePage(this.course, this.ratings, this.instructors, this.years);
//    }

//    @Override
//    public CoursePage getResult(){
//        return this.coursePage;
//        return new CoursePage(course, ratings, instructor);
//    }


// We can have basic configuration hashmap under builder, basic configuration that directly
// takes the information of all ratings, instructors and store them inside and used
// to default page and
// filter configuration hashmap that can filter year and instructors.
//    private HashMap<String, Object> basicConfiguration;
//    private HashMap<String, Object> filterConfiguration;

}
