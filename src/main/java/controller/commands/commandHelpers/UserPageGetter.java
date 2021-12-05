package controller.commands.commandHelpers;

import controller.commands.CommandExecutor;
import usecase.UserManager;

/**
 * Gets the user's profile page. This class ensures the single responsibility principle is being followed.
 */
public class UserPageGetter implements PageGetter {
    private UserManager um;

    /**
     * Gets the user's profile page
     * @param ce command executor to get the page from
     */
    public void getPage(CommandExecutor ce) {
        um = ce.getUserManager();
        ce.setPageManager(um);
    }

    /**
     * @return the string to print upon success
     */
    @Override
    public String getSuccessString() {
        return "now viewing profile of " + um.getID();
    }
}
