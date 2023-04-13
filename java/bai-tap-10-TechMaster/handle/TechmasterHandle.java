package handle;

import java.util.List;
import java.util.Scanner;

import entity.Group;
import entity.Manager;
import entity.Student;
import entity.Teacher;
import entity.Techmaster;

public class TechmasterHandle {
    public Techmaster createTechmaster(Scanner sc) {
        ManagerHandle managerHandle = new ManagerHandle();
        Manager manager = managerHandle.createManager(sc);

        TeacherHandle teacherHandle = new TeacherHandle();
        Teacher teacher = teacherHandle.createTeacher(sc);

        GroupHandle groupHandle = new GroupHandle();
        Group group = groupHandle.createGroup(sc);

        return new Techmaster(manager, teacher, group);

    }

    public void addStudent(Scanner sc, Techmaster techmaster) {
        InputControl inputControl = new InputControl();
        StudentHandle studentHandle = new StudentHandle();

        // Lay nhom va danh sach hoc vien
        Group group = techmaster.getGroup();
        List<Student> students = group.getStudents();

        // Bo sung hoc vien moi
        System.out.println("Nhap so hoc vien can bo sung:");
        int n = inputControl.getInput(sc, 0, null);
        for (int i = 0; i < n; i++) {
            students.add(studentHandle.createStudent(sc, i + 1));
        }

        // Update lai cac gia tri moi
        group.setStudents(students);
        techmaster.setGroup(group);
    }

    public void updateRank(Scanner sc, Techmaster techmaster) {
        InputControl inputControl = new InputControl();
        System.out.println("Nhap id hoc vien can dieu chinh hoc luc:");
        int id = inputControl.getInput(sc, 1, null);

        // Lay nhom va danh sach hoc vien
        Group group = techmaster.getGroup();
        List<Student> students = group.getStudents();

        try {
            System.out.println(students.get(id-1));
            System.out.println("Cap nhat hoc luc moi:");
            students.get(id-1).setRank(sc.nextLine());

            // Cap nhat vao techmaster
            group.setStudents(students);
            techmaster.setGroup(group);

            System.out.println("Hoc vien sau khi cap nhat hoc luc:");
            System.out.println(students.get(id-1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Khong tim thay hoc vien id '" + id + "'.");
        }
    }

    public void removeStudent(Scanner sc, Techmaster techmaster) {
        InputControl inputControl = new InputControl();
        System.out.println("Nhap id hoc vien can xoa:");
        int id = inputControl.getInput(sc, 1, null);

        // Lay nhom va danh sach hoc vien
        Group group = techmaster.getGroup();
        List<Student> students = group.getStudents();

        try {
            System.out.println(students.get(id-1));
            students.remove(id-1);

            // Cap nhat vao techmaster
            group.setStudents(students);
            techmaster.setGroup(group);

            System.out.println("Hoan thanh xoa khoi danh sach.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Khong tim thay hoc vien id '" + id + "'.");
        }
    }
}
