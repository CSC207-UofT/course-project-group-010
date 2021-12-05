# SOLID

How does our program follow SOLID principles?

I will briefly explain, and add more examples at the bottom of the document. Feel free to skip them if you think we've satisfied the criteria for this part.

### Single Responsibility Principle

The main actors in our program is the frontend and backend team. Commands, initially, do not follow SOLID principles as they produce side effects AND display something to the screen.

However, we added helper classes to adhere to SOLID principles.

Eg. NewUserCommand prompts the user for a user type, name, id, and other data. Ids must follow a specific regex pattern, and otherData varies depending on whether the user type is a student
or an instructor. Getting user input, processing it to make sure it is valid, and constructing the user is a lot of responsibilities for one method. Thus, we used the strategy and factory
method pattern to break it up.

[THIS WAS ALREADY MENTIONED IN COMMAND DESIGN.MD, skip the following if you already know how it works]

We made a UserBuilder interface, where StudentUserBuilder and InstructorUserBuilder follow it(found in controller.commands.commandHelpers). It contains methods related to processing data,
which would correspond to the backend team. We initialize one of these builders using a factory method in NewUserCommand, then use it to process user input. I also refactored
the user creation process into a separate method(createUser), which corresponds to the backend team as well. The frontend team is responsible for the run() method in NewUserCommand,
displaying the results of the construction and such.

### Open/Closed principle

Eg. The command system is open for extension, closed for modification.
- CommandExecutor has a processRequest method, that uses the Command abstract class's run() method to run any of its subclasses.
- New commands are created by extending Command, then implementing the run() method
- Therefore, CommandExecutor is closed for modification(no modification necessary if we make a new command), but Commands are open for extension(by extending the Command class)

### Liskov Substitution Principle

Subclasses can be substituted for parent classes without breaking the code.
- Eg. Any subclass of command can be substituted for the Command class in CommandExecutor.processRequest() method, as they all must implement the abstract run() method
- Eg. CommandExecutor has instance variable "pageManager" of type IReadModifiable. It takes advantages of the shared methods in IReadModifiable, that all implementing classes must have.
IReadModifiable specifies the return type and exceptions that can be thrown by the shared methods, thus substituting a child class will not produce unwanted exceptions or anything.

One concern you may have is eg. in controller.Commands.CommentCommands.ReplyCommand, we downcast IReadModifiable to CommentPresenter. This would not follow the LSP normally, but
we assure that IReadModifiable is an instance of CommentPresenter using our authorization system.

We check that the user is allowed to run the reply command on the IReadModifiable object. Currently, as an internal rule, that implies that the object is an instance of CommentPresenter.
Thus, you would not be able to reach the line of code that does the casting if your object was not that specific subclass, and therefore it follows the LSP.

### Interface Segregation Principle

Interfaces shouldn't be too complex, break large interfaces into smaller ones

For example, IGettable is an interface for classes that can give data to a presenter, using the shared getData() method. IAuthorizable is an interface for classes that the user
can take action on, such as CommentPresenter(which the user can reply to). We realized some classes need both interfaces(like CourseManager, which you can view AND interact with), and other
classes may only need one. Also, the functions of the two interfaces aren't really related.

Therefore, we separated the two interfaces, and made a new interface IReadModifiable that extends them both for use in the program. The interfaces aren't too complex, and are broken up so that
classes won't have to implement unnecessary methods.

### Dependency Inversion Principle

High level classes do not depend on low level classes. This is shown in our import statements. Classes in a package corresponding to one layer of clean architecture only depend on
classes in the next layer of clean architecture, as well as Interfaces, Exceptions and Constants. We will show this in [!!!!]

Abstractions don't depend on details, details depend on abstractions. Our interfaces and abstract classes do not change very often. For example, no public methods
were added to the Command abstract class after it's creation. We only added some protected helper methods. IGettable and IAuthorizable haven't changed since their creation, except
when refactoring the method names. We clearly decided what each interface would do in our initial design, and only built our implementing classes around these interfaces.

One issue that we have is the fact that DatabaseGetter(gateway class) depends on Database(outer layer). This is bad. We considered inverting the dependency by making a
database Interface and having DatabaseGetter depend on that, but we realized that it wouldn't solve the problem. Since our database isn't a real database, if we were to implement
a real database we would have to change the database Interface as well(to reflect how actual databases work). So even though we would create an interface and technically "invert the dependency,"
DatabaseGetter would still depend on the implementation of Database. Therefore, we decided to wait until an actual database is introduced to change our classes.

More explanation is in phase2/specific-design-explanations/database.md. PLEASE VISIT THAT if you think our decision is not justified.
