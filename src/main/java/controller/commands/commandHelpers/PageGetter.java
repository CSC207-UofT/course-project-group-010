package controller.commands.commandHelpers;

import controller.commands.CommandExecutor;
import exceptions.ArgumentException;
import exceptions.CommandNotAuthorizedException;
import exceptions.NotInDatabaseException;

import java.io.IOException;

/**
 * Any class which has the purpose to get a page. eg. to get the comment section page, course page, user profile page.
 */
public interface PageGetter {
    /**
     * Gets a page of a certain type
     * @param ce
     * @throws CommandNotAuthorizedException
     * @throws ArgumentException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws NotInDatabaseException
     */
    void getPage(CommandExecutor ce) throws CommandNotAuthorizedException, ArgumentException, IOException, ClassNotFoundException, NotInDatabaseException;

    /**
     * @return string to print if getPage is successful
     */
    String getSuccessString();
}
