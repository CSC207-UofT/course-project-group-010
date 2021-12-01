package controller;

import constants.UserType;
import exceptions.CommandNotAuthorizedException;
import interfaces.IAuthorizable;
import interfaces.IHasPermission;

import java.util.List;
import java.util.Map;

public class AuthHelper {
    public boolean checkAuth(IAuthorizable a, IHasPermission user, String method) throws CommandNotAuthorizedException {

        Map<UserType, List<String>> authDict = a.getAuthDict();
        UserType permissionLevel = user.getPermissionLevel();
        if (!authDict.containsKey(permissionLevel)) {
            throw new CommandNotAuthorizedException("You do not have the permission to take this action.");
        } else if (authDict.get(permissionLevel).contains(method) ||
                authDict.get(permissionLevel).contains("all")) {
            return true;
        } else {
            throw new CommandNotAuthorizedException("You do not have the permission to take this action");
        }

    }

}