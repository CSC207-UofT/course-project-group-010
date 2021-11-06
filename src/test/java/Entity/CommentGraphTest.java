package Entity;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

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

        CommentGraph commentGraph = new CommentGraph(questions, "John Smith");

        for (var i : commentGraph.getVertices().values())
        {
            System.out.println(i.toString());
        }

        Scanner input = new Scanner(System.in);

        System.out.println("Enter id:");
        String id1 = input.nextLine();
        commentGraph.reply(id1, "Sample Text 1", "Jimmy");
        commentGraph.vote(id1, true);
        commentGraph.vote(id1, true);
        commentGraph.vote(id1, true);
        commentGraph.vote(id1, true);
        commentGraph.vote(id1, true);

        System.out.println("Enter id:");
        String id2 = input.nextLine();
        commentGraph.reply(id2, "Sample Text 2", "Timmy");
        commentGraph.vote(id2, true);

        System.out.println("Enter id:");
        String id3 = input.nextLine();
        commentGraph.reply(id3, "Sample Text 3", "Zimmy");
        commentGraph.vote(id3, true);
        commentGraph.vote(id3, true);
        commentGraph.vote(id3, true);

        // var printerList = commentGraph.depthPrinter("head");

        // for (var i : printerList)
        // {
        //     System.out.println(i.toString());
        // }

        commentGraph.print(commentGraph.getVertices().get("head"), 0);

        System.out.println("Enter id:");
        String id4 = input.nextLine();


        CommentGraphHelper helper = new CommentGraphHelper();
        List<CommentGraph.Comment> path = helper.depthFirstPath(commentGraph, "head", id4);

        for (var i : path)
        {
            System.out.println(i.toString());
        }
    }
}
