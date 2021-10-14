package Entity;

import java.util.HashMap;
import java.util.List;

public class StudentUser extends User {
    private HashMap<Integer, List<Course>> courses;
    private String programDetail;

    /**
     * @param displayName student user's display name.
     * @param ID student user's ID.
     */
    public StudentUser(String displayName, String ID, String programDetail) {
        super(displayName, ID);
        this.permissionLevel = 0;
        this.reviewCount = 0;
        this.programDetail = programDetail;
        this.courses = new HashMap<Integer, List<Course>>();
    }


    String getProgramDetail() {
        return this.programDetail;
    }

    @Override
    public String toString() {
        // TODO: implement
        return super.toString();
    }


    /**
     *
     * Junhyuk Park : I think most of management things like
     * log in, leaving review, etc should be done by Manager classes
     * in use case layers.
     *
     * Provide a review (i.e a reply in the thread under the review question prompt)
     * Scenario Walkthrough:
     * i) User logs in
     * ii) User goes to the course page
     * Search course name in Course directory
     * Select add review or check other reviews and upvotes
     * Provide details necessary in order to be allowed to leave review (year you took this course,
     * instructor name, etc)
     * iii) Replies to prompt (leaves review)
     * Provide a rating for the course ( out of 10)
     * Provide upvote/downvote to any reviews
     */
}
