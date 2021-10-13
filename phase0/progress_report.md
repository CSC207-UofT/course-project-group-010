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

## Scenario Walkthrough

## Skeleton Program

This can be found in the src folder.

We have packages corresponding to each layer, with additional packages to group similar classes together.
They are in the src/main/java folder

src/main/data is some sample data that allows the program to run

src/test/java is where our tests are stored

You can run the program by [adsklfjaslfjsalkjsdalfkjsadlfsajd]

## Questions we are struggling with

## What has worked so far with our design

## What has each group member been working on and plans to work on?

- Kevin: planned initial CRC model, working on the writeup of that then helping out on outer layer classes.
  - Plans to continue working on program design and helping write test cases
- Nima: working on entity classes and the authorization system of the program
- Fiona: working on interfaces, database gateway classes
- Noah: working on use case classes for the Course entity
- Junhyuk: Working on use case classes for the User entity