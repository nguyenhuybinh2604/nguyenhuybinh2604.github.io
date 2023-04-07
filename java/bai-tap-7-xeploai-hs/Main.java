import java.util.Scanner;

import entity.Student;
import handle.StudentHandle;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;

        // Kiem soat gia tri n la so nguyen duong
        do {
            System.out.println("Nhap so luong sinh vien (>0): ");
            // Yeu cau gia tri so nguyen
            while (!sc.hasNextInt()) {
                System.out.println("Hay nhap mot so nguyen >0:");
                sc.nextLine();
            }
            n = Integer.parseInt(sc.nextLine());
        } while (n <= 0);

        // Khai bao objects
        StudentHandle studentHandle = new StudentHandle();
        Student[] students = new Student[n];

        // Tao tung ban ghi student
        for (int i = 0; i < n; i++) {
            students[i] = studentHandle.createStudent(sc, i);
        }

        // goi method list toan bo students
        studentHandle.displayAllStudents(students);

        // goi method tinh ty le % xep loai students
        studentHandle.calculatePercentage(students);
    }
}
