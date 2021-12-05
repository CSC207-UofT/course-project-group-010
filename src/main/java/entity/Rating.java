package entity;

import exceptions.CommandNotAuthorizedException;
import interfaces.IUser;

import java.io.Serializable;

public class Rating implements Serializable {

    private final IUser rater;
    private double score;

    // TODO consider not using this
//    public Rating(IUser rater, float score, String instructor) {
//        this.rater = rater;
//        this.score = score;
//    }

    public Rating(IUser rater, double score) {
        this.rater = rater;
        this.score = score;
    }

    /**
     * Getter for the rater's program of study. For use in calculating program-specific relative rating.
     *
     * @return string representation of rater's program of study
     */
    public String getRaterProgramOfStudy() {
        return rater.getOtherData().containsKey("programDetail") ? rater.getOtherData().get("programDetail") : "N/A";
    }

    /**
     * Getter for the score of the rating.
     *
     * @return Score
     */
    public double getScore() {
        return score;
    }

    /**
     * Set the rating score.
     *
     * @param score The score to be set
     * @throws CommandNotAuthorizedException if the score is out of range
     */
    // TODO this is only used in tests, we can delete
//    public void setScore(float score) throws CommandNotAuthorizedException {
//        if (isInRange(score))
//            this.score = score;
//        else
//            throw new CommandNotAuthorizedException("Score " + score + " must be between 0 and 1 inclusive.");
//    }

    /**
     * Helper method to check if a given score is within the acceptable range.
     *
     * @param score The score trying to be set
     * @return Whether the score is acceptable
     */
    // TODO this is only used in setScore, which is only used in tests, we can delete
//    private boolean isInRange(float score) {
//        // FIXME: score bounds should probably be stored as a constant
//        return score >= 0 && score <= 1;
//    }

//    /**
//     * Getter for the instructor of the course that this rating is for.
//     *
//     * @return Instructor name
//     */
//    public String getInstructor() {
//        return this.instructor;
//    }

    /**
     * Getter for the author of this rating, a.k.a. the rater.
     *
     * @return rater's StudentUser object
     */
//    public IUser getRater() {
//        return this.rater;
//    }

    @Override
    public String toString() {
        return Double.toString(getScore());
    }
}
