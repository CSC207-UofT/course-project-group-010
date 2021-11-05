package Entity;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class CommentGraphTest {
    @Test(timeout = 100)
    public void testGraph() {
        List<String> questions = new ArrayList<>();
        questions.add("Q1");
        questions.add("Q2");
        questions.add("Q3");

        CommentGraph commentGraph = new CommentGraph(questions, "generic");
    }
}
