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
    // Current idea is to have hard-coded objects in the database.
    // Normally, this class would take output from the database(Eg. a map) and initialize a
    // CourseManager object using the output, but right now it will basically do nothing.
    public CourseManager getEntry(String id) {
        // TODO implement this, fix course/rating/courseManager
        // I still don't like that this instantiates the entity classes so we should change that.
        // you could literally just put a method inside courseManager that will set the rating for the course
        // as long as it doesn't have to reference the entities.

        // ok implementation is not good, we need to work on this
        // how will we create a couseManager object from a map of data?
        // it holds too many other objects and we need to fix that.
        // For now, we'll just hardcode one into Commands.checkout.
        return null;
    }

    @Override
    public boolean setEntry(CourseManager entry) {
        return false;
    }
}
