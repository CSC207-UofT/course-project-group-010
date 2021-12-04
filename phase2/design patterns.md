# Design Patterns

### Entity

- UserFactory(entity.UserFactory) uses the factory pattern to produce a user based on a specified type. Eg. if you specify UserTypes.STUDENT, it will give back a StudentUser.

### Use Case

- CoursePageBuilder and Director(useCase.coursePage) implement the builder pattern to build a coursepage(which sets the basic data, creates ratings, creates the comment section, etc.)

### Controller

- UserBuilder(controller.commands.commandHelpers) is an interface that helps process input for creating a new user(eg. user ID must follow a specific regex, so there is a method
for checking that). This is a strategy pattern, as both StudentUserBuilder and InstructorUserBuilder(that implement this interface)
can be used in controller.commands.NewUserCommand to help build a new user. 
The factory method pattern was implemented in NewUserCommand that gets the correct UserBuilder and runs the shared methods.
- The same idea was implemented in controller.commands.CheckoutCommand. controller.commands.commandHelpers.PageGetter is a strategy, and CommentsPageGetter, CoursePageGetter and
UserPageGetter all implement this interface. A factory method was created in CheckoutCommand to help implement this strategy pattern.

Checkout phase2/specific-design-explanations/commands.md for more details on this.

- DataPrinter is somewhat an example of the Facade pattern. Basically in the print command, we noticed that the frontend team cares about how we process data gotten from the IGettable
interface, and the backend team cares about how we get that data in the first place. Thus, controllers.commands.commandHelpers.DataPrinter is a class that has the single responsibility of
processing a map for the purpose of frontend display, and the run() method in controllers.commands.PrintCommand simply delegates the printing part to this class. Therefore, it's somewhat
of a Facade.

- CommandConstants uses the factory pattern to return the appropriate command when we enter a method(string). Eg. it maps "checkout" to new CheckoutCommmand(). This is used
in CommandExecutor to execute many different objects that all derive the Command class.

### Could we implement more design patterns?

Problably. Many classes have quite a few methods, and the Facade pattern could have been implemented. However, we didn't notice any significant breaches of the Single Responsibility Principle,
so we are choosing not to implement the pattern. This pattern is quite easy to implement into a class anyways(as you just make a new class that does the responsibilities of a method), so
if we ever see that a single method is corresponding to multiple actors, we will implement it.
