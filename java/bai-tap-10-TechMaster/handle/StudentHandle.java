package handle;

import java.util.Scanner;

import entity.Student;

public class StudentHandle {
    public Student createStudent(Scanner sc, Integer i) {
        InputControl inputControl = new InputControl();
        String studentStatus;
        if (i==null) {
            studentStatus = ":";
        } else {
            studentStatus = " thu " + i + ":";
        }
        System.out.println("Nhap ten cua hoc vien" + studentStatus);
        String studentName = sc.nextLine();
        System.out.println("Nhap tuoi cua hoc vien" + studentStatus);
        int studentAge = inputControl.getInput(sc, 1, null);
        System.out.println("Nhap hoc luc cua hoc vien" + studentStatus);
        String studentRank = sc.nextLine();
        return new Student(studentName, studentAge, studentRank);
    }
}
