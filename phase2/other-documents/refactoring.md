# Refactoring Evidence

Here is some evidence of refactoring.

[Merge pull request #109 from CSC207-UofT/tests](https://github.com/CSC207-UofT/course-project-group-010/commit/c9c8ff9ec8d308a71181d1ba7b6179ec3a37a686)

REFACTORED
- previously had CommentsGetter and UserGetter, but implemented a strategy pattern in CheckoutCommand. These both now implement PageGetter, and their methods were refactored and the classes were renamed.

Other refactor we have done
- Merged PermissionLevel and Usertype, as we noticed they both held the same values(that identify a user as student/instructor)
- Refactored method names(that happens a lot)