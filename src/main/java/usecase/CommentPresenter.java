package usecase;

import constants.CommandConstants;
import constants.UserType;
import entity.CommentGraph;
import exceptions.ArgumentException;
import exceptions.InvalidIDException;
import interfaces.IReadModifiable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wrapper class for CommentManager
 * breaks the CommentManager's methods into larger, more human-related methods.
 * Keeps technical methods in CommentManager, to keep things open for extension.
 */
public class CommentPresenter implements IReadModifiable {
    private CommentManager cm;
    private String currentID;
    private String fullPath;
    private Map<UserType, List<String>> authDict;


    public CommentPresenter(CommentManager cm) {
        this.cm = cm;
        this.currentID = "root";
        this.fullPath = "root";
        this.authDict = getDefaultAuthDict();
    }

    public CommentManager getCommentManager() {
        return this.cm;
    }

    /**
     * Works like the cd command in linux.
     * You can enter something such as [id1]/[id2] and it will update the fullPath and currentID accordingly.
     * .. is used to go backwards, eg. ../..
     * @param arg
     * @throws ArgumentException
     */
    public void cdCommand(String arg) throws ArgumentException {
        // Fun fact, cd statnds for change dcomment !
        List<String> arguments = parseArgumentString(arg);
        String pathTraversed = "";
        for (String id : arguments) {
            try {
                if (id.equals("..")) {
                    checkoutParentID();
                } else {
                    checkoutSingleID(id);
                }
                pathTraversed += id + "/";
            } catch (InvalidIDException e) {
                String errorStr = e.getMessage() + "\n(we managed to traverse " + pathTraversed + ")";
                throw new ArgumentException(errorStr);
            }
        }
    }

    public String getFullPath() {
        return this.fullPath;
    }

    // Make use of the CommentManager's main functions that people will reasonably interact with.

    /**
     * Replies to a comment with specified text.
     * @param commentID
     * @param text
     * @param userName
     * @throws InvalidIDException
     */
    public void replyToComment(String commentID, String text, String userName) throws InvalidIDException
    {
        this.cm.replyToComment(commentID, text, userName);
    }

    /**
     * Replies to current comment with specified text.
     * @param text
     * @param userName
     * @throws InvalidIDException
     */
    public void replyToComment(String text, String userName) throws InvalidIDException {
        this.cm.replyToComment(currentID, text, userName);
    }

    /**
     * Upvotes/Downvotes a comment with given id
     * @param commentID
     * @param up
     */
    public void vote(String commentID, boolean up) {
        this.cm.vote(commentID, up);
    }

    /**
     * Upvotes/downvotes the current comment
     * @param up
     */
    public void vote(boolean up) {
        this.cm.vote(currentID, up);
    }

    private List<String> parseArgumentString(String argument) {
        return List.of(argument.split("/"));
    }

    // helpers for the cd command

    private void checkoutSingleID(String id) throws InvalidIDException {
        if (cm.hasChildID(this.currentID, id)) {
            this.currentID = id;
            this.fullPath += "/" + id;
        } else {
            throw new InvalidIDException("Could not check out comment with id " + id);
        }
    }

    private void checkoutParentID() throws InvalidIDException {
        if (cm.getParentComment(this.currentID) instanceof CommentGraph.Comment) {
            this.currentID = cm.getParentComment(this.currentID).getId();
            this.fullPath = this.fullPath.substring(0, this.fullPath.lastIndexOf("/"));
            this.fullPath = this.fullPath.trim();
        } else {
            throw new InvalidIDException("parent comment not found for " + this.currentID);
        }
    }

    @Override
    public Map<UserType, List<String>> getAuthDict() {
        return this.authDict;
    }

    @Override
    public HashMap<String, Object> getData() throws IllegalArgumentException {
        HashMap<String, Object> map = new HashMap<>();
        String coolString = "Path: " + this.fullPath + "\n" + cm.displaySubsetThread(currentID, true, -1);
        map.put(CommandConstants.allDataString, coolString);
        return map;
    }

    private Map<UserType, List<String>> getDefaultAuthDict() {
        Map<UserType, List<String>> permDict = new HashMap<>();
        List<String> l = Arrays.asList("displayfullthread", "displaysubsetthread", "getpath", "reply", "vote", "print", "commentcd");
        List<String> studentPermissions = l;
        List<String> instructorPermissions = l;
        permDict.put(UserType.STUDENT, studentPermissions);
        permDict.put(UserType.INSTRUCTOR, instructorPermissions);
        return permDict;
    }
}