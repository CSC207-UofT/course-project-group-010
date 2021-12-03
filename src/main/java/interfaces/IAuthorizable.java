package interfaces;

import constants.UserType;

import java.util.List;
import java.util.Map;

public interface IAuthorizable {
    /**
     * Gets an authorization dictionary, mapping UserTypes to actions they are allowed to take.
     * @return
     */
    Map<UserType, List<String>> getAuthDict();
}
