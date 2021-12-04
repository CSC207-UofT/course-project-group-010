# Comment System
The comment system involves complex data structures and algorithms, and I will explain
the classes involved here.

### CommentGraph, CommentGraphHelper(Entity Package)

Comments are stored in a graph data structure. CommentGraph manages a
graph of comment objects, and contains algorithms to add, delete, and interact
with comments. It also contains all of the data regarding the entire comment section.

The reason for which CommentGraph code is long is due to the complexity of the data structure. Graphs require nodes and 
edges, each of which require special data. In our implementation the nodes of the graph require information and
navigation data in order to function. In addition to this there must be methods that interact with and link nodes
together creating more complexity. These complex methods require long explanations so that someone looking at our code
will understand, thus adding to the bulk of the file. Ultimately CommentGraph has a single responsibility, which is to
generate a reddit like thread that can be used for courses in our program.

CommentGraphHelper is simply a helper class to CommentGraph. It is responsible for creating efficient sorting,
pathfinding, and ID generation for the nodes in the graph.

### CommentManager(Use Case package)

CommentManager manages use cases for the CommentGraph, adding a lot of functions.
Many of these functions are quite technical, and would not be reasonably used by a human user. They aid the functions
that a human would reasonably use.

eg. displaySubsetThread displays all replies up to a certain depth. This would never be used by a human, but 
for example, if we wanted to add the feature of displaying a few replies and giving the option to "show more replies"(like in a typical
youtube/instagram/reddit comment section), we would use displaySubsetThread.

### CommentPresenter(Use Case package)

CommentPresenter is a wrapper class for CommentManager, and it bundles the technical commands into
more general, human-useable commands.

For example, what happens when the user wants to represent the comment thread as a string? Currently, we just displaySubsetThread to the maximum depth.
(Using the CommentManager's technical methods to produce a human-useable one).

CommentPresenter uses CommentManager's methods to create a directory structure for the comment thread, allowing the user
to traverse it the same way a windows user traverses their computer's file system with the cd command.

**Why did we choose this design?**

This design is easily extendable. We separated program features(CommentPresenter) from technical functionality(CommentManager).
If we want to add a new feature, we can leverage CommentManager's existing methods. 

Also, CommentManager's methods will probably never be changed, as they fulfill simple tasks like replying/voting on a comment, whereas CommentPresenter's methods will probably be customized and added upon(eg.
if we want to give more functionality to the user). We recognized these two categories of methods, and believe that separating them makes the code more organized.

In regards to CommentGraph we decided against using a design pattern such as builder or factory as the data structure is already complex.
Adding another layer in the form of a design pattern would render the code very difficult to work with as well as create much longer
files.