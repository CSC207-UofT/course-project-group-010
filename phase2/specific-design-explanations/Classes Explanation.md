Make sure you follow the instructions of /README.md before coming here!

# Explanation of Classes(By layer)

Note that this program has students/instructors that can login(users), view courses, rate courses, and view the comment
section.

## Entity

Entity classes store data, and have methods that allow other classes to modify their data.

- IUser is the parent class of InstructorUser and StudentUser. These classes store user related data. The UserFactory
  class will define which subclass(StudentUser or Instructor User) to call .
- Course stores course related data. Rating stores data that is related to Course, that is bundled by the CourseManager
  object
- CommentGraph stores data for the comment section for a course. It uses the graph data structure to add/remove/find
  data. CommentGraphHelper is a helper class.

## Use Case

Use case classes manage use cases for entity classes, and modify their data after checking that the necessary conditions
are met.

- CommentManager helps the rest of the program use CommentGraph objects. It has functionalities such as returning the
  string representation of a thread, getting the parent comment of a thread, and replying to/voting on a comment.
- CourseManager bundles a Course object with a CommentManager that represents its comment thread, and a Rating that
  represents its rating. Provides functionalities such as the ability to rate the course, get its comment thread, and
  give up necessary data.
- UserManager initializes with a User object, and manages it. It provides functionalities related to saving,
  authorization, and presentation.
- CommentPresenter is a wrapper class for CommentManager that helps present it in a more human-friendly manner. It
    manages comments like a file system structure, maintaining a "path" that the user is
    viewing.

## Interfaces

Many interfaces were used relating to Use Case Classes. Here are the notable ones.

- IAuthorizable: Any class that has authorizable actions that can be taken on it
    - eg. A User may want to rate a course. IAuthorizable must provide some sort of indication as to which users are
      allowed to do that.
    - Classes implementing IAuthorizable must do this by returning an AuthDict, which helps with the authorization
      process.
- IHasPermission: Any class that may want to take actions that require authorization
    - eg. A User must have a permission level associated with it so the program knows that privileges it has.
- IDBSaveable: When we save to the database, we want to associate an ID with each class that implements IDBSaveable.
    - We save objects in a map with the id as the key.
- IGettable: Anything that can give up data to a presenter, to be presented on the UI. Data is currently given as a map,
  processed by controller.commandHelpers.DataPrinter, and then printed onto the screen.
- IReadModifiable: Extends IGettable and IAuthorizable
    - Many classes implement this, such as CoursePages, which can give data to a presenter and must check that users are
      authorized to take certain actions concerning it.
- IUser: An defined interface for creating an object. Any user class (StudentUser and InstructorUser) implement this
  interfaces. This interface acts as a superclass specifies generic behavior and delegates the detail to subclasses.

## controller

### Commands

- The commands package contains commands that a user can run from the CLI. They all extend the command class.
- Look at commands.md for specific info on how they are run.

- CommandRequest translates a text request to a method, and a list of arguments. This was made because it can be easily
  adapted to many forms of request.
- CommandExecutor represents the state of the program, and helps execute commands. It holds a User(representing the
  current user that is logged in), an instance of IReadModifiable(representing the course page/comment page/user info
  page that the user is currently viewing), and has a method to help process commands. This is an example of the
  open/closed principle, as it doesn't need to be modified as new commands are made, as it uses the shared run() method
  in commands to run specific commands and return their output.

### DatabaseGetter

- These act as somewhat of a gateway between a "database" and the program
- YOU(Pan) told us to not use an actual database, so we currently save all data to a ser file as a map in the form {id :
  object}
- The DatabaseGetter simply gets this map, and saves this map.
- If we were to use an SQL database, for example, the DataBaseGetters would query the database and return the entries,
  still acting as a gateway between the database and the program.
- These are currently singleton classes, as they simply communicate with an external database.

### Other

- AuthHelper helps with authentication, working with classes that implement IAuthorizable, and classes that implement
  IHasPermission. This isolates the authorization functionality, that is relied upon by many command classes, to a
  single class. The authorization process is always the same.

## Constants and exceptions

The Constants package contains various constants that are used around the program. It also contains enums that are used.
You can look at the individual files for more details.

exceptions are custom exceptions that may be thrown by the program. This helps make more accurate, descriptive
exceptions. That's all.

# outer

- The Database class simply reads and writes from a database. It currently just returns all data from the database as a
  map, because YOU(Pan) told us not to use an actual database. If we were to use SQL, for example, this class would have
  the same functionality but be implemented slightly differently. The DatabaseGetters would still end up returning a
  single entry when getEntry() is run, or modifying a single entry when setEntry() is run, not changing anything in the
  program other than the implementations of their methods. The functionalities of Database and DatabaseGetter methods
  are quite general for this specific purpose(eg. Getting an entry/Saving all)
- ScreenIO contains our main method, which sets up the CLI.
