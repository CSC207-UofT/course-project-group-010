package UseCase.CommentManager;

import Constants.PermissionLevel;
import Entity.CommentGraph;
import Interface.IReadModifiable;

import java.io.Serializable;
import java.util.*;

/**
 * Class that handles comments in a CommentGraph
 */
public class CommentManager implements IReadModifiable, Serializable
{
    // initial CommentGraph
    CommentGraph commentGraph;
    Map<PermissionLevel, List<String>> authDict;

    /**
     * CommentManager constructor
     *
     * @param commentGraph
     */
    public CommentManager(CommentGraph commentGraph)
    {
        // Initialize CommentGraph
        this.commentGraph = commentGraph;
        // get Default authdict
        this.authDict = getDefaultAuthDict();
    }

    /**
     * Gets the formatted String representation of a comment by its id.
     *
     * @param id
     * @return
     */
    public String getCommentById(String id)
    {
        return this.commentGraph.getComment(id).getFormattedRepresentation();
    }

    /**
     * Gets a list of formatted String representations of Comments that contain the provided String in their text
     * attribute.
     *
     * @param text String to search for.
     * @return List of formatted Strings.
     */
    public List<String> getCommentsByText(String text)
    {
        // new empty list
        List<String> comments = new ArrayList<>()
        {
        };

        // dictionary of vertices from CommentGraph
        HashMap<String, CommentGraph.Comment> vertices = this.commentGraph.getVertices();

        // search for text
        for (String key : vertices.keySet())
        {
            // if text found
            if (vertices.get(key).getText().contains(text))
            {
                // add to list
                comments.add(vertices.get(key).getFormattedRepresentation());
            }
        }

        // return list
        return comments;
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
     * Get the depth of the specified Comment.
     *
     * @param id of Comment.
     * @return depth value.
     */
    public int getDepth(String id)
    {
        return this.commentGraph.getComment(id).getDepth();
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

    //IMPORTANT: the getData method doesn't really suit this type of data, as graphs are very complex and dynamic objects.
    public HashMap<String, Object> getData()
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put("FullThread", displayEntireThread(true, -1));
        return map;

        /*
        Commands that a user would have access to would be:
        > displayFullThread(uses displayEntireThread function)
        > displaySubsetThread(uses displaySubsetThread function)
        > displayPath(uses getPath function)
        > reply(uses replyToComment function)
        > vote(uses vote function)
         */
    }

    private Map<PermissionLevel, List<String>> getDefaultAuthDict() {
        Map<PermissionLevel, List<String>> permDict = new HashMap<>();
        // for now, everyone can make a new user
        List<String> l = Arrays.asList("displayfullthread", "displaysubsetthread", "getpath", "reply", "vote");
        List<String> studentPermissions = l;
        List<String> instructorPermissions = l;
        permDict.put(PermissionLevel.STUDENT, studentPermissions);
        permDict.put(PermissionLevel.INSTRUCTOR, instructorPermissions);
        return permDict;
    }

    @Override
    public Map<PermissionLevel, List<String>> getAuthDict() {
        return this.authDict;
    }
}
