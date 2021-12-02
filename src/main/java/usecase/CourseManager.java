package usecase;

import constants.ProgramConstants;
import constants.UserType;
import entity.CommentGraph;
import entity.Course;
import entity.Rating;
import entity.StudentUser;
import exceptions.ArgumentException;
import interfaces.IDBSaveable;
import interfaces.IReadModifiable;
import interfaces.IUser;
import usecase.coursePage.CoursePage;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The CourseManager modifies the information in CoursePage. Reflecting relative scores depending
 * on student's program.
 *
 * Example usage:
 * CourseManager courseManager = new CourseManager(coursePage);
 * courseManager.filterInstructor("A");
 * courseManager.defaultCoursePage();
 * courseManager.updateCommentVote("ABCD", true);
 * courseManager.updateRating(5, user);
 */

@SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
public class CourseManager implements IReadModifiable, IDBSaveable, Serializable {

    private Course course;
    private List<Rating> ratings;
    private CoursePage coursePage;
    private Map<UserType, List<String>> authDict;

    /**
     * Constructor of CourseManager.
     *
     * @param coursePage CoursePage that CourseManager is going to modify.
     */
    public CourseManager(CoursePage coursePage) {
        this.authDict = getDefaultAuthDict();
        this.course = coursePage.getCourse();
        this.ratings = coursePage.getRatings();
        this.coursePage = coursePage;
    }

    public CommentManager getCommentSection() {
        return this.coursePage.getThread();
    }

    /**
     * Add rating to the CoursePage.
     * @param ratingNum score that a user wants to leave.
     * @param user user who leaves a rating.
     */
    public void addRating(float ratingNum, IUser user) throws ArgumentException {
        if (ratingNum > 10 || ratingNum < 0) {
            throw new ArgumentException("Rating must be between 0 and 10");
        }
        List<Rating> ratingList = this.coursePage.getRatings();
        if (ratingList == null) {
            ratingList = new ArrayList<>();
            this.coursePage.setRatings(ratingList);
            this.ratings = this.coursePage.getRatings();
        }

        Rating r = new Rating(user, ratingNum);
        ratingList.add(r);
        this.updateAvgScore();

    }

    // TODO this is never used except in tests(and now I removed it from tests). Consider deleting.
    /**
     * Updates a rating that a current user already left.
     *
     * @param ratingNum A rating score that a user wants to change to. (0 <= ratingNum <= 1)
     * @param user      A user who wants to change its rating score.
     * @throws Exception When rating cannot be updated.
     */
//    public void updateRating(float ratingNum, IUser user) throws Exception {
//        for (Rating r : coursePage.getRatings()) {
//            if (r.getRater().getID().equals(user.getID())) {
//                r.setScore(ratingNum);
//                this.updateAvgScore();
//                return;
//            }
//        }
//        throw new Exception("Rating is not updated");
//    }


    /**
     * Change current relative score of CoursePage.
     *
     * @param program string of program name of raters' that will be filtered.
     */
    // TODO only used in relativerating command, if we remove that then remove this.
    // also I made rating take IUsers and not StudentUsers. That is way too specific and will fuck up SOLID principles.
    // I changed the way rater.getPoST is implemented so now it works with IUser.
    public float getRelativeRating(String program) {
        List<Rating> filteredRatings = this.ratings.stream().filter(
                r -> r.getRaterProgramOfStudy().equals(program)).collect(Collectors.toList());
        if(filteredRatings.isEmpty()) {
            return -1;
        }
        float total = 0;
        for(Rating r : filteredRatings) {
            total += r.getScore();
        }
        return total / filteredRatings.size();

    }

    public Map<String, Float> getRelativeRatings() {
        Map<String, Float> retMap = new HashMap<>();
        ProgramConstants pc = new ProgramConstants();
        for (String program : pc.getPossiblePrograms()) {
            if (getRelativeRating(program) != -1) {
                retMap.put(program, getRelativeRating(program));
            }
        }
        return retMap;
    }

    // Getters

    // TODO getCoursePage is used once in the program(by filterinstructorcommand, which is not in use anymore), and otherwise is only called in tests. Consider deleting
    /**
     * Get current coursePage of courseManager.
     *
     * @return Current coursePage.
     */
    public CoursePage getCoursePage() {
        return this.coursePage;
    }

    // TODO getComment is only called in tests, consider deleting
//    public CommentManager getComment() throws ArgumentException {
//        if (this.coursePage.getCommentGraph() == null) {
//            throw new ArgumentException("No comment section. try starting one[startcomment]!");
//        } else {
//            return new CommentManager(this.coursePage.getCommentGraph());
//        }
//    }

    // TODO this is only called in tests, consider deleting
//    public List<String> getRatingPrograms() {
//        HashSet<String> ratingPrograms = new HashSet<>();
//        this.ratings.stream().forEach(r -> ratingPrograms.add(r.getRaterProgramOfStudy()));
//        return ratingPrograms.stream().collect(Collectors.toList());
//    }

    /**
     * Get Data about courseManager in HashMap.
     *
     * @return HashMap<name, data>.
     */
    @Override
    public HashMap<String, Object> getData() {
        Object avgScore = this.coursePage.getAverageScore() == -1 ? "No ratings" : this.coursePage.getAverageScore();
        HashMap<String, Object> infoMap = new HashMap<>();
        infoMap.put("courseName", this.coursePage.getCourse().getName());
        infoMap.put("courseCode", this.coursePage.getCourse().getCode());
        infoMap.put("courseDescription", this.coursePage.getCourse().getDescription());
        infoMap.put("all instructors", this.coursePage.getInstructors());
        infoMap.put("rating", avgScore);
        infoMap.put("relative ratings", getRelativeRatings());

        return infoMap;
    }


    /**
     * Private method that returns average rating score of current coursePage.
     */
    private void updateAvgScore() {
        if (this.ratings == null) {
            this.coursePage.setAverageScore(-1);
            return;
        }
        float total = 0;
        for (Rating r : this.ratings) {
            total += r.getScore();
        }
        this.coursePage.setAverageScore(total / this.ratings.size());
    }

    /**
     * Get course code of course that CourseManager is handling.
     *
     * @return course code.
     */
    @Override
    public String getID() {
        return this.course.getCode();
    }

    /**
     * Get permission level hashmap of authorization hashmap.
     *
     * @return Current AuthDict.
     */
    @Override
    public Map<UserType, List<String>> getAuthDict() {
        return this.authDict;
    }


    /**
     * Get default authorization dictionary.
     *
     * @return Map of permission level and list of string.
     */
    public Map<UserType, List<String>> getDefaultAuthDict() {
        Map<UserType, List<String>> retDict = new HashMap<>();
        List<String> studentPermissions = Arrays.asList("print", "checkout", "rate", "filter", "getcomments", "startcomment");
        List<String> instructorPermissions = Arrays.asList("print", "checkout", "filter", "getcomments", "startcomment");
        retDict.put(UserType.STUDENT, studentPermissions);
        retDict.put(UserType.INSTRUCTOR, instructorPermissions);
        return retDict;
    }
}
