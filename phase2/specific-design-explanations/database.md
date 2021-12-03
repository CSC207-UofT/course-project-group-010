# Database

Our database classes are set up for the purpose of extension, as we are not using
actual databases or anything like that.

**Database(outer)**

Our Database class is in the outer layer, which is good. It depends on other classes,
not the other way around.

Currently, it uses serialization. SaveToFile/loadFromFile will save/load a map to a file. 
The map saves IDBSaveable objects, mapping its id(from the interface method getID) to the object itself.

**DatabaseGetter(controller)**

DatabaseGetters are singleton classes that simply act as a gateway between
the program and some external database.

Currently, it initializes by getting the entire map of students and courses from
the Database class, and simply checks the map for entries.

**How would we extend this?**

eg. if we wanted to use SQL, we don't need to change the DatabaseGetter interface, we only
have to change the implementation of the methods. The 
getEntry, addEntry, containsKey and saveAll methods would correspond to SQL
queries instead of managing a map of users/courses.

Then, the database class will change to be an SQL database instead of our current
file reader database.

So basically, Database represents the actual database, DatabaseGetters act as a
gateway, that interacts with the database. Our group decided that most databases will work like this,
so we designed the classes to reflect taht structure.

## The biggest design problem that we have(and our justification for keeping it there)

Currently DatabaseGetters depend on Database. This is bad. I considered inverting the dependency by creating
a database Interface that Database extends. However, the moment we change to an actual database, the database interface
would have to change as well(eg. we won't "loadData" with an SQL database, we would connect to the db instead.)

Thus, the interface depends on the class that implements it, and so the dependency is still there.

My
solution is to keep this dependency here until databases are changed in the future, as real databases are vastly different
than our current "database." Then, we'd create a Database interface, and change the implementation of DatabaseGetter's methods.
Since the DatabaseGetter interface won't change, and the rest of the program only uses the shared methods, nothing else needs to be changed.