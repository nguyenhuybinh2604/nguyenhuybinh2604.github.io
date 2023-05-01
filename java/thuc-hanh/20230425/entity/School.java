package entity;

import java.util.List;

public class School {
    private List<Student> students;
    private String group;
    private String course;
    private String semester;


    public School() {
    }

    public School(List<Student> students, String group, String course, String semester) {
        this.students = students;
        this.group = group;
        this.course = course;
        this.semester = semester;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCourse() {
        return this.course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSemester() {
        return this.semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public School students(List<Student> students) {
        setStudents(students);
        return this;
    }

    public School group(String group) {
        setGroup(group);
        return this;
    }

    public School course(String course) {
        setCourse(course);
        return this;
    }

    public School semester(String semester) {
        setSemester(semester);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " students='" + getStudents() + "'" +
            ", group='" + getGroup() + "'" +
            ", course='" + getCourse() + "'" +
            ", semester='" + getSemester() + "'" +
            "}";
    }

}
