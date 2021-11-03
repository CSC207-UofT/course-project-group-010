package Controller;

import Exceptions.CommandNotAuthorizedException;
import Interface.IAuthorizable;
import Interface.IHasPermission;

import java.util.List;
import java.util.Map;

public class AuthHelper {
    public boolean checkAuth(IAuthorizable a, IHasPermission user, String method) throws CommandNotAuthorizedException {

        Map<Integer, List<String>> authDict = a.getAuthDict();
        int permissionLevel = user.getPermissionLevel();
        if (!authDict.containsKey(permissionLevel)) {
            throw new CommandNotAuthorizedException("You do not have the permission to take this action.");
        } else if (authDict.get(permissionLevel).contains(method)) {
            return true;
        } else {
            throw new CommandNotAuthorizedException("You do not have the permission to take this action");
        }

    }

}