package controller.commands.commandHelpers;

import controller.commands.CommandExecutor;
import exceptions.ArgumentException;
import exceptions.CommandNotAuthorizedException;
import exceptions.NotInDatabaseException;

import java.io.IOException;

public interface PageGetter {
    void getPage(CommandExecutor ce) throws CommandNotAuthorizedException, ArgumentException, IOException, ClassNotFoundException, NotInDatabaseException;

    String getSuccessString();
}
