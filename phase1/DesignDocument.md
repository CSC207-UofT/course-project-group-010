# START HERE

Read the updated specification, additional functionality, then go through the files listed at the bottom of this
document.

# Updated Specification

Users (Students or Instructors) can sign into the app " University Course Review System" using an ID. 

There will be courses. Each course will have a page with basic info (course name & code & description & instructors) and course ratings (overall rating & relative rating based on their program of study).

Also, each course page has a comment thread, with questions, that the user can display, reply to, upvote, and downvote.

Users can view the content of course pages and comments

Users can answer these specific questions or add reviews/upvote to each existing answers.

Users can leave a rating on a course. The course will take the average rating of all users and display it on the page.

This application will have a command line interface, where users can enter text commands and get back text responses.

# Additional functionality Added 

-New functionality to course page (
    1. a list of questions
)

-New functionality to users (
    1. answer these specific questions shown on the course page 
    2. add reviews to each existing answer from a list of questions shown on the course page
    3. upvote/downvote the existing answers shown on the course page
    4. users can create new users, login and logout now.(creating new users is a debugging tool)
)

-New functionality to authorization (
    1. Pages can restrict students/instructors from running certain commands, or give students/instructors permissions to do certain things
    (this doesn't really affect the program right now, as commands requiring higher permissions haven't been implemented)
)

-New functionality for databases (
    1. When you input "end" to stop the program, all data is saved(serialized) to .ser files.
    2. You can save all users/courses in the session using the saveall command.
)

# Features we considered(and may implement later)

The relative rating for course based on the program of study

# Helpful Diagram

Some diagrams can be found in Diagrams. These were optional, so please don't mark them at all. 
They are meant to show relevant classes for a few systems in this program, such as the Authorization system, 
and the Command System.

# Some files

- Clean architecture.md: Explains why our program follows clean architecture
- Solid Design Principles.md: Explains why our program follows SOLID principles
- Major design decisions: Explains our rationale for features, and for implementing software design decisions
  (such as design patterns)
- PackagingStrategy.md: Explains our rationale for using the Clean Architecture Layer packaging structure
- Progress Report.md: Our progress report.
- Other.md: A few extra details

