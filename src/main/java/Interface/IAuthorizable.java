package Interface;

public interface IAuthorizable {
    /**
     * Return the required Authorization level for this specific action.
     *
     * @return The current required Authorization level for this specific action.
     */
    int getAuthRequirement();
}

