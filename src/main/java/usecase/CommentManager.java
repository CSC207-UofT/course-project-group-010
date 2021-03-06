package usecase;

// imports

import entity.CommentGraph;
import exceptions.InvalidIDException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CommentManager Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * Class that handles comments in a CommentGraph
 */
public class CommentManager implements Serializable
{

//======================================================================================================================
// Comment Manager Instance Variables
//======================================================================================================================

    // initial CommentGraph
    final CommentGraph commentGraph;

//======================================================================================================================
// Comment Manager Constructors
//======================================================================================================================

    public CommentManager(CommentGraph commentGraph)
    {
        // Initialize CommentGraph
        this.commentGraph = commentGraph;
    }

//======================================================================================================================
// User Interaction Functions
//======================================================================================================================

    /**
     * Reply to a comment.
     *
     * @param commentId id of Comment to reply to.
     * @param text      Text of reply.
     * @param userName  name of user that created reply
     */
    public void replyToComment(String commentId, String text, String userName) throws InvalidIDException
    {
        if (this.commentGraph.getVertices().containsKey(commentId))
        {
            this.commentGraph.reply(commentId, text, userName);
        }

        else
        {
            throw new InvalidIDException();
        }
    }

    /**
     * upvote or downvote comment
     *
     * @param commentId id of comment to upvote or downvote
     * @param up        upvote or downvote
     */
    public void vote(String commentId, boolean up)
    {
        // upvote comment
        if (up)
        {
            this.commentGraph.upvote(commentId);
        }

        //downvote comment
        else
        {
            this.commentGraph.downvote(commentId);
        }
    }

//======================================================================================================================
// String Representation Functions
//======================================================================================================================

    /**
     * Helper function for getting string representation
     *
     * @param descendingSort sort by descending votes or not
     * @param upToDepth      get up to a certain depth
     * @param startComment   comment to start from
     * @return String representation.
     */
    private String getThreadHelper(Boolean descendingSort, int upToDepth, CommentGraph.Comment startComment)
    {
        // get depth to get String representation up to.
        int endDepth;

        // if upToDepth is less than 0 simply get the representation up to the MaxDepth.
        if (upToDepth < 0)
        {
            endDepth = this.commentGraph.getMaxDepth();
        }
        else
        {
            endDepth = upToDepth;
        }

        // return String representation.
        return commentGraph.stringRepresentation(startComment, 0, endDepth, descendingSort);
    }

    /**
     * Get complete String representation of CommentGraph
     *
     * @param descendingSort sort by descending votes or not
     * @param upToDepth      get up to a certain depth
     * @return String representation
     */
    public String displayEntireThread(Boolean descendingSort, int upToDepth)
    {
        // Comment to start from
        CommentGraph.Comment startComment = this.commentGraph.getComment("root");
        // return String representation
        return getThreadHelper(descendingSort, upToDepth, startComment);
    }

    /**
     * Get subset of String representation of Comment Graph
     *
     * @param startId        Comment to start from
     * @param descendingSort sort by descending votes or not
     * @param upToDepth      get up to a certain depth
     * @return String representation
     */
    public String displaySubsetThread(String startId, Boolean descendingSort, int upToDepth)
    {
        // Comment to start from
        CommentGraph.Comment startComment = this.commentGraph.getComment(startId);
        // return String representation
        return getThreadHelper(descendingSort, upToDepth, startComment);
    }

    /**
     * Generate a path in String form from one Comment to another.
     *
     * @param startId id of Comment to start at
     * @param endId   id of Comment to end at
     * @return the path from one Comment to another
     */
    public String getPath(String startId, String endId)
    {
        // Comment to start at
        CommentGraph.Comment startComment = this.commentGraph.getComment(startId);
        // Comment to end at
        CommentGraph.Comment endComment = this.commentGraph.getComment(endId);
        // return the path
        return this.commentGraph.stringPath(startComment, endComment);
    }

//======================================================================================================================
// Getters and Special Functions
//======================================================================================================================

    /**
     * Get the vote of a Comment given the id.
     *
     * @param id of Comment.
     * @return vote value.
     */
    public int getVote(String id)
    {
        return this.commentGraph.getComment(id).getVote();
    }

    /**
     * Gets the parent of a Comment given an id.
     *
     * @param id of Comment.
     * @return Comment Object.
     */
    public CommentGraph.Comment getParentComment(String id)
    {
        return this.commentGraph.getComment(id).getPrev();
    }

    /**
     * Gets the list of children of a Comment given an id.
     *
     * @param id of Comment.
     * @return list of Comment Objects.
     */
    public List<CommentGraph.Comment> getChildrenComments(String id)
    {
        return this.commentGraph.getComment(id).getNext();
    }

    /**
     * Checks if findID is the id of one of the children of a Comment given an ID
     *
     * @param startID id of Comment to start at
     * @param findID id of comment to find
     * @return true if findId is the id of one of the children of a Comment given an ID. false if it is not
     */
    public boolean hasChildID(String startID, String findID)
    {
        List<CommentGraph.Comment> lst = this.commentGraph.getComment(startID).getNext();
        for (CommentGraph.Comment cm : lst)
        {
            if (cm.getId().equals(findID))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets child ids of a comment(ids of all the replies)
     * @param startID id of the comment to get child IDs for
     * @return the child IDs, as a list
     */
    public List<String> getChildIDs(String startID) {
        List<CommentGraph.Comment> lst = this.commentGraph.getComment(startID).getNext();
        List<String> childIDs = new ArrayList<>();
        for (CommentGraph.Comment cm : lst) {
            childIDs.add(cm.getId());
        }
        return childIDs;
    }
}

