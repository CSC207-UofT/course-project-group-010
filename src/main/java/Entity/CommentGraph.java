package Entity;

import java.util.HashMap;
import java.util.Random;

public class CommentGraph {
    private HashMap<String, Comment> vertices;
    private int size;
    private Comment head;

    private class Comment {
        private String id;
        private String text;
        private int depth;
        private int upvote;
        private Boolean isQuestion;
        private Comment next;
        private Comment prev;

//        private Comment (String text, String prevId)
//        {
//
//        }

        private String idGenerator()
        {
            char[] charArray = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
                    'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                    'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                    'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

            Random rand = new Random();
            char[] encodedChars = new char[5];
            for (int i = 0; i < 5; i++)
            {
                encodedChars[i] = charArray[rand.nextInt(62)];
            }

            return new String(encodedChars);
        }
    }

    private class DijkstraAttributes {
        private Boolean visited;
    }

    public static void main(String[] args) {

    }
}



