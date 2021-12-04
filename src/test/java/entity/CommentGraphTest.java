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

    public static String unEscapeString(String s){
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<s.length(); i++)
            switch (s.charAt(i)){
                case '\n': sb.append("\\n"); break;
                case '\t': sb.append("\\t"); break;
                // ... rest of escape characters
                default: sb.append(s.charAt(i));
            }
        return sb.toString();
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



}
