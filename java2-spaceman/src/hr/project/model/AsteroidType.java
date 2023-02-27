/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.project.model;

/**
 *
 * @author Goran
 */
public enum AsteroidType {
    BIG(1),
    MEDIUM(2),
    SMALL(3),
    TINY(4);

    private final int size;

    private AsteroidType(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public static AsteroidType from(int size) {
        for (AsteroidType value : values()) {
            if (value.size == size) {
                return value;
            }
        }
        throw new RuntimeException("Wrong type");
    }

    public int getSubtype() {
        if (this == BIG) {
            return 4;
        } else {
            return 2;
        }
    }

}
