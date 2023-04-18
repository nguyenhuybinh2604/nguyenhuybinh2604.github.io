package entity;

import service.Polygon;

public class Square implements Polygon {
    public double squareSide;

    public Square() {
    }

    public Square(double squareSide) {
        this.squareSide = squareSide;
    }

    public double getSquareSide() {
        return this.squareSide;
    }

    public void setSquareSide(double squareSide) {
        this.squareSide = squareSide;
    }

    public Square squareSide(double squareSide) {
        setSquareSide(squareSide);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " squareSide='" + getSquareSide() + "'" +
                "}";
    }

    @Override
    public void display() {
        System.out.println(toString());
    }

    @Override
    public double calculateArea() {
        System.out.println("Dien tich hinh vuong:");
        return squareSide * squareSide;
    }

}
