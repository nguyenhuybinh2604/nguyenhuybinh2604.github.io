import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập họ và tên (hoa/thường): ");
        String hoTen = sc.nextLine();
        String[] arrayHoTen = hoTen.split(" ");
        System.out.println("Sau khi chuẩn hóa: ");
        for (int i=0; i < arrayHoTen.length; i++) {
            arrayHoTen[i] = arrayHoTen[i].toLowerCase();
            String phanDau = arrayHoTen[i].substring(0,1).toUpperCase();
            String phanSau = arrayHoTen[i].substring(1);
            arrayHoTen[i] = phanDau + phanSau;
            System.out.print(arrayHoTen[i] + " ");
        }
        System.out.println();
    }
}