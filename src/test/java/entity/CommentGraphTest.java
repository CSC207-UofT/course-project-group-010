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

    @Test
    public void testGetMaxDepth()
    {
        CommentGraph cg = new CommentGraph("Test", "Test");
        cg.reply("root", "sampleText1", "sampleUser1");
        int expected = 1;
        int actual = cg.getMaxDepth();
        assert expected == actual;
    }

    @Test
    public void testGetSize()
    {
        HashMap<String, List<String>> initialComments = new HashMap<>();
        initialComments.put("sampleUser1", List.of("sampleText1"));
        initialComments.put("sampleUser2", List.of("sampleText2"));
        CommentGraph cg = new CommentGraph("Test", "Test", initialComments);

        int expected = 3;
        int actual = cg.getSize();

        assert actual == expected;
    }

    @Test
    public void testStringRepresentation()
    {
        HashMap<String, List<String>> initialComments = new HashMap<>();
        initialComments.put("sampleUser1", List.of("sampleText1"));
        initialComments.put("sampleUser2", List.of("sampleText2"));
        CommentGraph cg = new CommentGraph("Test", "Test", initialComments);

        String expected = "> Test [id: root]\nTest\n[+] 0 [-]\n\n    > sampleUser1 [id: 7DU2C]\n    sampleText1\n  " +
                "  [+] 0 [-]\n\n    > sampleUser2 [id: 0ZSJX]\n    sampleText2\n    [+] 0 [-]\n\n";
        String actual = cg.stringRepresentation(cg.getVertices().get("root"), 0, 1, true);

        assert expected.length() == actual.length();
    }

    @Test
    public void testStringPath()
    {
        HashMap<String, List<String>> initialComments = new HashMap<>();
        initialComments.put("sampleUser1", List.of("sampleText1"));
        initialComments.put("sampleUser2", List.of("sampleText2"));
        CommentGraph cg = new CommentGraph("Test", "Test", initialComments);

        String id = null;
        for (var i : cg.getVertices().keySet())
        {
            if (cg.getVertices().get(i).getText().equals("sampleText2"))
            {
                id = i;
                break;
            }
        }

        String expected = "> Test [id: root]\nTest\n[+] 0 [-]\n\n    > sampleUser2 [id: 5xySx]\n    sampleText2\n    [+] 0 [-]\n\n";
        String actual = cg.stringPath(cg.getVertices().get("root"), cg.getVertices().get(id));

        assert expected.length() == actual.length();
    }

    @Test
    public void testReply()
    {
        CommentGraph cg = new CommentGraph("Test", "Test");
        cg.reply("root", "sampleText1", "sampleUser1");

        assert cg.getVertices().size() == 2;
    }

    @Test
    public void testUpvote()
    {
        CommentGraph cg = new CommentGraph("Test", "Test");
        cg.upvote("root");
        assert cg.getVertices().get("root").getVote() == 1;
    }

    @Test
    public void testDownvote()
    {
        CommentGraph cg = new CommentGraph("Test", "Test");
        cg.downvote("root");
        assert cg.getVertices().get("root").getVote() == -1;
    }

    @Test
    public void testToString()
    {
        CommentGraph cg = new CommentGraph("Test", "Test");
        CommentGraph.Comment comment = cg.getComment("root");

        String expected = "Test root 0 Test";
        String actual = comment.toString();

        assert expected.equals(actual);
    }

    @Test
    public void testFormattedRepresentation()
    {
        CommentGraph cg = new CommentGraph("Test", "Test");
        CommentGraph.Comment comment = cg.getComment("root");

        String expected = "[> Test [id: root], Test, [+] 0 [-]]";
        String actual = comment.formattedRepresentation().toString();

        assert expected.equals(actual);
    }

    @Test
    public void testGetId()
    {
        CommentGraph cg = new CommentGraph("Test", "Test");
        String expected = "root";
        String actual = cg.getComment("root").getId();

        assert expected.equals(actual);
    }

    @Test
    public void testGetText()
    {
        CommentGraph cg = new CommentGraph("Test", "Test");
        String expected = "Test";
        String actual = cg.getComment("root").getText();

        assert expected.equals(actual);
    }

    @Test
    public void testGetUserName()
    {
        CommentGraph cg = new CommentGraph("Test", "Test");
        String expected = "Test";
        String actual = cg.getComment("root").getUserName();

        assert expected.equals(actual);
    }

    @Test
    public void testGetVote()
    {
        CommentGraph cg = new CommentGraph("Test", "Test");
        int expected = 0;
        int actual = cg.getComment("root").getVote();

        assert expected == actual;
    }

    @Test
    public void testGetDepth()
    {
        CommentGraph cg = new CommentGraph("Test", "Test");
        int expected = 0;
        int actual = cg.getComment("root").getDepth();

        assert expected == actual;
    }

    @Test
    public void testGetFormattedRepresentation()
    {
        CommentGraph cg = new CommentGraph("Test", "Test");
        CommentGraph.Comment comment = cg.getComment("root");

        String expected = "> Test [id: root]\nTest\n[+] 0 [-]\n\n";
        String actual = comment.getFormattedRepresentation();

        assert expected.equals(actual);
    }
}
