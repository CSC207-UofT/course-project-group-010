# University Course Review System

This is a course review system for University Courses, kind of like RateMyProf with additional features.
Students and professors will be able to view courses, leave reviews/ratings, and chat in the comments section.

PLEASE READ THIS ENTIRE DOCUMENT BEFORE YOU BEGIN MARKING, IT WILL ALLOW YOU TO MARK FASTER BY DECREASING THE TIME YOU SPEND LOOKING AROUND THE CODE.

Go to the phase0 folder for phase0, I would recommend looking at progress_report.md first

Go to the phase1 folder for phase1, I would STRONGLY recommend starting with DesignDocument.md. It references all the
other documents.

# How to mark us:
We want to streamline your marking process, and let you understand our program quickly. Here are the steps to do so

First, understand how our program was build(code-wise)
- go to Classes Explanation.md, and make sure you understand what classes can be found in each package. You don't have to read the full document.
- go to Commands.md. This will explain our design choices regarding the commands package, which you seem to find confusing. Hopefully this helps.
- [other markdown docs to explain our decisions]
- [progress report here]
- Then, try running the program.

# Run the program

- Clone this folder with github desktop
- Make sure you mark src/main/java as sources root(rightclick > mark directory as > sources root)
- Also mark src/test/java as test sources root(rightclick > mark directory as > test sources root)
- Run the main() method in the outer.ScreenIO class

Inside the program:
- the enter "help" to get help, or enter [commandname] -h for specific command help
- listusers and listcourses to see available course and users.
- You can start by making a new user with the newuser command, then login as the new user.
- Try accessing a page with the checkout command.

# Commands overview

Overview of commands by type. In the future, this will be included in helpcommand() for convenience.

- help: displays all commands and their respective help strings
- basic commands for logging in, viewing pages: login, checkout, print, logout
- saving/database related: saveall, newusuer, createcourse
- Main comment related commands: getcomments, displayfullthread, reply, vote
- Rating related commands: filter, rate
- Other comment commands displaysubsetthread, getpath

# Sample commands to demonstrate functionality:
You may be prompted to enter additional info while running these commands. We will load some sample data into the system for you to check out.

> login [id here]

// Seeing Basic information

> checkout MAT137
> 
> print

// Interacting with the comment thread

> checkout -c
> 
> print
> 
> reply root
> 
> print

// Check out our comment navigation feature

> print [this will print two comments, now that you have replied. You will navigate to your own reply]
> 
> cd [id of your recent comment]
> 
> print
> 
> cd ..
> 
> print

// Rating the course(must filter by instructor to rate the course)

> checkout MAT137 [goes back to looking at the course page]
>
> rate 2
> 
> print

// Creating a new user to also rate the course

> logout
> 
> newuser [follow CLI prompts]
> 
> checkout MAT137
> 
> rate 5

// Checking that the rating has updated

> print [should show different rating]
> 
> saveall

// Showing our serialization functionality

> end [ends program, automatically saves]
> 
> [run ScreenIO.main() again]
> 
> listusers
> 
> listcourses
> 
> [check things out again, it should have saved your progress]
