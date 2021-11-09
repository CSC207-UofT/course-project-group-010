#Design Patterns

##Factory Method

- ## _serializable_

We implement a “serializable” interface in the classes of “Database” and “DatabaseGetter”. 

Using serialization and deserialization helps us to get the data from the database and save the new information into our database. 

- Database Class 
  - The subclass “Database” extends the Serializable interface, and it has detailed methods such as “saveToFile” and “loadDatabase”.

- DatabaseGetter Class
  - The subclass “DatabaseGetter” extends the Serializable interface. Then, its subclasses (“ UserDatabaseGetter” and “CoureseDatabaseGetter”)  extend “DatabaseGetter” class with detailed methods such as “getentry” and “set entry”.
  - In this way, we could utilize the polymorphic on our coding to reduce the duplication and decrease the complication of coding.



##Command Design Pattern

We follow Command Design Pattern to create our Command functions under the "Commands" folder. 

We create an abstract base class “ command” and some derived classes (such as “RateCommand”, “PrintCommand” and “HelpCommand”).

We have "CommandRequest" class to instantiate a Command object for each execution request and " CommandExecutor" class to hold the information for executing the command. 




##Builder Design Pattern

We follow Builder Design Pattern to create our coursepage class to simplify the complex creation process of creating the course page. Thus, each course page has the same construction process (such as add year, add instructor, add rating) but it can create different representations (such as one-course page for MAT 137 and one-course page for CSC 207). 







