# Major Design Decisions

## Software Design

These are software design decisions that we made, and some rationale behind them.

### Simple Factory
We implemented a simple factory design pattern using CommandConstants. This is useful because
we want the CommandExecutor class to interact with all the related Command objects, so we can fetch the command object
corresponding to the user's input easily using this design

### Command Pattern
We implemented a command design pattern using Commands. Commands each implement a run method, that contains business logic.
The CommandExecutor simply has to call this method with some arguments inputted by the user to achieve the desired effects of the command.
This makes it easy to create new commands(by extending the Command class).

### Builder Pattern
We implemented a builder design pattern for CoursePage/CourseManager. CoursePages require a lot of information to be initialized,
so we're making a separate class to help build it.

### Serialization
Using serialization and deserialization helps us to get the data from the database and save the new information into our database.
The IDBSaveable interface forces classes that extend it to implement a getID() method.
Currently, our database saves a map of ids to their respective objects, which extend the serializable interface.
When we load the program, we deserialize the objects stored in users.ser and courses.ser files to essentially get the previous machine state.

### Other
Dependency injection is used in many places. It's not a large pattern, it's probably in a lot of methods to make sure
classes are depending on the right layers.

## Features

These are features that we decided to include in the project, and our rationale.
Some features, such as relative rating, may not be implemented yet.

###Relative Rating 
The main purpose of our system is to help the students most.
So, we realize that students from the different programs may experience different levels of difficulty when taking the same course. 
But providing relative ratings based on the program detail will give students true insights into the courses. 

###Database 
We want to find an easier way to access the data.
Thus, we decide to experiment serializable interface since it is Java's built-in interface and allows us to transfer objects through the network.

###Comment 
The main purpose of our system is to help the students in the most effective way. 
Thus,we decide to let users answer the list of general questions and upvote/downvote for the responses to these questions. 
Through this way, the comments shown on our course page will focus on the most important/relevant area of the course. 
