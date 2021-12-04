package entity;

import org.junit.Test;

import java.util.*;

public class CommentGraphHelperTest
{
    /**
     * Tests that the commentSort function in CommentGraphHelper returns a sorted list
     */
    @Test
    public void testCommentSort()
    {
        // create commentGraph with initial comments
        HashMap<String, List<String>> initialComments = new HashMap<>();
        initialComments.put("sampleUser1", List.of("sampleText1"));
        initialComments.put("sampleUser2", List.of("sampleText2"));
        CommentGraph cg = new CommentGraph("Test", "Test", initialComments);

        // find id of specified comment
        String id = null;
        for (var i : cg.getVertices().keySet())
        {
            if (cg.getVertices().get(i).getText().equals("sampleText2"))
            {
                id = i;
                break;
            }
        }

        // upvote the id with the specified comment
        cg.upvote(id);

        // get list of comments
        List<CommentGraph.Comment> comments = new ArrayList<>(cg.getVertices().values());

        // sort the list of comments
        CommentGraphHelper commentGraphHelper = new CommentGraphHelper();
        commentGraphHelper.commentSort(comments, true);

        // get list of text from comments
        List<String> actual = new ArrayList<>();
        for (var i : comments)
        {
            actual.add(i.getText());
        }

        // assert actual == expected
        assert (Objects.equals(actual.get(0), "sampleText2"));
    }

    /**
     * Tests that depthFirstPath generates a path from start to end
     */
    @Test
    public void testDepthFirstPath()
    {
        // create commentGraph with initial comments
        HashMap<String, List<String>> initialComments = new HashMap<>();
        initialComments.put("sampleUser1", List.of("sampleText1"));
        initialComments.put("sampleUser2", List.of("sampleText2"));
        CommentGraph cg = new CommentGraph("Test", "Test", initialComments);

        // create new comment under root with reply
        cg.reply("root", "sampleText3", "sampleUser3");

        // find the id of the newly created comment
        String id = null;
        for (var i : cg.getVertices().keySet())
        {
            if (cg.getVertices().get(i).getText().equals("sampleText3"))
            {
                id = i;
                break;
            }
        }

        // generate the path
        CommentGraphHelper commentGraphHelper = new CommentGraphHelper();
        List<CommentGraph.Comment> path = commentGraphHelper.depthFirstPath(cg, "root", id);

        // expected output
        List<String> expected = List.of("sampleText3", "Test");

        // actual output
        List<String> actual = new ArrayList<>();
        for (var i : path)
        {
            actual.add(i.getText());
        }

        // assert actual == expected
        assert expected.equals(actual);
    }

    @Test
    public void testGenId()
    {
        CommentGraphHelper commentGraphHelper = new CommentGraphHelper();
        String generatedId = commentGraphHelper.genId();

        assert generatedId.length() == 5 && generatedId.matches("[A-Za-z0-9]+");
    }
}
