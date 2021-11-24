package UseCase.CommentManager;

// imports
import Constants.CommandConstants;
import Constants.PermissionLevel;
import Entity.CommentGraph;
import Interface.IReadModifiable;

import java.io.Serializable;
import java.util.*;

/**
 * Class that handles comments in a CommentGraph
 */
public class CommentManagerTemp
{
    // initial CommentGraph
    CommentGraph commentGraph;
    // authorization mapping
    Map<PermissionLevel, List<String>> authDict;
}
