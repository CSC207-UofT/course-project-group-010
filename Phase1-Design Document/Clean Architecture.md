#Clean Architecture

## Scenario Walkthrough 

Assumption : We assume a student already has an account in our system, with an associated id. 

### Step 1: The student tries to log in  
- The student will login through the command line interface, passing their id as an argument.
- This request will be sent to the CommandExecutor, that will log the user in.

- (How this actually happens will be as follows:)
  - CommandExecutor communicates with Database, which will give up data if it has an entry for a student with the inputted id.
  - CommandExecutor will initialize a new UserManager class to manage the data it received, and stores it inside itself.
  - The CommandExecutor will output that it successfully logged in.
  
### Step 2: The student tries to view a course page 
- The student will request to view a course page(that exists) through the CLI
- Passed to CommandExecutor, which breaks the task up as follows:
  - looks into the Database, which gives back data.
  - checks with AuthHelper to see if student is allowed to access that data
  - If yes, Similar to how it stored the student data in a use-case class, CommandExecutor initializes a CourseManager to store the course's data.
  
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

  

## Dependency Rule
We follow the dependency rule consistently when interacting with details in the outer layer as the inner layers will not depend on the outer layers in our system.  
For example, our entity class will not depend on the gateway class (ScreenIO). Also, Outer layer is more concrete and specific than the inner layer

## Entities - Enterprise Business Rules
Entities (Such as StudentUser and Rating) represent the business objects of the application.


## Use Cases - Application Business Rules 
UseCase (such as CoursePage and CourseManager) are application-specific business rules and any change made inside this folder will not impact the entities level.



