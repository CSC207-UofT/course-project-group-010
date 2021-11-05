package Entity;

import org.hamcrest.core.AnyOf;

import javax.lang.model.type.NullType;
import java.util.HashMap;
import java.util.Random;

public class CommentGraph {
    private HashMap<String, Comment> vertices;
    private int size;
    private Comment head;

    private String idGenerator() {
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
        private Comment next;
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



