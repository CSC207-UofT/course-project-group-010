package Entity;

import java.text.MessageFormat;
import java.util.*;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Comment Graph Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 * The CommentsGraph Class allows the creation of comments that are linked together in a directed graph data structure,
 * where each node can only have one parent but multiple children, making it very similar to a tree data structure.
 * This graph is akin to threads found on the website Reddit.
 */

public class CommentGraph
{
    // Dictionary that stores all the vertices (comments) in the graph.
    private HashMap<String, Comment> vertices;
    // Stores the size of the graph. Size is defined by the number of comments within it.
    private int size;
    // The depth of the lowest comment in the graph
    private int maxDepth;
    // Root comment of the entire graph.
    private Comment root;

    /**
     * CommentGraph Multiple mainComments Constructor, takes a list of Strings that represents the text found in the
     * topmost comments under the root (depth 1 comments). An example usage of this constructor is for professors to
     * create a graph with the topmost comments under the root being questions about their course which students can
     * then reply to.
     *
     * @param mainComments      a list of Strings that contains the text for the topmost comments under the root
     *                          (depth 1 comments)
     * @param mainCommentType   the type of comment that will be posted, ex. "Questions", "Announcements", ...
     * @param mainCommenterName the username of whoever controls the main comments in the graph, for example a professor
     *                          in a course.
     */
    public CommentGraph(List<String> mainComments, String mainCommentType, String mainCommenterName)
    {
        // initializes empty CommentGraph
        emptyCommentGraphInitializer(mainCommentType, mainCommenterName);
        // each comment in mainComments is added to the CommentGraph and linked to the root node.
        for (String mainComment : mainComments)
        {
            // adds comment to the CommentGraph and links to root comment.
            reply("root", mainComment, mainCommenterName);
        }
    }

    /**
     * CommentGraph Single mainComment Constructor, takes a String that represents the text found in the topmost comment
     * under the root (depth 1 comments). An example usage of this constructor is for a professor to create a graph with
     * the topmost comment under the root being a single question about their course which students can then reply to.
     *
     * @param mainComment       a String that contains the text for the topmost comments under the root (depth 1 comment)
     * @param mainCommentType   the type of comment that will be posted, ex. "Questions", "Announcements", ...
     * @param mainCommenterName the username of whoever controls the main comments in the graph, for example a professor
     *                          in a course.
     */
    public CommentGraph(String mainComment, String mainCommentType, String mainCommenterName)
    {
        // initializes empty CommentGraph
        emptyCommentGraphInitializer(mainCommentType, mainCommenterName);
        // adds comment to the CommentGraph and links to root comment.
        reply("root", mainComment, mainCommenterName);
    }

    /**
     * Method that initializes an empty CommentGraph
     * @param mainCommentType   the type of comment that will be posted, ex. "Questions", "Announcements", ...
     * @param mainCommenterName the username of whoever controls the main comments in the graph, for example a professor
     *                          in a course.
     */
    private void emptyCommentGraphInitializer(String mainCommentType, String mainCommenterName)
    {
        // initializes the dictionary of vertices to an empty HashMap.
        this.vertices = new HashMap<>();
        // initializes the size of the CommentGraph to be as there are no Comments within it.
        this.size = 0;
        // creates the root comment
        this.root = createComment("root", mainCommentType, mainCommenterName);
        // adds the root comment to the CommentGraph.
        add_vertex("root", this.root);
    }

//======================================================================================================================
// Comment Graph Getters
//======================================================================================================================

    /**
     * Method that gets the dictionary of ids and related comments in a CommentGraph.
     * @return a HashMap consisting of a String key (id) and a Comment value.
     */
    public HashMap<String, Comment> getVertices()
    {
        // return the vertices instance variable of CommentGraph.
        return this.vertices;
    }

    /**
     * Method that gets the maximum depth of the CommentGraph.
     * @return
     */
    public int getMaxDepth()
    {
        // return the maxDepth instance variable.
        return this.maxDepth;
    }

//======================================================================================================================
// Comment Graph String Representation
//======================================================================================================================

    /**
     * Helper variable that stores data for the stringRepresentationRecursive method. This allows for a much more
     * modifiable and maintainable recursive function in case something needs to be changed.
     */
    public String stringRepresentationHelper = "";

    /**
     * Method that gets the String representation of a CommentGraph up to a certain depth.
     * @param start the Comment at which to begin the string representation
     * @param depth the depth of the comment, this controls the indentation in the String
     * @param endDepth the depth at which to stop the String representation, for example if you only wanted the String
     *                 representation of the root comment, its subcomments, and its subcomments, you would use depth 2,
     *                 whereas if you wanted the string representation of the CommentGraph in its entirety, you would
     *                 use the maximum depth of the CommentGraph.
     * @param reverseSorted determines whether to sort by increasing or decreasing votes.
     * @return the String representation of the CommentGraph.
     */
    public String stringRepresentation(Comment start, int depth, int endDepth, boolean reverseSorted)
    {
        // reset the stringRepresentationHelper variable.
        stringRepresentationHelper = "";
        // assign the stringRepresentationHelper with the new representation.
        stringRepresentationRecursive(start, depth, endDepth, reverseSorted);
        // create temporary variable that stores the representation
        String strRep = stringRepresentationHelper;
        // reset the stringRepresentationHelper variable.
        stringRepresentationHelper = "";
        // return the String representation.
        return strRep;
    }

