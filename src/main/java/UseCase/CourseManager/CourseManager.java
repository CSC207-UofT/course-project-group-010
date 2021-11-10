package UseCase.CourseManager;

import Entity.Instructor;
import UseCase.CoursePage.CoursePage;

public class CourseManager {
    private CoursePage coursePage;

    public CourseManager (CoursePage coursePage)
    {
        this.coursePage = coursePage;
<<<<<<< Updated upstream
    }

    public fliterData(Instructor instructor, int year)
    {
        if (this.coursePage.getInstructors().contains(instructor) && this.coursePage.getYears().contains(year))
        {
            this.coursePage.setInstructor(instructor);
=======
        this.authDict = getDefaultAuthDict();

    }


    public void updateRating(int ratingNum, UserManager user) throws CommandNotAuthorizedException {
        // TODO check if rating is in the allowed range?
        Rating ratingToProcess = this.coursePage.getRating();
        // TODO change the code such that the casting below is not required, user.getUser() will not always be a student
        ratingToProcess.processRating(ratingNum, (StudentUser)user.getUser());
        this.coursePage.setRating(ratingToProcess);
    }


    // Modify coursePage's Ratings and CommentGraph so that they belong to the specified instructor.
    public void filterInstructor(String instructor){
        List<String> instructors = this.coursePage.getInstructors();

        if (instructors.contains(instructor)){
            List<Rating> newRating = new ArrayList<>();
            for (Rating r: this.coursePage.getRatings()){
                //If the rating was provided for the specified instructor, add to new rating list.
                if (r.getInstructor() == instructor){
                    newRating.add(r);
                }
            }
            //Set List of ratings to new list of ratings that belong under this instructor across all years
            this.coursePage.setRatings(newRating);
            this.coursePage.setInstructor(instructor); //set Page's instructor to specified instructor
        }

        else
        {
            //do not change the instructor
        }
    }

    // Modify coursePage's Ratings and CommentGraph so that they belong to the specified year.
    public void filterYear(int year){
        List<Integer> years = this.coursePage.getYears();

        if (years.contains(year)) {
            //Set coursePage's year to the specified year.
>>>>>>> Stashed changes
            this.coursePage.setYear(year);
            List<Rating> newRating = new ArrayList<>();
            for (Rating r : this.coursePage.getRatings()) {
                //If this rating was provided under the specified year, add to new rating list.
                if (r.getYear() == year) {
                    newRating.add(r);
                }
            }
            this.coursePage.setRatings(newRating);
            this.coursePage.setYear(year);

        }
    }

    public leaveReview(Student student)
    {
        // if Student is authorized
        // then compute new review
    }
}
