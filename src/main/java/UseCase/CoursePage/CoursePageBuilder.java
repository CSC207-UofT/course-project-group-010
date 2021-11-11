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

abstract class CoursePageBuilder {

    private int year;
    private List<Integer> years;
    private Course course; // course object.
    private List<Rating> ratings; // All ratings for this course.
    private String instructor; // Selected Instructor to view this course.
    private List<String> instructors; //list of instructors teaching the course.

    //Approach #1
    // We can allow infoMap parameters to be set in the CoursePageBuilder so that we can just constructPage in Director
    // without having to ask for a lot of list/hashmap objects in the constructCoursePage constructor.

    // Kevin has access to the database so he can make some infomap that contains all this information. We will need
    // to coordinate with his database to infomap conversions.

    //This will make Director really easy to use, something like:


    // CoursePageBuilder cpb = new CoursePageBuilder();

    // cpb.setInfoMap(InfoMap)

    // Director d = new Director();

    // d.constructCoursePage(cpb) for default page

    // d.constructCoursePage(cpb, filter_year, filter_instructor) for optional filtering.

    // ============================================================================================================= //


    //Approach #2
    //We don't worry about filtering at all, we just build a page and have kevin use CourseManager to filter through
    //everything. He would still have to create infoMaps and set them in the Builder.

    // ============================================================================================================= //


    //ISSUES TO RESOLVE:


    // 1) There is a lot of overlap between CourseManager and Director/Builder.

    // Say a user wants to view a coursepage, so we construct the default with no filters.
    // Say they now select the filtering option to filter by year/instructor/both.
    //Do we need to construct a completely new CoursePage after this user's input, or can CourseManager take care of it?




    //CASE 1: COURSE-MANAGER CAN HANDLE IT

    //If CourseManager can take care of it then we only need to construct a CoursePage once, provided with
    //infoMap relevant to the Course. We do not concern ourself with filtering since CourseManager can just
    //modify the CoursePage without making a new one.




    //CASE 2: DO NOT LET COURSE-MANAGER HANDLE IT, INSTEAD CONSTRUCT NEW PAGE FOR ANY MODIFICATION

    //Anytime we need to update/change info on the CoursePage, we have to construct a new one. This is very
    //inefficient so obviously not this.




    //CASE 3: MIDDLEGROUND

    //For certain actions, we use CourseManager to modify the coursepage such as updating after new user input.
    //For filtering or large-scale actions, we have to construct a new page CourseManager would have to modify too
    //many things.




    //CASE 4: MERGE THE CLASSES INTO ONE CLASS USED BOTH FOR CONSTRUCTION AND MODIFICATION OF A COURSE-PAGE

    // Merge CourseManager into Builder/Director. I think this can work, we can have the Director be responsible
    // for both big changes and small changes. We have to consult kevin on this since he deisnged it and is using
    // it in a lot of places.

    // ============================================================================================================= //


    // 2) We need to be able to represent empty Pages for when there are no user inputs. Save for Phase 2.

    //We need to take into account how this will run when we have the first User use the program.
    //Assume we have data for courses, years taught, and instructors who taught.
    //User logs in using their utor, we add them to the database and authenticate.

    //They check out a CoursePage that has no Ratings and no Comments. We need to be able to represent this. ***


    // Info map example;
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





}
