# Walkthrough

## The Scenario
The scenario will be broken into 2 parts.

(We will assume a student already has an account in our system, with an associated id)
### The student tries to log in
- The student will login through the command line interface, passing their id as an argument.
- This request will be sent to the CommandExecutor, that will log the user in.
  
- (How this actually happens will be as follows:)
  - CommandExecutor communicates with UserDatabase, which will give up data if it has an entry for a student with the inputted id.
  - CommandExecutor will initialize a new UserManager class to manage the data it received, and stores it inside itself.
  - The CommandExecutor will output that it successfully logged in.
## The student tries to view a course page
- The student will request to view a course page(that exists) through the CLI
- Passed to CommandExecutor, which breaks the task up as follows:
  - looks into the CourseDatabase, which gives back data.
  - checks with AuthHelper to see if student is allowed to access that data
  - If yes, Similar to how it stored the student data in a use-case class, CommandExecutor initializes a CourseManager to store the course's data.
## The student tries to leave a rating
- The commandExecutor has the courseManager object stored from the previous scenario, and:
  - checks with AuthHelper if the student is allowed to leave a rating
  - asks courseManager to process the rating if the student is allowed.
## The student tries to print the course's info after leaving the rating
- The commandExecutor has the courseManager object stored from the previous scenario, and:
  - checks with AuthHelper if the student is allowed to leave a rating
  - asks courseManager to give up its info for presentation purposes(as it implements IGettable)
  - displays that info on the screen.
