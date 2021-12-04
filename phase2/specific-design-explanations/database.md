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

This problem was discussed briefly in SOLID.md.

Currently DatabaseGetters depend on Database. This is bad. I considered inverting the dependency by creating
a database Interface that Database extends. However, that doesn't REALLY invert the dependency.

eg. currently, our Database simply produces a map of ids to objects. It produces the entire database, and that is reflected in its method signature. A real database
wouldn't have such a method.

Suppose we make a Database interface, and have DatabaseGetter depend on that. The dependency would technically be inverted. However, the Database interface depends on the Database class, not the other way around. If we were to use a real database, we would have to change the Database interface AND the class. Thus, DatabaseGetter still indirectly depends on the Database class. 

### Our solution

Our solution is to keep the issue there, and fix it when we move onto actual databases. Most actual databases have similar functionality, so then an interface would make sense. It's just because our database is so fundamentally different from an actual database that we can't change much right now. Then, the only class that depends on it is DatabaseGetter, and thus we'd have to change that as well.

However, the methods inside DatabaseGetter would provide the same functionality. The DatabaseGetter abstract class, which is depended on by Commands and such, would not change. Thus, we would only have to change one class, which is good.

Eg. We switch to SQL. Then,
- the initializer of DatabaseGetters get changed to something else(eg. connecting to the DB)
- the shared methods getEntry, addEntry, saveAll get changed to SQL queries, rather than their current implementations
- Methods will do the same things, and other classes don't need to change

Thus, it's not a very big problem, so we're deciding not to address it for now.
