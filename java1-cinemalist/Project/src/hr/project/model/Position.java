package hr.project.model;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Goran
 */
public enum Position {

    Actor(1),
    Director(2);

    @XmlJavaTypeAdapter(PositionAdapter.class)
    private final int id;

    private Position(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Position from(int id) {
        for (Position value : values()) {
            if (value.id == id) {
                return value;
            }
        }
        throw new RuntimeException("Position does not exists");
    }

    public static int from(Position position) {
        return position.getId();
    }
}
