package Entity;


import Exceptions.CommandNotAuthorizedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Rating {

    private Course course; //Course object which this rating is for.
    private HashMap<String, List<Integer>> scores; //Mapping Program names to list of ratings.
    private List<StudentUser> users; //Contains list of StudentUser objects who have already provided a rating for this
    //Course.

    /**
     * Map {Philosophy: [2/10, 3/10], CS: [9/10, 10/10]})
     */

    //Constructors
    public Rating() {
        this.scores = new HashMap<String, List<Integer>>(); //Empty hashmap
        this.users = new ArrayList<StudentUser>(); //Empty list
    }


        //Methods
        /**
         * @return a string indicating that the student has successfully left a review iff this student
         * has not already placed a review for this course rating.
         * Precondition: Rating object has already set a scores
         */
        public boolean processRating(Integer rating, StudentUser student) throws CommandNotAuthorizedException {

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
        }

        //Getters

        public Integer getRating(){
            List<List<Integer>> values = new ArrayList<>(this.scores.values());
            int to_return;
            to_return = 0;

            for (List<Integer> l : values) {
                int sum;
                sum = 0;
                for (int i : l){
                    sum = sum + i;
                }
                to_return += sum;


            }
            return to_return;
        }


        //return the hashmap mapping program names and their score for the course.
        HashMap<String, List<Integer>> getScores(){
            return this.scores;
        }

        //return the list of all users who have left a rating for this course.
        List<StudentUser> getUser(){
            return this.users;
        }


        //Setters

        public void setUsers (List < StudentUser > u) {
            this.users = u;
        }

        public void setScores (HashMap<String, List<Integer>>s){
            this.scores = s;
        }

        @Override
        public String toString() {
            return getRating().toString();
        }

}











