package controller.databasegetter;

import constants.FileConstants;
import exceptions.CommandNotAuthorizedException;
import exceptions.NotInDatabaseException;
import outer.database.Database;
import usecase.CourseManager;

import java.io.IOException;
import java.util.Map;

/**
 * A course database. Will be like a gateway class between an actual database(eg. SQL)
 * and this program.
 */
public class CourseDatabaseGetter extends DatabaseGetter<CourseManager> {

    private static CourseDatabaseGetter instance = null;
    // private final Database<CourseManager> db;
    private final Map<String, CourseManager> courseDict;

    private CourseDatabaseGetter() throws IOException, ClassNotFoundException {
        courseDict = Database.loadDB(new FileConstants().COURSE_FILE);
    }

    public static CourseDatabaseGetter getInstance() throws IOException, ClassNotFoundException {
        if (instance == null) {
            instance = new CourseDatabaseGetter();
        }
        return instance;
    }

    /**
     * Gets a course that is saved in the database using id.
     * @param id the id of the course
     * @return
     * @throws NotInDatabaseException
     */
    @Override
    public CourseManager getEntry(String id) throws NotInDatabaseException {
        try {
            return this.courseDict.get(id);
        } catch (Exception e) {
            throw new NotInDatabaseException("Course not found in Database");
        }
    }

    /**
     * Saves a new object to the database
     * @param entry the entry to save
     * @throws CommandNotAuthorizedException
     */
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
        Database.saveToFile(new FileConstants().COURSE_FILE, this.courseDict);
    }

    /**
     * String representation of this cdg will list all courses and their codes.
     * @return
     */
    @Override
    public String toString() {
        StringBuilder retStr = new StringBuilder();
        for (String key : this.courseDict.keySet()) {
            retStr.append(key).append(": ");
            Map<String, Object> dataMap = courseDict.get(key).getData();
            if (dataMap.containsKey("courseName")) {
                retStr.append(dataMap.get("courseName")).append("\n");
            } else {
                retStr.append("[name missing]\n");
            }
        }
        return retStr.toString().strip();
    }
}
