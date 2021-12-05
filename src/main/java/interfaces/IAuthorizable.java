package interfaces;

import constants.UserType;

import java.util.List;
import java.util.Map;

/**
 * Any class that has authorizable actions that different users can take
 */
public interface IAuthorizable {
    /**
     * Gets an authorization dictionary, mapping UserTypes to actions they are allowed to take.
     * @return the dictionary
     */
    Map<UserType, List<String>> getAuthDict();
}
