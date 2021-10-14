package Entity;

/** My brain meltdown at the end of the meeting. I thought we have created a rule of ratings,
 *  but I can't comprehend now.
 */

public class Rating {
    private int score;
    private Course course;
    private User user; // Program of study?? User??

    /**
     * Map {Philosophy: [2/10, 3/10], CS: [9/10, 10/10]})
     * I thought I understood what this map thing does, but not now...
     */

    public Rating(int score, Course course, User user) {
        this.score = score;
        this.course = course;
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("(Score: %d, Course: %s, User: %s)", this.getScore(),
                             this.getCourse().name, this.getUser().getDisplayName());
    }

    int getScore() {
        return this.score;
    }

    void setScore(int score) {
        this.score = score;
    }

    Course getCourse() {
        return this.course;
    }

    void setCourse() {
        this.course = course;
    }

    User getUser() {
        return this.user;
    }

    void setUser() {
        this.user = user;
    }
}
