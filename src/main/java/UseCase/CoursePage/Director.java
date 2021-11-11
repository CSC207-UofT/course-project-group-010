package UseCase.CoursePage;

import Entity.Course;
import Entity.InstructorUser;
import Entity.Rating;
import UseCase.CourseManager.CourseManager;

import java.util.List;
import java.util.Optional;


//Kevin Should not require to instantiate any objects to construct, just accept required parameters to make the object
//within the builder.

public class Director {
    private int year;
    private InstructorUser instructor;



    // CPB cpb = new CPB();
    // Director d = new Director();
    // d.constructCoursePage(cpb, 'mat137', 'mathematics pro', 'this is math', 3, 4, 5,)
    // CoursePage cp = cpb.getResult()
    // Construct CoursePage WITHOUT filters (default CoursePage)

    // We want kevin to NOT have to make any entities when calling on d.constructCoursePage().
    // He may give a List["mat137", "Mathematics", "this course is..."] for course, another list or hashmap for
    // commentGraphs and Ratings, and we'll have to make those objects.
    // The CoursePageBuilder should handle the above, so in Director for example, it would something like:


    // public void constructCoursePage(CoursePageBuilder cpb, List<String> course, List<List<String>>> CommentGraph,
    //                                   etc)) {
    // cpb.setCourse(course) <--- cpb takes list of string with info required to make a course object, sets it for CP.
    // cpb.setCommentGraph(CommentGraph)

    //We'll figure out filtering later.

    // ALSO find optimal minimum requirement for initializing a page. How many parameters should the CoursePage constructor
    // need in order to be constructed? Definitely a Course, maybe ratings? idk up to you.


    public void constructCoursePage(CoursePageBuilder cpb, Course course, List<Rating> ratings) {
    }


    //Construct CoursePage filtered by BOTH year and instructor

//    public void constructCoursePage(CoursePageBuilder cpb, Course course, List<Rating> ratings,
//                                    Integer filter_year, String instructor_filter) {
//        cpb.setCourse(course);
//        cpb.setRatings(ratings);
//        cpb.filterInstructor(instructor_filter);
//        cpb.filterYear(filter_year);
//        cpb.getResult();
//
//
//    }
//
//    //Construct CoursePage filtered ONLY by year
//    public void constructCoursePage(CoursePageBuilder cpb, Course course, List<Rating> ratings,
//                                    Integer filter_year) {
//
//        cpb.setCourse(course);
//        cpb.setRatings(ratings);
//        cpb.filterYear(filter_year);
//
//
//
//
//    }
//
//
//    //Construct CoursePage filtered ONLY by instructor
//    public void constructCoursePage(CoursePageBuilder cpb, Course course, List<Rating> ratings, String instructor_filter) {
//    }







}


