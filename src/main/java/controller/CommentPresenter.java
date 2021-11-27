package controller;

import constants.CommandConstants;
import constants.PermissionLevel;
import entity.CommentGraph;
import exceptions.ArgumentException;
import interfaces.IReadModifiable;
import usecase.CommentManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a class that extends the CommentManager functionality, and help the program present it.
 * It breaks the CommentManager's methods into larger, more human-related methods
 * It's like a wrapper class. I think that's the correct terminology
 */
// TODO consider moving this to usecase, or merging it with commentManager
public class CommentPresenter implements IReadModifiable {
    private CommentManager cm;
    private String currentID;
    private String fullPath;
    private Map<PermissionLevel, List<String>> authDict;


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
        List<String> arguments = parseArgumentString(arg);
        for (String id : arguments) {
            if (id.equals("..")) {
                checkoutParentID();
            } else {
                checkoutSingleID(id);
            }
        }
    }

    public String getFullPath() {
        return this.fullPath;
    }

    // Make use of the CommentManager's main functions that people will reasonably interact with.

    public void replyToComment(String commentID, String text, String userName) {
        this.cm.replyToComment(commentID, text, userName);
    }

    public void vote(String commentID, boolean up) {
        this.cm.vote(commentID, up);
    }

    private List<String> parseArgumentString(String argument) {
        return List.of(argument.split("/"));
    }

    private void checkoutSingleID(String id) throws ArgumentException {
        if (cm.hasChildID(this.currentID, id)) {
            this.currentID = id;
            this.fullPath += "/" + id;
        } else {
            throw new ArgumentException("Could not check out comment with id " + id);
        }
    }

    private void checkoutParentID() throws ArgumentException {
        if (cm.getParentComment(this.currentID) instanceof CommentGraph.Comment) {
            this.currentID = cm.getParentComment(this.currentID).getId();
            this.fullPath = this.fullPath.substring(0, this.fullPath.lastIndexOf("/"));
            this.fullPath = this.fullPath.trim();
        } else {
            throw new ArgumentException("parent comment not found for " + this.currentID);
        }
    }

    @Override
    public Map<PermissionLevel, List<String>> getAuthDict() {
        return this.authDict;
    }

    @Override
    public HashMap<String, Object> getData() throws IllegalArgumentException {
        HashMap<String, Object> map = new HashMap<>();
        String coolString = "Path: " + this.fullPath + "\n" + cm.displaySubsetThread(currentID, true, -1);
        map.put(CommandConstants.allDataString, coolString);
        return map;
    }

    private Map<PermissionLevel, List<String>> getDefaultAuthDict() {
        Map<PermissionLevel, List<String>> permDict = new HashMap<>();
        List<String> l = Arrays.asList("displayfullthread", "displaysubsetthread", "getpath", "reply", "vote", "print", "commentcd");
        List<String> studentPermissions = l;
        List<String> instructorPermissions = l;
        permDict.put(PermissionLevel.STUDENT, studentPermissions);
        permDict.put(PermissionLevel.INSTRUCTOR, instructorPermissions);
        return permDict;
    }
}