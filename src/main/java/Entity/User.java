package Entity;

public abstract class User {
    private final String displayName; //Username
    private final String ID; //UTemail

    // Student Permission level : 0
    // TA Permissionlevel : 1
    // Instructor Permission level : 2
    int permissionLevel;
    int reviewCount;

    // TODO: maybe password also can be here. If we don't care about security things.

    User(String displayName, String ID) {
        // This can be absolutely changeable
        this.displayName = displayName;
        this.ID = ID;
    }

    /**
     * @return user's profile.
     */
    String getProfile() {
        // TODO: implement getProfile
        return this.getDisplayName() + "\n" + this.getID();
    }

    @Override
    public String toString() {
        // Same as getProfile for now
        return this.getDisplayName() + "\n" + this.getID();
    }

    /**
     * @return user's ID.
     */
    String getID() {
        return this.ID;
    }

    /**
     * @return user's display name.
     */
    String getDisplayName() {
        return this.displayName;
    }
}
