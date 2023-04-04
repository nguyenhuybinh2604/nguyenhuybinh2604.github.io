
public class Rectangle extends Shape{
    private double height, width;

    public Rectangle() {
    }

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getPerimeter(){

        return ((this.height+this.width)*2);
    };

    public double getArea(){

        return (this.height*this.width);
    };

    @Override
    public String toString() {
    return "{" +
    " Height='" + getHeight() + "'" +
    " Width='" + getWidth() + "'" +
    ", Area='" + getArea() + "'" +
    ", Perimeter='" + getPerimeter() + "'" +
    "}";
    }

}
