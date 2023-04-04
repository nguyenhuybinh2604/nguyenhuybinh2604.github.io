import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap chieu dai: ");
        double width = Double.parseDouble(sc.nextLine());
        System.out.println("Nhap chieu cao: ");
        double height = Double.parseDouble(sc.nextLine());
        System.out.println("Nhap canh hinh vuong: ");
        double side = Double.parseDouble(sc.nextLine());
        Rectangle rectangle = new Rectangle(height,width);
        System.out.println(rectangle.toString());
        Square square = new Square(side);
        System.out.println(square.toString());
    }
}
