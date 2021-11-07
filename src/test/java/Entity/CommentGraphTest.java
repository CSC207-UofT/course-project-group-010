package Entity;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommentGraphTest {
    @Test(timeout = 100)
    public void testGraph() {

    }

    @Test()
    public void testPrint()
    {
        
        
        
    }

    public static void main(String[] args) {
        List<String> questions = new ArrayList<>();
        questions.add("How did you like the course?");
        questions.add("Did you find anything difficult in the course");
        questions.add("Anything that the course coordinators can do to improve the course?");

        CommentGraph commentGraph = new CommentGraph(questions, "Questions", "John Smith");

        for (var i : commentGraph.getVertices().values())
        {
            System.out.println(i.toString());
        }

        Scanner input = new Scanner(System.in);

        System.out.println("Enter id:");
        String id1 = input.nextLine();
        commentGraph.reply(id1, "Sample Text 1", "Jimmy");
        commentGraph.upvote(id1);
        commentGraph.upvote(id1);
        commentGraph.upvote(id1);
        commentGraph.upvote(id1);
        commentGraph.upvote(id1);

        System.out.println("Enter id:");
        String id2 = input.nextLine();
        commentGraph.reply(id2, "Sample Text 2", "Timmy");
        commentGraph.upvote(id2);

        System.out.println("Enter id:");
        String id3 = input.nextLine();
        commentGraph.reply(id3, "Sample Text 3", "Zimmy");
        commentGraph.upvote(id3);
        commentGraph.upvote(id3);
        commentGraph.upvote(id3);

        // var printerList = commentGraph.depthPrinter("root");

        // for (var i : printerList)
        // {
        //     System.out.println(i.toString());
        // }

        System.out.println(commentGraph.stringRepresentation(commentGraph.getVertices().get("root"), 0, commentGraph.getMaxDepth()));

        System.out.println("Enter id:");
        String id4 = input.nextLine();
        System.out.println("Enter id:");
        String id5 = input.nextLine();


        CommentGraphHelper helper = new CommentGraphHelper();
        List<CommentGraph.Comment> path = helper.depthFirstPath(commentGraph, id4, id5);

        for (var i : path)
        {
            System.out.println(i.toString());
        }
    }
}
