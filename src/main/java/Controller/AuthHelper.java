package Controller;

import constants.PermissionLevel;
import exceptions.CommandNotAuthorizedException;
import Interface.IAuthorizable;
import Interface.IHasPermission;

import java.util.List;
import java.util.Map;

public class AuthHelper {
    public boolean checkAuth(IAuthorizable a, IHasPermission user, String method) throws CommandNotAuthorizedException {

        Map<PermissionLevel, List<String>> authDict = a.getAuthDict();
        PermissionLevel permissionLevel = user.getPermissionLevel();
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