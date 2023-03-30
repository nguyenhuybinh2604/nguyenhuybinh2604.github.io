import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int soMin = 0;
        int soMax = 0;
        System.out.println("Nhập số lượng mảng: ");
        int soluongMang = sc.nextInt();
        int[][] arrayMinMax = new int[soluongMang][2];
        // Arrays.fill(arrayMinMax, 0);
        for (int i = 0; i < soluongMang; i++) {

            System.out.println("Nhập độ dài mảng thứ " + (i + 1));
            int doDai = sc.nextInt();
            for (int j = 0; j < doDai; j++) {
                System.out.println("Nhập phần tử thứ " + (j + 1) + " của mảng " + (i + 1) + ": ");
                int soNhap = sc.nextInt();
                if (j == 0) {
                    arrayMinMax[i][0] = soNhap;
                    arrayMinMax[i][1] = soNhap;
                }
                if (i == 0 && j == 0) {
                    soMin = soNhap;
                    soMax = soNhap;
                }
                if (soNhap < arrayMinMax[i][0]) {
                    arrayMinMax[i][0] = soNhap;
                }
                if (soNhap > arrayMinMax[i][1]) {
                    arrayMinMax[i][1] = soNhap;
                }
                if (soNhap < soMin) {
                    soMin = soNhap;
                }
                if (soNhap > soMax) {
                    soMax = soNhap;
                }
            }
            System.out.println("Số nhỏ nhất của tất cả các mảng là: " + soMin);
            System.out.println("Số lớn nhất của tất cả các mảng là: " + soMax);
        }
    }
}