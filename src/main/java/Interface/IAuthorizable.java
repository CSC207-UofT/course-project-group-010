package Interface;

import Constants.PermissionLevel;

import java.util.List;
import java.util.Map;

public interface IAuthorizable {
    Map<PermissionLevel, List<String>> getAuthDict();
}
