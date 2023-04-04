import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap quang duong di chuyen (km): ");
        double distance = Double.parseDouble(sc.nextLine());
        Buses bus = new Buses();
        Trains train = new Trains();
        Airplanes airplane = new Airplanes();
        bus.setDistance(distance);
        train.setDistance(distance);
        airplane.setDistance(distance);
        System.out.println("Thoi gian di chuyen bang bus = " + bus.getTime() + "h.");
        System.out.println("Thoi gian di chuyen bang tau hoa = " + train.getTime() + "h.");
        System.out.println("Thoi gian di chuyen bang may bay = " + airplane.getTime() + "h.");
    }
   
}