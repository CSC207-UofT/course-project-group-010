package UseCase;

import entity.*;
import java.util.*;


public class Director {
    /**
     * A director class to build pages as the client wishes. Currently only constructs CoursePage, but can be
     * extended to construct any new types of pages in the future.
     *
     *  Example Usage:
     *      1) Create CoursePageBuilder and Director objects first.
     *
     *               CoursePageBuilder cpb = new CoursePageBuilder();
     *               Director d = new Director();
     *
     *      2) Gather any necessary data required by the constructCoursePage constructor.
     *
     *      3) Input data into Director.constructCoursePage.
     *
     *               d.constructCoursePage(cpb, List<String> course, List<String> instructors)
     *               CoursePage cp = cpb.getResult();
     *
     *      After cpb.getResult(), cpb resets. Therefore the same CoursePageBuilder object can be called by the director
     *      again with different inputs.
     *
     *
     *
     * This is the constructor for constructing a CoursePage with only Course info and a list of instructors.
     * It denotes the minimum amount of data we need to make a CoursePage.
     *
     * @param cpb         a CoursePageBuilder object that will build CoursePage as needed.
     * @param course      A list of strings containing Course info in this format:
     *                    <p>
     *                    [CourseName, CourseCode, Optional(CourseDescription)]
     *                    CourseDescription may be omitted if not available.
     * @param instructors A list of instructors who have taught/are teaching this course.
     */
    //Make CoursePage with only Course info and list of instructors.
    public void constructCoursePage(CoursePageBuilder cpb,
                                    List<String> course,
                                    List<String> instructors) {

        cpb.buildCourse(course);
        cpb.setInstructors(instructors);
    }

    /**
     * This is the constructor for constructing a CoursePage with course, instructors, ratings, and commentGraphs.
     *
     * @param cpb         a CoursePageBuilder object that builds CoursePage as needed.
     * @param course      A list of strings needed to construct a Course object.
     * @param instructors A list of instructors who have taught/are teaching this course.
     * @param ratings     A list of of list of strings, containing Rating info in the format of List<List<String>> .
     *                    Example;
     *
     * [ [String<StudentUser1 DisplayName>, String<StudentUser1 ID>, String<rating score>, String courseCode],  <-- rating1
     *
     *   [String<StudentUser2 DisplayName>, String<StudentUser2 ID>, String<rating score>, String courseCode],  <-- rating2
     *
     *   [String<StudentUser3 DisplayName>, String<StudentUser3 ID>, String<rating score>, String courseCode],  <-- rating3
     *
     *                                                      ......
     *
     *   [String<StudentUserN DisplayName>, String<StudentUserN ID>, String<rating score>, String courseCode] ] <-- ratingN
     *
     * @param typeName    A list containing the rootType and rootName of the commentGraph for this page.
     *
     * @param initialComments A hashmap containing the necessary info to create commentGraph for this page.
     *
     *
     */
    //Make CoursePage with Course info , list of instructors, and ratings.
    public void constructCoursePage(CoursePageBuilder cpb,
                                    List<String> course,
                                    List<String> instructors,
                                    List<List<String>> ratings,
                                    List<String> typeName,
                                    HashMap<String, List<String>> initialComments) {

        //Use previous constructor to avoid duplicate code; Course and Instructors are set in CoursePage.
        constructCoursePage(cpb, course, instructors);
        cpb.buildRatings(ratings);
        cpb.buildCommentGraph(typeName, initialComments);
    }






//    /**
//     * This is the constructor for constructing a CoursePage with Course info , list of instructors, and CommentGraphs.
//     *
//     * @param cpb           A CoursePageBuilder object that builds CoursePage as needed.
//     * @param course        A list of strings needed to construct a Course object.
//     * @param instructors   A list of instructors who have taught/are teaching this course.
//     * @param commentGraphs A hashmap of mainComments mapped to a list containing mainCommentType and mainCommenterName
//     *                      <p>
//     *                      Example:
//     *                      <p>
//     *                      {  [List<String> mainComments: [String mainCommentType, String mainCommenterName],
//     *                      <p>
//     *                      [List<String> mainComments: [String mainCommentType, String mainCommenterName,
//     *                      <p>
//     *                      .............
//     *                      <p>
//     *                      [List<String> mainComments, String mainCommentType, String mainCommenterName] }
//     */
//    //Have to use Hashmap. If using List to contain all of this, they won't have the same type, have to cast everywhere.
//    public void constructCoursePage(CoursePageBuilder cpb,
//                                    List<String> course,
//                                    List<String> instructors,
//                                    List<String> typeName,
//                                    HashMap<String, List<String>> initialComments) {
//
//        //Use previous constructor to avoid duplicate code; CoursePage's Course, Instructors, and Ratings are now set.
//        constructCoursePage(cpb, course, instructors);
//        cpb.buildCommentGraph(typeName, initialComments);
//    }







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

//    //Create empty arraylist to contain all commentGraph objects.
//    List<CommentGraph> cp_commentGraph = new ArrayList<>();
//
//    //Collect all keys(Main Comments Lists) in hashmap.
//    Set<List<String>> keys = commentGraphs.keySet();
//
//    //Iterate over all Main Comments
//        for(List<String> main_comment: keys){
//
//        //Get the mainCommentType, mainCommenterName, and instructor associated with this main_comment in Hashmap.
//        List<String> g_rest = commentGraphs.get(main_comment);
//
//        //Create CommentGraph with the key (main_comment) and the rest of the required info.
//
//        //                                Main Comment  mainCommentType, mainCommenterName, instructor
//        CommentGraph cg = new CommentGraph(main_comment, g_rest.get(0), g_rest.get(1), g_rest.get(2));
//
//        //add this commentGraph to list of commentGraphs to be assigned to CoursePage.
//        cp_commentGraph.add(cg);
//    }

    //Set CommentGraph in CoursePage to above.
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


