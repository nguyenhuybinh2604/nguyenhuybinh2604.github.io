package entity;

import service.Polygon;

public class Rectangle implements Polygon {
    private double longSide;
    private double shortSide;

    public Rectangle() {
    }

    public Rectangle(double longSide, double shortSide) {
        this.longSide = longSide;
        this.shortSide = shortSide;
    }

    public double getLongSide() {
        return this.longSide;
    }

    public void setLongSide(double longSide) {
        this.longSide = longSide;
    }

    public double getShortSide() {
        return this.shortSide;
    }

    public void setShortSide(double shortSide) {
        this.shortSide = shortSide;
    }

    public Rectangle longSide(double longSide) {
        setLongSide(longSide);
        return this;
    }

    public Rectangle shortSide(double shortSide) {
        setShortSide(shortSide);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " longSide='" + getLongSide() + "'" +
                ", shortSide='" + getShortSide() + "'" +
                "}";
    }

    @Override
    public void display() {
        System.out.println(toString());
    }

    @Override
    public double calculateArea() {
        System.out.println("Dien tich hinh chu nhat:");
        return shortSide * longSide;
    }

}
