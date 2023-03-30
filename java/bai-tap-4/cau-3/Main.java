import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập số lượng mảng: ");
        int soluongMang = sc.nextInt();
        int[][] arrayMinMax = new Mainint[soluongMang][2];
        Arrays.fill(arrayMinMax, 0);
        for (int i = 0; i < soluongMang; i++) {
            int soMax;
            int soMin;
            System.out.println("Nhập độ dài mảng thứ " + (i + 1));
            int doDai = sc.nextInt();
            for (j=0; j<doDai; j++) {
                System.out.println("Nhập phần tử thứ " + (j+1) + " của mảng " + (i+1) + ": ");
                
            }
            
        }

    }
}