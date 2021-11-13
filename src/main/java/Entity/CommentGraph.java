package Entity;


import Entity.CommentGraphHelper;

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
    // Instructor of the graph
    private String instructor;

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
    public CommentGraph(List<String> mainComments, String mainCommentType, String mainCommenterName, String instructor)
    {
        // initializes empty CommentGraph
        emptyCommentGraphInitializer(mainCommentType, mainCommenterName, instructor);
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
    public CommentGraph(String mainComment, String mainCommentType, String mainCommenterName, String instructor)
    {
        // initializes empty CommentGraph
        emptyCommentGraphInitializer(mainCommentType, mainCommenterName, instructor);
        // adds comment to the CommentGraph and links to root comment.
        reply("root", mainComment, mainCommenterName);
    }

    public CommentGraph(String mainCommentType, String mainCommenterName, String instructor)
    {
        // initializes empty CommentGraph
        emptyCommentGraphInitializer(mainCommentType, mainCommenterName, instructor);
        //set instructor
        this.instructor = instructor;
    }

    /**
     * Method that initializes an empty CommentGraph
     *
     * @param mainCommentType   the type of comment that will be posted, ex. "Questions", "Announcements", ...
     * @param mainCommenterName the username of whoever controls the main comments in the graph, for example a professor
     *                          in a course.
     */
    private void emptyCommentGraphInitializer(String mainCommentType, String mainCommenterName, String instructor)
    {
        // initializes the dictionary of vertices to an empty HashMap.
        this.vertices = new HashMap<>();
        // initializes the size of the CommentGraph to be as there are no Comments within it.
        this.size = 0;
        // creates the root comment
        this.root = createComment("root", mainCommentType, mainCommenterName);
        //set instructor
        this.instructor = instructor;
        // adds the root comment to the CommentGraph.
        addVertex("root", this.root);
    }


//======================================================================================================================
// Comment Graph Getters
//======================================================================================================================

    /**
     * Method that gets the dictionary of ids and related comments in a CommentGraph.
     *
     * @return a HashMap consisting of a String key (id) and a Comment value.
     */
    public HashMap<String, Comment> getVertices()
    {
        // return the vertices instance variable of CommentGraph.
        return this.vertices;
    }

    /**
     * Method that returns Comment based on id.
     *
     * @param id of Comment.
     * @return Comment based on id.
     */
    public Comment getComment(String id)
    {
        // return the associated Comment.
        return this.vertices.get(id);
    }

    /**
     * Method that gets the maximum depth of the CommentGraph.
     *
     * @return an integer that represents the maximum depth of the CommentGraph.
     */
    public int getMaxDepth()
    {
        // return the maxDepth instance variable.
        return this.maxDepth;
    }

    /**
     * Method that gets the size of the CommentGraph.
     *
     * @return an integer that represents the size of the CommentGraph.
     */
    public int getSize()
    {
        // return the size instance variable.
        return this.size;
    }

    public String getInstructor()
    {
        // get the instructor
        return this.instructor;
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
     *
     * @param start         the Comment at which to begin the string representation
     * @param depth         the depth of the comment, this controls the indentation in the String
     * @param endDepth      the depth at which to stop the String representation, for example if you only wanted the String
     *                      representation of the root comment, its subcomments, and its subcomments, you would use depth 2,
     *                      whereas if you wanted the string representation of the CommentGraph in its entirety, you would
     *                      use the maximum depth of the CommentGraph.
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
     *
     * @param start         the Comment at which to begin the string representation.
     * @param depth         the depth of the comment, this controls the indentation in the String.
     * @param endDepth      the depth at which to stop the String representation.
     * @param reverseSorted determines whether to sort by increasing or decreasing votes.
     */
    public void stringRepresentationRecursive(Comment start, int depth, int endDepth, boolean reverseSorted)
    {
        // String representation of current comment.
        stringRepresentationHelper = stringRepresentationHelper +
                "    ".repeat(depth) + start.formattedRepresentation().get(0) + "\n" +
                "    ".repeat(depth) + start.formattedRepresentation().get(1) + "\n" +
                "    ".repeat(depth) + start.formattedRepresentation().get(2) + "\n\n";

        // Sort the list of next comments by vote, reversed or not based on reverseSorted boolean.
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

    /**
     * Generates the path from one comment to another given there is a valid path.
     *
     * @param startComment
     * @param endComment
     * @return
     */
    public String stringPath(Comment startComment, Comment endComment)
    {
        // initialize new helper
        CommentGraphHelper stringPathHelper = new CommentGraphHelper();
        // get path from helper
        List<Comment> path = stringPathHelper.depthFirstPath(this, startComment.getId(), endComment.getId());
        // reverse the path
        Collections.reverse(path);

        //convert path to formatted String representation
        String strPath = "";
        for (Comment comment : path)
        {
            strPath = strPath +
                    "    ".repeat(comment.depth) + comment.formattedRepresentation().get(0) + "\n" +
                    "    ".repeat(comment.depth) + comment.formattedRepresentation().get(1) + "\n" +
                    "    ".repeat(comment.depth) + comment.formattedRepresentation().get(2) + "\n\n";
        }

        // return String representation.
        return strPath;
    }

//======================================================================================================================
// Comment creation and linking
//======================================================================================================================

    /**
     * Method that creates a comment with no edges to other comments, contains an id, text, and name of the user that
     * created it.
     *
     * @param id       unique id of the comment.
     * @param text     text of the comment.
     * @param userName name of the user who made the comment.
     * @return the newly created Comment
     */
    private Comment createComment(String id, String text, String userName)
    {
        // set the next comments to an empty List, meaning there are no edges coming out of the comment.
        List<Comment> next = new ArrayList<>();
        // set the navigation attributes to have no previous or next nodes.
        NavigationAttributes nav = new NavigationAttributes(next, null);
        // set the information attributes to contain id, text, and userName
        InformationAttributes info = new InformationAttributes(id, text, userName);
        // return the Comment
        return new Comment(nav, info, 0);
    }

    /**
     * Add a comment to the CommentGraph.
     *
     * @param id      unique id of the comment.
     * @param comment the Comment to the CommentGraph.
     */
    private void addVertex(String id, Comment comment)
    {
        // add to the dictionary of vertices in CommentGraph.
        this.vertices.put(id, comment);
        // increase the size of the CommentGraph by 1.
        this.size += 1;
    }

    /**
     * Method that links two comments together with an edge.
     *
     * @param prevId  the id of the parent Comment.
     * @param comment the Comment to link to the parent.
     */
    private void link(String prevId, Comment comment)
    {
        // Get current comment.
        Comment vertex1 = this.vertices.get(comment.info.id);
        // Get parent comment.
        Comment vertex2 = this.vertices.get(prevId);

        // set the previous Comment of the current comment to the parent comment.
        vertex1.nav.prev = vertex2;
        // append the current Comment to the list of next comments of the parent Comment.
        vertex2.nav.next.add(vertex1);

        // set the depth of the linked Comment based on the depth of the parent Comment.
        int newDepth = vertex2.depth + 1;
        comment.depth = newDepth;

        // set the maxDepth of the CommentGraph if the newDepth is greater than the current maxDepth.
        if (newDepth > this.maxDepth)
        {
            this.maxDepth = newDepth;
        }
    }

//======================================================================================================================
// Reply Functionality
//======================================================================================================================

    /**
     * Method that allows a comment to be the reply of another.
     *
     * @param prevId   the id of the parent comment.
     * @param text     the text of the reply.
     * @param userName the name of the user that made the reply.
     */
    public void reply(String prevId, String text, String userName)
    {
        // if the reply has no text or the parent id is not in the dictionary of vertices
        if (text.equals("") || !this.vertices.containsKey(prevId))
        {
            // do nothing
        }
        //otherwise
        else
        {
            // generate unique id for comment
            String uniqueId = genUniqueId();
            // create comment with unique id, text, and name of the user
            Comment comment = createComment(uniqueId, text, userName);
            // add the comment to the CommentGraph
            addVertex(uniqueId, comment);
            // link the comment to its parent
            link(prevId, comment);
        }
    }

    /**
     * Method that makes sure that the id is in fact unique, there is a very small chance that this code will actually
     * run due to the number of possibilities (62P5 with repetition = 916,132,832 possibilities), but this is here just
     * in case.
     *
     * @return a String that represents a uniqueId.
     */
    private String genUniqueId()
    {
        // generate using genId() function in helper class.
        CommentGraphHelper genIdHelper = new CommentGraphHelper();
        String uniqueId = genIdHelper.genId();
        // until a unique id is generated, which is most likely already the case before the while loop occurs.
        while (vertices.containsKey(uniqueId))
        {
            // set id to a new id.
            uniqueId = genIdHelper.genId();
        }

        // return the unique id.
        return uniqueId;
    }

//======================================================================================================================
// Vote Functionality
//======================================================================================================================

    /**
     * Upvote a comment
     *
     * @param id of the Comment to upvote
     */
    public void upvote(String id)
    {
        // increase vote by 1
        this.vertices.get(id).info.vote += 1;
    }

    /**
     * Downvote a comment
     *
     * @param id of the Comment to downvote
     */
    public void downvote(String id)
    {
        // decrease vote by 1
        this.vertices.get(id).info.vote -= 1;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Comment Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * The Comment class are the nodes for the CommentGraph. It stores all attributes related to comments, including
     * navigation attributes, and information attributes.
     */
    public class Comment
    {
        // navigation attributes for the Comment.
        private NavigationAttributes nav;
        // information attributes for the Comment.
        private InformationAttributes info;
        // depth of the Comment.
        private int depth;

        /**
         * Comment class constructor that initializes with navigation attributes, info attributes, and depth of the
         * Comment.
         *
         * @param nav   NavigationAttributes of the Comment.
         * @param info  InformationAttributes of the Comment.
         * @param depth of the Comment.
         */
        private Comment(NavigationAttributes nav, InformationAttributes info, int depth)
        {
            // Initializes NavivationAttributes.
            this.nav = nav;
            // Initializes InformationAttributes.
            this.info = info;
            // Initializes depth of the Comment.
            this.depth = depth;
        }

//======================================================================================================================
// Comment String Representations
//======================================================================================================================

        /**
         * Overrides the String representation of the Comment
         *
         * @return String representation of the Comment.
         */
        @Override
        public String toString()
        {
            // returns a String int the form "userName id vote text"
            return MessageFormat.format("{0} {1} {2} {3}", this.info.userName, this.info.id,
                    this.info.vote, this.info.text);
        }

        /**
         * Method that returns a formatted String representation of a Comment.
         *
         * @return a formatted String that represents a Comment.
         */
        public List<String> formattedRepresentation()
        {
            // first part of String containing userName and id
            String s1 = MessageFormat.format("> {0} [{1}]", this.info.userName, this.info.id);
            // second part of String containing text
            String s2 = MessageFormat.format("{0}", this.info.text);
            // third part of string containing vote
            String s3 = MessageFormat.format("[+] {0} [-]", this.info.vote);

            // ArrayList containing all three parts of the String
            List<String> representation = new ArrayList<>()
            {
            };
            representation.add(s1);
            representation.add(s2);
            representation.add(s3);

            // return String representation
            return representation;
        }

//======================================================================================================================
// Comment Getters
//======================================================================================================================

        /**
         * Getter that returns the next linked Comments.
         *
         * @return List of next Comments.
         */
        public List<Comment> getNext()
        {
            return this.nav.next;
        }

        /**
         * Getter that returns the parent Comment.
         *
         * @return parent Comment.
         */
        public Comment getPrev()
        {
            return this.nav.prev;
        }

        /**
         * Getter that returns whether the Comment has been visited or not.
         *
         * @return true or false.
         */
        public Boolean getVisited()
        {
            return this.nav.visited;
        }

        /**
         * Getter that returns the id of the Comment.
         *
         * @return String id.
         */
        public String getId()
        {
            return this.info.id;
        }

        /**
         * Getter that returns the text of the Component.
         *
         * @return String text.
         */
        public String getText()
        {
            return this.info.text;
        }

        /**
         * Getter that returns the userName of the Comment.
         *
         * @return String userName.
         */
        public String getUserName()
        {
            return this.info.userName;
        }

        /**
         * Getter that returns the vote value of the Comment.
         *
         * @return Integer vote.
         */
        public int getVote()
        {
            return this.info.vote;
        }

        /**
         * Getter that returns the depth of the Comment.
         *
         * @return Integer depth.
         */
        public int getDepth()
        {
            return this.depth;
        }

        /**
         * Gets the formatted representation of a Comment.
         *
         * @return formatted String representation.
         */
        public String getFormattedRepresentation()
        {
            return this.formattedRepresentation().get(0) + "\n" +
                    this.formattedRepresentation().get(1) + "\n" +
                    this.formattedRepresentation().get(2) + "\n\n";
        }
    }

//======================================================================================================================
// Comment Attributes
//======================================================================================================================

    /**
     * NavigationAttributes stores values used for navigation for the Comment class.
     */
    public class NavigationAttributes
    {
        // List of children Comments
        private List<Comment> next;
        // Parent Comment
        private Comment prev;
        // Distance to nextDistance used for Dijkstra's algorithm, may be used in the future
        private double nextDistance;
        // Determines if Comment has been visited while path finding, may be used in the future
        private Boolean visited;

        /**
         * Constructor for NavigationAttributes.
         *
         * @param next children nodes.
         * @param prev parent node.
         */
        private NavigationAttributes(List<Comment> next, Comment prev)
        {
            // Initialize children nodes.
            this.next = next;
            // Initialize parent node.
            this.prev = prev;
            // Initialize next distance as infinity as per Dijkstra's algorithm.
            this.nextDistance = Double.POSITIVE_INFINITY;
            // Initialize visited as false.
            this.visited = false;
        }
    }

    /**
     * InformationAttributes stores values used for textual information for the Comment class.
     */
    public class InformationAttributes
    {
        // id of Comment.
        private String id;
        // text in Comment.
        private String text;
        // name of user that created Comment.
        private String userName;
        // vote value of the Comment.
        private int vote;

        /**
         * Constructor for InformationAttributes.
         *
         * @param id       String id.
         * @param text     String text.
         * @param userName String userName.
         */
        private InformationAttributes(String id, String text, String userName)
        {
            // Initialize id of Comment.
            this.id = id;
            // Initialize text of Comment.
            this.text = text;
            // Initialize name of user of Comment.
            this.userName = userName;
            // Initialize vote value to 0.
            this.vote = 0;
        }
    }
}



