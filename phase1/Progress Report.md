# Progress Report

## Questions group is struggling with

- Comment related commands cast the commandExecutor's currentlyViewingPage to a CommentManager object, because it first
  verifies that we are allowed to run comment related commands on currentlyViewingPage(which implies it's a
  CommentManager). Is this alright to do? Is there a better way to do this? Because the casting isn't...strictly
  enforced.
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
- Fiona: Wrote the Design Document, Code Style and Testing.
- Noah: Implemented CommentGraph system. Will wait to implement more features to commentGraph(e.g searching and merging)
  and other graphs.
- Nima and Junhyuk : Implemented coursepage system.
- Wilson: Work on rating functions for phrase 2 submission.
- Noah:
  - For Phase 1 I was responsible for implementing comment functionality into the program. In order to do this I created a data structure that is a mix between a directed graph and a tree. The commentGraph is composed of various subclasses which help create comments and store them. There are many functions in the commentGraph, that allow users to reply to comments, display comments, etc... There are also recursive depth based functions which help navigate through the graph. The following files were created by me: CommentGraph.java, CommentGraphManager.java, CommentGraphHelper.java, CommentGraphManagerTest.java. For the future, I plan to add more features to CommentGraph, including searching and merging graph functionalities. I also plan to create graph data structures for other parts of our program. I made commits under three separate usernames (different computers), `noahsub`, `noahsub_laptop`, `Noah Subedar`. 


