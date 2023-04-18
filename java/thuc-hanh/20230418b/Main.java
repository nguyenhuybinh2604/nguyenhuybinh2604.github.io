import java.util.Scanner;

import entity.Rectangle;
import entity.Square;
import handle.ShapeHandle;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ShapeHandle shapeHandle = new ShapeHandle();

        Rectangle rectangle = shapeHandle.addRectangle(sc);
        rectangle.display();
        System.out.println(rectangle.calculateArea()); 

        Square square = shapeHandle.addSquare(sc);
        square.display();
        System.out.println(square.calculateArea());    
    }
}
