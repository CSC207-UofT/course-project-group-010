package interfaces;

import constants.UserType;

public interface IHasPermission {

    /**
     * Gets the type of user, to identify what permissions they have.
     * @return
     */
    UserType getPermissionLevel();
}