    /**
     * Method that recursively generates the String representation of CommentGraph up to a certain depth.
     * @param start the Comment at which to begin the string representation.
     * @param depth the depth of the comment, this controls the indentation in the String.
     * @param endDepth the depth at which to stop the String representation.
     * @param reverseSorted determines whether to sort by increasing or decreasing votes.
     */
    public void stringRepresentationRecursive(Comment start, int depth, int endDepth, boolean reverseSorted)
    {
        // String representation of current comment.
        stringRepresentationHelper = stringRepresentationHelper +
                "    ".repeat(depth) + start.formattedRepresentation().get(0) + "\n" +
                "    ".repeat(depth) + start.formattedRepresentation().get(1) + "\n" +
                "    ".repeat(depth) + start.formattedRepresentation().get(2) + "\n";

        // Sort the list of next comments by vote, reversed or not based on reserseSorted boolean.
        CommentGraphHelper sortHelper = new CommentGraphHelper();
        List<Comment> sortedComments = sortHelper.commentSort(start.getNext(), reverseSorted);

        // Stop appending to the string representation if a certain depth is reached.
        if (depth == endDepth)
        {
            return;
        }

        // for each comment in the list of next comments
        for (var subComment : sortedComments)
        {
            // recursively call the method on the subcomment and increase the depth by 1.
            stringRepresentationRecursive(subComment, depth + 1, endDepth, reverseSorted);
        }
    }

//======================================================================================================================
// Comment creation and linking
//======================================================================================================================

    /**
     * Method that creates a comment with no edges to other comments, contains an id, text, and name of the user that
     * created it.
     * @param id unique id of the comment.
     * @param text text of the comment.
     * @param userName name of the user who made the comment.
     * @return
     */
    private Comment createComment(String id, String text, String userName)
    {
        List<Comment> next = new ArrayList<>();
        NavigationAttributes nav = new NavigationAttributes(next, null);
        InformationAttributes info = new InformationAttributes(id, text, userName);
        return new Comment(nav, info, 0);
    }

    private void add_vertex(String id, Comment comment)
    {
        this.vertices.put(id, comment);
        this.size += 1;
    }

    private void link(String prevId, Comment comment)
    {
        Comment vertex1 = this.vertices.get(comment.info.id);
        Comment vertex2 = this.vertices.get(prevId);

        vertex1.nav.prev = vertex2;
        vertex2.nav.next.add(vertex1);

        int newDepth = vertex2.depth + 1;
        comment.depth = newDepth;

        if (newDepth > this.maxDepth)
        {
            this.maxDepth = newDepth;
        }

        this.size += 1;
    }

//======================================================================================================================
// Reply Functionality
//======================================================================================================================

    public void reply(String prevId, String text, String userName)
    {
        if (text.equals("") || !this.vertices.containsKey(prevId))
        {
            // do nothing
        }
        else
        {
            String uniqueId = genUniqueId();
            Comment comment = createComment(uniqueId, text, userName);
            add_vertex(uniqueId, comment);
            link(prevId, comment);
        }
    }

    private String genUniqueId()
    {
        // makes sure that the id is in fact unique, there is a very small chance that this code will actually run
        // due to the number of possibilities, but this is here just in case.
        CommentGraphHelper genIdHelper = new CommentGraphHelper();
        String uniqueId = genIdHelper.genId();
        while (vertices.containsKey(uniqueId))
        {
            uniqueId = genIdHelper.genId();
        }
        return uniqueId;
    }

//======================================================================================================================
// Vote Functionality
//======================================================================================================================

    public void upvote(String id)
    {
        this.vertices.get(id).info.vote += 1;
    }

    public void downvote(String id)
    {
        this.vertices.get(id).info.vote -= 1;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Comment Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public class Comment
    {
        private NavigationAttributes nav;
        private InformationAttributes info;
        private int depth;

        private Comment(NavigationAttributes nav, InformationAttributes info, int depth)
        {
            this.nav = nav;
            this.info = info;
            this.depth = depth;
        }

//======================================================================================================================
// Comment String Representations
//======================================================================================================================

        @Override
        public String toString()
        {
            return MessageFormat.format("{0} {1} {2} {3}", this.info.userName, this.info.id,
                    this.info.vote, this.info.text);
        }

        public List<String> formattedRepresentation()
        {
            String s1 = MessageFormat.format("↳ {0} [{1}]", this.info.userName, this.info.id);
            String s2 = MessageFormat.format("{0}", this.info.text);
            String s3 = MessageFormat.format("↑ {0} ↓", this.info.vote);

            List<String> representation = new ArrayList<>()
            {
            };
            representation.add(s1);
            representation.add(s2);
            representation.add(s3);

            return representation;
        }

//======================================================================================================================
// Comment Getters
//======================================================================================================================

        public List<Comment> getNext()
        {
            return this.nav.next;
        }

        public Comment getPrev()
        {
            return this.nav.prev;
        }

        public Boolean getVisited()
        {
            return this.nav.visited;
        }

        public String getId()
        {
            return this.info.id;
        }

        public String getText()
        {
            return this.info.text;
        }

        public String getUserName()
        {
            return this.info.userName;
        }

        public int getVote()
        {
            return this.info.vote;
        }

        public int getDepth()
        {
            return this.depth;
        }
    }

//======================================================================================================================
// Comment Attributes
//======================================================================================================================

    public class NavigationAttributes
    {
        private List<Comment> next;
        private Comment prev;
        private double nextDistance;
        private Boolean visited;

        private NavigationAttributes(List<Comment> next, Comment prev)
        {
            this.next = next;
            this.prev = prev;
            this.nextDistance = Double.POSITIVE_INFINITY;
            this.visited = false;
        }
    }

    public class InformationAttributes
    {
        private String id;
        private String text;
        private String userName;
        private int vote;

        private InformationAttributes(String id, String text, String userName)
        {
            this.id = id;
            this.text = text;
            this.userName = userName;
            this.vote = 0;
        }
    }
}



