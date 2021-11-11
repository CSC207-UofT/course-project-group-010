#Clean Architecture

## Dependency Rule
We follow the dependency rule consistently when interacting with details in the outer layer as the inner layers will not depend on the outer layers in our system.  
For example, our entity class will not depend on the outer layer class (ScreenIO). Also, Outer layer is more concrete and specific than the inner layer

## Entities - Enterprise Business Rules
Entities (Such as StudentUser and Rating) represent the business objects of the application. They only store/modify data within themselves, and do not depend on anything.

## Use Cases - Application Business Rules 
UseCases (such as CoursePage and CourseManager) manage use cases for entity classes, changing data if it makes logical sense to.
These contain a bit more data that pertains to the program and not to the entities themselves(eg. Authorization level is used in the program,
but it's not relevant data for the entities to store).

## Controllers, Presenters, Gateways
The command package contains commands. These commands delegate work to other command classes and use case classes, and report on the result.
They do only depend on other classes in the same layer, and use case classes.
When they access entity methods, they do so through interfaces such as the "User" abstract class. This follows the dependency rule.

The database package does the same. It saves use-case classes, accessing them using the IDBSaveable interface.

The IGettable interface allows outer layer classes to access relevant fields of Use Case classes, for the purpose of displaying
information on the screen.

## Outer
ScreenIO is the main class in this application. It only depends on controller classes, and nothing else.

Database objects will save UseCase objects, which isn't really clean architecture. However, using the
dependency injection design pattern, we simply take the objects and serialize them, so Database doesn't actually
depend on how the objects are created, it uses the Serializable interface to save them.

## CRC model

You can look at the CRC model in the phase0 folder. It kind of outlines how our program is structured currently.

The only difference between the current program and the CRC model from before is that we added extra classes,
such as a lot of derived Command classes, a CommentManager class, etc.

These classes follow the general idea that we had where we had entities, and then "managers" that manage entities,
and then controllers that divide up tasks and give output to ScreenIO.

## Scenario Walkthrough

This is a scenario walkthrough that may help show how classes interact with each other.

Assumption : We assume a student already has an account in our system, with an associated id.

### Step 1: The student tries to log in
- The student will login through the command line interface, passing their id as an argument.
- This request will be sent to the CommandExecutor, that will log the user in.

- (How this actually happens will be as follows:)
  - CommandExecutor initializes a new LoginCommand(see it's processRequest method), and calls the run() method
  - LoginCommand communicates with Database, which will return a UserManager object if it has an entry for a student with the inputted id.
  - CommandExecutor will store this object it receives inside itself.
  - The LoginCommand that was run will output that it successfully logged in.

### Step 2: The student tries to view a course page
- The student will request to view a course page(that exists) through the CLI
- Passed to CommandExecutor, which breaks the task up as follows:
  - looks into the Database, which gives back a CourseManager object if the course exists.
  - checks with AuthHelper to see if student is allowed to access that data
  - If yes, Similar to how it stored the student data in a use-case class, CommandExecutor takes the CourseManager to store the course's data.
  - Command returns a success string, that is displayed on the CLI as well.

### Step 3: The student tries to leave a rating
- The commandExecutor has the courseManager object stored from the previous scenario, and:
  - checks with AuthHelper if the student is allowed to leave a rating
  - asks courseManager to process the rating if the student is allowed.

### Step 4: The student tries to print the course's info after leaving the rating
- The commandExecutor has the courseManager object stored from the previous scenario, and:
  - checks with AuthHelper if the student is allowed to leave a rating
  - asks courseManager to give up its info for presentation purposes(as it implements IGettable)
  - displays that info on the screen

### Step 5 : The student can leave a comment in course page
- The commandRequest asks CommentManager to process the comment to show the review in course page

The CommentManager class(UseCase) depend on the methods of CommentGraph class(entity)
- Clean Architecture - dependence on adjacent layer: Use Cases class depend on entities 

