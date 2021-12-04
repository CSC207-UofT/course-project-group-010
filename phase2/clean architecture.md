# Clean Architecture + Scenario Walkthrough

Our program follows clean architecture

- a diagram of how our program approximately works can be found in phase2/diagram.png
- an explanation of what each class does can be found in phase2/specific-design-explanations/Classes Explanation.md

# Scenario Walkthrough

Assume the user is logged in and viewing a page, and enters checkout -c into the console

Outer layer
- ScreenIO processes the request, by delegating work to controller classes
Controller
- This command will be processed by a commandRequest object, which separates the command into a method(checkout) and arguments(["-c"])
- The request is sent to CommandExecutor, which fetches the appropriate command from CommandConstants(factory pattern).
- The CommandExecutor gets back a CheckoutCommand, and uses it's run() method to run it with the arguments given
- CheckoutCommand has a factory method getPageGetter, in this case since the user entered -c, it will initialize a CommentPageGetter to fetch the comment section
- The CommentGetter will get the comment section from CommandExecutor's currentlyViewingPage(type IReadModifiable)
Use Case
- The user is currently viewing a page, thus currentlyViewingPage is type CourseManager(this is an internal rule that is enforced in our code). The CourseManager gives up
it's respective comment section, in the form of a CommentManager object
Controller
- We initialize a CommentPresenter object to wrap the commentManager object, and set that to the user's currentlyViewingPage
- We return the success string(found in CommentPageGetter) of "now viewing comment section for page."
- The CheckoutCommand's run() method will return that as well.
Outer layer
- ScreenIO will print this output to the screen, notifying that the user is now checking out the comment section for the page.

As shown, the data travels down through the layers, and then back up through the layers as methods calls return different values.

# Violations?

The only violation of clean architecture is Database. This problem is explained in phase2/specific-design-explanations/database.md.
Otherwise, if you look at our import statements, you will see that there are no incorrect dependencies, by looking at phase2/CleanArchitectureDependencies.pdf.

# Is the Dependency Rule consistently followed when interacting with details in the outer layer?

eg. if you run the print command while viewing a page, the outer layer will call the command(controller) to gather data and return a string to be printed on the screen. Classes
from non-adjacent layers don't interact, and the request data and response goes through the layers one at a time.
