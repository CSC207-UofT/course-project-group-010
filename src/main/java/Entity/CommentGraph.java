package Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CommentGraph {
    private HashMap<String, Comment> vertices;
    private int size;
    private Comment head;

    private void add_vertex(String id, Comment comment)
    {
        this.vertices.put(id, comment);
        this.size += 1;
    }

    private void link(String prevId, Comment comment)
    {
        Comment prevComment = this.vertices.get(prevId);
        prevComment.nav.next.add(comment);
        comment.nav.prev = prevComment;
        comment.depth = prevComment.depth + 1;
        this.size += 1;
    }

    private Comment createComment(String id, String text)
    {
        NavigationAttributes nav = new NavigationAttributes(null, null);
        InformationAttributes info = new InformationAttributes(id, text);
        return new Comment(nav, info, 0);
    }

    public void reply(String text, String prevId)
    {
        if (text.equals("") || !this.vertices.containsKey(prevId))
        {
            // do nothing
        }

        else
        {
            String uniqueId = genUniqueId();
            Comment comment = createComment(uniqueId, text);
            add_vertex(uniqueId, comment);
            link(prevId, comment);
        }
    }

    private String genUniqueId()
    {
        // makes sure that the id is in fact unique, there is a very small chance that this code will actually run
        // due to the number of possibilities, but this is here just in case.
        String uniqueId = genId();
        while (vertices.containsKey(uniqueId)){
            uniqueId = genId();
        }
        return uniqueId;
    }

    private String genId()
    {
        char[] charArray = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
                'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        Random rand = new Random();
        char[] encodedChars = new char[5];
        for (int i = 0; i < 5; i++) {
            encodedChars[i] = charArray[rand.nextInt(62)];
        }

        return new String(encodedChars);
    }

    private class Comment {
        private NavigationAttributes nav;
        private InformationAttributes info;
        private int depth;

        private Comment(NavigationAttributes nav, InformationAttributes info, int depth) {
            this.nav = nav;
            this.info = info;
            this.depth = depth;
        }
    }

    private class NavigationAttributes {
        private List<Comment> next;
        private Comment prev;
        private double nextDistance;
        private Boolean visited;

        private NavigationAttributes(Comment next, Comment prev) {
            this.next = next;
            this.prev = prev;
            this.nextDistance = Double.POSITIVE_INFINITY;
            this.visited = false;
        }
    }

    private class InformationAttributes {
        private String id;
        private String text;
        private int upvote;

        private InformationAttributes(String id, String text) {
            this.id = id;
            this.text = text;
            this.upvote = 0;
        }
    }
}



