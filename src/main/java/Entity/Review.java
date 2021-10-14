package Entity;
import java.time.LocalDate;

public class Review {
    private final User writer; //Could also have only the User's ID and not the user object
    private string content; //Review content
    private final LocalDate date; //Date of review submission
    private InstructorUser instructor; //Instrucor for the course this review belongs to

    public Review(User writer, String content, LocalDate date, InstructorUser instructor) {
        this.writer = writer;
        this.content = content;
        this.date = date;
        this.instructor = instructor;
    }

    void setWriter(User writer) {
        this.writer = writer;
    }

    User getWriter() {
        return this.writer;
    }

    void setContent(String content) {
        this.content = content;
    }

    String getContent() {
        return this.content;
    }

    void setdate(LocalDate date) {
        this.date = date;
    }

    int getYear() {
        return this.year;
    }

    void setInstructor(InstructorUser instructor) {
        this.instructor = instructor;
    }

    InstructorUser getInstructor() {
        return this.instructor;
    }
    @Override
    public String toString() {
        return this.content + "\n\n" + "Year : " + this.year;
    }

}
