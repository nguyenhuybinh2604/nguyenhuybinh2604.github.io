package handle;

import java.util.Scanner;

import entity.Rectangle;
import entity.Square;

public class ShapeHandle {
    public Rectangle addRectangle(Scanner sc) {
        System.out.println("Nhap chieu dai:");
        double longSide = Double.parseDouble(sc.nextLine());
        System.out.println("Nhap chieu cao:");
        double shortSide = Double.parseDouble(sc.nextLine());
        return new Rectangle(longSide, shortSide);
    }

    public Square addSquare(Scanner sc) {
        System.out.println("Nhap canh hinh vuong:");
        double squareSide = Double.parseDouble(sc.nextLine());
        return new Square(squareSide);
    }
}
