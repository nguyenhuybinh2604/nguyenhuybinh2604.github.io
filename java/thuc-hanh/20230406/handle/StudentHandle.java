package handle;

import java.util.Scanner;

import entity.Student;

public class StudentHandle {
    public Student createStudent(Scanner sc, int i) {
        System.out.println("Nhap ten sv thu " + (i + 1) + ": ");
        String name = sc.nextLine();
        System.out.println("Nhap diem toan: ");
        double scoreMath = Double.parseDouble(sc.nextLine());
        System.out.println("Nhap diem ly: ");
        double scorePhysics = Double.parseDouble(sc.nextLine());
        System.out.println("Nhap diem hoa: ");
        double scoreChemistry = Double.parseDouble(sc.nextLine());
        Student student = new Student(name, scoreMath, scorePhysics, scoreChemistry);
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
            }
        }
        if (soluongA + soluongB + soluongC > 0) {
            System.out.println("Ty le sv xep loai A =" + (soluongA / (soluongA + soluongB + soluongC) * 100) + "%");
            System.out.println("Ty le sv xep loai B =" + (soluongB / (soluongA + soluongB + soluongC) * 100) + "%");
            System.out.println("Ty le sv xep loai C =" + (soluongC / (soluongA + soluongB + soluongC) * 100) + "%");
        } else {
            System.out.println("Khong du du lieu.");
        }
    }

}