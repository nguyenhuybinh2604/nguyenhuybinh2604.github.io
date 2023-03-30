import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       
        //
        // String flag = "Y";
        // int tong = 0;
        // while (flag.equalsIgnoreCase("Y")) {
        //     System.out.println("Hay nhap mot so nguyen: ");
        //     int n = Integer.parseInt(sc.nextLine());
        //     tong += n;
        //     System.out.println("Do you want to continue (Y/N): ");
        //     flag = (String) sc.nextLine();
        // }
        // System.out.println("Tong cac so vua nhap: " + tong);

        // Vong lap do - while
        // int i = 1;

        // int tong = 0;
        // int tich = 1;
        // int tongSoChan = 0;
        // System.out.println("Nhap so n:");
        // int n = sc.nextInt();
        // do {
        // tong = tong + i;
        // if (i != 0) {
        // tich = tich * i;
        // }
        // ;
        // if (i % 2 == 0) {
        // tongSoChan = tongSoChan + i;
        // }
        // ;
        // i++;
        // } while (i <= n);
        // System.out.println("Tong cua " + n + " so dau tien la: " + tong);
        // System.out.println("Tich cua " + n + " so dau tien la: " + tich);
        // System.out.println("Tong cac so chan cua " + n + " so dau tien la: " +
        // tongSoChan);

        // Vong lap while
        // int i = 1;

        // int tong = 0;
        // int tich = 1;
        // int tongSoChan = 0;
        // System.out.println("Nhap so n:");
        // int n = sc.nextInt();
        // while (i <= n) {
        // tong = tong + i;
        // if (i != 0) {
        // tich = tich * i;
        // }
        // ;
        // if (i % 2 == 0) {
        // tongSoChan = tongSoChan + i;
        // }
        // ;
        // i++;
        // }
        // System.out.println("Tong cua " + n + " so dau tien la: " + tong);
        // System.out.println("Tich cua " + n + " so dau tien la: " + tich);
        // System.out.println("Tong cac so chan cua " + n + " so dau tien la: " +
        // tongSoChan);

        // Vong lap for
        // int tong = 0;
        // int tich = 1;
        // int tongSoChan = 0;
        // System.out.println("Nhap so n:");
        // int n = sc.nextInt();
        // for (int i = 0; i <= n; i++) {
        // tong = tong + i;
        // if (i != 0) {
        // tich = tich * i;
        // }
        // ;
        // if (i % 2 == 0) {
        // tongSoChan = tongSoChan + i;
        // }
        // ;
        // }
        // System.out.println("Tong cua " + n + " so dau tien la: " + tong);
        // System.out.println("Tich cua " + n + " so dau tien la: " + tich);
        // System.out.println("Tong cac so chan cua " + n + " so dau tien la: " +
        // tongSoChan);

        // // Bai tap 05 switch calculator

        // System.out.println("Nhap so thu 1:");
        // double a = Double.parseDouble(sc.nextLine());
        // System.out.println("Nhap so thu 2:");
        // double b = Double.parseDouble(sc.nextLine());
        // System.out.println("Nhap phep tinh ( + , - , * , / ):");
        // String phepTinh = sc.nextLine();
        // switch (phepTinh) {
        // case "+":
        // System.out.println("Ket qua: " + a + " + " + b + " = " + (a + b));
        // break;
        // case "-":
        // System.out.println("Ket qua: " + a + " - " + b + " = " + (a - b));
        // break;
        // case "*":
        // System.out.println("Ket qua: " + a + " x " + b + " = " + (a * b));
        // break;
        // case "/":
        // if (b != 0) {
        // System.out.println("Ket qua: " + a + " / " + b + " = " + (a / b));
        // } else {
        // System.out.println("Loi chia cho 0.");
        // };
        // break;
        // default:
        // System.out.println("Khong xac dinh.");
        // }
        // ;

        // // Bai tap 04 switch

        // System.out.println("Nhap ten xe oto:");
        // String n = sc.nextLine();
        // switch (n) {
        // case "i10":
        // System.out.println("Hyundai");
        // break;
        // case "i8":
        // System.out.println("BMW");
        // break;
        // case "Camry":
        // System.out.println("Toyota");
        // break;
        // case "E200":
        // System.out.println("Mercedes");
        // break;
        // default:
        // System.out.println("Khong xac dinh");
        // }
        // ;

        // Bai tap 03

        // System.out.println("Nhap so dien su dung trong thang:");
        // double a = sc.nextDouble();
        // if (a < 0) {
        // System.out.println("Ban can nhap so khong am.");
        // } else if (a >= 0 && a <= 50) {
        // System.out.println("So tien: " + 1000 * a);
        // } else {
        // double sovuot = a - 50;
        // System.out.println("So tien: " + (1000*50+1200*sovuot));
        // }
        // ;

        // Bai tap 02

        // PT bac 2 ax^2+bx+c=0
        // System.out.println("Nhap gia tri a:");
        // double a = sc.nextDouble();
        // System.out.println("Nhap gia tri b:");
        // double b = sc.nextDouble();
        // System.out.println("Nhap gia tri c:");
        // double c = sc.nextDouble();
        // if (a == 0) {
        // if (b == 0 && c == 0) {
        // System.out.println("Vo so nghiem.");
        // } else if (b == 0 && c != 0) {
        // System.out.println("Vo nghiem.");
        // } else {
        // System.out.println("Ket qua: " + (-c/b));
        // }
        // }
        // else {
        // double delta = b*b-4*a*c;
        // if (delta<0) {
        // System.out.println("Vo nghiem.");
        // }
        // else if (delta==0) {
        // System.out.println("Ket qua: " + (-b/a));
        // }
        // else {
        // System.out.println("Nghiem thu 01: " + (-b-Math.sqrt(delta))/(2*a));
        // System.out.println("Nghiem thu 02: " + (-b+Math.sqrt(delta))/(2*a));
        // }
        // }
        // ;

        // Bai tap 01
        // System.out.println("Nhap gia tri a:");
        // int a = sc.nextInt();
        // System.out.println("Nhap gia tri b:");
        // int b = sc.nextInt();
        // if (a==0 && b==0) {
        // System.out.println("Vo so nghiem.");
        // }
        // else if (a==0 && b!=0) {
        // System.out.println("Vo nghiem.");
        // }
        // else {System.out.println("Ket qua: " + (-(float)b/(float)a));};
    }
}