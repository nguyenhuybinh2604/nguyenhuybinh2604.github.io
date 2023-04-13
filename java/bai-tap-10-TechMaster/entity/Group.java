package entity;

import java.util.List;

public class Group {
    private String subject;
    private List<Student> students;

    public Group() {
    }

    public Group(String subject, List<Student> students) {
        this.subject = subject;
        this.students = students;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {

        return
                " Mon hoc: " + this.subject + " \n" +
                " Danh sach hoc vien: \n" + this.students + " \n";

    }

}
