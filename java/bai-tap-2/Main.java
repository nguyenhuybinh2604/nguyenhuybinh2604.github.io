import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // System.out.println("Mời bạn nhập tên hàng nhập kho:");
        // String tenHang = input.nextLine();
        // System.out.println("Mời bạn nhập ngày tháng năm sinh (yyyy/MM/dd):");
        // LocalDate ngaySinh = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        // System.out.println("Mời bạn nhập thời gian nhập hàng (yyyy/MM/dd HH:mm:ss):");
        // LocalDateTime tGianNhapHang = LocalDateTime.parse(input.nextLine(),
        //         DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        // System.out.println("Mời bạn nhập thời gian (HH:mm:ss):");
        // LocalTime tGian = LocalTime.parse(input.nextLine(), DateTimeFormatter.ofPattern("HH:mm:ss"));
        // System.out.println("Tên hàng là: " + tenHang);
        // System.out.println("Sinh nhật là: " + ngaySinh);
        // System.out.println("Thời gian nhập hàng là: " + tGianNhapHang);
        // System.out.println("Thời gian là: " + tGian);

        double v2 = 5.6d;
        if (v2 > 3) {
            System.out.println("Kha");
        }
        else if (v2 > 5) {
            System.out.println("Tot");
        }
        else {
            System.out.println("Khac");
        }
        // System.out.println(v1.equals(v2));
    }
}
