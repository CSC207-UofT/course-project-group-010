package Entity;

import org.hamcrest.core.IsInstanceOf;

import java.util.*;

public class CommentGraphHelper {
    public List<CommentGraph.Comment> commentSort(List<CommentGraph.Comment> comments, boolean reverse) {
        comments.sort(Comparator.comparing(CommentGraph.Comment::getVote));

        if (reverse) {
            Collections.reverse(comments);
        }

        return comments;
    }

    public List<CommentGraph.Comment> depthFirstPath(CommentGraph commentGraph, String startId, String endId)
    {
        CommentGraph.Comment curr = commentGraph.getVertices().get(endId);
        List<CommentGraph.Comment> path = new ArrayList<>(){};
        path.add(curr);
        while (!curr.getId().equals(startId))
        {
            curr = curr.getPrev();
            path.add(curr);

            if (curr.getId().equals("root") && !startId.equals("root"))
            {
                return new ArrayList<>(){};
            }
        }

        return path;
    }

    public String genId() {
        char[] alphabet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
                'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        Random rand = new Random();
        char[] encodedChars = new char[5];
        for (int i = 0; i < 5; i++) {
            encodedChars[i] = alphabet[rand.nextInt(62)];
        }

        return new String(encodedChars);
    }

    private class Queue {
        private List<Object> items;

        private Queue(Object first) {
            if (first == null) {
                this.items = new ArrayList<>() {
                };
            } else {
                this.items = new ArrayList<>() {
                };
                this.items.add(first);
            }
        }

        private Boolean isEmpty() {
            return this.items.size() == 0;
        }

        private void add(Object item) {
            this.items.add(item);
        }

        private Object remove_lifo() {
            if (this.isEmpty()) {
                throw new NoSuchElementException();
            } else {
                Object itemToReturn = this.items.get(0);
                this.items.remove(0);
                return itemToReturn;
            }
        }

        private List<Object> toList() {
            return this.items;
        }
    }
}


