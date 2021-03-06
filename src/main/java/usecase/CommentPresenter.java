package usecase;

import constants.CommandConstants;
import constants.UserType;
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
    private final CommentManager cm;
    private String currentID;
    private String fullPath;
    private final Map<UserType, List<String>> authDict;


    public CommentPresenter(CommentManager cm) {
        this.cm = cm;
        this.currentID = "root";
        this.fullPath = "root";
        this.authDict = getDefaultAuthDict();
    }

    /**
     * Works like the cd command in linux, but for comments. Navigates to a specific reply, or backwards to the parent comment.
     * Will update the fullPath and currentID accordingly.
     * @param arg [id1]/[id2]... will make the program go to subcomment id1, then subcomment id2. .. will make the program
     *            go backwards.
     * @throws ArgumentException if we cannot cd to the place
     */
    public void cdCommand(String arg) throws ArgumentException {
        // Fun fact, cd stands for change dcomment !
        List<String> arguments = parseArgumentString(arg);
        StringBuilder pathTraversed = new StringBuilder();
        for (String id : arguments) {
            try {
                if (id.equals("..")) {
                    checkoutParentID();
                } else {
                    checkoutSingleID(id);
                }
                pathTraversed.append(id).append("/");
            } catch (InvalidIDException e) {
                String errorStr = e.getMessage() + "\n(we managed to traverse " + pathTraversed + ")";
                throw new ArgumentException(errorStr);
            }
        }
    }

    // Make use of the CommentManager's main functions that people will reasonably interact with.

    /**
     * Replies to a comment with specified text.
     * @param commentID comment id to reply to
     * @param text text to add
     * @param userName username to display
     * @throws InvalidIDException if invalid id is entered
     */
    public void replyToComment(String commentID, String text, String userName) throws InvalidIDException
    {
        this.cm.replyToComment(commentID, text, userName);
    }

    /**
     * Replies to CURRENT comment with specified text
     * @param text text of the reply
     * @param userName username to display
     * @throws InvalidIDException if invalid id is entered
     */
    public void replyToComment(String text, String userName) throws InvalidIDException {
        this.cm.replyToComment(currentID, text, userName);
    }

    /**
     * Upvotes/Downvotes a comment with given id
     * @param commentID id to vote on
     * @param up whether to vote up or down.
     */
    public void vote(String commentID, boolean up) {
        this.cm.vote(commentID, up);
    }

    /**
     * Upvotes/downvotes the CURRENT comment
     * @param up whether to vote up or down
     */
    public void vote(boolean up) {
        this.cm.vote(currentID, up);
    }

    public String getFullPath() {
        return this.fullPath;
    }

    /**
     * Gets authorization dictionary, part of the IAuthorizable interface.
     * @return authorization dictionary
     */
    @Override
    public Map<UserType, List<String>> getAuthDict() {
        return this.authDict;
    }

    /**
     * Gets relevant data to be presented on the screen. Part of the IGettable interface.
     * @return the data as a map
     */
    @Override
    public HashMap<String, Object> getData() {
        HashMap<String, Object> map = new HashMap<>();
        String coolString = "Path: " + this.fullPath + "\n" + cm.displaySubsetThread(currentID, true, -1);
        map.put(CommandConstants.allDataString, coolString);
        return map;
    }

    /**
     * Parses the user's argument string for the cd command. eg. "../abc/.." --> ["..", "abc", ".."]
     * @param argument argument string
     * @return parsed argument string as a list
     */
    private List<String> parseArgumentString(String argument) {
        return List.of(argument.split("/"));
    }

    // helpers for the cd command

    /**
     * helper method for checking out an id. Helps with cd [id]
     * @param id the id of the comment to checkout
     * @throws InvalidIDException if it is an invalid id
     */
    private void checkoutSingleID(String id) throws InvalidIDException {
        if (cm.hasChildID(this.currentID, id)) {
            this.currentID = id;
            this.fullPath += "/" + id;
        } else {
            throw new InvalidIDException("Could not check out comment with id " + id);
        }
    }

    /**
     * helper method for checking out parent comment. Helps with cd ..
     * @throws InvalidIDException if we cannot find a parent comment
     */
    private void checkoutParentID() throws InvalidIDException {
        if (cm.getParentComment(this.currentID) != null) {
            this.currentID = cm.getParentComment(this.currentID).getId();
            this.fullPath = this.fullPath.substring(0, this.fullPath.lastIndexOf("/"));
            this.fullPath = this.fullPath.trim();
        } else {
            throw new InvalidIDException("parent comment not found for " + this.currentID);
        }
    }

    /**
     * Gets the default authorization dictionary. All users can take all possible actions on this commentPresenter.
     * Helper method for the getAuthDict() method.
     * @return permission dictionary
     */
    private Map<UserType, List<String>> getDefaultAuthDict() {
        Map<UserType, List<String>> permDict = new HashMap<>();
        List<String> l = Arrays.asList("displayfullthread", "displaysubsetthread", "getpath", "reply", "vote", "print", "commentcd");
        permDict.put(UserType.STUDENT, l);
        permDict.put(UserType.INSTRUCTOR, l);
        return permDict;
    }
}