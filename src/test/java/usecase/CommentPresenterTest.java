package usecase;

import constants.CommandConstants;
import constants.UserType;
import entity.CommentGraph;
import exceptions.ArgumentException;
import exceptions.InvalidIDException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CommentPresenterTest {
    public CommentManager cm;
    public CommentPresenter cp;
    public CommentGraph cg;

    @Before
    public void setup() {
        cg = new CommentGraph("Comments", "Comments");
        cm = new CommentManager(cg);
        cp = new CommentPresenter(cm);

    }

    @Test
    public void testCPBasic() throws InvalidIDException, ArgumentException {
        assertEquals("root", cp.getFullPath());
        cp.replyToComment("root", "asdfasdf", "kevin");
        List<String> children = cm.getChildIDs("root");
        cp.vote(true);
        cp.cdCommand(children.get(0));
        assertEquals("root/" + children.get(0), cp.getFullPath());
        cp.cdCommand("..");
        assertEquals("root", cp.getFullPath());
    }

    @Test
    public void testReplyVote() throws InvalidIDException {
        cp.vote("root", true);
        cp.vote("root", false);
        assertEquals(0, cm.getVote("root"));
        cp.vote(false);
        cp.vote(true);
        cp.replyToComment("asdfasdf", "kevin");
        assertEquals(0, cm.getVote("root"));
        assertEquals(1, cm.getChildIDs("root").size());
    }

    @Test(expected = ArgumentException.class)
    public void testInvalidCD() throws ArgumentException {
        cp.cdCommand("../../..");
    }

    @Test(expected = ArgumentException.class)
    public void testInvalidCD2() throws ArgumentException {
        cp.cdCommand("asdf/asdf/asdf/asdf/");
    }

    @Test
    public void testGetData() {
        assertTrue(cp.getData().containsKey(CommandConstants.allDataString));
    }

    @Test
    public void testAuthDict() {
        assertTrue(cp.getAuthDict().containsKey(UserType.STUDENT));
        assertTrue(cp.getAuthDict().containsKey(UserType.INSTRUCTOR));
    }
}
