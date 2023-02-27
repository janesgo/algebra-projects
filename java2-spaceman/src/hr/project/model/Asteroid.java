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
public class Asteroid extends Character implements Externalizable {

    private static final long serialVersionUID = 1L;

    private AsteroidType type;

    public Asteroid() {
    }

    public Asteroid(Polygon shape, AsteroidType type, int x, int y, int speed) {
        super(shape, x, y);
        super.setMovement(new Point2D(-speed, 0));
        this.type = type;
    }

    public AsteroidType getType() {
        return type;
    }

    public void setType(AsteroidType type) {
        this.type = type;
    }

    public int getPoints() {
        return type.getSize() * 10;
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
        type = (AsteroidType) in.readObject();
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
