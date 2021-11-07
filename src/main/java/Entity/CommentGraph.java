package Entity;

import java.text.MessageFormat;
import java.util.*;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Comment Graph Class
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
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
    // Root comment of the entire graph.
    private Comment root;

    public HashMap<String, Comment> getVertices()
    {
        return this.vertices;
    }

    public CommentGraph(List<String> questions, String profName)
    {
        this.vertices = new HashMap<String, Comment>();
        this.size = 0;
        this.root = createComment("root", "Questions", profName);
        add_vertex("root", this.root);

        for (String question : questions)
        {
            reply("root", question, profName);
        }
    }

//======================================================================================================================
// Comment Graph String Representation
//======================================================================================================================

    public String stringRepresentationHelper = "";

    public void stringRepresentation(Comment start, int depth)
    {
        stringRepresentationHelper = "";
        stringRepresentationRecursive(start, depth);
        System.out.println(stringRepresentationHelper);
    }

    public void stringRepresentationRecursive(Comment start, int depth)
    {
        stringRepresentationHelper = stringRepresentationHelper +
                "    ".repeat(depth) + start.formattedRepresentation().get(0) + "\n" +
                "    ".repeat(depth) + start.formattedRepresentation().get(1) + "\n" +
                "    ".repeat(depth) + start.formattedRepresentation().get(2) + "\n";

        CommentGraphHelper sortHelper = new CommentGraphHelper();
        List<Comment> sortedComments = sortHelper.commentSort(start.nav.next, true);

        for (var subcomment : sortedComments)
        {
            stringRepresentationRecursive(subcomment, depth + 1);
        }
    }

//======================================================================================================================
// Comment creation and linking
//======================================================================================================================

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
        comment.depth = vertex2.depth + 1;
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



