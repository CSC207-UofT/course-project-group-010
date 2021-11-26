package usecase;

import entity.CommentGraph;
import usecase.commentManager.CommentDisplayCleanupManager;
import usecase.commentManager.CommentManager;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CommentManagerTest
{
    /**
     * This method is used for building a sample CommentGraph. THIS IS NOT HOW A COMMENTGRAPH SHOULD NORMALLY BE
     * CONSTRUCTED. This implementation calls private methods which should not be used outside their respective
     * classes. In this case I had no choice due to the random nature of the id generation which would make it
     * impossible to test otherwise.
     *
     * @return A sample CommentGraph that is used for tests.
     * @throws NoSuchMethodException
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static CommentGraph sampleCommentGraphBuilder() throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException
    {
        // Get private methods from commentGraph Class
        Class<?> commentGraphClass = Class.forName("entity.CommentGraph");

        Method createCommentMethod = commentGraphClass.getDeclaredMethod("createComment", String.class, String.class, String.class);
        createCommentMethod.setAccessible(true);

        Method addVertexMethod = commentGraphClass.getDeclaredMethod("addVertex", String.class, CommentGraph.Comment.class);
        addVertexMethod.setAccessible(true);

        Method linkMethod = commentGraphClass.getDeclaredMethod("link", String.class, CommentGraph.Comment.class);
        linkMethod.setAccessible(true);


        // create an empty Comment Graph
        CommentGraph CSC207Thread = new CommentGraph("Questions", "Instructor", new HashMap<>());

        // Manually create comments and links, this should not be done, only done for the sake of testing.
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

    /**
     * Tests that the displayEntireThread method displays the String representation of the entire CommentGraph up to its
     * full depth.
     *
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void testCompleteStringRepresentationFullDepth() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        CommentManager commentManager = new CommentManager(sampleCommentGraphBuilder());
        String expected = commentManager.displayEntireThread(true, -1);
        String actual = "> Instructor [id: root]\nQuestions\n[+] 0 [-]\n\n    > Instructor [id: id2]\n    What did you find difficult in the course?\n    [+] 4 [-]\n\n        > Student 4 [id: id7]\n        Everything\n        [+] -4 [-]\n\n    > Instructor [id: id3]\n    Anything that the course coordinators can do to improve the course?\n    [+] 2 [-]\n\n        > Student 4 [id: id8]\n        No, the course was perfect.\n        [+] 1 [-]\n\n    > Instructor [id: id1]\n    How did you like the course?\n    [+] 1 [-]\n\n        > Student 1 [id: id4]\n        I really liked the course!\n        [+] 5 [-]\n\n            > Student 3 [id: id6]\n            Me too!\n            [+] 7 [-]\n\n        > Student 2 [id: id5]\n        I didn't like the course.\n        [+] -3 [-]\n\n";

        /*
        > Instructor [root]
        Questions
        [+] 0 [-]
            > Instructor [id2]
            What did you find difficult in the course?
            [+] 4 [-]
                > Student 4 [id7]
                Everything
                [+] -4 [-]
            > Instructor [id3]
            Anything that the course coordinators can do to improve the course?
            [+] 2 [-]
                > Student 4 [id8]
                No, the course was perfect.
                [+] 1 [-]
            > Instructor [id1]
            How did you like the course?
            [+] 1 [-]
                > Student 1 [id4]
                I really liked the course!
                [+] 5 [-]
                    > Student 3 [id6]
                    Me too!
                    [+] 7 [-]
                > Student 2 [id5]
                I didn't like the course.
                [+] -3 [-]
        */

        assert (expected.equals(actual));
    }

    /**
     * Tests that the displayEntireThread method displays the String representation of the entire CommentGraph up to
     * depth 2.
     *
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     */
    @Test
    public void testCompleteStringRepresentationDepth2() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException
    {
        CommentManager commentManager = new CommentManager(sampleCommentGraphBuilder());
        String expected = commentManager.displayEntireThread(true, 2);
        String actual = "> Instructor [id: root]\nQuestions\n[+] 0 [-]\n\n    > Instructor [id: id2]\n    What did you find difficult in the course?\n    [+] 4 [-]\n\n        > Student 4 [id: id7]\n        Everything\n        [+] -4 [-]\n\n    > Instructor [id: id3]\n    Anything that the course coordinators can do to improve the course?\n    [+] 2 [-]\n\n        > Student 4 [id: id8]\n        No, the course was perfect.\n        [+] 1 [-]\n\n    > Instructor [id: id1]\n    How did you like the course?\n    [+] 1 [-]\n\n        > Student 1 [id: id4]\n        I really liked the course!\n        [+] 5 [-]\n\n        > Student 2 [id: id5]\n        I didn't like the course.\n        [+] -3 [-]\n\n";

        /*
        > Instructor [root]
        Questions
        [+] 0 [-]
            > Instructor [id2]
            What did you find difficult in the course?
            [+] 4 [-]
                > Student 4 [id7]
                Everything
                [+] -4 [-]
            > Instructor [id3]
            Anything that the course coordinators can do to improve the course?
            [+] 2 [-]
                > Student 4 [id8]
                No, the course was perfect.
                [+] 1 [-]
        */

        assert (expected.equals(actual));
    }

    /**
     * Tests that the displaySubsetThread method displays the String representation of a subset of a CommentGraph
     * up to the full depth.
     *
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     */
    @Test
    public void testSubsetStringRepresentationFullDepth() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException
    {
        CommentManager commentManager = new CommentManager(sampleCommentGraphBuilder());
        String expected = commentManager.displaySubsetThread("id2", true, -1);
        String actual = "> Instructor [id: id2]\nWhat did you find difficult in the course?\n[+] 4 [-]\n\n    > Student 4 [id: id7]\n    Everything\n    [+] -4 [-]\n\n";

        /*
        > Instructor [id2]
        What did you find difficult in the course?
        [+] 4 [-]
            > Student 4 [id7]
            Everything
            [+] -4 [-]
        */

        assert (expected.equals(actual));
    }

    /**
     * Tests that the displaySubsetThread method displays the String representation of a subset of a CommentGraph up
     * to depth 0.
     *
     * @throws NoSuchMethodException
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void testSubsetStringRepresentationDepth0() throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException
    {
        CommentManager commentManager = new CommentManager(sampleCommentGraphBuilder());
        String expected = commentManager.displaySubsetThread("id2", true, 0);
        String actual = "> Instructor [id: id2]\nWhat did you find difficult in the course?\n[+] 4 [-]\n\n";

        /*
        > Instructor [id2]
        What did you find difficult in the course?
        [+] 4 [-]
        */

        assert (expected.equals(actual));
    }

    /**
     * Tests that path generation from one Comment to another Comment given that there is a valid path works properly.
     *
     * @throws NoSuchMethodException
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void testPath() throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException
    {
        CommentManager commentManager = new CommentManager(sampleCommentGraphBuilder());
        String expected = commentManager.getPath("root", "id6");
        String actual = "> Instructor [id: root]\nQuestions\n[+] 0 [-]\n\n    > Instructor [id: id1]\n    How did you like the course?\n    [+] 1 [-]\n\n        > Student 1 [id: id4]\n        I really liked the course!\n        [+] 5 [-]\n\n            > Student 3 [id: id6]\n            Me too!\n            [+] 7 [-]\n\n";

        /*
        > Instructor [root]
        Questions
        [+] 0 [-]
            > Instructor [id1]
            How did you like the course?
            [+] 1 [-]
                > Student 1 [id4]
                I really liked the course!
                [+] 5 [-]
                    > Student 3 [id6]
                    Me too!
                    [+] 7 [-]
        */

        assert (expected.equals(actual));
    }

    /**
     * Tests that reply functionality works correctly.
     *
     * @throws NoSuchMethodException
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void testReply() throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException
    {
        CommentManager commentManager = new CommentManager(sampleCommentGraphBuilder());
        commentManager.replyToComment("id6", "Sample Reply", "Student 8");
        assert (commentManager.getChildrenComments("id6").size() == 1);
    }

    /**
     * Tests that vote functionality works correctly.
     *
     * @throws NoSuchMethodException
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void testVote() throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException
    {
        CommentManager commentManager = new CommentManager(sampleCommentGraphBuilder());
        int initialVote = commentManager.getVote("id1");
        commentManager.vote("id1", true);
        assert (commentManager.getVote("id1") == initialVote + 1);
    }

    public static void main(String[] args) throws IOException, InterruptedException 
    {
        HashMap<String, List<String>> initialQuestions = new HashMap<>();
        initialQuestions.put("Prof 1", Arrays.asList("How did you like the course?", "Was the course fun?"));
        initialQuestions.put("Prof 2", Arrays.asList("Tell us how we can improve the course."));
        CommentGraph cg = new CommentGraph("Questions", "RootName", initialQuestions);

        CommentManager cm = new CommentManager(cg);
        String display = cm.displayEntireThread(true, -1);
        System.out.println("\n");
        System.out.print(display);

        // Thread.sleep(5000);
        
        // cm.clearThreadDisplay(display);
        // Thread.sleep(1000);
        // System.out.println("----------CLEARING---------------------\n".repeat(10));

        
        Thread.sleep(5000);
        CommentDisplayCleanupManager.setCommentNewLineCount(display);
        CommentDisplayCleanupManager.eraseCurrentThread();
        
    }
}