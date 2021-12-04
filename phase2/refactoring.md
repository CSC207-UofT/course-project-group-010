# Refactoring Evidence
https://github.com/CSC207-UofT/course-project-group-010/commit/c9c8ff9ec8d308a71181d1ba7b6179ec3a37a686 Merge pull request #109 from CSC207-UofT/tests

REFACTORED
- previously had CommentsGetter and UserGetter, but implemented a strategy pattern in CheckoutCommand. These both now implement PageGetter, and their methods were refactored and the classes were renamed.
