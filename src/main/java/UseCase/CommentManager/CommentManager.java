package UseCase.CommentManager;

import Entity.CommentGraph;

import java.util.List;

/**
 * Class that handles comments in a CommentGraph
 */
public class CommentManager
{
    // initial CommentGraph
    CommentGraph commentGraph;

    /**
     * CommentManager constructor
     *
     * @param commentGraph
     */
    public CommentManager(CommentGraph commentGraph)
    {
        // Initialize CommentGraph
        this.commentGraph = commentGraph;
    }

    public int getVote(String id)
    {
        return this.commentGraph.getComment(id).getVote();
    }

    public CommentGraph.Comment getParentComment(String id)
    {
        return this.commentGraph.getComment(id).getPrev();
    }

    public List<CommentGraph.Comment> getChildrenComments(String id)
    {
        return this.commentGraph.getComment(id).getNext();
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
     * Generate a path in String form from one Comment to another.
     *
     * @param startId id of Comment to start at
     * @param endId   if of Comment to end at
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

    /**
     * Reply to a comment.
     *
     * @param commentId id of Comment to reply to.
     * @param text      Text of reply.
     * @param userName  name of user that created reply
     */
    public void replyToComment(String commentId, String text, String userName)
    {
        // reply to comment.
        this.commentGraph.reply(commentId, text, userName);
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
}
