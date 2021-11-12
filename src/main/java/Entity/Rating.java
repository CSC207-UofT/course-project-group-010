package Entity;


import Exceptions.CommandNotAuthorizedException;

public class Rating {

    private final StudentUser rater;
    private float score;
    private String courseCode;  // Course object which this rating is for.
    private String instructor;
    private int year;

    public Rating(StudentUser rater, float score, String courseCode, String instructor) {
        this.rater = rater;
        this.score = score;
        this.courseCode = courseCode;
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

    public String getRaterProgramOfStudy() {
        return rater.getProgramDetail();
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) throws CommandNotAuthorizedException {
        if (isInRange(score))
            this.score = score;
        else
            // TODO: reject rating change
            throw new CommandNotAuthorizedException("Score " + score + " must be between 0 and 1 inclusive.");
    }

    // FIXME: score bounds should probably be stored as a constant
    private boolean isInRange(float score) {
        return score >= 0 && score <= 1;
    }

    public String getInstructor() {
        return this.instructor;
    }


    @Override
    public String toString() {
        return Float.toString(getScore());
    }

    public StudentUser getRater() {
        return this.rater;
    }
}
