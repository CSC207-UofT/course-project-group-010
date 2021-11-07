package Entity;

import org.hamcrest.core.IsInstanceOf;

import java.util.*;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Comment Graph Helper Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 * Class that contains a variety of helper subclasses and methods for CommentGraph.java
 */
public class CommentGraphHelper
{

//======================================================================================================================
// Sorting Methods
//======================================================================================================================

    /**
     * Method that sorts a List of comments by their upvote value.
     * @param comments List of Comments.
     * @param reverse Boolean that decides if sorted by ascending (reverse=false) or descending (reverse=true)
     * @return List of sorted Comments.
     */
    public List<CommentGraph.Comment> commentSort(List<CommentGraph.Comment> comments, boolean reverse)
    {
        // sort by vote using built in Java Timsort
        comments.sort(Comparator.comparing(CommentGraph.Comment::getVote));

        // reverse List if reverse==true
        if (reverse)
        {
            Collections.reverse(comments);
        }

        // return sorted List of Comments
        return comments;
    }

//======================================================================================================================
// Path Finding Methods
//======================================================================================================================

    /**
     * Method that generates the path from one Comment to another given that there is a valid path.
     * @param commentGraph graph of Comments.
     * @param startId id of start Comment.
     * @param endId id of end Comment.
     * @return a List containing the path
     */
    public List<CommentGraph.Comment> depthFirstPath(CommentGraph commentGraph, String startId, String endId)
    {
        // get end Comment
        CommentGraph.Comment curr = commentGraph.getVertices().get(endId);
        // Initialize empty path
        List<CommentGraph.Comment> path = new ArrayList<>()
        {
        };
        // add end Comment to path
        path.add(curr);
        // until start Comment is reached
        while (!curr.getId().equals(startId))
        {
            // set curr to Parent Comment.
            curr = curr.getPrev();
            // add curr to path.
            path.add(curr);

            // if root is reached but root was not the start id, then there is no valid path
            if (curr.getId().equals("root") && !startId.equals("root"))
            {
                // return empty list because no valid path
                return new ArrayList<>()
                {
                };
            }
        }

        // return path from start to end Comment.
        return path;
    }

//======================================================================================================================
// Id Generation
//======================================================================================================================

    /**
     * Generates a unique String id with base 62 encoding for human-readable ids.
     * @return unique String id.
     */
    public String genId()
    {
        // array of allowed characters
        char[] alphabet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
                'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        // new random variable
        Random rand = new Random();
        // empty array of size 5
        char[] encodedChars = new char[5];
        // pick random character from alphabet with base 62 encoding
        for (int i = 0; i < 5; i++)
        {
            // add character to encodedChars
            encodedChars[i] = alphabet[rand.nextInt(62)];
        }

        // return unique String id
        return new String(encodedChars);
    }
}


