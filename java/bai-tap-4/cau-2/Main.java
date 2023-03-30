import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập độ dài mảng số nguyên: ");
        int doDai = sc.nextInt();
        int[] arraySo = new int[doDai];
        System.out.println("Sau khi chuẩn hóa: ");
        for (int i=0; i < arraySo.length; i++) {
            System.out.println("Nhập số nguyên thứ " + (i+1));
            arraySo[i] = sc.nextInt();
        }
        System.out.println("Mảng vừa nhập là: ");
        for (int so: arraySo){
            System.out.print(so + " "); 
        }
        System.out.println();
        System.out.println("Mảng sau điều chỉnh là: ");
        for (int i=0; i < arraySo.length; i++) {
            if (arraySo[i] % 2 == 0) {
                arraySo[i]++;
            }
            System.out.print(arraySo[i] + " ");
        }
        System.out.println();
    }
}