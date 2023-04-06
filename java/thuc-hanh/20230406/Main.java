import java.util.Scanner;

import entity.Student;
import handle.StudentHandle;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Nhap so luong sinh vien: ");
        int n = Integer.parseInt(sc.nextLine());

        StudentHandle studentHandle = new StudentHandle();

        Student[] students = new Student[n];

        for (int i = 0; i < n; i++) {
            students[i] = studentHandle.createStudent(sc, i);
        }

        studentHandle.displayAllStudents(students);
        studentHandle.calculatePercentage(students);

    }
}
