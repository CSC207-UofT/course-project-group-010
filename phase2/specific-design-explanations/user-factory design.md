# Users - Factory Design Pattern

Thank you for your(Pan) suggestion. We implemented a factory design pattern for users to standardize the architectural
model for a range of users. This implementation not only simples our coding but also increase the extensibility of our
program for adding more types of users in the future.

Thus, the NewUserCommand will pass the user information to UserManager and then UserManager asks UserFactory to define
which type of user to create. IUser interface specifies generic behavior of user and delegates the detail to
subclasses (( StudentUser and InstructorUser). 

