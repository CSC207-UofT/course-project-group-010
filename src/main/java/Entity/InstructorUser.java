package Entity;

import java.util.HashMap;
import java.util.List;

public class InstructorUser extends User {

    HashMap<Integer, List<Course>> courses;
    String position;

    public InstructorUser(String displayName, String ID, String position) {
        super(displayName, ID);
        this.position = position;
    }

    /**
     * Add new review prompt question
     * Upvote/Downvote Reviews
     * Optional : Add comment to a review
     *
     * Scenario Walkthrough:
     * i) User logs in
     * ii) User goes to the course page
     * Search course name in Course directory
     * Select add prompt question or check other reviews and upvotes
     */
}
