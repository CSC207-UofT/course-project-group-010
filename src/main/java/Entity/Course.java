package Entity;

public class Course {
    private String name; // Course name; i.e Introduction to Computer Science
    private String code; // Course code; i.e CSC110
    private String description; // Default description of the course that may be altered.
    private Rating rating; // Rating of this course. Default value is null.


    //Constructors
    public Course(String name, String code) {
        this.name = name;
        this.code = code;
        this.description = "There is currently no description available for this course";
        this.rating = new Rating();
    }




    //Getters
    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public Rating getRating() {
        return this.rating;
    }





    //Setters
    public void setDescription(String s) {
        this.description = s;
    }

    public void setName(String s){
        this.name = s;
    }

    public void setCode(String s){
        this.name = s;
    }

    //ONLY use this if you're going to set a completely different Rating object for this course.
    //If you want to add a rating to the course, use the Rating method processRating on Course.rating.
    public void setRating(Rating r) {
        this.rating = r;
    }

}
