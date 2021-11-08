package UseCase;


import Entity.CommentGraph;
import UseCase.CommentManager.CommentManager;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        CSC207Thread.upvote("id1");

        CommentGraph.Comment question2 = (CommentGraph.Comment) createCommentMethod.invoke(CSC207Thread, "id2", "What did you find difficult in the course?", "Instructor");
        addVertexMethod.invoke(CSC207Thread, "id2", question2);
        linkMethod.invoke(CSC207Thread, "root", question2);
        CSC207Thread.upvote("id2");
        CSC207Thread.upvote("id2");
        CSC207Thread.upvote("id2");
        CSC207Thread.upvote("id2");

        CommentGraph.Comment question3 = (CommentGraph.Comment) createCommentMethod.invoke(CSC207Thread, "id3", "Anything that the course coordinators can do to improve the course?", "Instructor");
        addVertexMethod.invoke(CSC207Thread, "id3", question3);
        linkMethod.invoke(CSC207Thread, "root", question3);
        CSC207Thread.upvote("id3");
        CSC207Thread.upvote("id3");

        CommentGraph.Comment reply1 = (CommentGraph.Comment) createCommentMethod.invoke(CSC207Thread, "id4", "I really liked the course!", "Student 1");
        addVertexMethod.invoke(CSC207Thread, "id4", reply1);
        linkMethod.invoke(CSC207Thread, "id1", reply1);
        CSC207Thread.upvote("id4");
        CSC207Thread.upvote("id4");
        CSC207Thread.upvote("id4");
        CSC207Thread.upvote("id4");
        CSC207Thread.upvote("id4");

        CommentGraph.Comment reply2 = (CommentGraph.Comment) createCommentMethod.invoke(CSC207Thread, "id5", "I didn't like the course.", "Student 2");
        addVertexMethod.invoke(CSC207Thread, "id5", reply2);
        linkMethod.invoke(CSC207Thread, "id1", reply2);
        CSC207Thread.downvote("id5");
        CSC207Thread.downvote("id5");
        CSC207Thread.downvote("id5");

        CommentGraph.Comment reply3 = (CommentGraph.Comment) createCommentMethod.invoke(CSC207Thread, "id7", "Everything", "Student 4");
        addVertexMethod.invoke(CSC207Thread, "id7", reply3);
        linkMethod.invoke(CSC207Thread, "id2", reply3);
        CSC207Thread.downvote("id7");
        CSC207Thread.downvote("id7");
        CSC207Thread.downvote("id7");
        CSC207Thread.downvote("id7");

        CommentGraph.Comment reply4 = (CommentGraph.Comment) createCommentMethod.invoke(CSC207Thread, "id8", "No, the course was perfect.", "Student 4");
        addVertexMethod.invoke(CSC207Thread, "id8", reply4);
        linkMethod.invoke(CSC207Thread, "id3", reply4);
        CSC207Thread.upvote("id8");

        CommentGraph.Comment replyReply1 = (CommentGraph.Comment) createCommentMethod.invoke(CSC207Thread, "id6", "Me too!", "Student 3");
        addVertexMethod.invoke(CSC207Thread, "id6", replyReply1);
        linkMethod.invoke(CSC207Thread, "id4", replyReply1);
        CSC207Thread.upvote("id6");
        CSC207Thread.upvote("id6");
        CSC207Thread.upvote("id6");
        CSC207Thread.upvote("id6");
        CSC207Thread.upvote("id6");
        CSC207Thread.upvote("id6");
        CSC207Thread.upvote("id6");

        return CSC207Thread;
    }

    @Test
    public void testCompleteStringRepresentationFullDepth() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        CommentManager commentManager = new CommentManager(sampleCommentGraphBuilder());
        String expected = commentManager.displayEntireThread(true, -1);
        String actual = "↳ Instructor [root]\nQuestions\n↑ 0 ↓\n\n    ↳ Instructor [id2]\n    What did you find difficult in the course?\n    ↑ 4 ↓\n\n        ↳ Student 4 [id7]\n        Everything\n        ↑ -4 ↓\n\n    ↳ Instructor [id3]\n    Anything that the course coordinators can do to improve the course?\n    ↑ 2 ↓\n\n        ↳ Student 4 [id8]\n        No, the course was perfect.\n        ↑ 1 ↓\n\n    ↳ Instructor [id1]\n    How did you like the course?\n    ↑ 1 ↓\n\n        ↳ Student 1 [id4]\n        I really liked the course!\n        ↑ 5 ↓\n\n            ↳ Student 3 [id6]\n            Me too!\n            ↑ 7 ↓\n\n        ↳ Student 2 [id5]\n        I didn't like the course.\n        ↑ -3 ↓\n\n";
        
        /*
        ↳ Instructor [root]
        Questions
        ↑ 0 ↓

            ↳ Instructor [id2]
            What did you find difficult in the course?
            ↑ 4 ↓

                ↳ Student 4 [id7]
                Everything
                ↑ -4 ↓

            ↳ Instructor [id3]
            Anything that the course coordinators can do to improve the course?
            ↑ 2 ↓

                ↳ Student 4 [id8]
                No, the course was perfect.
                ↑ 1 ↓
        */

        assert (expected.equals(actual));
    }

    @Test
    public void testCompleteStringRepresentationDepth2() throws ClassNotFoundException,
            InvocationTargetException, NoSuchMethodException, IllegalAccessException
    {
        CommentManager commentManager = new CommentManager(sampleCommentGraphBuilder());
        String expected = commentManager.displayEntireThread(true, 2);
        String actual = "↳ Instructor [root]\nQuestions\n↑ 0 ↓\n\n    ↳ Instructor [id2]\n    What did you find difficult in the course?\n    ↑ 4 ↓\n\n        ↳ Student 4 [id7]\n        Everything\n        ↑ -4 ↓\n\n    ↳ Instructor [id3]\n    Anything that the course coordinators can do to improve the course?\n    ↑ 2 ↓\n\n        ↳ Student 4 [id8]\n        No, the course was perfect.\n        ↑ 1 ↓\n\n    ↳ Instructor [id1]\n    How did you like the course?\n    ↑ 1 ↓\n\n        ↳ Student 1 [id4]\n        I really liked the course!\n        ↑ 5 ↓\n\n        ↳ Student 2 [id5]\n        I didn't like the course.\n        ↑ -3 ↓\n\n";

        /*
        ↳ Instructor [root]
        Questions
        ↑ 0 ↓

            ↳ Instructor [id2]
            What did you find difficult in the course?
            ↑ 4 ↓

                ↳ Student 4 [id7]
                Everything
                ↑ -4 ↓

            ↳ Instructor [id3]
            Anything that the course coordinators can do to improve the course?
            ↑ 2 ↓

                ↳ Student 4 [id8]
                No, the course was perfect.
                ↑ 1 ↓
        */
        
        assert (expected.equals(actual));
    }

    @Test
    public void testSubsetStringRepresentationFullDepth() throws ClassNotFoundException,
            InvocationTargetException, NoSuchMethodException, IllegalAccessException
    {
        CommentManager commentManager = new CommentManager(sampleCommentGraphBuilder());
        String expected = commentManager.displaySubsetThread("id2", true, -1);
        String actual = "↳ Instructor [id2]\nWhat did you find difficult in the course?\n↑ 4 ↓\n\n    ↳ Student 4 [id7]\n    Everything\n    ↑ -4 ↓\n\n";

        /*
        ↳ Instructor [id2]
        What did you find difficult in the course?
        ↑ 4 ↓

            ↳ Student 4 [id7]
            Everything
            ↑ -4 ↓
        */

        assert(expected.equals(actual));
    }

    @Test
    public void testSubsetStringRepresentationDepth0() throws NoSuchMethodException, 
    ClassNotFoundException, InvocationTargetException, IllegalAccessException{
        CommentManager commentManager = new CommentManager(sampleCommentGraphBuilder());
        String expected = commentManager.displaySubsetThread("id2", true, 0);
        String actual = "↳ Instructor [id2]\nWhat did you find difficult in the course?\n↑ 4 ↓\n\n";

        /*
        ↳ Instructor [id2]
        What did you find difficult in the course?
        ↑ 4 ↓
        */

        assert(expected.equals(actual));
    }

    @Test
    public void testPath() throws NoSuchMethodException, 
    ClassNotFoundException, InvocationTargetException, IllegalAccessException{
        CommentManager commentManager = new CommentManager(sampleCommentGraphBuilder());
        String expected = commentManager.getPath("root", "id6");
        String actual = "↳ Instructor [root]\nQuestions\n↑ 0 ↓\n\n    ↳ Instructor [id1]\n    How did you like the course?\n    ↑ 1 ↓\n\n        ↳ Student 1 [id4]\n        I really liked the course!\n        ↑ 5 ↓\n\n            ↳ Student 3 [id6]\n            Me too!\n            ↑ 7 ↓\n\n";

        /*
        ↳ Instructor [root]
        Questions
        ↑ 0 ↓

            ↳ Instructor [id1]
            How did you like the course?
            ↑ 1 ↓

                ↳ Student 1 [id4]
                I really liked the course!
                ↑ 5 ↓

                    ↳ Student 3 [id6]
                    Me too!
                    ↑ 7 ↓
        */

        assert(expected.equals(actual));
    }

    @Test
    public void testReply() throws NoSuchMethodException, 
    ClassNotFoundException, InvocationTargetException, IllegalAccessException{
        CommentManager commentManager = new CommentManager(sampleCommentGraphBuilder());
        commentManager.replyToComment("id6", "Sample Reply", "Student 8");
        assert(commentManager.getChildrenComments("id6").size() == 1);
    }

    @Test
    public void testVote() throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException{
        CommentManager commentManager = new CommentManager(sampleCommentGraphBuilder());
        int initialVote = commentManager.getVote("id1");
        commentManager.vote("id1", true);
        assert(commentManager.getVote("id1") == initialVote + 1);
    }
}
