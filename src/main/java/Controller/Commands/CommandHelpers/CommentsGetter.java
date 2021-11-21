package Controller.Commands.CommandHelpers;

import Controller.Commands.CommandExecutor;
import Exceptions.CommandNotAuthorizedException;
import UseCase.CourseManager.CourseManager;

import java.io.InvalidClassException;

public class CommentsGetter {

    public void getCommentSection(CommandExecutor ce) throws CommandNotAuthorizedException {
        try {
            ce.setPageManager(((CourseManager) ce.getPageManager()).getOnlyComment());
        } catch (RuntimeException e) {
            // casting exception, etc.
            throw new CommandNotAuthorizedException("Current viewing page does not have a comment section");
        }
        // otherwise, just throw whatever exception upwards.
    }
}
