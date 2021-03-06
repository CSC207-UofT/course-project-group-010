package entity;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class CommentGraphTest
{
    /**
     * Test the constructor
     */
    @Test
    public void testCommentGraphConstructor()
    {
        // create graph with initial comments
        HashMap<String, List<String>> initialComments = new HashMap<>();
        initialComments.put("sampleUser1", List.of("sampleText1"));
        initialComments.put("sampleUser2", List.of("sampleText2"));
        CommentGraph cg = new CommentGraph("Test", "Test", initialComments);

        // check that correct amount of comments are added to the graph
        assert cg.getVertices().size() == 3;
    }

    /**
     * Tests the empty constructor
     */
    @Test
    public void testCommentGraphEmptyConstructor()
    {
        // create graph
        CommentGraph cg = new CommentGraph("Test", "Test");

        // check that correct amount of comments are added to the graph
        assert cg.getVertices().size() == 1;
    }

    /**
     * Tests that vertices are correctly retrieved
     */
    @Test
    public void testGetVertices()
    {
        // create graph
        CommentGraph cg = new CommentGraph("Test", "Test");
        // get root vertex
        assert cg.getVertices().containsKey("root");
    }

    /**
     * Tests that comments are correctly retrieved
     */
    @Test
    public void testGetComment()
    {
        // create graph
        CommentGraph cg = new CommentGraph("Test", "Test");
        // expected value
        String expected = "Test";
        // actual actual
        String actual = cg.getComment("root").getText();
        // check that text matches => got correct string
        assert expected.equals(actual);
    }

    /**
     * Tests that maximum depth if correctly retrieved
     */
    @Test
    public void testGetMaxDepth()
    {
        // create graph
        CommentGraph cg = new CommentGraph("Test", "Test");
        cg.reply("root", "sampleText1", "sampleUser1");
        // expected value
        int expected = 1;
        // get maximum depth
        int actual = cg.getMaxDepth();
        // check maximum depth is the same
        assert expected == actual;
    }

    /**
     * Tests that the size of the graph is correctly retrieved
     */
    @Test
    public void testGetSize()
    {
        // create graph with initial comments
        HashMap<String, List<String>> initialComments = new HashMap<>();
        initialComments.put("sampleUser1", List.of("sampleText1"));
        initialComments.put("sampleUser2", List.of("sampleText2"));
        CommentGraph cg = new CommentGraph("Test", "Test", initialComments);
        // expected value
        int expected = 3;
        // get size of graph
        int actual = cg.getSize();
        // check that size of graph is correct
        assert actual == expected;
    }

    /**
     * Tests that the string representation of the graph is correct
     */
    @Test
    public void testStringRepresentation()
    {
        // create graph with initial comments
        HashMap<String, List<String>> initialComments = new HashMap<>();
        initialComments.put("sampleUser1", List.of("sampleText1"));
        initialComments.put("sampleUser2", List.of("sampleText2"));
        CommentGraph cg = new CommentGraph("Test", "Test", initialComments);

        // expected string
        String expected = "> Test [id: root]\nTest\n[+] 0 [-]\n\n    > sampleUser1 [id: 7DU2C]\n    sampleText1\n  " +
                "  [+] 0 [-]\n\n    > sampleUser2 [id: 0ZSJX]\n    sampleText2\n    [+] 0 [-]\n\n";
        // actual string representation
        String actual = cg.stringRepresentation(cg.getVertices().get("root"), 0, 1, true);
        // check that string representation is correct
        assert expected.length() == actual.length();
    }

    /**
     * Tests that the string path generated is correct
     */
    @Test
    public void testStringPath()
    {
        // create graph with initial comments
        HashMap<String, List<String>> initialComments = new HashMap<>();
        initialComments.put("sampleUser1", List.of("sampleText1"));
        initialComments.put("sampleUser2", List.of("sampleText2"));
        CommentGraph cg = new CommentGraph("Test", "Test", initialComments);

        // get id
        String id = null;
        for (var i : cg.getVertices().keySet())
        {
            if (cg.getVertices().get(i).getText().equals("sampleText2"))
            {
                id = i;
                break;
            }
        }

        // expected string
        String expected = "> Test [id: root]\nTest\n[+] 0 [-]\n\n    > sampleUser2 [id: 5xySx]\n    sampleText2\n    [+] 0 [-]\n\n";
        // check that the path representation is correct
        String actual = cg.stringPath(cg.getVertices().get("root"), cg.getVertices().get(id));
        // check that the path is correct
        assert expected.length() == actual.length();
    }

    /**
     * Tests that the reply functionality works
     */
    @Test
    public void testReply()
    {
        // create graph
        CommentGraph cg = new CommentGraph("Test", "Test");
        cg.reply("root", "sampleText1", "sampleUser1");

        // check that the size increases when a comment is replied to
        assert cg.getVertices().size() == 2;
    }

    /**
     * Tests that the upvote functionality works
     */
    @Test
    public void testUpvote()
    {
        // create graph
        CommentGraph cg = new CommentGraph("Test", "Test");
        cg.upvote("root");
        // check that the vote is correct
        assert cg.getVertices().get("root").getVote() == 1;
    }

    /**
     * Tests that donate functionality works
     */
    @Test
    public void testDownvote()
    {
        // create graph
        CommentGraph cg = new CommentGraph("Test", "Test");
        cg.downvote("root");
        // check that the vote is correct
        assert cg.getVertices().get("root").getVote() == -1;
    }

    /**
     * Tests that string representation works
     */
    @Test
    public void testToString()
    {
        // create graph
        CommentGraph cg = new CommentGraph("Test", "Test");
        CommentGraph.Comment comment = cg.getComment("root");
        // expected string
        String expected = "Test root 0 Test";
        // actual representation
        String actual = comment.toString();
        // check that representation is correct
        assert expected.equals(actual);
    }

    /**
     * Tests that the formatted representation works
     */
    @Test
    public void testFormattedRepresentation()
    {
        // create graph
        CommentGraph cg = new CommentGraph("Test", "Test");
        CommentGraph.Comment comment = cg.getComment("root");
        // expected string
        String expected = "[> Test [id: root], Test, [+] 0 [-]]";
        // actual representation
        String actual = comment.formattedRepresentation().toString();
        // check that the representation is correct
        assert expected.equals(actual);
    }

    /**
     * Tests that id generation works correctly
     */
    @Test
    public void testGetId()
    {
        // create graph
        CommentGraph cg = new CommentGraph("Test", "Test");
        // expected value
        String expected = "root";
        // actual value
        String actual = cg.getComment("root").getId();
        // check that actual and expected are the same
        assert expected.equals(actual);
    }

    /**
     * Tests that text getter works correctly
     */
    @Test
    public void testGetText()
    {
        // create graph
        CommentGraph cg = new CommentGraph("Test", "Test");
        // expected value
        String expected = "Test";
        // actual value
        String actual = cg.getComment("root").getText();

        assert expected.equals(actual);
    }

    /**
     * Tests that user name getter works correctly
     */
    @Test
    public void testGetUserName()
    {
        // create graph
        CommentGraph cg = new CommentGraph("Test", "Test");
        // expected value
        String expected = "Test";
        // actual value
        String actual = cg.getComment("root").getUserName();

        assert expected.equals(actual);
    }

    /**
     * Tests that vote getter works correctly
     */
    @Test
    public void testGetVote()
    {
        // create graph
        CommentGraph cg = new CommentGraph("Test", "Test");
        // expected value
        int expected = 0;
        // actual value
        int actual = cg.getComment("root").getVote();

        assert expected == actual;
    }

    /**
     * Tests that depth getter works correctly
     */
    @Test
    public void testGetDepth()
    {
        // create graph
        CommentGraph cg = new CommentGraph("Test", "Test");
        // expected value
        int expected = 0;
        // actual value
        int actual = cg.getComment("root").getDepth();

        assert expected == actual;
    }

    /**
     * Tests that formatted representation getter works correctly
     */
    @Test
    public void testGetFormattedRepresentation()
    {
        // create graph
        CommentGraph cg = new CommentGraph("Test", "Test");
        CommentGraph.Comment comment = cg.getComment("root");
        // expected value
        String expected = "> Test [id: root]\nTest\n[+] 0 [-]\n\n";
        // actual value
        String actual = comment.getFormattedRepresentation();
        // check that actual and expected are the same
        assert expected.equals(actual);
    }
}
