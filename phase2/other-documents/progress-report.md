# Progress Report

## What has worked well so far with our design since phase 1



## Summary of what each group member has been working on since phase 1 + pull requests

- Kevin:  I code every day, so my pull requests are small but there's a lot of them. I implemented factory/strategy patterns for commands,
  wrote tests, and cleaned unused methods.
    - [adding factory/strategy patterns](https://github.com/CSC207-UofT/course-project-group-010/commit/5ddf83715f61e20ca0d0ce7b3d8acb9a75f71406) ||
      [cleaning up unused methods](https://github.com/CSC207-UofT/course-project-group-010/commit/7c19b3375be20919d07d09906550ca258a4611f4)
    - I looked through all the classes to delete unused methods by using the "find usages" intelliJ feature. I also implemented patterns in Command Classes, that used to
      do many different things. As a result, the design of our program follows SOLID principles better, and our classes are easier to understand without redundant methods.
- Fiona: I worked on implemented design patterns for users, wrote test cases, performed refactors, wrote accessibility report, helped with design document, and fixed warnings using inspect code feature.  
    - [adding factory patterns to users](https://github.com/CSC207-UofT/course-project-group-010/pull/89/commits/09d842dfb805c348b28775a32459dbdc8dc1f3a5)
      -I implemented factory patterns in Users by creating/modifying UserFactory, IUser, StudentUser, InstructorUser, and UserManager class.
- Noah:
  - I created the commentGraph data structure that our program uses for threads in course pages. This includes `CommentGraph.java`, `CommentGraphHelper.java`, `CommentManager.java`, and related test files.
  - In regards to Phase 2, I added various ways to search through data in the graph data structure.
  - Some notable pull requests are
    - [creating commentGraph functionality](https://github.com/CSC207-UofT/course-project-group-010/pull/39)
    - [making improvements to commentGraph](https://github.com/CSC207-UofT/course-project-group-010/pull/101)
- Nima:
  - I created/polished entities (except UserFactory, that is Fiona's) and provided detailed javadoc where needed. I also implemented the CoursePage use-case utilizing a Builder design pattern with a Builder interface, a CoursePageBuilder, and a Director, updated for Phase 2. 
    - [creating entities](https://github.com/CSC207-UofT/course-project-group-010/pull/16)
    - [addition of Builder Design under Kevin's Pull Request]([https://github.com/CSC207-UofT/course-project-group-010/pull/73)
    - [polishing of code/javadoc](https://github.com/CSC207-UofT/course-project-group-010/pull/139)  
    - I didn't make many pull requests so a lot of my work was pulled to main via others' pull requests since we were working on the same files.
- Junhyuk: Implemented relative ratings related methods mainly in CourseManager. Fixed warnings using inspect code feature.
    - [CourseManager implementation](https://github.com/CSC207-UofT/course-project-group-010/pull/100)
- Wilson:
  - Assisted Fiona with [implementing Simple Factory pattern](https://github.com/CSC207-UofT/course-project-group-010/pull/89) for User subclass instantiation
    - This ensures that UserManager follows OCP and also removes duplicate methods in UserManager
  - Various minor refactors (to fix naming conventions, use more readable variable names, remove unnecessary dependencies etc.)
  - Code review / Debugging (see GitHub issues board)
  - Suggest further program enhancements (not implemented due to time constraints)
    - Better course rating calculation algorithm based on [SteamDB's rating system](https://steamdb.info/blog/steamdb-rating/)
    - Strategy pattern for course rating to allow different algorithms to be used
    - [Re-rate optimization](https://github.com/CSC207-UofT/course-project-group-010/issues/144)
