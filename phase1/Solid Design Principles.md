# Solid Design Principles

How well does your design adhere to the SOLID design principles?
Give us specific examples of how your design adheres to the SOLID principles.

##Single responsibility principle (SRP)

Each class of our system only has responsibility for a single part of system functionality.
Specific example, Under entity, rating class under entity is only responsible for course rating and review class is only responsible for course review. 

In addition, methods in the same class don't really rely on a common helper method or anything like that, so even
though some classes have methods with different functions in them, they have different responsibilities and therefore
follow the SRP.

##Open/closed principle (OCP)

Our system entities are open for extension but closed for modification. 
Specific examples:

-The Rating class is stored in the CoursePage class, and is responsible for processing and calculating ratings. 
Currently, it reports the average rating of the course. However, in the future we want to implement the "relative rating" feature,
reporting on different ratings for students of every major. We would simply make a new rating class, and use it in CoursePage.
Thus, the current Rating class is closed for modification, but open for extension.

-The Commandexecutors process command and will not be modified. 
The commandExecutor simply takes commands and accesses the run() method. This functionality won't change, as it depends on the
Command abstract class.

When we want to make a new type of command, we extend the Command Class, and it can automatically be ran in CommandExecutor.
Thus, CommandExecutor is closed for modification(as the function that runs commands stays the same), but open to extension.


##Liskov substitution principle (LSP)

Specific examples:
User Class is Superclass. StudentUser and InstructorUser are subclasses of User Class.
The object of SuperClass(User Class), such as "displayName", can be replaceable with the objects of its subclasses and the application still works.
So, the input parameters of StudentUser class (String displayName, String ID, Map<String, String> otherData) is the same as the input parameters of the User class.

Command is a superclass that is extended by many commands. In CommandExecutor, we use the command's run() method.
All of the child classes of Command can be substituted there, and it wouldn't break the program.

One issue that the TA discussed in phase 0 was the Command's help() method. He says that we could not substitute a child
class of command in place of an actual command, and suggested that we make a dictionary that maps each command type to its relevant help string.
However, I(Kevin) don't see how substituting a child class would break the code. The help() method in the parent class isn't abstract
simply because it provides a default value to be given if a child class chooses not to override the help method.

Also, implementing help() this way keeps the help string inside its respective command class, which I think is a good bundling of information.
If the command ever changes, it's easy to find the help string and change it too. However, I acknowledge that I didn't change this functionality
and didn't understand the TA's comment, so this may not follow SOLID principles and I will change it when I understand why it is bad.


##Interface segregation principle (ISP)

Specific example:
We have many small and specific interfaces and each has its own specific function : IAuthorizable, IDBSaveable and IHasPermission.

These interfaces are not very long, and only have methods pertaining to one functionality that we want classes to guarantee.


##Dependency inversion principle (DIP)

Specific examples:

Database can save classes such as UserManager and CourseManager, which are use case classes. This would not be
ok, but it accesses the IDBSaveable and Serializable interfaces, rather than the classes themselves. Thus, the
dependency is "inverted."

This happens elsewhere as well. We access many related objects through their shared interfaces, rather than casting to the objects themselves.