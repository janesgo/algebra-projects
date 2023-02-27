/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.project.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Goran
 */
public class Astronaut extends Character implements Externalizable {

    private static final long serialVersionUID = 1L;

    private static final int LASER_SPEED = 1;
    private static final Polygon SHAPE = new Polygon(
            79, 49, 74, 44, 70, 44, 67, 39, 62, 39, 53, 44, 49, 41, 49, 25,
            39, 13, 20, 13, 20, 0, 16, 0, 16, 18, 8, 18, 9, 44, 0, 44, 0, 75,
            14, 75, 14, 100, 49, 100, 49, 93, 45, 89, 44, 70, 55, 72, 60, 72,
            67, 65, 70, 60, 75, 60, 75, 55, 79, 55
    );

    private AstronautType type;

    public Astronaut() {
    }

    public Astronaut(int x, int y, AstronautType type) {
        super(SHAPE, x, y);
        this.type = type;
    }

    public AstronautType getType() {
        return type;
    }

    public void setType(AstronautType type) {
        this.type = type;
    }

    @Override
    public void move() {
        super.move();
    }

    public void moveLeft() {
        if (this.getShape().getLayoutX() < 5) {
            stop();
        } else {
            super.setMovement(new Point2D(-5, 0));
        }

    }

    public void moveRight() {
        if (this.getShape().getLayoutX() > 725) {
            stop();
        } else {
            super.setMovement(new Point2D(5, 0));
        }
    }

    public void moveUp() {
        if (this.getShape().getLayoutY() < -5) {
            stop();
        } else {
            super.setMovement(new Point2D(0, -5));
        }
    }

    public void moveDown() {
        if (this.getShape().getLayoutY() > 500) {
            stop();
        } else {
            super.setMovement(new Point2D(0, 5));
        }
    }

    public void stop() {
        super.setMovement(Point2D.ZERO);
    }

    public Laser fire(int acceleration) throws Exception {
        Laser laser = new Laser(
                (int) this.getShape().getLayoutX() + (int) this.getShape().getLayoutBounds().getWidth(),
                (int) this.getShape().getLayoutY() + (int) (this.getShape().getLayoutBounds().getHeight() / 2),
                LASER_SPEED);
        laser.setMovement(laser.getMovement().normalize().multiply(acceleration));
        return laser;
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        ArrayList<Double> dots = (ArrayList<Double>) in.readObject();
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(dots);
        setShape(polygon);
        getShape().setLayoutX(in.readDouble());
        getShape().setLayoutY(in.readDouble());
        Double[] coordinates = (Double[]) in.readObject();
        setMovement(new Point2D(coordinates[0], coordinates[1]));
        setInvisibility(in.readBoolean());
        type = (AstronautType) in.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        ArrayList<Double> arrayList = new ArrayList<>(getShape().getPoints());
        out.writeObject(arrayList);
        out.writeDouble(getShape().getLayoutX());
        out.writeDouble(getShape().getLayoutY());
        out.writeObject(new Double[]{getMovement().getX(), getMovement().getY()});
        out.writeBoolean(isInvisible());
        out.writeObject(type);
    }

}
