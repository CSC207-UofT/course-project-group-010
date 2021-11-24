package Controller.commands.commandHelpers;

import Controller.commands.CommandExecutor;
import UseCase.UserManager;

public class UserGetter {
    public void getUserPage(CommandExecutor ce) {
        UserManager um = ce.getUserManager();
        ce.setPageManager(um);
    }
}
