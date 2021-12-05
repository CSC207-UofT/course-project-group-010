package entity;

import exceptions.ArgumentException;
import exceptions.CommandNotAuthorizedException;
import interfaces.IUser;

import java.io.Serializable;

public class Rating implements Serializable {

    private final IUser rater;
    private double score;
    private final double MAX_SCORE = 10.0;
    private final double MIN_SCORE = 0.0;


    /**
     * Initializes a new rating, throws an exception if it is not between the
     * accepted bounds
     * @param rater User that is rating
     * @param score Score that the user gave.
     * @throws ArgumentException
     */
    public Rating(IUser rater, double score) throws ArgumentException {
        if (score > MAX_SCORE || score < MIN_SCORE) {
            throw new ArgumentException("Rating must be between 0 and 10");
        }
        this.rater = rater;
        this.score = score;
    }

    /**
     * Gets the rater
     * @return the rater
     */
    public IUser getRater() {
        return rater;
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
    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return Double.toString(getScore());
    }
}
