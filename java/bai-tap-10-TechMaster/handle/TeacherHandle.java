package handle;

import java.util.Scanner;

import entity.Teacher;

public class TeacherHandle {
    public Teacher createTeacher(Scanner sc){
        InputControl inputControl = new InputControl();
        System.out.println("Nhap ten cua giang vien:");
        String teacherName = sc.nextLine();
        System.out.println("Nhap tuoi cua giang vien:");
        int teacherAge = inputControl.getInput(sc, 1, null);
        return new Teacher(teacherName, teacherAge);
    }
}
