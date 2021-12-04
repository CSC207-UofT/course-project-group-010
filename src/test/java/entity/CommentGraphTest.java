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


}
