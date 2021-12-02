package usecase;

// imports

import constants.CommandConstants;
import entity.CommentGraph;
import exceptions.InvalidIDException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CommentManager Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// TODO delete anything that isn't used, and justify the existence of the other methods in this class.
/**
 * Class that handles comments in a CommentGraph
 */
public class CommentManager implements Serializable
{

//======================================================================================================================
// Comment Manager Instance Variables
//======================================================================================================================

    // initial CommentGraph
    CommentGraph commentGraph;
    // authorization mapping
    // Map<PermissionLevel, List<String>> authDict;

//======================================================================================================================
// Comment Manager Constructors
//======================================================================================================================

    public CommentManager(CommentGraph commentGraph)
    {
        // Initialize CommentGraph
        this.commentGraph = commentGraph;
        // get Default authdict
        // this.authDict = getDefaultAuthDict();
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
            // reply to comment.
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

//======================================================================================================================
// Searching Functions
//======================================================================================================================

    /**
     * Searches for comments by username and returns their formatted representation.
     *
     * @param userName to search for
     * @return list of formatted comments.
     */
//    public List<String> getCommentsByUserName(String userName)
//    {
//        // new empty list
//        List<String> comments = new ArrayList<>()
//        {
//        };
//
//        // dictionary of vertices from CommentGraph
//        HashMap<String, CommentGraph.Comment> vertices = this.commentGraph.getVertices();
//
//        // search for text
//        for (String key : vertices.keySet())
//        {
//            // if text found
//            if (vertices.get(key).getUserName().contains(userName))
//            {
//                // add to list
//                comments.add(vertices.get(key).getFormattedRepresentation());
//            }
//        }
//
//        // return list
//        return comments;
//    }

    /**
     * Searches for comments by id and returns their formatted representation.
     *
     * @param id to search for.
     * @return list of formatted comments.
     */
//    public String getCommentById(String id) throws InvalidIDException
//    {
//        // if id is valid (i.e, it exists)
//        if (commentGraph.getVertices().containsKey(id))
//        {
//            // return formatted comment
//            return this.commentGraph.getComment(id).getFormattedRepresentation();
//        }
//
//        // if id is invalid (i.e, it doesn't exist)
//        else
//        {
//            // throw InvalidIDException
//            throw new InvalidIDException();
//        }
//    }

    /**
     * Searches for comments by id and returns their formatted representation.
     *
     * @param text String to search for.
     * @return List of formatted Strings.
     */
//    public List<String> getCommentsByText(String text)
//    {
//        // new empty list
//        List<String> comments = new ArrayList<>()
//        {
//        };
//
//        // dictionary of vertices from CommentGraph
//        HashMap<String, CommentGraph.Comment> vertices = this.commentGraph.getVertices();
//
//        // search for text
//        for (String key : vertices.keySet())
//        {
//            // if text found
//            if (vertices.get(key).getText().contains(text))
//            {
//                // add to list
//                comments.add(vertices.get(key).getFormattedRepresentation());
//            }
//        }
//
//        // return list
//        return comments;
//    }

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
     * Get the depth of the specified Comment.
     *
     * @param id of Comment.
     * @return depth value.
     */
    // TODO consider removing
//    public int getDepth(String id)
//    {
//        return this.commentGraph.getComment(id).getDepth();
//    }

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
     * @param startID
     * @param findID
     * @return
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

    public List<String> getChildIDs(String startID) {
        List<CommentGraph.Comment> lst = this.commentGraph.getComment(startID).getNext();
        List<String> childIDs = new ArrayList<>();
        for (CommentGraph.Comment cm : lst) {
            childIDs.add(cm.getId());
        }
        return childIDs;
    }

//======================================================================================================================
// Data and Authorization Functions
//======================================================================================================================

    /**
     * Implementing the IGettable interface, gives the entire thread by default.
     *
     * @return
     */
    public HashMap<String, Object> getData()
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put(CommandConstants.allDataString, displayEntireThread(true, -1));
        return map;

        /*
        Commands that a user would have access to would be:
        > displayFullThread(uses displayEntireThread function)
        > displaySubsetThread(uses displaySubsetThread function)
        > displayPath(uses getPath function)
        > reply(uses replyToComment function)
        > vote(uses vote function)

        > parentComment (uses getParentComment function)
        > childrenComments (uses getChildrenComments function)
        > depthOfComment (uses getDepth function)
        > searchByUser(uses getCommentsByUserName function)
        > searchById(uses getCommentById)
        > searchByText(uses getCommentsByText)
         */
        // TODO search functions potentially[low prio]
    }

//    private Map<PermissionLevel, List<String>> getDefaultAuthDict()
//    {
//        Map<PermissionLevel, List<String>> permDict = new HashMap<>();
//        List<String> l = Arrays.asList("none");
//        List<String> studentPermissions = l;
//        List<String> instructorPermissions = l;
//        permDict.put(PermissionLevel.STUDENT, studentPermissions);
//        permDict.put(PermissionLevel.INSTRUCTOR, instructorPermissions);
//        return permDict;
//    }
//
//    @Override
//    public Map<PermissionLevel, List<String>> getAuthDict()
//    {
//        return this.authDict;
//    }
}

