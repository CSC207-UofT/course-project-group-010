package UseCase.CoursePage;

import Entity.*;
import UseCase.CourseManager.CourseManager;

import java.util.*;


//Kevin Should not require to instantiate any objects to construct, just accept required parameters to make the object
//within the builder.

public class Director {
    private Builder builder; // builder for builder.
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

    // Director constructor version 1
    // I think this version is better.



    Director(Builder builder) {
        this.builder = builder;
    }
//    public void constructCoursePage(HashMap<String, Object> basicConfiguration) {
//
//        this.builder.setBasicConfiguration(basicConfiguration);
//        this.builder.build();
//    }

    // constructCoursePage version 2
    // Let builder allow to filter its filterConfiguration
//    public void constructCoursePage(HashMap<String, Object> basicConfiguration, HashMap<String, Object> filterConfiguration) {
//        //
//        this.builder.setBasicConfiguration(basicConfiguration);
//        this.builder.setFilterConfiguration(filterConfiguration);
//        this.builder.build();
//    }


    // Todo: Assumptions: CourseData exists so Course object can always be created.
    //                    Always know which instructors taught what Course from database.
    //
    //  Todo: Combinations of construction:
    //        Make course page given CourseData and instructors.
    //        Make course page given CourseData and instructors AND Ratings.
    //        Make course page given CourseData and instructors AND Ratings AND CommentGraphs.


    // ToDo : director.constructCoursePage MUST receive the following at minimum:
    //        1) A list of strings containing Course info in this format: [CourseName, CourseCode, Optional(CourseDescription)]
    //           You can omit CourseDescription if you don't have it.
    //        2) A list of instructors who have taught this course.


    //Make CoursePage with only Course info and list of instructors.
    public void constructCoursePage(CoursePageBuilder cpb, List<String> course, List<String> instructors) {

        Course c = new Course(course.get(0), course.get(1));
        if(instructors.size() == 3){
            c.setDescription(course.get(2));
        }
        cpb.setCourse(c);
        cpb.setInstructors(instructors);
    }


    // ToDo : This constructor MUST receive the following at minimum:
    //        1) A list of strings containing Course info in this format: [CourseName, CourseCode, Optional(CourseDescription)]
    //           You can omit CourseDescription if you don't have it.
    //        2) A list of instructors who have taught this course.
    //        3) A list of strings containing Rating info in this format: List<List<String>>
    //           [ [String<StudentUser DisplayName>, String<StudentUser ID>, String<rating score>, String<courseCode>, String<instructor>], <-- rating1
    //             [String<StudentUser DisplayName>, String<StudentUser ID>, String<rating score>, String<courseCode>, String<instructor>],  <-- rating2
    //             ....
    //             [String<StudentUser DisplayName>, String<StudentUser ID>, String<rating score>, String<courseCode>, String<instructor>]] <-- ratingN
    //        Director will convert from string to required object type to initialize required object.
    //Make CoursePage with Course info , list of instructors, and ratings.
    public void constructCoursePage(CoursePageBuilder cpb, List<String> course, List<String> instructors,
                                    List<List<String>> ratings) {

        Course c = new Course(course.get(0), course.get(1));
        if(instructors.size() == 3){
            c.setDescription(course.get(2));
        }
        cpb.setCourse(c);
        cpb.setInstructors(instructors);

        //Creating a list of Rating of objects to be assigned to CoursePage.

        List<Rating> cp_ratings = new ArrayList<>(); //Empty Array list to add Rating objects to.

        for(List<String> l: ratings) {

            //Create StudentUser object
            StudentUser s = new StudentUser(l.get(0), l.get(1));

            //Convert score from string to float.
            float f = Float.parseFloat(l.get(2));

            //Create Rating object
            Rating r = new Rating(s, f, l.get(3), l.get(4));

            //Add Rating object to cp_ratings.
            cp_ratings.add(r);
        }

        //Set CoursePageBuilder's ratings to the above.
        cpb.setRatings(cp_ratings);
        }



    // ToDo : This constructor MUST receive the following at minimum:
    //        1) A list of strings containing Course info in this format: [CourseName, CourseCode, Optional(CourseDescription)]
    //           You can omit CourseDescription if you don't have it.
    //        2) A list of instructors who have taught this course.
    //        3) A list of strings containing Rating info in this format: List<List<String>>
    //           [ [String<StudentUser DisplayName>, String<StudentUser ID>, String<rating score>, String<courseCode>, String<instructor>], <-- rating1
    //             [String<StudentUser DisplayName>, String<StudentUser ID>, String<rating score>, String<courseCode>, String<instructor>],  <-- rating2
    //             ....
    //             [String<StudentUser DisplayName>, String<StudentUser ID>, String<rating score>, String<courseCode>, String<instructor>]] <-- ratingN
    //        Director will convert from string to required object type to initialize required object.
    //       4) A list of all main comments
    //       5) A hashmap of mainComments mapped to a list containing mainCommentType, mainCommenterName, and instructor.
    //          { [List<String> mainComments: [String mainCommentType, String mainCommenterName, String<Instructor1>],
    //            [List<String> mainComments: [String mainCommentType, String mainCommenterName, String<Instructor2> ],
    //           ..........
    //           [ List<String> mainComments, String mainCommentType, String mainCommenterName, String<InsturctorN> ] ]
    //       Have to use Hashmap. If using List to contain all of this, they won't have the same type, have to cast everywhere.

    public void constructCoursePage(CoursePageBuilder cpb, List<String> course, List<String> instructors,
                                    HashMap<List<String>, List<String>> commentGraphs) {

        Course c = new Course(course.get(0), course.get(1));
        if(instructors.size() == 3){
            c.setDescription(course.get(2));
        }
        cpb.setCourse(c);
        cpb.setInstructors(instructors);

        //Creating a list of Rating of objects to be assigned to CoursePage.

        List<Rating> cp_ratings = new ArrayList<>(); //Empty Array list to add Rating objects to.

        for(List<String> l: ratings) {

            //Create StudentUser object
            StudentUser s = new StudentUser(l.get(0), l.get(1));

            //Convert score from string to float.
            float f = Float.parseFloat(l.get(2));

            //Create Rating object
            Rating r = new Rating(s, f, l.get(3), l.get(4));

            //Add Rating object to cp_ratings.
            cp_ratings.add(r);
        }

        //Set CoursePageBuilder's ratings to the above.
        cpb.setRatings(cp_ratings);

        List<CommentGraph> cp_graph = new ArrayList<>();
        Set<List<String>> keys = commentGraphs.keySet();

        for(List<String> k: keys){

            List<String> g_rest = commentGraphs.get(k);
            CommentGraph cg = new CommentGraph(k, g_rest.get(0), g_rest.get(1), g_rest.get(2), g_rest.get(3));

        }



            //Add Rating object to cp_ratings.
            cp_ratings.add(r);
        }



        }
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


