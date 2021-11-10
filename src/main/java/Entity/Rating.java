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
        }

        public Double getRating(){
            List<List<Integer>> values = new ArrayList<>(this.scores.values());
            int num_of_raters = 0;
            int sum_of_ratings = 0;

            for (List<Integer> l : values) {
                int sum = 0;
                int num_r = 0;
                for (int i : l){
                    num_r ++;
                    sum = sum + i;
                }
                num_of_raters += num_r;
                sum_of_ratings += sum;
            }
            // so sum is the sum of all ratings right??
            double a = sum_of_ratings;
            double b = num_of_raters;
            System.out.println("sum of ratings is" + a);
            System.out.println("number of raters is" + b);
            return a/b;
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

        public void addUser (StudentUser u) {
            this.users.add(u);
        }

        public void addScore (String program, int score){
            if (this.scores.containsKey(program)) {
                List<Integer> listOfScores = this.scores.get(program);
                listOfScores.add(score);
            } else {
                this.scores.put(program, List.of(score));
            }
        }

        @Override
        public String toString() {
            return getRating().toString();
        }

}











