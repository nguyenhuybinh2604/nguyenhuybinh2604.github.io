import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(Math.round((30.444444*100)/100D));

        System.out.println("Nhap phan loai sinh vien (IT/Biz):");
        String loaiSinhVien = sc.nextLine();

        while (loaiSinhVien.equalsIgnoreCase("it") == true
                || loaiSinhVien.equalsIgnoreCase("biz") == true) {

            System.out.println("Nhap ten sinh vien:");
            String name = sc.nextLine();

            System.out.println("Nhap nganh hoc:");
            String major = sc.nextLine();

            switch (loaiSinhVien.toLowerCase()) {
                case "it": {

                    double diemJava = 0;
                    double diemHtml = 0;
                    double diemCss = 0;

                    do {
                        System.out.println("Nhap diem Java (0-10):");
                        while (!sc.hasNextDouble()) {
                            System.out.println("Hay nhap mot so (0-10):");
                            sc.nextLine();
                        }
                        diemJava = Double.parseDouble(sc.nextLine());
                    } while (diemJava < 0 || diemJava > 10);

                    do {
                        System.out.println("Nhap diem Html (0-10):");
                        while (!sc.hasNextDouble()) {
                            System.out.println("Hay nhap mot so (0-10):");
                            sc.nextLine();
                        }
                        diemHtml = Double.parseDouble(sc.nextLine());
                    } while (diemHtml < 0 || diemHtml > 10);

                    do {
                        System.out.println("Nhap diem Css (0-10):");
                        while (!sc.hasNextDouble()) {
                            System.out.println("Hay nhap mot so (0-10):");
                            sc.nextLine();
                        }
                        diemHtml = Double.parseDouble(sc.nextLine());
                    } while (diemCss < 0 || diemCss > 10);

                    SinhVienIT sinhvienIT = new SinhVienIT();

                    sinhvienIT.setName(name);
                    sinhvienIT.setMajor(major);
                    sinhvienIT.setDiemJava(diemJava);
                    sinhvienIT.setDiemHtml(diemHtml);
                    sinhvienIT.setDiemCss(diemCss);

                    System.out.println(sinhvienIT.xuat());
                    break;
                }
                case "biz": {
                    double diemMarketing = 0;
                    double diemSales = 0;

                    do {
                        System.out.println("Nhap diem Marketing (0-10):");
                        while (!sc.hasNextDouble()) {
                            System.out.println("Hay nhap mot so (0-10):");
                            sc.nextLine();
                        }
                        diemMarketing = Double.parseDouble(sc.nextLine());
                    } while (diemMarketing < 0 || diemMarketing > 10);

                    do {
                        System.out.println("Nhap diem Sales (0-10):");
                        while (!sc.hasNextDouble()) {
                            System.out.println("Hay nhap mot so (0-10):");
                            sc.nextLine();
                        }
                        diemSales = Double.parseDouble(sc.nextLine());
                    } while (diemSales < 0 || diemSales > 10);

                    SinhVienBiz sinhvienBiz = new SinhVienBiz();

                    sinhvienBiz.setName(name);
                    sinhvienBiz.setMajor(major);
                    sinhvienBiz.setDiemMarketing(diemMarketing);
                    sinhvienBiz.setDiemSales(diemSales);

                    System.out.println(sinhvienBiz.xuat());
                    break;
                }
            }
            System.out.println("Nhap phan loai sinh vien (IT/Biz):");
            loaiSinhVien = sc.nextLine();
        }
    }
}