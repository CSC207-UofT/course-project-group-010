# Design document

## Main update since phase 1

Our main update of this phase was implementing relative rating. This feature allows users to see average ratings filtered by the the programs of users who left ratings.

We also cleaned up a lot of unused methods, and made helper classes for certain commands to satisfy the single responsibility principle(you can read about that in other documents)

## New design patterns that are implemented in this phase
1. Factory: User factory class produces a user depending on user's inputs and CommandConstants class returns a new command based on user's inputs with factory design pattern.
2. Builder: CoursePageBuilder and Director classes build a CoursePage instance using Builder design pattern. 
3. Facade: PrintCommand class delegates in print method using facade methods.
More details in design patterns.md
