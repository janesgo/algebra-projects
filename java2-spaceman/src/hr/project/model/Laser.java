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
public class Laser extends Character implements Externalizable {

    private static final long serialVersionUID = 1L;

    public Laser() {
    }

    public Laser(int x, int y, int speed) {
        super(new Polygon(0, 0, 22, 0, 22, 5, 0, 5), x, y);
        super.setMovement(new Point2D(speed, 0));
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
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        ArrayList<Double> arrayList = new ArrayList<>(getShape().getPoints());
        out.writeObject(arrayList);
        out.writeDouble(getShape().getLayoutX());
        out.writeDouble(getShape().getLayoutY());
        out.writeObject(new Double[]{getMovement().getX(), getMovement().getY()});
        out.writeBoolean(isInvisible());
    }

}
