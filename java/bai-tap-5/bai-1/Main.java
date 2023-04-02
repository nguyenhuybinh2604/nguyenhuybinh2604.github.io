import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap chieu dai hinh chu nhat: ");
        double sideA = Double.parseDouble(sc.nextLine());

        System.out.print("Nhap chieu rong hinh chu nhat: ");
        double sideB = Double.parseDouble(sc.nextLine());

        System.out.print("Nhap do dai canh hinh vuong: ");
        double side = Double.parseDouble(sc.nextLine());

        if (sideA >= 0 && sideB >= 0 && side >= 0) {

            Rectangle rectangle = new Rectangle();
            rectangle.setSideA(sideA);
            rectangle.setSideB(sideB);

            Square square = new Square();
            square.setSide(side);

            System.out.println("Thong tin hinh chu nhat: ");
            System.out.print(rectangle.toString());
            System.out.print("Chu vi= " + rectangle.perimeter(rectangle.getSideA(), rectangle.getSideB()) + ", ");
            System.out.println("Dien tich= " + rectangle.area(rectangle.getSideA(), rectangle.getSideB()));

            System.out.println("Thong tin hinh vuong: ");
            System.out.print(square.toString());
            System.out.print("Chu vi= " + square.perimeter(square.getSide(), square.getSide()) + ", ");
            System.out.println("Dien tich= " + square.area(square.getSide(), square.getSide()));
        } else {
            System.out.println("Phai nhap so khong am.");
        }
    }
}