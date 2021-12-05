package entity;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class CommentGraphTest
{
    @Test
    public void testCommentGraphConstructor()
    {
        HashMap<String, List<String>> initialComments = new HashMap<>();
        initialComments.put("sampleUser1", List.of("sampleText1"));
        initialComments.put("sampleUser2", List.of("sampleText2"));
        CommentGraph cg = new CommentGraph("Test", "Test", initialComments);

        assert cg.getVertices().size() == 3;
    }

    @Test
    public void testCommentGraphEmptyConstructor()
    {
        CommentGraph cg = new CommentGraph("Test", "Test");
        assert cg.getVertices().size() == 1;
    }

    @Test
    public void testGetVertices()
    {
        CommentGraph cg = new CommentGraph("Test", "Test");
        assert cg.getVertices().containsKey("root");
    }

    @Test
    public void testGetComment()
    {
        CommentGraph cg = new CommentGraph("Test", "Test");
        String expected = "Test";
        String actual = cg.getComment("root").getText();
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