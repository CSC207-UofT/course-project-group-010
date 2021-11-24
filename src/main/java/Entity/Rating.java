package Entity;

import exceptions.CommandNotAuthorizedException;

import java.io.Serializable;

public class Rating implements Serializable {

    private final StudentUser rater;
    private final String instructor;
    private float score;

    public Rating(StudentUser rater, float score, String instructor) {
        this.rater = rater;
        this.score = score;
        this.instructor = instructor;
    }

    /**
     * @return a string indicating that the student has successfully left a review iff this student
     * has not already placed a review for this course rating.
     * Precondition: Rating object has already set a scores
     */
    /*public boolean processRating(Integer rating, StudentUser student) throws CommandNotAuthorizedException {
        if (!(0 <= rating && rating <= 10)) {
            throw new CommandNotAuthorizedException("Pleease provide a rating 1-10");
        }
        if (!users.contains(student)) { //Student hasn't already left a review for this course
            if (!this.scores.containsKey(student.getProgramDetail())) { //program_name not already in scores hashmap

                this.scores.put(student.getProgramDetail(), new ArrayList<>()); //Make list value at program_name

                this.scores.get(student.getProgramDetail()).add(rating); //Add rating to the list at program_name

                this.users.add(student); //student can no longer leave another rating for this course

                return true;

            } else { //program_name is already in scores hashmap
                this.scores.get(student.getProgramDetail()).add(rating); //add rating to list value at program_name
                this.users.add(student); //student can no longer leave another rating for this course
                return true;
            }
        }
        // This line is bad, this string will never see the light of day
        // return student.getID() + "\n" + "has already placed a rating for this course.";
        throw new CommandNotAuthorizedException(student.getID() + " has already placed a rating for this course.");
    }*/

    /**
     * Getter for the rater's program of study. For use in calculating program-specific relative rating.
     *
     * @return string representation of rater's program of study
     */
    public String getRaterProgramOfStudy() {
        return rater.getProgramDetail();
    }

    /**
     * Getter for the score of the rating.
     *
     * @return Score
     */
    public float getScore() {
        return score;
    }

    /**
     * Set the rating score.
     *
     * @param score The score to be set
     * @throws CommandNotAuthorizedException if the score is out of range
     */
    public void setScore(float score) throws CommandNotAuthorizedException {
        if (isInRange(score))
            this.score = score;
        else
            throw new CommandNotAuthorizedException("Score " + score + " must be between 0 and 1 inclusive.");
    }

    /**
     * Helper method to check if a given score is within the acceptable range.
     *
     * @param score The score trying to be set
     * @return Whether the score is acceptable
     */
    private boolean isInRange(float score) {
        // FIXME: score bounds should probably be stored as a constant
        return score >= 0 && score <= 1;
    }

    /**
     * Getter for the instructor of the course that this rating is for.
     *
     * @return Instructor name
     */
    public String getInstructor() {
        return this.instructor;
    }

    /**
     * Getter for the author of this rating, a.k.a. the rater.
     *
     * @return rater's StudentUser object
     */
    public StudentUser getRater() {
        return this.rater;
    }

    @Override
    public String toString() {
        return Float.toString(getScore());
    }
}
