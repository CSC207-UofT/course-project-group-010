package usecase;

import entity.CommentGraph;
import exceptions.InvalidIDException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CommentManagerTest2 {

    public CommentManager cm;
    public CommentPresenter cp;
    public CommentGraph cg;

    @Before
    public void setup() {
        cg = new CommentGraph("Comments", "Comments");
        cm = new CommentManager(cg);
    }

    @Test(expected = InvalidIDException.class)
    public void testReplyInvalid() throws InvalidIDException {
        cm.replyToComment("asdfasdf", "ha", "ha");
    }

    @Test
    public void testChildIDs() throws InvalidIDException {
        cm.replyToComment("root", "hasdf", "asd");
        List<String> children = cm.getChildIDs("root");
        assertEquals(1, cm.getChildIDs("root").size());
        assertEquals(true, cm.hasChildID("root", children.get(0)));
        assertEquals("root", cm.getParentComment(children.get(0)).getId());
        cm.vote("root", false);
        assertEquals(false, cm.hasChildID("root", "asdfadsgsdf"));
    }
}
