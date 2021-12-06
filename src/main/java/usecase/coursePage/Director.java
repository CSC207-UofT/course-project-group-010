package usecase.coursePage;


import java.util.List;


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
     * This is the Director method for constructing a CoursePage with only Course info and a list of instructors.
     * It denotes the minimum amount of data we need to make a CoursePage.
     *
     * @param cpb         a CoursePageBuilder object that will build CoursePage as needed.
     * @param course      A list of strings containing Course info in this format:
     *
     *                    [CourseName, CourseCode, Optional(CourseDescription)]
     *                    CourseDescription may be omitted if not available.
     *
     * @param instructors A list of instructors who have taught/are teaching this course.
     */
    public void constructCoursePage(CoursePageBuilder cpb,
                                    List<String> course,
                                    List<String> instructors) {

        cpb.buildCourse(course);
        cpb.setInstructors(instructors);
    }
}


