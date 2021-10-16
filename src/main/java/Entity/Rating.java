package Entity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Rating {
    private HashMap<String, List<Double>> scores; //Mapping Program names to list of ratings.
    private List<StudentUser> users; //Contains list of StudentUser objects who have already provided a rating for this
    //Course.
    /**
     * Map {Philosophy: [2/10, 3/10], CS: [9/10, 10/10]})
     */
    //Constructors
    public Rating() {
        this.scores = new HashMap<String, List<Double>>(); //Empty hashmap
        this.users = new ArrayList<StudentUser>(); //Empty list
    }




    //Methods
    /**
     * @return a string indicating that the student has successfully left a review iff this student
     * has not already placed a review for this course.
     */
    public String processRating(Double rating, StudentUser student){

        if (!users.contains(student)) { // If Student hasn't already left a review for this course
            if (!this.scores.containsKey(student.getProgramDetail())) {// if program_name not already in scores hashmap

                this.scores.put(student.getProgramDetail(), new ArrayList<>()); //Make list value at program_name

                this.scores.get(student.getProgramDetail()).add(rating); //Add rating value to the list at program_name

                this.users.add(student); //student can no longer leave another rating for this course

                return student.getID() + "\n" + "has successfully rated" + "\n" + "this course" + "\n" +
                        rating + "%"; //indicate successful addition of rating

            } else { //if the program_name is already in the scores hashmap
                this.scores.get(student.getProgramDetail()).add(rating); //add rating to list value at program_name
                this.users.add(student); //student can no longer leave another rating for this course
                return student.getdisplayName() + "\n" + "has successfully rated" + "\n" + "this course" +
                        "\n" + rating + "%";//indicate successful addition of rating
            }
        }
        return student.getID() + "\n" + "has already placed a rating for this course.";
    }




    //Getters

    //Return average rating across all programs.
    public Double getRating(){
        List<List<Double>> values = new ArrayList<>(this.scores.values()); //Getting a list of lists of scores.
        double total_scores; //total_scores
        total_scores = 0;
        int num_scores = this.scores.keySet().size();

        for (List<Double> l : values) {
            double sum;
            sum = 0;
            for (Double i : l){
                sum = sum + i;
            }
            total_scores += sum;


        }
        return total_scores / num_scores;
    }

    //return the hashmap mapping program names and their score for the course.
    public HashMap<String, List<Double>> getScores(){
        return this.scores;
    }

    //return the list of all users who have left a rating for this course.
    public List<StudentUser> getUser(){
        return this.users;
    }




    //Setters
    public void setUsers (List < StudentUser > u) {
        this.users = u;
    }

    public void setScores (HashMap<String, List<Double>>s){
        this.scores = s;
    }


}











