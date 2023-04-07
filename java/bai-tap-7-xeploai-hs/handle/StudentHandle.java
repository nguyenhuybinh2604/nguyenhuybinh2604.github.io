package handle;

import java.util.Scanner;

import entity.Student;

public class StudentHandle {
    public Student createStudent(Scanner sc, int i) {

        System.out.println("Nhap ten sv thu " + (i + 1) + ": ");
        String name = sc.nextLine();

        double scoreMath;
        double scorePhysics;
        double scoreChemistry;

        // Kiem soat gia tri n la so 0-10
        do {
            System.out.println("Nhap diem Toan (0-10): ");
            // Yeu cau gia tri la so
            while (!sc.hasNextDouble()) {
                System.out.println("Hay nhap mot so (0-10): ");
                sc.nextLine();
            }
            scoreMath = Double.parseDouble(sc.nextLine());
        } while (scoreMath < 0 || scoreMath > 10);

        // Kiem soat gia tri n la so 0-10
        do {
            System.out.println("Nhap diem Ly (0-10): ");
            // Yeu cau gia tri la so
            while (!sc.hasNextDouble()) {
                System.out.println("Hay nhap mot so (0-10): ");
                sc.nextLine();
            }
            scorePhysics = Double.parseDouble(sc.nextLine());
        } while (scorePhysics < 0 || scorePhysics > 10);

        // Kiem soat gia tri n la so 0-10
        do {
            System.out.println("Nhap diem Hoa (0-10): ");
            // Yeu cau gia tri la so
            while (!sc.hasNextDouble()) {
                System.out.println("Hay nhap mot so (0-10): ");
                sc.nextLine();
            }
            scoreChemistry = Double.parseDouble(sc.nextLine());
        } while (scoreChemistry < 0 || scoreChemistry > 10);

        // Khoi tao ban ghi student theo tham so
        Student student = new Student(name, scoreMath, scorePhysics, scoreChemistry);

        // Tra ve ban ghi
        return student;
    }

    public void displayAllStudents(Student[] students) {
        for (Student st : students) {
            System.out.println(st);
        }
    }

    public void calculatePercentage(Student[] students) {
        double soluongA = 0;
        double soluongB = 0;
        double soluongC = 0;

        int n = students.length;

        // Dem so student theo xep loai
        for (int i = 0; i < n; i++) {
            switch (students[i].xeploai()) {
                case "A": {
                    soluongA++;
                    break;
                }
                case "B": {
                    soluongB++;
                    break;
                }
                case "C": {
                    soluongC++;
                    break;
                }
                default: {
                    break;
                }
            }
        }

        //In ra ket qua tinh ty le
        if (soluongA + soluongB + soluongC > 0) {
            System.out.println("Ty le sv xep loai A =" + (soluongA / (soluongA + soluongB + soluongC) * 100) + "%");
            System.out.println("Ty le sv xep loai B =" + (soluongB / (soluongA + soluongB + soluongC) * 100) + "%");
            System.out.println("Ty le sv xep loai C =" + (soluongC / (soluongA + soluongB + soluongC) * 100) + "%");
        } else {
            System.out.println("Khong du du lieu.");
        }
    }

}