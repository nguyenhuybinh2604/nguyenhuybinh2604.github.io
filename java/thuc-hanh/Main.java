import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public void vdArray2Chieu_chiaHetCho3() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so dong cua matrix: ");
        int soDong = sc.nextInt();
        System.out.println("Nhap so cot cua matrix: ");
        int soCot = sc.nextInt();
        int[][] matrix = new int[soDong][soCot];
        int soTong = 0;
        for (int i = 0; i < soDong; i++) {
            for (int j = 0; j < soCot; j++) {
                System.out.print("Nhap phan tu thu " + (i + 1) + "-" + (j + 1) + ": ");
                matrix[i][j] = sc.nextInt();
                if (matrix[i][j] % 3 == 0) {
                    soTong += matrix[i][j];
                }
            }
        }
        System.out.println("Tong cac phan tu chia het cho 3 = " + soTong);
    }

    public void vdArray2Chieu_tinhTong2Matrix() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so dong cua matrix A: ");
        int soDongA = sc.nextInt();
        System.out.println("Nhap so cot cua matrix A: ");
        int soCotA = sc.nextInt();
        System.out.println("Nhap so dong cua matrix B: ");
        int soDongB = sc.nextInt();
        System.out.println("Nhap so cot cua matrix B: ");
        int soCotB = sc.nextInt();
        if (soDongA == soDongB && soCotA == soCotB) {
            int[][] matrixA = new int[soDongA][soCotA];
            int[][] matrixB = new int[soDongA][soCotA];
            int[][] matrixTong = new int[soDongA][soCotA];
            for (int i = 0; i < soDongA; i++) {
                for (int j = 0; j < soCotA; j++) {
                    System.out.print("Matran A - Nhap phan tu thu " + (i + 1) + "-" + (j + 1) + ": ");
                    matrixA[i][j] = sc.nextInt();
                }
            }
            for (int i = 0; i < soDongA; i++) {
                for (int j = 0; j < soCotA; j++) {
                    System.out.print("Matran B - Nhap phan tu thu " + (i + 1) + "-" + (j + 1) + ": ");
                    matrixB[i][j] = sc.nextInt();
                    matrixTong[i][j] = matrixA[i][j] + matrixB[i][j];
                }
            }
            System.out.println("Ma tran tong la: ");
            for (int i = 0; i < soDongA; i++) {
                for (int j = 0; j < soCotA; j++) {
                    System.out.print(matrixTong[i][j] + "\t");
                }
                System.out.println();
            }
        } else {
            System.out.println("02 matrix khong bang nhau.");
        }
    }

    public void vdInputParse() {
        Scanner input = new Scanner(System.in);
        System.out.println("Mời bạn nhập tên hàng nhập kho:");
        String tenHang = input.nextLine();
        System.out.println("Mời bạn nhập ngày tháng năm sinh (yyyy/MM/dd):");
        LocalDate ngaySinh = LocalDate.parse(input.nextLine(),
                DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println("Mời bạn nhập thời gian nhập hàng (yyyy/MM/dd HH:mm:ss):");
        LocalDateTime tGianNhapHang = LocalDateTime.parse(input.nextLine(),
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        System.out.println("Mời bạn nhập thời gian (HH:mm:ss):");
        LocalTime tGian = LocalTime.parse(input.nextLine(),
                DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("Tên hàng là: " + tenHang);
        System.out.println("Sinh nhật là: " + ngaySinh);
        System.out.println("Thời gian nhập hàng là: " + tGianNhapHang);
        System.out.println("Thời gian là: " + tGian);

        double v2 = 5.6d;
        if (v2 > 3) {
            System.out.println("Kha");
        } else if (v2 > 5) {
            System.out.println("Tot");
        } else {
            System.out.println("Khac");
        }
    }

    public static void main(String[] args) {
        // Main thuchanh = new Main();
        // thuchanh.vdArray2Chieu_tinhTong2Matrix();
        // <tenlass> <tenDoiTuong> = new <tenClass- Ham khoi tao Khong / hoac Co tham
        // so>();
        // Laptop alienware = new Laptop();
        // alienware.setModel("Legion 5 pro");
        // alienware.setPrice(1100);
        // System.out.println(alienware.getPrice());
        // Product sanpham1 = new Product("id1", "laptop", 1200);
        // System.out.println("Ten san pham la: " + sanpham1.getName());
        // System.out.println("Thue tren san pham la: " + sanpham1.thueNK());
        // System.out.println(sanpham1.toString());
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so nhan vien: ");
        int n = Integer.parseInt(sc.nextLine());
        Employee[] array = new Employee[n];
        for (int i=0; i<n; i++) {
            System.out.print("Nhap id cua nhan vien thu " + (i+1) + ": ");
            String id = sc.nextLine();
            System.out.print("Nhap ten cua nhan vien thu " + (i+1) + ": ");
            String name = sc.nextLine();
            System.out.print("Nhap dia chi cua nhan vien thu " + (i+1) + ": ");
            String address = sc.nextLine();
            System.out.print("Nhap tuoi cua nhan vien thu " + (i+1) + ": ");
            int age = Integer.parseInt(sc.nextLine());
            System.out.print("Nhap diem kinh nghiem cua nhan vien thu " + (i+1) + ": ");
            double experience = Double.parseDouble(sc.nextLine());
            System.out.print("Nhap noi lam viec cua nhan vien thu " + (i+1) + ": ");
            String placeWork = sc.nextLine();
            Employee employee = new Employee(experience, placeWork);
            array[i] = employee;
        }
        System.out.println("Thong tin toan bo nhan vien: ");
        for (int i=0; i<n; i++) {
            System.out.println(array[i].toString());
        }
        // Employee employee1 = new Employee();    
        // employee1.setName("Lionel Messi");
        // employee1.setAddress("Buenos Aires");
        // employee1.setAge(20);
        // employee1.setExperience(99);
        // employee1.setPlaceWork("Paris");
        // System.out.println(employee1.toString());

    }
}