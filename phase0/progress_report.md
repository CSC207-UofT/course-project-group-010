# Progress Report

## Specification - specification.md

We want our app to be a university course review system.

Our end goal is to have students and profs be able to view pages for courses, with students leaving reviews/ratings
for the courses. Students and profs can interact in the comments section of each course, discussing strategies for success,
course difficulty, and other topics.

For Phase0, our specification only includes the necessities for this app to run. Users can log in, view course pages
and leave ratings. Additional features are listed in the markdown file as well.

## CRC model

The CRC model for our program approximately follows a clean architecture structure.

- Entity classes store data for users, courses, and anything else that has data
- Use case classes manage use cases for entities. For example, UserManager will initialize with a user and will manage its use cases and update the userData accordingly
- Controller/presenter/gateways are in the next layer. 
  - CommandExecutor is a controller that will break each command that we came up with in our specification(eg. login, rateCourse) into smaller tasks
  - Database classes will probably be gateways to external databases(like SQL) in the future.
- ScreenIO is our only outer layer class. It will take text input from the user and call the appropriate functions.

CRC model is illustratred in a diagram called CRC.jpg, also in the same directory. You will have to rightclick > open image in new tab to see the full image.

## Scenario Walkthrough - walkthrough.md

Basic scenario walkthrough.

The scenario is: a user tries to login, view a page, then leave a rating on the page.

It shows what classes would handle what responsibilities when these actions are taken. Also, to demonstrate how this works in the actual code, you can run the program and enter the following commands:

> login 12345

> checkout MAT137

> print

> rate 5

> print

## Skeleton Program

This can be found in the src folder.

We have packages corresponding to each layer, with additional packages to group similar classes together.
They are in the src/main/java folder

src/main/data is some sample data that allows the program to run
Update: we hardcoded data into our program. This will be where future sample data goes.

src/test/java is where our tests are stored

### Running the program:
- If it can't run after cloning, try "new project from VCS" in the IntelliJ editor.
  - github desktop cloning seems to encounter issues with problems
  - main method is in src/main/java/Outer/ScreenIO.
- the enter "help" to get help.
- login 12345 and checkout MAT137 are the only valid login and checkout cases for now.
  - courses and users are currently hardcoded.
- Example: login 12345, checkout MAT137, print, rate 5, rate 10[will not allow you], print
- program can only close by termination for now.

## Questions we are struggling with
- CommandManager uses polymorphism. For example, it holds a "currentlyViewing" object that extends IGettable.
  - IGettable is an interface that ensures a class can give up data to a presenter
  - However, we want to also ensure that it's a course or something if the user tries to leave a rating, but the prof said it's bad to check instanceof and stuff
  - So we have other interfaces that get implemented. However, variables can only have one class.
  - So how do we ensure that one variable meets multiple categories, or is there a better implementation method?

- Some of our use case classes are closely tied with the Entity classes. If a controller wants to initialize a use case class, it needs to initialize entity classes as well right now.
However, if we try to take data from a database, it may be too complex to transfer all the data that needs to be stored. Should we try simplifying
the entity objects, or try advancing the Databases, or do something different?
  - For example, CourseManager requires a coursePage to be initialized.
  which requires a rating, instructor, course to be initialized. 

- Finally, we haven't really figured out what Database classes do. I think it should be a gateway to external databases(eg. SQL),
but then what do I make it do? Do I make it return a map that other classes can use, or return an object? And for the userDatabase, how could I
differentiate between studentUsers and profUsers and initialize them differently?

- Why is this project so hard?

## What has worked so far with our design
- Copying JShell was great! On a serious note, looking at how JShell allowed me to figure out that what commands have in common
is that they all take in arguments and then output a string for now, so it was easy to make polymorphic command classes from then on.
Also, I copied the the Command design pattern because I was learning it in class, and broke commands into request objects. I think that made the program really nice.
  - New commands are really easy to integrate now, and the project's mostly about fine-tuning the use-case classes and entity classes to be more workable.

- Outer classes in general are integrated quite nicely. They don't depend on the wrong classes at all. For example, the CommandExecutor class hasn't changed since I first
worked on it, even though entities weren't even created at that point. Use case classes need a bit of work, but hopefully they will be like that too.

## What has each group member been working on and plans to work on?

- Kevin: planned initial CRC model, working on the writeup of that then helping out on outer layer classes.
  - Plans to continue working on program design and helping write test cases
- Nima: working on entity classes
  - Plans to implement the authorization system of the program
- Fiona: working on interfaces, database gateway classes
- Noah: working on use case classes for the Course entity
- Junhyuk: Working on use case classes for the User entity
Everyone plans to thoroughly test this program later on.
