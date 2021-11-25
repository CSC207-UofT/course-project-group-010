package controller.commands.commandHelpers;

import controller.commands.CommandExecutor;
import usecase.UserManager;

public class UserGetter {
    public void getUserPage(CommandExecutor ce) {
        UserManager um = ce.getUserManager();
        ce.setPageManager(um);
    }
}
