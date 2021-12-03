# Commands
This document will explain how commands approximately work in the code. Commands seem confusing, as there are a lot of different classes, but it is necessary for the code.

All commands extend the Command class, so you only need to understand how the parent class works.

### Command has 4 important points:

- Its initializer sets its minimum and maximum arguments. All derived classes will call super([minargs], [maxargs]) to set their individual minimum and maximum arguments
- The run method is called to run the command. Each child class overrides it. This will do something in the program(like edit data, call use case classes, etc.), then return a string or throw an exception
- The help method is expected to be overridden by child classes. It is implemented in the parent class with a default message "help is not implemented yet," and child classes can override the help string that is returned
- There are numerous protected check___ methods, that check things such as: whether the correct number of arguments were passed in, whether the user is logged in/viewing a page, etc.
These typically get run at the start of the run() method.

### How does a command get called?

CommandConstants maps each command to a method. It is a factory, in a sense.

For example, if you call CommandConstants.get("help"), it will return a new HelpCommand.

Therefore, in order to run a command,
- the user inputs something (eg. "checkout abc")
- the input gets split into a method and arguments(method="checkout", arguments=["abc"])
- We get the corresponding command from the factory(new CheckoutCommand())
- We run the command's run() method with the arguments(CheckoutCommand.run(arguments))

I'm using CheckoutCommand to denote an object of type CheckoutCommand here.

# Command Design Decisions

There are a lot of command classes, and design decisions had to be made.
I will explain them here.

**IMPORTANT:** most commands simply break up a larger task into logical pieces, eg. checking if the user
is allowed to do the command, and then performing the command. You can verify this by looking at the command classes.

### NewUserCommand
NewUserCommand is supposed to prompt the user for the following things:

- Name
- Id
- ProgramDetail(for students), Position(for instructors)

Then create a studentuser or instructoruser based on the data inputted. One key idea is that users both have an instance variable
called otherData, which is a map of miscellaneous data. eg. for students, it maps programDetail to their programDetail, and for profs, it maps position to their position.

**The problem**

There's a different procedure for collecting inputs for the type of user. For example, both students and instructors have to input something into
otherData, but the key it gets mapped to is different.

**The solution**

I made a UserBuilder interface. It's not really a builder, it just contains the logic for processing each user input and returns the proper value. It's kind of like a builder,
but it doesn't actually build anything.

StudentUserBuilder and IntructorUserBuilder implement the UserBuilder interface. Then, based on the user's initial input of [student/instructor], I get its respective builder
using a factory method, and then execute the shared methods in order to build a user object.

**(IGNORE IF YOU ARE RUNNING OUT OF TIME) More explanation**

*In the StudentUserBuilder, the processOtherData() method maps "programDetail" to the user's input, whereas in InstructorUserBuilder, it maps "position" to the user's input*

*Then I implemented the Strategy pattern and a factory method pattern into the NewUserCommand class. The final execution is as follows*

### DataPrinter, PageGetter(CommentsPageGetter, UserPageGetter, CoursePageGetter)

These classes all serve to satisfy the single responsibility principle, without introducing speculative generality. It isolates tasks involving logic so that
command classes only delegate to other classes for complex functions, and do not contain logic of their own.

- DataPrinter processes the getData() command from the IGettable interface, returning a string that can be printed
  on the screen. This is used in the print command, which can print course pages, comment pages and user profiles.
- PageGetters are used in the CheckoutCommand class. Checkout allows the user to check out courses, comment sections for courses, or their own user profile
  (by entering checkout [code], checkout -c or checkout -u). I used a factory method in CheckoutCommand to return a PageGetter, that is responsible for getting the correct page.
  check this out by looking at the getPageGetter method in CheckoutCommand.

### Other Commands
Other commands simply partition a large task into smaller ones. It then delegates work to other classes.

eg. PrintCommand does the following
- runs checkAll(), a method that checks if the user is logged in, viewing a page and is allowed to run the "print" command on the page he is currently viewing.
- runs the getData() method(found in the IGettable interface) on the page that the user is currently viewing, to get data to be displayed
- Instantiates a DataPrinter to process this data, representing it as a nice string.
- Returns the string.
