package interfaces;

import constants.UserType;

/**
 * Any class that represents a user that may take authorizable actions, that must be given a permission level identifier
 * in order to be authorized.
 */
public interface IHasPermission {

    /**
     * Gets the type of user, to identify what permissions they have.
     *
     * @return the permission level the type of user have
     */
    UserType getPermissionLevel();
}
