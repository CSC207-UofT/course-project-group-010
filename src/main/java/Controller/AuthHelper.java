package Controller;

import Controller.Commands.CommandExceptions.CommandNotAuthorizedException;
import Entity.User;

public class AuthHelper {
    public boolean checkAuth(Authorizable a, int userPermission, String action) throws CommandNotAuthorizedException {

        if ( !(a.dictionary.contains(action)) ){
            return false;
        }

        if (!a.dictionary.contains(action) & !(userPermission >= a.dictionary.get(action))) {
            throw CommandNotAuthorizedException;}
        return true;
    }

}