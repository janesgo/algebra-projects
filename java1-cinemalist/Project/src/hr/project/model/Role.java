package hr.project.model;

/**
 *
 * @author Goran
 */
public enum Role {

    Administrator(1),
    User(2);

    private final int id;

    private Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Role from(int id) {
        for (Role value : values()) {
            if (value.id == id) {
                return value;
            }
        }
        throw new RuntimeException("Role does not exists");
    }
}
