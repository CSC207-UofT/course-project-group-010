package Entity;

import org.hamcrest.core.IsInstanceOf;

import java.util.*;

public class CommentGraphHelper {
    public List<CommentGraph.Comment> commentSort(List<CommentGraph.Comment> comments, boolean reverse) {
        comments.sort(Comparator.comparing(CommentGraph.Comment::getUpvote));

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
        while (!curr.getInfo().getId().equals(startId))
        {
            curr = curr.getNav().getPrev();
            System.out.println(curr.toString());
            path.add(curr);
        }

        return path;
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


