package Entity;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommentGraphTest {
    @Test(timeout = 100)
    public void testGraph() {
        List<String> questions = new ArrayList<>();
        questions.add("Q1");
        questions.add("Q2");
        questions.add("Q3");

        CommentGraph commentGraph = new CommentGraph(questions, "Jhon Doe");
        System.out.println(commentGraph.getVertices().get("head").toString());

        for (var i : commentGraph.getVertices().values())
        {
            System.out.println(i.toString());
        }
    }
}
