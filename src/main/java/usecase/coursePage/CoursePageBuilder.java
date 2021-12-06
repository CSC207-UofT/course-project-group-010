package usecase.coursePage;

import entity.CommentGraph;
import entity.Course;
import entity.Rating;
import interfaces.Builder;

import java.util.ArrayList;
import java.util.List;

public class CoursePageBuilder implements Builder {

    private Course course;
    private List<String> instructors;
    private List<Rating> ratings = new ArrayList<>();

    /**
     * A CoursePageBuilder. The Director class uses CoursePageBuilder to build a CoursePage object as needed.
     * A CoursePage object is always built with an empty array for its ratings as it will be updated via CourseManager.
     *
     * @param course set the Course object for the CoursePageBuilder.
     */
    @Override
    public void setCourse(Course course) {
        this.course = course;
    }


    /**
     * @param instructors set the instructors parameter for CoursePageBuilder.
     */
    @Override
    public void setInstructors(List<String> instructors) {
        this.instructors = instructors;
    }


    /**
     * @param course builds the courses for the CoursePage.
     */
    @Override
    public void buildCourse(List<String> course) {
        Course c = new Course(course.get(0), course.get(1));
        if (course.size() == 3) {
            c.setDescription(course.get(2));
        }
        //Set CoursePageBuilder's course to the above.
        this.setCourse(c);
    }


    /**
     * @return a CoursePage object by using the CoursePageBuilder's set variables.
     */
    public CoursePage getResult() {
        CoursePage cp = new CoursePage(this.course, this.instructors);
        cp.setRatings(this.ratings);
        cp.setCommentGraph(new CommentGraph("Comments", "Comments"));
        this.reset();
        return cp;
    }

    /**
     * reset this CoursePageBuilder so that it may be used again.
     * Currently, there is not a need necessarily to reset the CoursePageBuilder as when called on by Director,
     * the Course and Instructors will automatically reset. However, following the Builder design this may be needed
     * in the future for extendability purposes.
     */
    @Override
    public void reset() {
        this.ratings =  new ArrayList<>();
    }
}
