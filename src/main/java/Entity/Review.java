package Entity;
import java.time.LocalDate;
import java.util.List;

public class Review {
    private User writer; //Could also have only the User's ID and not the user object
    private String content; //Review content
    private LocalDate date; //Date of review submission
    private List<String> replies; //list containing replies/comment objects (to be implemented later).


    //Constructors

    public Review(User writer, String content, LocalDate date) {
        this.writer = writer;
        this.content = content;
        this.date = date;
    }

    //Getters

    User getWriter() {
        return this.writer;
    }
    String getContent() {
        return this.content;
    }
    public int getYear() {
        return this.date.getYear();
    }

    //Setters

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setdate(LocalDate date) {
        this.date = date;
    }




    @Override
    public String toString() {
        return this.content + "\n\n" + "Year : " + this.date.getYear();
    }

}
