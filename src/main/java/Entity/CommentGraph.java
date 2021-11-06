package Entity;

import java.text.MessageFormat;
import java.util.*;

public class CommentGraph {
    private HashMap<String, Comment> vertices;
    private int size;
    private Comment head;

    public HashMap<String, Comment> getVertices() {
        return this.vertices;
    }

    public CommentGraph(List<String> questions, String profName) {
        this.vertices = new HashMap<String, Comment>();
        this.size = 0;
        this.head = createComment("head", "Questions", profName);
        add_vertex("head", this.head);

        for (String question : questions) {
            reply("head", question, profName);
        }
    }

    public void print(Comment start, int depth)
    {
        printHelper = "";
        levelPrinter(start, depth);
        System.out.println(printHelper);
    }

    public String printHelper = "";

    public void levelPrinter(Comment start, int depth)
    {
        printHelper = printHelper +  "    ".repeat(depth) + start.formattedRepresentation().get(0) + "\n" +
                       "    ".repeat(depth) + start.formattedRepresentation().get(1) + "\n" +
                       "    ".repeat(depth) + start.formattedRepresentation().get(2) + "\n";

        CommentGraphHelper sortHelper = new CommentGraphHelper();
        List<Comment> sortedComments = sortHelper.commentSort(start.nav.next, true);

        for (var subcomment : sortedComments)
        {
            levelPrinter(subcomment, depth+1);
        }
    }



    public void vote(String id, boolean upvote) {
        if (upvote) {
            this.vertices.get(id).info.upvote += 1;
        } else {
            this.vertices.get(id).info.upvote -= 1;
        }
    }

    private void add_vertex(String id, Comment comment) {
        this.vertices.put(id, comment);
        this.size += 1;
    }

    private void link(String prevId, Comment comment) {
        Comment vertex1 = this.vertices.get(comment.info.id);
        Comment vertex2 = this.vertices.get(prevId);

        vertex1.nav.prev = vertex2;
        vertex2.nav.next.add(vertex1);
        comment.depth = vertex2.depth + 1;
        this.size += 1;

    }

    private Comment createComment(String id, String text, String userName) {
        List<Comment> next = new ArrayList<>();
        NavigationAttributes nav = new NavigationAttributes(next, null);
        InformationAttributes info = new InformationAttributes(id, text, userName);
        return new Comment(nav, info, 0);
    }

    public void reply(String prevId, String text, String userName) {
        if (text.equals("") || !this.vertices.containsKey(prevId)) {
            // do nothing
        } else {
            String uniqueId = genUniqueId();
            Comment comment = createComment(uniqueId, text, userName);
            add_vertex(uniqueId, comment);
            link(prevId, comment);
        }
    }

    private String genUniqueId() {
        // makes sure that the id is in fact unique, there is a very small chance that this code will actually run
        // due to the number of possibilities, but this is here just in case.
        String uniqueId = genId();
        while (vertices.containsKey(uniqueId)) {
            uniqueId = genId();
        }
        return uniqueId;
    }

    private String genId() {
        char[] alphabet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
                'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        Random rand = new Random();
        char[] encodedChars = new char[5];
        for (int i = 0; i < 5; i++) {
            encodedChars[i] = alphabet[rand.nextInt(62)];
        }

        return new String(encodedChars);
    }

    public class Comment implements Comparable<Comment>{
        private NavigationAttributes nav;
        private InformationAttributes info;
        private int depth;

        private Comment(NavigationAttributes nav, InformationAttributes info, int depth) {
            this.nav = nav;
            this.info = info;
            this.depth = depth;
        }

        @Override
        public int compareTo(Comment comment)
        {
            return Integer.compare(this.info.upvote, comment.info.upvote);
        }

        @Override
        public String toString() {
//            String spacing = "  ".repeat(depth);
//            String s = MessageFormat.format("{0}↳ {1} [{3}]\n{0}  {4}\n{0}  ↑ {2} ↓", spacing, this.info.userName,
//                    this.info.upvote, this.info.id, this.info.text);
//            return s;

            return MessageFormat.format("{0} {1} {2} {3}", this.info.userName, this.info.id, this.info.upvote, this.info.text);
        }

        public int getUpvote()
        {
            return this.info.upvote;
        }

        public NavigationAttributes getNav()
        {
            return this.nav;
        }

        public InformationAttributes getInfo()
        {
            return this.info;
        }



        public List<String> formattedRepresentation()
        {
            String s1 = MessageFormat.format("↳ {0} [{1}]", this.info.userName, this.info.id);
            String s2 = MessageFormat.format("{0}", this.info.text);
            String s3 = MessageFormat.format("↑ {0} ↓", this.info.upvote);

            List<String> representation = new ArrayList<>(){};
            representation.add(s1);
            representation.add(s2);
            representation.add(s3);

            return representation;
        }

        public int getDepth()
        {
            return this.depth;
        }
    }

    public class NavigationAttributes {
        private List<Comment> next;
        private Comment prev;
        private double nextDistance;
        private Boolean visited;

        private NavigationAttributes(List<Comment> next, Comment prev) {
            this.next = next;
            this.prev = prev;
            this.nextDistance = Double.POSITIVE_INFINITY;
            this.visited = false;
        }

        public List<Comment> getNext() {
            return this.next;
        }

        public Comment getPrev() {
            return this.prev;
        }

        public double getNextDistance() {
            return this.nextDistance;
        }

        public Boolean getVisited() {
            return this.visited;
        }

        void setVisited(Boolean visited) {
            this.visited = visited;
        }
    }

    public class InformationAttributes {
        private String id;
        private String text;
        private String userName;
        private int upvote;

        private InformationAttributes(String id, String text, String userName) {
            this.id = id;
            this.text = text;
            this.userName = userName;
            this.upvote = 0;
        }

        public String getId()
        {
            return this.id;
        }
    }
}



