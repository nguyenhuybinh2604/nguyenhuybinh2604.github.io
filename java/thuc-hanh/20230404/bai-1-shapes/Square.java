public class Square extends Shape {
    private double side;

    public Square() {
    }

    public Square(double side) {
        this.side = side;
    }

    public double getSide() {
        return this.side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double getPerimeter() {
        return ((this.side + this.side) * 2);
    };

    @Override
    public double getArea() {
        return (this.side * this.side);
    };

    @Override
    public String toString() {
    return "{" +
    " Height='" + getSide() + "'" +
    ", Area='" + getArea() + "'" +
    ", Perimeter='" + getPerimeter() + "'" +
    "}";
    }

}
