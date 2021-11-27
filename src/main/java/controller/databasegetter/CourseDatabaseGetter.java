package controller.databasegetter;

import constants.FileConstants;
import exceptions.CommandNotAuthorizedException;
import exceptions.NotInDatabaseException;
import outer.database.Database;
import usecase.CourseManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A course database. Will be like a gateway class between an actual database(eg. SQL)
 * and this program.
 */
public class CourseDatabaseGetter extends DatabaseGetter<CourseManager> {

    private static CourseDatabaseGetter instance = null;
    private final Database<CourseManager> db;
    private final Map<String, CourseManager> courseDict;

    private CourseDatabaseGetter() throws IOException, ClassNotFoundException {
        Map<String, CourseManager> courseDict1;
        this.db = new Database<>();
        courseDict1 = this.db.loadDatabase(new FileConstants().COURSE_FILE);
        if (courseDict1 == null) {
            courseDict1 = new HashMap<>();
        }
        this.courseDict = courseDict1;
    }

    public static CourseDatabaseGetter getInstance() throws IOException, ClassNotFoundException {
        if (instance == null) {
            instance = new CourseDatabaseGetter();
        }
        return instance;
    }

    @Override
    // Current idea is to have hard-coded objects in the database.
    // Normally, this class would take output from the database(Eg. a map) and initialize a
    // CourseManager object using the output, but right now it will basically do nothing.
    public CourseManager getEntry(String id) throws NotInDatabaseException {
        // TODO implement this, fix course/rating/courseManager
        // I still don't like that this instantiates the entity classes so we should change that.
        // you could literally just put a method inside courseManager that will set the rating for the course
        // as long as it doesn't have to reference the entities.

        // ok implementation is not good, we need to work on this
        // how will we create a couseManager object from a map of data?
        // it holds too many other objects and we need to fix that.
        // For now, we'll just hardcode one into Commands.checkout.
        try {
            return this.courseDict.get(id);
        } catch (Exception e) {
            throw new NotInDatabaseException("Course not found in Database");
        }
    }

    @Override
    public void setEntry(CourseManager entry) {
        this.courseDict.put(entry.getID(), entry);
    }

    @Override
    public void addEntry(CourseManager entry) throws CommandNotAuthorizedException {
        if (!this.courseDict.containsKey(entry.getID())) {
            this.courseDict.put(entry.getID(), entry);
        } else {
            throw new CommandNotAuthorizedException("course with inputted code already in database");
        }
    }

    @Override
    public boolean containsKey(String key) {
        return this.courseDict.containsKey(key);
    }

    @Override
    public void saveAll() throws IOException {
        this.db.saveToFile(new FileConstants().COURSE_FILE, this.courseDict);
    }

    /**
     * String representation of this cdg will list all courses and their codes.
     * @return
     */
    @Override
    public String toString() {
        StringBuilder retStr = new StringBuilder();
        for (String key : this.courseDict.keySet()) {
            // TODO make it easier to access course's basic info from courseManager
            // TODO this relies on the implementation of getData(), so technically it relies on courseManager and that's bad.
            retStr.append(key + ": ");
            Map<String, Object> dataMap = courseDict.get(key).getData();
            if (dataMap.containsKey("courseName")) {
                retStr.append(dataMap.get("courseName") + "\n");
            } else {
                retStr.append("[name missing]\n");
            }
        }
        return retStr.toString().strip();
    }
}
