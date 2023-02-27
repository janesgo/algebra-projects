/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.project.model;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 *
 * @author Goran
 */
public abstract class Character {

    private Polygon shape;
    private Point2D movement;
    private boolean invisible;

    public Character() {
    }

    public Character(Polygon shape, int x, int y) {
        this.shape = shape;
        this.shape.setLayoutX(x);
        this.shape.setLayoutY(y);
        this.movement = Point2D.ZERO;
    }

    public Polygon getShape() {
        return shape;
    }

    public void setShape(Polygon shape) {
        this.shape = shape;
    }

    public Point2D getMovement() {
        return movement;
    }

    public void setMovement(Point2D point) {
        this.movement = point;
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisibility(boolean invisible) {
        this.invisible = invisible;
    }

    public boolean collide(Character character) {
        return Shape.intersect(this.getShape(), character.getShape())
                .getBoundsInLocal().getWidth() != -1;
    }

    public void move() {
        this.shape.setLayoutX(this.shape.getLayoutX() + this.movement.getX());
        this.shape.setLayoutY(this.shape.getLayoutY() + this.movement.getY());
    }
}
