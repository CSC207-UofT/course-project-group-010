package usecase;

import constants.ProgramConstants;
import constants.UserType;
import entity.Course;
import entity.Rating;
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

    private final Course course;
    private List<Rating> ratings;
    private final CoursePage coursePage;
    private final Map<UserType, List<String>> authDict;

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
    public void addRating(double ratingNum, IUser user) throws ArgumentException {
        List<Rating> ratingList = this.coursePage.getRatings();
        if (ratingList == null) {
            ratingList = new ArrayList<>();
            this.coursePage.setRatings(ratingList);
            this.ratings = this.coursePage.getRatings();
        }

        Rating r = new Rating(user, ratingNum);
        if (getRating(user) != null) {
            ratingList.remove(getRating(user));
        }
        ratingList.add(r);
        this.updateAvgScore();

    }

    /**
     * Gets the rating left by a user, if the user already left a rating.
     * @param user user you are searching for.
     * @return the user and the rating left by this user
     */
    private Rating getRating(IUser user) {
        List<Rating> ratingList = this.coursePage.getRatings();
        if (ratingList != null) {
            for (Rating r : ratingList) {
                if (r.getRater() == user) {
                    return r;
                }
            }
        }
        return null;
    }

    /**
     * Change current relative score of CoursePage.
     *
     * @param program string of program name of raters' that will be filtered.
     */
    public double getRelativeRating(String program) {
        List<Rating> filteredRatings = this.ratings.stream().filter(
                r -> r.getRaterProgramOfStudy().equals(program)).collect(Collectors.toList());
        if(filteredRatings.isEmpty()) {
            return -1;
        }
        double total = 0;
        for(Rating r : filteredRatings) {
            total += r.getScore();
        }
        return total / filteredRatings.size();

    }

    public Map<String, Double> getRelativeRatings() {
        Map<String, Double> retMap = new HashMap<>();
        ProgramConstants pc = new ProgramConstants();
        for (String program : pc.getPossiblePrograms()) {
            if (getRelativeRating(program) != -1) {
                retMap.put(program, getRelativeRating(program));
            }
        }
        return retMap;
    }

    // Getters

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
        double total = 0;
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
