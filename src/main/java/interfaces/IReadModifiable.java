package interfaces;

/**
 * Anything that is both Authorizable and Gettable, such as a CourseManager, that will be displayed on the screen,
 * But needs authorization to do so.
 */
public interface IReadModifiable extends IAuthorizable, IGettable {
}
