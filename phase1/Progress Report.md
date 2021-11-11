#Progress Report 

## Questions group is struggling with 

- Comment related commands cast the commandExecutor's currentlyViewingPage to a CommentManager object, because
it first verifies that we are allowed to run comment related commands on currentlyViewingPage(which implies it's a CommentManager).
Is this alright to do? Is there a better way to do this? Because the casting isn't...strictly enforced.
- One difficulty in testing is that it's difficult to test the Commands using code, because we have to create
  an entire CommandExecutor class, load some sample data, etc. I(Kevin) have been testing it manually by running
  the program. Suggestions on how to test better would be appreciated.

## What has worked well so far with our design 

- Design patterns are working well. Command/Simple Factory pattern is useful in the commands package.
Dependency injection is a simple fix to enforce clean architecture.
- Reliance on interfaces is useful, it inverts the dependency in a lot of places in the code.


## A summary of what each group member has been working on and plans to work on next

- Kevin: Implemented Authorization system, Database system, added comment related commands, helped with writeup.
Will wait to implement features that team members create, by adding relevant commands and such.