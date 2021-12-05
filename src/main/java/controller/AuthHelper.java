package controller;

import constants.UserType;
import exceptions.CommandNotAuthorizedException;
import interfaces.IAuthorizable;
import interfaces.IHasPermission;

import java.util.List;
import java.util.Map;

public class AuthHelper {
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