package UseCase;


import Entity.CommentGraph;
import UseCase.CommentManager.CommentManager;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CommentManagerTest
{
    public static CommentGraph sampleCommentGraphBuilder() throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException
    {
        Class<?> commentGraphClass = Class.forName("Entity.CommentGraph");

        Method createCommentMethod = commentGraphClass.getDeclaredMethod("createComment", String.class, String.class, String.class);
        createCommentMethod.setAccessible(true);

        Method addVertexMethod = commentGraphClass.getDeclaredMethod("addVertex", String.class, CommentGraph.Comment.class);
        addVertexMethod.setAccessible(true);

        Method linkMethod = commentGraphClass.getDeclaredMethod("link", String.class, CommentGraph.Comment.class);
        linkMethod.setAccessible(true);

        CommentGraph CSC207Thread = new CommentGraph("Questions", "Instructor");

        CommentGraph.Comment question1 = (CommentGraph.Comment) createCommentMethod.invoke(CSC207Thread, "id1", "How did you like the course?", "Instructor");
        addVertexMethod.invoke(CSC207Thread, "id1", question1);
        linkMethod.invoke(CSC207Thread, "root", question1);

        CommentGraph.Comment question2 = (CommentGraph.Comment) createCommentMethod.invoke(CSC207Thread, "id2", "What did you find difficult in the course?", "Instructor");
        addVertexMethod.invoke(CSC207Thread, "id2", question2);
        linkMethod.invoke(CSC207Thread, "root", question2);

        CommentGraph.Comment question3 = (CommentGraph.Comment) createCommentMethod.invoke(CSC207Thread, "id3", "Anything that the course coordinators can do to improve the course?", "Instructor");
        addVertexMethod.invoke(CSC207Thread, "id3", question3);
        linkMethod.invoke(CSC207Thread, "root", question3);

        CommentGraph.Comment reply1 = (CommentGraph.Comment) createCommentMethod.invoke(CSC207Thread, "id4", "I really liked the course!", "Student 1");
        addVertexMethod.invoke(CSC207Thread, "id4", reply1);
        linkMethod.invoke(CSC207Thread, "id1", reply1);

        CommentGraph.Comment reply2 = (CommentGraph.Comment) createCommentMethod.invoke(CSC207Thread, "id5", "I didn't like the course.", "Student 2");
        addVertexMethod.invoke(CSC207Thread, "id5", reply2);
        linkMethod.invoke(CSC207Thread, "id1", reply2);

        CommentGraph.Comment reply3 = (CommentGraph.Comment) createCommentMethod.invoke(CSC207Thread, "id7", "Everything", "Student 4");
        addVertexMethod.invoke(CSC207Thread, "id7", reply3);
        linkMethod.invoke(CSC207Thread, "id2", reply3);

        CommentGraph.Comment reply4 = (CommentGraph.Comment) createCommentMethod.invoke(CSC207Thread, "id8", "No, the course was perfect.", "Student 4");
        addVertexMethod.invoke(CSC207Thread, "id8", reply4);
        linkMethod.invoke(CSC207Thread, "id3", reply4);

        CommentGraph.Comment replyReply1 = (CommentGraph.Comment) createCommentMethod.invoke(CSC207Thread, "id6", "Me too!", "Student 3");
        addVertexMethod.invoke(CSC207Thread, "id6", replyReply1);
        linkMethod.invoke(CSC207Thread, "id4", replyReply1);

        return CSC207Thread;
    }

    public void upvoteNTimes(CommentGraph commentGraph, String id, int n)
    {
        for (int i = 0; i < n; i++)
        {
            commentGraph.upvote(id);
        }
    }

    public void downvoteNTimes(CommentGraph commentGraph, String id, int n)
    {
        for (int i = 0; i < n; i++)
        {
            commentGraph.downvote(id);
        }
    }

    @Test(timeout = 100)
    public void testCompleteStringRepresentation() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {

    }

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException
    {
        CommentManager commentManager = new CommentManager(sampleCommentGraphBuilder());
        System.out.println(commentManager.displayEntireThread(true, -1));
    }
}
