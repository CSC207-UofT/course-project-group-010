package controller;

import constants.UserType;
import exceptions.CommandNotAuthorizedException;
import interfaces.IAuthorizable;
import interfaces.IHasPermission;

import java.util.List;
import java.util.Map;

/**
 * Helps check if users are allowed to take actions.
 */
@SuppressWarnings("StatementWithEmptyBody")
public class AuthHelper {
    /**
     * Checks if user is allowed to take a certain method on an authorizable object
     * @param a the object that the user wants to interact with
     * @param user the user
     * @param method the action the user wants to take
     * @throws CommandNotAuthorizedException if the user is not allowed to take the action
     */
    public void checkAuth(IAuthorizable a, IHasPermission user, String method) throws CommandNotAuthorizedException {

        Map<UserType, List<String>> authDict = a.getAuthDict();
        UserType permissionLevel = user.getPermissionLevel();
        if (!authDict.containsKey(permissionLevel)) {
            throw new CommandNotAuthorizedException("You cannot take this action.");
        } else if (authDict.get(permissionLevel).contains(method) ||
                authDict.get(permissionLevel).contains("all")) {
        } else {
            throw new CommandNotAuthorizedException("You are not able to take this action, or are not authorized to do so.");
        }

    }

}