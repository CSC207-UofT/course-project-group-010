package Entity;

import org.hamcrest.core.IsInstanceOf;

import java.util.*;

public class CommentGraphHelper{
    public List<CommentGraph.Comment> commentSort(List<CommentGraph.Comment> comments, boolean reverse)
    {
       comments.sort(Comparator.comparing(CommentGraph.Comment::getUpvote));

       if (reverse)
       {
           Collections.reverse(comments);
       }

       return comments;
    }




}
