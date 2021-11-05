package Entity;

import java.util.HashMap;

public class CommentGraph
{
    private HashMap<String, Comment> vertices;
    private int size;
    private Comment head;

    private class Comment
    {
        private String id;
        private String text;
        private int depth;
        private int upvote;
        private Boolean isQuestion;
        private Comment next;
        private Comment prev;
    }

    private class DijkstraAttributes
    {
        private Boolean visited;
    }
}
