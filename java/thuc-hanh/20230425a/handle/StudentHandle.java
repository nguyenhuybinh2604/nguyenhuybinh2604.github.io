package handle;

import java.nio.channels.ShutdownChannelGroupException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import entity.School;
import entity.Student;

public class StudentHandle {

    public void inputData(Scanner sc, InputControl inputControl, School school) {
        System.out.println("Nhap lop hoc:");
        String group = sc.nextLine();
        System.out.println("Nhap khoa hoc:");
        String course = sc.nextLine();
        System.out.println("Nhap ky hoc:");
        String semester = sc.nextLine();
        school.setGroup(group);
        school.setCourse(course);
        school.setSemester(semester);
        System.out.println("Nhap so hoc sinh:");
        int n = inputControl.getInput(sc, 0, null);
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Hoc sinh thu " + i + " :");
            System.out.println("Nhap ho ten:");
            String name = sc.nextLine();
            System.out.println("Nhap ngay sinh:");
            LocalDate dob = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("d/MM/yyyy"));
            System.out.println("Nhap que quan:");
            String hometown = sc.nextLine();
            Student student = new Student(name, dob, hometown);
            students.add(student);
        }
        school.setStudents(students);
    }

    public void displayStudents1(School school) {
        List<Student> students = school.getStudents();
        for (Student student : students) {
            if (student.getHometown().equals("Thai Nguyen") && student.getDob().getYear()==1985) {
                System.out.println(student);
            }
        }
    }

    public void displayStudents2(School school) {
        List<Student> students = school.getStudents();
        for (Student student : students) {
            if (school.getGroup().equals("10A1")) {
                System.out.println(student);
            }
        }
    }

}
