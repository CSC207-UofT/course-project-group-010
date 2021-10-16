package Controller.Database;

import UseCase.CourseManager.CourseManager;
import UseCase.CoursePage.CoursePage;

import java.util.HashMap;
import java.util.Map;

/**
 * A course database. Will be more like a gateway class between an actual database(eg. SQL)
 * and this program.
 */
public class CourseDatabase extends Database<CourseManager>{
    @Override
    public Map<String, String> getEntry(String id) {
        // ok implementation is not good, we need to work on this
        // how will we create a couseManager object from a map of data?
        // it holds too many other objects and we need to fix that.
        // For now, we'll just hardcode one into Commands.checkout.
        // TODO FIX THIS
        return null;
    }

    @Override
    public boolean setEntry(CourseManager entry) {
        return false;
    }
}
