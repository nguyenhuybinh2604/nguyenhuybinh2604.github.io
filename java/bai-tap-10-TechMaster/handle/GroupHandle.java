package handle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Group;
import entity.Student;

public class GroupHandle {
    public Group createGroup(Scanner sc) {
        InputControl inputControl = new InputControl();
        System.out.println("Nhap mon hoc:");
        String subject = sc.nextLine();
        System.out.println("Nhap so hoc vien:");
        int n = inputControl.getInput(sc, 0, null);
        StudentHandle studentHandle = new StudentHandle();

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Hoc vien thu " + (i+1) + ":");
            students.add(studentHandle.createStudent(sc, i+1));
        }
        return new Group(subject, students);
    }
}
