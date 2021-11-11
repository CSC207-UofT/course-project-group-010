# Solid Design Principles

How well does your design adhere to the SOLID design principles?
Give us specific examples of how your design adheres to the SOLID principles.

##Single responsibility principle (SRP)

Each class of our system only has responsibility for a single part of system functionality.
Specific example, Under entity, rating class under entity is only responsible for course rating and review class is only responsible for course review. 

##Open/closed principle (OCP)

Our system entities are open for extension but closed for modification. 
Specific example:

-The Rating class and Course page class get and store all the rating students provided. 
When we want to add new functionality as overall rating per course and relative rating per course, we just need to add the calculation method to our course page class. 

-The Commandexecutors process command and will not be modified. 
When we want to make a new type of command, we could extend the command class 


##Liskov substitution principle (LSP)

Specific example:
User Class is Superclass. StudentUser and InstructorUser are subclasses of User Class.
The object of SuperClass(User Class), such as "displayName", can be replaceable with the objects of its subclasses and the application still works.
So, the input parameters of StudentUser class (String displayName, String ID, Map<String, String> otherData) is the same as the input parameters of the User class.  


##Interface segregation principle (ISP)

Specific example:
We have many small and specific interfaces and each has its own specific methods : IAuthorizable, IDBSaverable and IHasPermission.


##Dependency inversion principle (DIP)
