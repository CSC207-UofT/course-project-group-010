package interfaces;

import constants.UserType;

import java.util.List;
import java.util.Map;

public interface IAuthorizable {
    Map<UserType, List<String>> getAuthDict();
}
