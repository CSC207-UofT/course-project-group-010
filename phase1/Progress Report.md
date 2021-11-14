# Progress Report

## Questions group is struggling with

- Comment related commands cast the commandExecutor's currentlyViewingPage to a CommentManager object, because it first
  verifies that we are allowed to run comment related commands on currentlyViewingPage(which implies it's a
  CommentManager). Is this alright to do? Is there a better way to do this? Because the casting isn't strictly
  enforced through code, it's enforced by our rules.
- One difficulty in testing is that it's difficult to test the Commands using code, because we have to create an entire
  CommandExecutor class, load some sample data, etc. I(Kevin) have been testing it manually by running the program.
  Suggestions on how to test better would be appreciated.

## What has worked well so far with our design

- Design patterns are working well. Command/Simple Factory pattern is useful in the commands package. Dependency
  injection is a simple fix to enforce clean architecture.
- Reliance on interfaces is useful, it inverts the dependency in a lot of places in the code.

## A summary of what each group member has been working on and plans to work on next

- Kevin: Implemented Authorization system, Database system, added comment related commands, helped with writeup. Will
  wait to implement features that team members create, by adding relevant commands and such.
- Fiona: Wrote the Design Document and the Test Cases, and worked on Code Style and Documentation.
- Noah: Implemented CommentGraph system. Plan to implement more features to commentGraph(e.g. searching and merging) as 
  well as implementing graphs in other parts of the program.
- Nima: Outlined interaction between command and CoursePage construction for user input. Implemented Builder design for
  constructing a CoursePage. Updated CoursePage entity. Plan to implement additional features such as profiles, 
  filtering pages by year (possibly) for phase 2.
- Junhyuk: Designed updated CourseManager in accordance with Command's interaction with User input. Implemented tests 
  for CourseManager and Builder classes. 
- Wilson: Work on rating functions for phrase 2 submission.

## Things we must work on in the future

- Making commands more intuitive. Eg. instead of displayfullthread for displaying a full comment thread and
print for displaying page-related information, it would be better if print did both things.
- Adding the rating functionality back with our new relative rating system, or roll back our past "average rating" system.