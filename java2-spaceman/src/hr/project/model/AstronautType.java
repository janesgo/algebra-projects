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
public enum AstronautType {
    GREEN(1),
    BLUE(2);

    private final int type;

    private AstronautType(int value) {
        this.type = value;
    }

    public int getValue() {
        return type;
    }
    
        public static AstronautType from(int type) {
        for (AstronautType value : values()) {
            if (value.type == type) {
                return value;
            }
        }
        throw new RuntimeException("Wrong type");
    }
}
