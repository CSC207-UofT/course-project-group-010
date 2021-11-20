package Controller.Commands.CommandHelpers;

import Controller.Commands.CommandExecutor;
import UseCase.UserManager;

public class UserGetter {
    public void getUserPage(CommandExecutor ce) {
        UserManager um = ce.getUserManager();
        ce.setPageManager(um);
    }
}
