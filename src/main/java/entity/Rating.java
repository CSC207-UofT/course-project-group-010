package entity;

import exceptions.CommandNotAuthorizedException;
import interfaces.IUser;

import java.io.Serializable;

public class Rating implements Serializable {

    private final IUser rater;
    private final float score;

    public Rating(IUser rater, float score) {
        this.rater = rater;
        this.score = score;
    }

    /**
     * Getter for the rater's program of study. For use in calculating program-specific relative rating.
     *
     * @return string representation of rater's program of study
     */
    public String getRaterProgramOfStudy() {
        return rater.getOtherData().getOrDefault("programDetail", "N/A");
    }

    /**
     * Getter for the score of the rating.
     *
     * @return Score
     */
    public float getScore() {
        return score;
    }

    @Override
    public String toString() {
        return Float.toString(getScore());
    }
}
